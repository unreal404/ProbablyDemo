package com.example.a55300.animationdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.bean.TypeBean;
import com.example.a55300.animationdemo.databinding.ItemMainBinding;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<TypeBean,MainAdapter.MainViewHolder>{
    Context context;

    public MainAdapter(List<TypeBean> data, Context context){
        super(R.layout.item_main, data);
        this.context = context;
    }

    @Override
    protected void convert(MainAdapter.MainViewHolder helper, TypeBean item) {
        ItemMainBinding binding = helper.getBinding();
        binding.titleTv.setText(item.getTitle());

        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    class MainViewHolder extends BaseViewHolder{
        public MainViewHolder(View itemView){
            super(itemView);
        }

        public ItemMainBinding getBinding(){
            return (ItemMainBinding)itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
