package com.hjq.shiwu.ui.fragment.realuse;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.BannerBean;
import com.hjq.shiwu.common.MyFragment;
import com.hjq.shiwu.ui.activity.HomeActivity;
import com.hjq.shiwu.ui.adapter.ImageAdapter;
import com.hjq.shiwu.widget.XCollapsingToolbarLayout;
import com.tencent.bugly.crashreport.CrashReport;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        BannerBean bannerBean = new BannerBean();
        bannerBean.setImageUrl("https://qzonestyle.gtimg.cn/qzone/qzactStatics/imgs/20200221155843_b40f01.png");
        BannerBean bannerBean1 = new BannerBean();
        bannerBean.setImageUrl("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%B9%BF%E5%91%8A%E5%9B%BE&step_word=&hs=0&pn=2&spn=0&di=23430&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2508641903%2C3193858088&os=2437101305%2C499851107&simid=4187236703%2C623583855&adpicid=0&lpn=0&ln=942&fr=&fmq=1584971586364_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0197935711ee3c6ac7251343b3addc.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bzv55s_z%26e3Bv54_z%26e3BvgAzdH3Fo56hAzdH3FZMTUcNDMcNzY%3D_z%26e3Bip4s&gsm=3&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        BannerBean bannerBean2 = new BannerBean();
        bannerBean.setImageUrl("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%B9%BF%E5%91%8A%E5%9B%BE&step_word=&hs=0&pn=9&spn=0&di=103070&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1487255121%2C510523177&os=16926473%2C323239343&simid=3382372189%2C160492051&adpicid=0&lpn=0&ln=942&fr=&fmq=1584971586364_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130302%2F9637549_082843274311_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bo3o_z%26e3BvgAzdH3Fkw5xtwgAzdH3F1jpwts-bdln9b_z%26e3Bip4s&gsm=a&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");

        bannerBeanList.add(bannerBean);
        bannerBeanList.add(bannerBean1);
        bannerBeanList.add(bannerBean2);

        useBanner();
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
            mSearchView.setImageResource(R.drawable.ic_search_black);
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
            mSearchView.setImageResource(R.drawable.ic_search_white);
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }

    @OnClick({R.id.tv_test_address, R.id.iv_test_search, R.id.tv_test_hint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test_address:

                break;
            case R.id.iv_test_search:
                toast("跳转到搜索界面");
                break;
            case R.id.tv_test_hint:
                toast("跳转到物品搜索界面");
                break;
        }
    }


    public void useBanner() {
        //--------------------------简单使用-------------------------------
        //创建（new banner()）或者布局文件中获取banner
        Banner banner =  findViewById(R.id.banner);
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

}