package com.hjq.shiwu.ui.fragment.realuse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.BannerBean;
import com.hjq.shiwu.bean.MessageEvent;
import com.hjq.shiwu.bean.MessageItem;
import com.hjq.shiwu.bean.PicBean;
import com.hjq.shiwu.bean.ThingsBean;
import com.hjq.shiwu.common.MyFragment;
import com.hjq.shiwu.ui.activity.HomeActivity;
import com.hjq.shiwu.ui.activity.add.PushActivity;
import com.hjq.shiwu.ui.activity.add.ThingsDetailActivity;
import com.hjq.shiwu.ui.adapter.HomeRvAdapter;
import com.hjq.shiwu.ui.adapter.ImageAdapter;
import com.hjq.shiwu.ui.adapter.MessageRvAdapter;
import com.hjq.shiwu.widget.XCollapsingToolbarLayout;
import com.tencent.bugly.crashreport.CrashReport;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目炫酷效果示例
 */
public final class TestFragmentA extends MyFragment<HomeActivity>//首页
        implements XCollapsingToolbarLayout.OnScrimsListener {

    @BindView(R.id.ctl_test_bar)
    XCollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.t_test_title)
    Toolbar mToolbar;

    @BindView(R.id.tv_test_address)
    TextView mAddressView;
    @BindView(R.id.tv_test_hint)
    TextView mHintView;
    @BindView(R.id.iv_test_search)
    ImageView mSearchView;

    @BindView(R.id.home_recyclerview)
    RecyclerView mRecyclerView;


    private List<ThingsBean> homeItemList = new ArrayList<>();


    private List<BannerBean> bannerBeanList = new ArrayList<>();


    public static TestFragmentA newInstance() {
        return new TestFragmentA();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_a;
    }

    @Override
    protected void initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);


        mAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashReport.testJavaCrash();
            }
        });




        
        
    }

    @Override
    protected void initData() {
        BannerBean bannerBean = new BannerBean("https://storage.53iq.com/group1/M00/10/31/CgoKTV5gVIiAXe-oAAC2Ju0mZIw314.jpg", "");
        BannerBean bannerBean1 = new BannerBean("https://storage.53iq.com/group1/M00/10/31/CgoKTV5gVJSAXxYbAAA4hLlxT-Y506.jpg", "");
        BannerBean bannerBean2 = new BannerBean("https://storage.53iq.com/group1/M00/10/31/CgoKTV5gVJSAXxYbAAA4hLlxT-Y506.jpg", "");

        bannerBeanList.add(bannerBean);
        bannerBeanList.add(bannerBean1);
        bannerBeanList.add(bannerBean2);

        useBanner();


        initHomeRv();
        EventBus.getDefault().register(this);


    }

    private void initHomeRv() {
        HomeRvAdapter homeRvAdapter = new HomeRvAdapter(R.layout.item_home_layout, homeItemList,getActivity());
        BmobQuery<ThingsBean> query = new BmobQuery<>();
        //按照时间降序
        query.order("-createdAt");
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(new FindListener<ThingsBean>() {
            @Override
            public void done(List<ThingsBean> object, BmobException e) {
                if (e == null) {
                    toast("刷新成功");
                    homeItemList.clear();
                    homeItemList.addAll(object);

                    homeRvAdapter.notifyDataSetChanged();

                } else {
                    toast("请检查网络连接");
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRvAdapter.setAnimationEnable(true);
        mRecyclerView.setAdapter(homeRvAdapter);

        homeRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Gson gson = new Gson();
                homeItemList.get(position);
                Intent intent = new Intent(getActivity(), ThingsDetailActivity.class);
                intent.putExtra("thingsBean",gson.toJson(homeItemList.get(position)));
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean statusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     * <p>
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black60));
            mSearchView.setImageResource(R.drawable.add);
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
            mSearchView.setImageResource(R.drawable.add);
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }

    @OnClick({R.id.tv_test_address, R.id.iv_test_search, R.id.tv_test_hint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test_address:

                break;
            case R.id.iv_test_search:
                Intent intent = new Intent(getActivity(), PushActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_hint:
                toast("跳转到物品搜索界面");


//                PicBean picBean = new PicBean();
//                BmobFile bmobFile = new BmobFile(new File("/storage/emulated/0/sina/weibo/storage/photoalbum_save/weibo/img-eee74c8114028f2198240e5a65c2eefe.jpg"));
//                picBean.setBmobFile(bmobFile);
//                bmobFile.upload(new UploadFileListener() {
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null) {
//                            toast("上传成功");
//                        } else {
//                            toast("上传失败");
//                        }
//
//                    }
//                });



                break;
        }
    }


    public void useBanner() {
        //--------------------------简单使用-------------------------------
        //创建（new banner()）或者布局文件中获取banner
        Banner banner = findViewById(R.id.banner);
        //默认直接设置adapter就行了
        banner.setAdapter(new ImageAdapter(bannerBeanList, getContext()));

        //--------------------------详细使用-------------------------------
//        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(getContext()));
//        banner.setIndicatorSelectedColorRes(R.color.main_color);
        banner.setIndicatorNormalColorRes(R.color.textColor);
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
        banner.setIndicatorSpace(BannerUtils.dp2px(20));
        banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        banner.setIndicatorWidth(10, 20);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.banner:

                }
            }
        });
//        banner.addItemDecoration(new MarginItemDecoration((int) BannerUtils.dp2px(50)));
//        banner.setPageTransformer(new DepthPageTransformer());
//        banner.setOnBannerListener(this);
//        banner.addOnPageChangeListener(this);
        //还有更多方法自己使用哦！！！！！！

        //-----------------当然如果你想偷下懒也可以这么用--------------------
        //banner所有set方法都支持链式调用(以下列举了一些方法)
//        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()))
//                .setOrientation(Banner.VERTICAL)
//                .setIndicator(new CircleIndicator(this))
//                .setUserInputEnabled(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getMes().equals("refreshHomeRv")) {
            initHomeRv();
        }

        /* Do something */};

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}