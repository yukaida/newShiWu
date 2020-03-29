package com.hjq.shiwu.http;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjq.shiwu.bean.UpLoadBean;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.hjq.http.EasyUtils.runOnUiThread;

public class localPicToYunPicUtils {


    private void uploadFile(String picPath, Context context) {

        File mFile;
        // 获取要上传的文件
        mFile = new File("picPath");

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

