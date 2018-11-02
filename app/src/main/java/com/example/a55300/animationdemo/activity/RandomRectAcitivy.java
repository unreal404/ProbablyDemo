package com.example.a55300.animationdemo.activity;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.databinding.ActivityRandomRectBinding;

public class RandomRectAcitivy extends AppCompatActivity {

    private ActivityRandomRectBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_random_rect);

        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
