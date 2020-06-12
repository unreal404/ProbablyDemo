package com.animationdemo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.animationdemo.R;
import com.animationdemo.adapter.VideoBannerAdapter;
import com.animationdemo.base.BaseActivity;
import com.animationdemo.bean.BannerBean;
import com.animationdemo.databinding.ActivityBannerBinding;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

public class BannerActivity extends BaseActivity {
    private ActivityBannerBinding binding;
    private List<BannerBean> bannerList = new ArrayList<>();
    private String title;
    private String videoUrl = "https://ionecard-app-new-api.ioneservice.com//uploads/20200608/1e13db7411ada444917d632016d450b4.mp4";
    private String imgUrl1 = "http://ionecard-app-new-api.ioneservice.com//uploads/20200604/07735b6271e8f20e7ce1b55da5d77fd9.png";
    private String imgUrl2 = "http://ionecard-app-new-api.ioneservice.com//uploads/20200604/9d97875bea53d63e303dd247d7463850.jpg";
    private VideoBannerAdapter bannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_banner);
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

        bannerList.clear();
        bannerList.add(new BannerBean(imgUrl2, "", 1));
        bannerList.add(new BannerBean(videoUrl, "", 2));
        bannerList.add(new BannerBean(videoUrl, "", 2));
        bannerList.add(new BannerBean(imgUrl1, "", 1));
        bannerList.add(new BannerBean(imgUrl2, "", 1));
        bannerList.add(new BannerBean(imgUrl1, "", 1));

        bannerAdapter = new VideoBannerAdapter(this, bannerList, binding.banner);
        binding.banner.setAdapter(bannerAdapter)
                .setIndicator(binding.indicator, false)
                .setBannerGalleryEffect(15, 15)
                .addPageTransformer(new AlphaPageTransformer())
                .start();

        if (bannerList.get(0).viewType == 2) {
            binding.banner.isAutoLoop(false);
            binding.banner.stop();
        }
    }

    @Override
    public void initListener() {
        binding.banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (bannerList.get(position).viewType == 2) {
                    binding.banner.isAutoLoop(false);
                    binding.banner.stop();
                } else {
                    binding.banner.isAutoLoop(true);
                    binding.banner.start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
