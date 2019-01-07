package com.example.a55300.animationdemo.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;
import com.allen.library.cookie.store.SPCookieStore;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class App extends Application {
    Map<String, Object> headerMaps = new HashMap<>();
    public static volatile Context applicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(headerMaps)
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(true)
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                //不设置的话，默认不对cookie做处理
                .setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();

        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //配置全局baseUrl
                .setBaseUrl("http://119.29.120.74/yuejian/public/index.php/admin/api/")
                //开启全局配置
                .setOkClient(okHttpClient);

    }

//    private void initX5() {
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);
//    }
}
