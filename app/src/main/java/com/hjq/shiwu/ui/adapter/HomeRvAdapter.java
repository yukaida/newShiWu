package com.hjq.shiwu.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.ThingsBean;
import com.hjq.shiwu.http.glide.GlideApp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeRvAdapter extends BaseQuickAdapter<ThingsBean, BaseViewHolder> {

    Context context;

    public HomeRvAdapter(int layoutResId, @Nullable List<ThingsBean> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ThingsBean thingsBean) {
        GlideApp.with(context)
                .load(thingsBean.getImage())
                .into((ImageView) baseViewHolder.getView(R.id.home_rv_item_pic));

        baseViewHolder.setText(R.id.home_rv_item_des, thingsBean.getDes());
        baseViewHolder.setText(R.id.home_rv_item_pushman, thingsBean.getName());

    }
}
