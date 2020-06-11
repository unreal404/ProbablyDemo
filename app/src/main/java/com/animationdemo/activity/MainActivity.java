package com.animationdemo.activity;

import android.content.Intent;

import android.os.Bundle;


import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.animationdemo.R;
import com.animationdemo.adapter.MainAdapter;
import com.animationdemo.base.BaseActivity;
import com.animationdemo.bean.TypeBean;
import com.animationdemo.databinding.ActivityMainBinding;
import com.animationdemo.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MainActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener{

    private MainAdapter adapter;
    private ActivityMainBinding binding;

    private List<TypeBean> typeBeanList = new ArrayList<>();

    private String url = "http://xtzx01-1255000116.cos-zwy.xuetangx.com/zwy/img/tmp/省委办省府办经济责任审计办法97d2dd116ee222055d31c589db6919ce.docx";
    private String url_zip = "http://xtzx01-1255000116.cos-zwy.xuetangx.com/zwy/img/tmp/省委办省府办经济责任审计办法97d2dd116ee222055d31c589db6919ce.docx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initData();

    }

    public void initView(){

    }

    public void initData(){
        adapter = new MainAdapter(getData());
        adapter.setOnItemClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {

    }

    private List<TypeBean> getData(){
        typeBeanList.add(new TypeBean("自定义圆环进度条", 0));
        typeBeanList.add(new TypeBean("RxJava + Retrofit请求天气（已封装）", 1));
        typeBeanList.add(new TypeBean("自定义TopBar", 2));
        typeBeanList.add(new TypeBean("自定义RandomRect", 3));
        typeBeanList.add(new TypeBean("display more message", 4));
        typeBeanList.add(new TypeBean("文件管理器", 5));
        typeBeanList.add(new TypeBean("TBS display file", 6));
        typeBeanList.add(new TypeBean("display zip", 7));

        return typeBeanList;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (typeBeanList.get(position).getType()) {
            case 0: toNextTopic(ProgressRingActivity.class,typeBeanList.get(position).getTitle()); break;
            case 1: toNextTopic(WeatherActivity.class,typeBeanList.get(position).getTitle()); break;
            case 2: toNextTopic(TopbarViewActivity.class,typeBeanList.get(position).getTitle()); break;
            case 3: toNextTopic(RandomRectActiviy.class,typeBeanList.get(position).getTitle()); break;
            case 4: toNextTopic(DisplayMoreMessageActivity.class,typeBeanList.get(position).getTitle()); break;
            case 5: toNextTopic(FileAdminActivity.class,typeBeanList.get(position).getTitle()); break;
            case 6:
                Intent intent = new Intent(MainActivity.this, DisplayFileActivity.class);
                intent.putExtra(ConstantUtil.RESOURCE_URL, url);
                intent.putExtra(ConstantUtil.RESOURCE_TITLE, typeBeanList.get(position).getTitle());
                startActivity(intent);
                break;
            case 7:
                toNextTopic(DisplayZipActivity.class, typeBeanList.get(position).getTitle());
                break;
            default:
        }
    }

    private void toNextTopic(Class<?> cls, String title) {
        if (title != null) {
            Intent intent = new Intent(MainActivity.this, cls);
            intent.putExtra(ConstantUtil.RESOURCE_TITLE, title);
            startActivity(intent);
        }
    }


}
