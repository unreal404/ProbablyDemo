package com.example.a55300.animationdemo.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.adapter.MainAdapter;
import com.example.a55300.animationdemo.bean.TypeBean;
import com.example.a55300.animationdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private List<TypeBean> getData(){
        typeBeanList.add(new TypeBean("自定义圆环进度条", 1));

        return typeBeanList;
    }
}
