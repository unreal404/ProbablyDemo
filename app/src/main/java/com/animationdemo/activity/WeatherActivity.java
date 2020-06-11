package com.animationdemo.activity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.allen.library.observer.CommonObserver;
import com.animationdemo.R;
import com.animationdemo.api.RequestUtil;
import com.animationdemo.base.BaseActivity;
import com.animationdemo.bean.HeWeatherBean;
import com.animationdemo.databinding.ActivityDisplayMessgaeBinding;

public class WeatherActivity extends BaseActivity {

    private ActivityDisplayMessgaeBinding binding;
    private String title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_messgae);
        initStatusBar(getResources().getColor(R.color.color_F));
        setStatusBarMode(this, 1);

        initView();
        initData();
        initListener();
    }

    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra("title");
        }
        binding.includeTitle.tvTitle.setText(title);
    }

    public   void initData() {

    }

    public void initListener(){
        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    binding.btnLocation.setVisibility(View.GONE);
                } else {
                    binding.btnLocation.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = binding.tvLocation.getText().toString();

                RequestUtil.getInstance().getWeather(location, new CommonObserver<HeWeatherBean>() {
                    @Override
                    protected void onError(String s) {

                    }

                    @Override
                    protected void onSuccess(HeWeatherBean heWeatherBean) {
                        if (heWeatherBean != null && heWeatherBean.getHeWeather6() != null && heWeatherBean.getHeWeather6().get(0).getDaily_forecast() != null) {
                            binding.tvMessgae.setText(heWeatherBean.toString());
                        } else {
                            binding.tvMessgae.setText(getResources().getString(R.string.fail_search));
                        }
                    }
                });
            }
        });
    }
}
