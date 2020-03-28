package com.hjq.shiwu.ui.activity.add;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.base.BaseDialog;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.ThingsBean;
import com.hjq.shiwu.common.MyActivity;
import com.hjq.shiwu.ui.dialog.DateDialog;
import com.hjq.shiwu.ui.dialog.MenuDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PushActivity extends MyActivity {


    @BindView(R.id.push_imageview)
    ImageView pushImageview;
    @BindView(R.id.push_kind_tv)
    TextView pushKindTv;
    @BindView(R.id.push_des_et)
    EditText pushDesEt;
    @BindView(R.id.push_remark_et)
    EditText pushRemarkEt;
    @BindView(R.id.push_losttime_tv)
    TextView pushLosttimeTv;
    @BindView(R.id.push_pushbutton)
    Button pushPushbutton;
    @BindView(R.id.push_pushtype_tv)
    TextView pushPushtypeTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_push;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.push_imageview, R.id.push_pushtype_tv,R.id.push_kind_tv, R.id.push_losttime_tv, R.id.push_pushbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.push_imageview:
                break;
            case R.id.push_kind_tv:
                List<String> dataListKind = new ArrayList<>();

                dataListKind.add("校园卡");
                dataListKind.add("水卡");
                dataListKind.add("身份证");
                dataListKind.add("手机");
                dataListKind.add("书本");
                dataListKind.add("其他");


                // 居中选择框
                new MenuDialog.Builder(this)
                        .setGravity(Gravity.CENTER)
                        // 设置 null 表示不显示取消按钮
                        //.setCancel(null)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setList(dataListKind)
                        .setListener(new MenuDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, int position, String string) {
                                pushKindTv.setText(string);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.push_losttime_tv:
                // 日期选择对话框
                new DateDialog.Builder(this)
                        .setTitle(getString(R.string.date_title))
                        // 确定按钮文本
                        .setConfirm(getString(R.string.common_confirm))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.common_cancel))
                        // 设置日期
                        //.setDate("2018-12-31")
                        //.setDate("20181231")
                        //.setDate(1546263036137)
                        // 设置年份
                        //.setYear(2018)
                        // 设置月份
                        //.setMonth(2)
                        // 设置天数
                        //.setDay(20)
                        // 不选择天数
                        //.setIgnoreDay()
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
//                                toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));

                                // 如果不指定时分秒则默认为现在的时间
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                // 月份从零开始，所以需要减 1
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
//                                toast("时间戳：" + calendar.getTimeInMillis());
                                pushLosttimeTv.setText(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.push_pushbutton:
                ThingsBean thingsBean = new ThingsBean();
                thingsBean.setType((pushPushtypeTv.getText().equals("丢失")?"lost":"found"));
                thingsBean.setDes(pushDesEt.getText().toString());
                thingsBean.setImage("https://storage.53iq.com/group1/M00/10/31/CgoKTV5gVIiAXe-oAAC2Ju0mZIw314.jpg");
                thingsBean.setKind(pushKindTv.getText().toString());
                thingsBean.setName("yukaida");
                thingsBean.setQqNubmber("1204799167");
                thingsBean.setTime(pushLosttimeTv.getText().toString());
                thingsBean.setTips(pushRemarkEt.getText().toString());

                thingsBean.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            toast("保存成功");
                        } else {
                            toast("保存失败了");
                        }
                    }
                });
                break;

            case R.id.push_pushtype_tv:
                List<String> dataPushType = new ArrayList<>();

                dataPushType.add("丢失");
                dataPushType.add("拾得");



                // 居中选择框
                new MenuDialog.Builder(this)
                        .setGravity(Gravity.CENTER)
                        // 设置 null 表示不显示取消按钮
                        //.setCancel(null)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setList(dataPushType)
                        .setListener(new MenuDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, int position, String string) {
                                pushPushtypeTv.setText(string);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;

            default:
                break;
        }
    }


}
