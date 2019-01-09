package com.example.a55300.animationdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.base.BaseActivity;
import com.example.a55300.animationdemo.databinding.ActivityProgressRingBinding;
import com.example.a55300.animationdemo.listener.DownloadUtils;
import com.example.a55300.animationdemo.listener.JsDownloadListener;
import com.example.a55300.animationdemo.util.ZipUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DisplayZipActivity extends BaseActivity implements JsDownloadListener {

    private String url_zip = "/storage/emulated/0/zj/word/%E5%8A%A8%E6%80%81%E8%AF%A6%E6%83%85.zip-lastModified1544149902-size2913352.zip";
    private String url = "http://xtzx01-1255000116.cos-zwy.xuetangx.com/zjwy/cms/zip/%E6%B5%8B%E8%AF%95zip%E9%99%84%E4%BB%B6.zip-lastModified1547001820-size7361385.zip";
    private ActivityProgressRingBinding binding;
    private String title;
    private String fileName = "";
    private String filePath = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_progress_ring);

        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            title = getIntent().getStringExtra("title");
//        }
//        binding.includeTitle.tvTitle.setText(title);
    }

    @Override
    public void initData() {
//        unZip(url_zip);
        downLoadFile(url);
    }

    @Override
    public void initListener() {
//        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void downLoadFile(String url) {
        int i = url.lastIndexOf("/");
        String tmp_url = url.substring(0, i + 1);
        fileName = url.substring(i + 1, url.length());
        filePath = createWordFile(this, String.format("%s", fileName)).getPath();
        if (!DownloadUtils.fileIsExists(filePath)) {
            DownloadUtils downloadUtils = new DownloadUtils(tmp_url, this);
            downloadUtils.download(fileName, filePath, new Observer() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object o) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            unZip(filePath);
        }

    }

    @Override
    public void onStartDownload() {
        showMsg(this, "开始下载");
    }

    @Override
    public void onProgress(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progress == 100) {
                    unZip(filePath);
                }
            }
        });
    }

    @Override
    public void onFinishDownload() {
        unZip(filePath);
    }

    @Override
    public void onFail(String errorInfo) {

    }

    public File createWordFile(Context context, String id) {

        File file = null;
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {

            file = new File(String.format("%s/animation/word/%s", Environment.getExternalStorageDirectory().getAbsolutePath(), id));
        } else {
//            file = new File(String.format("%s/%s.txt", context.getCacheDir().getAbsolutePath(), id));
        }

//        L.d("vivi","file "+file.getAbsolutePath());

        return file;

    }

    private void unZip(String url_zip) {
        String filePath_zip = Environment.getExternalStorageDirectory().getAbsolutePath() + "/animation/word";
        int i = url_zip.lastIndexOf("/");
        String fileName_zip = url_zip.substring(i, url_zip.length() - 4);
        try {
            File file = new File(filePath_zip + fileName_zip);
            file.mkdir();
            ZipUtils.UnZipFolder(url_zip, filePath_zip + fileName_zip);
            Intent intent = new Intent(DisplayZipActivity.this, FileAdminActivity.class);
            intent.putExtra("extStorage", filePath_zip + fileName_zip);
            intent.putExtra("title", fileName_zip);
            startActivity(intent);
            finish();
        } catch (Exception e){
            Toast.makeText(DisplayZipActivity.this, e+"", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
