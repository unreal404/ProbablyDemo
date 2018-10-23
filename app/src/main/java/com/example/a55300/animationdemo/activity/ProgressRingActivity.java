package com.example.a55300.animationdemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.databinding.ProgressRingBinding;



public class ProgressRingActivity extends AppCompatActivity{

    ProgressRingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.progress_ring);

        binding.pring.setProgress(100);
    }
}
