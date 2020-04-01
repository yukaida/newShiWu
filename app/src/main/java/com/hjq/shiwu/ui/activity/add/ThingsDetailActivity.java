package com.hjq.shiwu.ui.activity.add;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.ThingsBean;
import com.hjq.shiwu.common.MyActivity;
import com.hjq.shiwu.http.glide.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThingsDetailActivity extends MyActivity {


    @BindView(R.id.TD_imageview)
    ImageView TDImageview;
    @BindView(R.id.TD_type_tv)
    TextView TDTypeTv;
    @BindView(R.id.TD_kind_tv)
    TextView TDKindTv;
    @BindView(R.id.TD_remark_tv)
    TextView TDRemarkTv;
    @BindView(R.id.TD_losttime_tv)
    TextView TDLosttimeTv;
    @BindView(R.id.TD_issuer_bt)
    Button TDIssuerBt;
    @BindView(R.id.TD_des_tv)
    TextView TDDesTv;
    @BindView(R.id.TD_userqq_bt)
    Button TDUserqqBt;

    ThingsBean thingsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_things_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        thingsBean = gson.fromJson(intent.getStringExtra("thingsBean"), ThingsBean.class);

        GlideApp.with(this)
                .load(thingsBean.getImage())
//                .circleCrop()
                .into(TDImageview);

        TDTypeTv.setText(thingsBean.getType().equals("lost") ? "丢失" : "拾得");
        TDKindTv.setText(thingsBean.getKind());
        TDRemarkTv.setText(thingsBean.getTips());
        TDDesTv.setText(thingsBean.getDes());
        TDLosttimeTv.setText(thingsBean.getTime());
        TDIssuerBt.setText(thingsBean.getName());
        TDUserqqBt.setText(thingsBean.getQqNubmber());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.TD_userqq_bt, R.id.TD_issuer_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.TD_userqq_bt://发布者qq
//                if (checkApkExist(this, "com.tencent.mobileqq")){
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+thingsBean.getQqNubmber()+"&version=1")));
//                }else{
//                    Toast.makeText(this,"本机未安装QQ应用",Toast.LENGTH_SHORT).show();
//                }

                String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=%s&version=1";

                url11 = String.format(url11, thingsBean.getQqNubmber());
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                } catch (Exception e) {
                    // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
                    toast("未安装QQ");
                }

                break;
            case R.id.TD_issuer_bt://发布者用户名



                break;
        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
