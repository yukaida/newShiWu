package com.hjq.shiwu.ui.fragment.realuse;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        BmobQuery<MessageItem> query = new BmobQuery<>();
        //按照时间降序
        query.order("-createdAt");
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(new FindListener<MessageItem>() {
            @Override
            public void done(List<MessageItem> object, BmobException e) {
                if (e == null) {
                    messageItemList.addAll(object);
                    Log.d(TAG, "initData: size" + messageItemList.toString());
                    messageRvAdapter.notifyDataSetChanged();
                    // ...
                } else {
                    toast("请检查网络连接");
                }
            }
        });

        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRvAdapter.setAnimationEnable(true);
        recyclerViewMessage.setAdapter(messageRvAdapter);

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}