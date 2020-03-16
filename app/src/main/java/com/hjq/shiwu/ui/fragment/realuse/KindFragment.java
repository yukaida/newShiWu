package com.hjq.shiwu.ui.fragment.realuse;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.KindItem;
import com.hjq.shiwu.common.MyFragment;
import com.hjq.shiwu.http.glide.OkHttpLoader;
import com.hjq.shiwu.ui.activity.HomeActivity;
import com.hjq.shiwu.ui.adapter.KindRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KindFragment extends MyFragment<HomeActivity> {//物品分类消息页面
    @BindView(R.id.kind_fragment_recyclerview)
    RecyclerView kindFragmentRecyclerview;


    private List<KindItem> kindItemList = new ArrayList<>();//分类数据list
    private KindRvAdapter kindRvAdapter = new KindRvAdapter(R.layout.item_kind, kindItemList);//分类数据适配器

    public static KindFragment newInstance() {
        return new KindFragment();
    }


    @Override

    protected int getLayoutId() {
        return R.layout.fragment_kind;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        KindItem kindItemCampusCard = new KindItem(R.drawable.kind_campuscard, "校园卡");
        KindItem kindItemWaterCard = new KindItem(R.drawable.kind_watercard, "水卡");
        KindItem kindItemIdentityCard = new KindItem(R.drawable.kind_identitycard, "身份证");
        KindItem kindItemPhone = new KindItem(R.drawable.kind_phone, "手机");
        KindItem kindItemBooks = new KindItem(R.drawable.kind_books, "书本");
        KindItem kindItemOther = new KindItem(R.drawable.kind_other, "其他");

        kindItemList.add(kindItemCampusCard);
        kindItemList.add(kindItemWaterCard);
        kindItemList.add(kindItemIdentityCard);
        kindItemList.add(kindItemPhone);
        kindItemList.add(kindItemBooks);
        kindItemList.add(kindItemOther);

        kindFragmentRecyclerview.setAdapter(kindRvAdapter);
        kindFragmentRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        kindRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (position) {
                    case 0:
                        toast(position);
                        break;
                    case 1:
                        toast(position);
                        break;
                    case 2:
                        toast(position);
                        break;
                    case 3:
                        toast(position);
                        break;
                    case 4:
                        toast(position);
                        break;
                    case 5:
                        toast(position);
                        break;

                }
            }
        });

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


}
