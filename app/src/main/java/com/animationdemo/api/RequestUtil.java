package com.animationdemo.api;


import com.allen.library.RxHttpUtils;
import com.allen.library.base.BaseObserver;
import com.allen.library.interceptor.Transformer;
import com.animationdemo.bean.HeWeatherBean;

public class RequestUtil {
    private static RequestUtil requestUtil = null;
    private static UserApi userApi;

    public static RequestUtil getInstance() {
        if (requestUtil == null) {
            synchronized (RequestUtil.class) {
                if (requestUtil == null) {
                    requestUtil = new RequestUtil();
                }
            }
        }
        if (userApi == null) {
            synchronized (UserApi.class) {
                if (userApi == null) {
                    userApi = RxHttpUtils.createApi(UserApi.class);
                }
            }
        }

        return requestUtil;
    }

    public void getWeather(String location, BaseObserver<HeWeatherBean> observer) {
        userApi.getWeather(location).compose(Transformer.<HeWeatherBean>switchSchedulers()).subscribe(observer);
    }

}
