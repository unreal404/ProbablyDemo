package com.animationdemo.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.animationdemo.R;
import com.animationdemo.bean.TypeBean;
import com.animationdemo.databinding.ItemMainBinding;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<TypeBean,MainAdapter.MainViewHolder>{

    public MainAdapter(List<TypeBean> data){
        super(R.layout.item_main, data);
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
