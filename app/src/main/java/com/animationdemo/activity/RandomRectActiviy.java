package com.animationdemo.activity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;

import com.animationdemo.R;
import com.animationdemo.base.BaseActivity;
import com.animationdemo.databinding.ActivityRandomRectBinding;

public class RandomRectActiviy extends BaseActivity {

    private ActivityRandomRectBinding binding;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_random_rect);
        initStatusBar(getResources().getColor(R.color.color_F));
        setStatusBarMode(this, 1);

        initView();
        initListener();
    }

    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra("title");
        }
        binding.includeTitle.tvTitle.setText(title);

        binding.randomRect.setmRectCount(40);
        binding.randomRect.setOffset(0);
    }

    @Override
    public void initData() {

    }

    public void initListener() {
        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
