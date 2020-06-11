package com.animationdemo.activity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Toast;

import com.animationdemo.R;
import com.animationdemo.base.BaseActivity;
import com.animationdemo.databinding.ActivityTopbarBinding;
import com.animationdemo.widget.TopBar;

public class TopbarViewActivity extends BaseActivity {

    private ActivityTopbarBinding binding;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topbar);
        initStatusBar(getResources().getColor(R.color.color_F));
        setStatusBarMode(this, 1);

        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra("title");
        }
        binding.includeTitle.tvTitle.setText(title);

        binding.topbar.setTitle(title);
    }

    @Override
    public void initListener() {
        binding.topbar.setOnTopBarBtnsClick(new TopBar.TopBarBtnsOnClickListener() {
            @Override
            public void leftBtnOnClick() {
                Toast.makeText(TopbarViewActivity.this, "BACK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void rightBtnOnClick() {
                Toast.makeText(TopbarViewActivity.this, "MORE", Toast.LENGTH_LONG).show();
            }
        });

        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
