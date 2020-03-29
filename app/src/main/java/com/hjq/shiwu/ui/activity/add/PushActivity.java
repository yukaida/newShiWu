package com.hjq.shiwu.ui.activity.add;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hjq.base.BaseDialog;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.shiwu.R;
import com.hjq.shiwu.bean.MessageEvent;
import com.hjq.shiwu.bean.ThingsBean;
import com.hjq.shiwu.bean.UpLoadBean;
import com.hjq.shiwu.common.MyActivity;
import com.hjq.shiwu.http.glide.GlideApp;
import com.hjq.shiwu.http.model.HttpData;
import com.hjq.shiwu.http.request.UpdateImageApi;
import com.hjq.shiwu.ui.activity.ImageActivity;
import com.hjq.shiwu.ui.activity.PersonalDataActivity;
import com.hjq.shiwu.ui.activity.PhotoActivity;
import com.hjq.shiwu.ui.dialog.DateDialog;
import com.hjq.shiwu.ui.dialog.MenuDialog;
import com.hjq.shiwu.ui.dialog.WaitDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.hjq.http.EasyUtils.runOnUiThread;

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

    BaseDialog waitDialog;
    /** 头像地址 */
    private String mAvatarUrl;

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
            case R.id.push_imageview://图片选择
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelected(List<String> data) {
                        if (true) {
                            mAvatarUrl = data.get(0);
                            GlideApp.with(getActivity())
                                    .load(mAvatarUrl)
                                    .into(pushImageview);
                            return;
                        }

                    }

                    @Override
                    public void onCancel() {

                    }
                });
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
                waitDialog = new WaitDialog.Builder(this)
                        // 消息文本可以不用填写
                        .setMessage(getString("正在发布 请稍等"))
                        .show();
//                postDelayed(waitDialog::dismiss, 2000);

//                // 上传头像
//                EasyHttp.post(getActivity())
//                        .api(new UpdateImageApi()
//                                .setImage(new File(mAvatarUrl)))
//                        .request(new HttpCallback<HttpData<String>>(PushActivity.this) {
//
//                            @Override
//                            public void onSucceed(HttpData<String> data) {
//                                mAvatarUrl = data.getData();
//                                GlideApp.with(getActivity())
//                                        .load(mAvatarUrl)
//                                        .into(pushImageview);
//                            }
//                        });
                Luban.with(this)
                        .load(mAvatarUrl)
                        .ignoreBy(200)
//                        .setTargetDir(getpath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                waitDialog.dismiss();
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                uploadFile(file,PushActivity.this);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                waitDialog.dismiss();
                            }
                        }).launch();





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

    private void pushData(String picpath) {
        ThingsBean thingsBean = new ThingsBean();
        thingsBean.setType((pushPushtypeTv.getText().equals("丢失")?"lost":"found"));
        thingsBean.setDes(pushDesEt.getText().toString());
        thingsBean.setImage(picpath);
        thingsBean.setKind(pushKindTv.getText().toString());
        thingsBean.setName("yukaida");
        thingsBean.setQqNubmber("1204799167");
        thingsBean.setTime(pushLosttimeTv.getText().toString());
        thingsBean.setTips(pushRemarkEt.getText().toString());

        thingsBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    waitDialog.dismiss();
                    toast("保存成功");
                    EventBus.getDefault().post(new MessageEvent("refreshHomeRv"));
                    finish();

                } else {
                    waitDialog.dismiss();
                    toast("保存失败了");
                }
            }
        });
    }


    private void uploadFile(File mFile, Context context) {

//        File mFile;
//        // 获取要上传的文件
//        mFile = new File(picPath);

       /* if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File directory = Environment.getExternalStorageDirectory();
            mFile = new File(directory, "text.jpg");
        }*/


        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        // 设置文件以及文件上传类型封装
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        // 文件上传的请求体封装
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "abc")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(multipartBody)
                .build();


        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                waitDialog.dismiss();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();

                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(result, UpLoadBean.class);

                if (upLoadBean != null) {

                            if (upLoadBean.getCode() == 200) {

                                pushData(upLoadBean.getData().getUrl());

//                                Glide.with(context).load(upLoadBean.getData().getUrl()).into(mImg);


                            } else {
                                waitDialog.dismiss();

                               Toast.makeText(context, "服务器故障", Toast.LENGTH_SHORT).show();

                            }


                        } else {

                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                        }




//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                        if (upLoadBean != null) {
//
//                            if (upLoadBean.getCode() == 200) {
//
//
//
////                                Glide.with(context).load(upLoadBean.getData().getUrl()).into(mImg);
//
//
//                            } else {
//
////                                mText.setText(upLoadBean.getRes());
//
//                            }
//
//
//                        } else {
//
//                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });

            }
        });


    }

}
