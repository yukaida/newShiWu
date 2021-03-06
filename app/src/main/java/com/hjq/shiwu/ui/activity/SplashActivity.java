package com.hjq.shiwu.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.shiwu.R;
import com.hjq.shiwu.common.MyActivity;
import com.hjq.shiwu.http.model.HttpData;
import com.hjq.shiwu.http.request.UserInfoApi;
import com.hjq.shiwu.http.response.UserInfoBean;
import com.hjq.shiwu.other.AppConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.shiwu.other.SPUtils;
import com.hjq.shiwu.ui.activity.add.PushActivity;

import butterknife.BindView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 闪屏界面
 */
public final class SplashActivity extends MyActivity {

    @BindView(R.id.iv_splash_lottie)
    LottieAnimationView mLottieView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        // 设置动画监听
        mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if (((String) SPUtils.get(SplashActivity.this, "qq", "")).length() > 3) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {


        // 获取用户信息
//        EasyHttp.post(this)
//                .api(new UserInfoApi())
//                .request(new HttpCallback<HttpData<UserInfoBean>>(this) {
//
//                    @Override
//                    public void onSucceed(HttpData<UserInfoBean> data) {
//
//                    }
//                });
    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .fullScreen(true)
                // 隐藏状态栏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentNavigationBar();
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
        //super.onBackPressed();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}