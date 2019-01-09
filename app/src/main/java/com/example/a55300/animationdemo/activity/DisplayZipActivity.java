package com.example.a55300.animationdemo.activity;

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
import com.example.a55300.animationdemo.util.ZipUtils;

import java.io.File;

public class DisplayZipActivity extends BaseActivity {

    private String url_zip = "/storage/emulated/0/zj/word/%E5%8A%A8%E6%80%81%E8%AF%A6%E6%83%85.zip-lastModified1544149902-size2913352.zip";
    private ActivityProgressRingBinding binding;
    private String title;

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
        unZip(url_zip);
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

    private void unZip(String url_zip) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/animation/word";
        int i = url_zip.lastIndexOf("/");
        String fileName = url_zip.substring(i, url_zip.length() - 4);
        try {
            File file = new File(filePath + fileName);
            file.mkdir();
            ZipUtils.UnZipFolder(url_zip, filePath + fileName);
        } catch (Exception e){
            Toast.makeText(DisplayZipActivity.this, e+"", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(DisplayZipActivity.this, FileAdminActivity.class);
        intent.putExtra("extStorage", filePath + fileName);
        intent.putExtra("title", fileName);
        startActivity(intent);
    }
}
