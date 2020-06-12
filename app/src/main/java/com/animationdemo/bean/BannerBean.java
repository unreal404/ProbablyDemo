package com.animationdemo.bean;

public class BannerBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public BannerBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public BannerBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }


    public void setImageRes(Integer imageRes) {
        this.imageRes = imageRes;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Integer getImageRes() {
        return imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getViewType() {
        return viewType;
    }
}
