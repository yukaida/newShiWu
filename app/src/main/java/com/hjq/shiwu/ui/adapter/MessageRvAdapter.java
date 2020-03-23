package com.hjq.shiwu.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.KindItem;
import com.hjq.shiwu.bean.MessageItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MessageRvAdapter extends BaseQuickAdapter<MessageItem, BaseViewHolder> {
    public MessageRvAdapter(int layoutResId, @Nullable List<MessageItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MessageItem kindItem) {
        baseViewHolder.setText(R.id.message_item_textView, kindItem.getContent());
    }
}
