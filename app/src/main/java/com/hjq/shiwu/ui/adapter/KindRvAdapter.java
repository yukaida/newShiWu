package com.hjq.shiwu.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.KindItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KindRvAdapter extends BaseQuickAdapter<KindItem, BaseViewHolder> {


    public KindRvAdapter(int layoutResId, @Nullable List<KindItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, KindItem kindItem) {
        baseViewHolder.setText(R.id.kind_item_text, kindItem.getName());
        baseViewHolder.setImageResource(R.id.kind_item_image, kindItem.getImage());

    }
}
