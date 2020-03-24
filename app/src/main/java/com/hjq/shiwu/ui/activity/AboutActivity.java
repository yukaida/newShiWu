package com.hjq.shiwu.ui.activity;

import android.view.Menu;

import com.hjq.shiwu.R;
import com.hjq.shiwu.common.MyActivity;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 关于界面
 */
public final class AboutActivity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_bottom_nav, menu);
        return true;
    }
}