package com.hjq.shiwu.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shiwu.bean.ThingsBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeRvAdapter extends BaseQuickAdapter<ThingsBean, BaseViewHolder> {

    public HomeRvAdapter(int layoutResId, @Nullable List<ThingsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ThingsBean thingsBean) {

    }
}
