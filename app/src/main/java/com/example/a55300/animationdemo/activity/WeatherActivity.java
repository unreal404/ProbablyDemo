package com.example.a55300.animationdemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.allen.library.observer.CommonObserver;
import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.api.RequestUtil;
import com.example.a55300.animationdemo.bean.HeWeatherBean;
import com.example.a55300.animationdemo.databinding.ActivityDisplayMessgaeBinding;

public class WeatherActivity extends AppCompatActivity {

    private ActivityDisplayMessgaeBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_messgae);

        initData();
    }

    private void initView() {

    }

    private  void initData() {
        RequestUtil.getInstance().getWeather("beijing", new CommonObserver<HeWeatherBean>() {
            @Override
            protected void onError(String s) {

            }

            @Override
            protected void onSuccess(HeWeatherBean heWeatherBean) {
                binding.messgae.setText(heWeatherBean.toString());
            }
        });
    }
}
