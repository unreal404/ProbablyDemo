package com.animationdemo.api;

import com.animationdemo.bean.HeWeatherBean;
import com.animationdemo.bean.PicBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {

    @GET("getshowpiclist")
    Observable<List<PicBean>> getPic();

    //和风天气 http://www.heweather.com/
    @GET("https://free-api.heweather.com/s6/weather/forecast?key=9a1789da1d5a403086ea811f289a9a8e")
    Observable<HeWeatherBean> getWeather(@Query("location") String location);
}
