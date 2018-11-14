package com.example.a55300.animationdemo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.adapter.MainAdapter;
import com.example.a55300.animationdemo.bean.TypeBean;
import com.example.a55300.animationdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener{

    private MainAdapter adapter;
    private ActivityMainBinding binding;

    private List<TypeBean> typeBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    public void initView(){

    }

    public void initData(){
        adapter = new MainAdapter(getData(),this);
        adapter.setOnItemClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private List<TypeBean> getData(){
        typeBeanList.add(new TypeBean("自定义圆环进度条", 0));
        typeBeanList.add(new TypeBean("RxJava + Retrofit请求天气（已封装）", 1));
        typeBeanList.add(new TypeBean("自定义TopBar", 2));
        typeBeanList.add(new TypeBean("自定义RandomRect", 3));
        typeBeanList.add(new TypeBean("display more message", 4));

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
            default:
        }
    }

    private void toNextTopic(Class<?> cls, String title) {
        if (title != null) {
            Intent intent = new Intent(MainActivity.this, cls);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }
}
