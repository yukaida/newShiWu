package com.hjq.shiwu.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.shiwu.R;
import com.hjq.shiwu.aop.SingleClick;
import com.hjq.shiwu.bean.UserData;
import com.hjq.shiwu.common.MyActivity;
import com.hjq.shiwu.helper.InputTextHelper;
import com.hjq.shiwu.http.model.HttpData;
import com.hjq.shiwu.http.request.GetCodeApi;
import com.hjq.shiwu.other.SPUtils;
import com.hjq.widget.view.CountdownView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity {
    private static final String TAG = "RegisterActivity";
    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;
    @BindView(R.id.et_register_name)
    AppCompatEditText etRegisterName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length()> 8 &&
                        mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();

        setOnClickListener(R.id.cv_register_countdown, R.id.btn_register_commit);
    }

    @Override
    protected void initData() {

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                if (true) {
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }

                // 获取验证码
                EasyHttp.post(this)
                        .api(new GetCodeApi()
                                .setPhone(mPhoneView.getText().toString()))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                toast(R.string.common_code_send_hint);
                                mCountdownView.start();
                            }

                            @Override
                            public void onFail(Exception e) {
                                super.onFail(e);
                                mCountdownView.start();
                            }
                        });
                break;
            case R.id.btn_register_commit:

                String qq = mPhoneView.getText().toString();
                String phone = mCodeView.getText().toString();
                String password = mPasswordView1.getText().toString();
                String name = etRegisterName.getText().toString();

                //注册账号
                UserData userData = new UserData();
                userData.setPhoneNumber(qq);
                userData.setQqNumber(phone);
                userData.setPassword(password);

                userData.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            toast("注册成功，返回objectId为：" + s);

                            SPUtils.put(RegisterActivity.this, "qq", qq);
                            SPUtils.put(RegisterActivity.this, "phone", phone);
                            SPUtils.put(RegisterActivity.this, "password", password);
                            SPUtils.put(RegisterActivity.this, "name", name);


                            Log.d(TAG, "done: sputils"+"qq"+qq+"\n"+

                                            "phone"+phone+"\n"+
                                            "password"+password+"\n"+
                                            "name"+name+"\n"

                                    );
                            LoginActivity.start(RegisterActivity.this, qq, password, phone);
                            //将qq号和密码传回
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            toast("注册失败：" + e.getMessage());
                        }
                    }
                });


//                if (true) {
//
//                    return;
//                }
//                // 提交注册
//                EasyHttp.post(this)
//                        .api(new RegisterApi()
//                        .setPhone(mPhoneView.getText().toString())
//                        .setCode(mCodeView.getText().toString())
//                        .setPassword(mPasswordView1.getText().toString()))
//                        .request(new HttpCallback<HttpData<RegisterBean>>(this) {
//
//                            @Override
//                            public void onSucceed(HttpData<RegisterBean> data) {
//                                LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
//                                setResult(RESULT_OK);
//                                finish();
//                            }
//                        });
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}