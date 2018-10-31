package com.example.a55300.animationdemo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.databinding.ActivityTopbarBinding;
import com.example.a55300.animationdemo.widget.TopBar;

public class TopbarViewActivity extends AppCompatActivity {

    private ActivityTopbarBinding binding;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topbar);

        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra("title");
        }
        binding.includeTitle.tvTitle.setText(title);

        binding.topbar.setTitle(title);
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
