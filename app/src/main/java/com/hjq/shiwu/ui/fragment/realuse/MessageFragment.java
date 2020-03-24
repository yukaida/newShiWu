package com.hjq.shiwu.ui.fragment.realuse;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.MessageItem;
import com.hjq.shiwu.common.MyFragment;
import com.hjq.shiwu.ui.activity.CopyActivity;
import com.hjq.shiwu.ui.activity.HomeActivity;
import com.hjq.shiwu.ui.adapter.MessageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 消息界面
 */
public final class MessageFragment extends MyFragment<HomeActivity> {
    private static final String TAG = "MessageFragment";
    @BindView(R.id.message_recyclerview)
    RecyclerView recyclerViewMessage;

    @BindView(R.id.message_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<MessageItem> messageItemList = new ArrayList<>();
    private MessageRvAdapter messageRvAdapter = new MessageRvAdapter(R.layout.item_message, messageItemList);

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initRefreshLayout();
        refresh();

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public void onStart() {
        super.onStart();
        // 进入页面，刷新数据
//        mSwipeRefreshLayout.setRefreshing(true);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    /**
     * 刷新
     */
    private void refresh() {
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
//        messageRvAdapter.getLoadMoreModule().setEnableLoadMore(false);
        // 下拉刷新，需要重置页数

        BmobQuery<MessageItem> query = new BmobQuery<>();
        //按照时间降序
        query.order("-createdAt");
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(new FindListener<MessageItem>() {
            @Override
            public void done(List<MessageItem> object, BmobException e) {
                if (e == null) {
                    toast("刷新成功");
                    messageItemList.clear();
                    messageItemList.addAll(object);
                    Log.d(TAG, "initData: size" + messageItemList.toString());
                    messageRvAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);

                } else {
                    toast("请检查网络连接");
                }
            }
        });

        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRvAdapter.setAnimationEnable(true);
        recyclerViewMessage.setAdapter(messageRvAdapter);

    }
}