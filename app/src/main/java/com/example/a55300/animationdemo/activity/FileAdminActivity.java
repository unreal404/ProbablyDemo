package com.example.a55300.animationdemo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.base.BaseActivity;
import com.example.a55300.animationdemo.databinding.ActivityFileAdminBinding;
import com.example.a55300.animationdemo.fragment.DirectoryFragment;
import com.example.a55300.animationdemo.util.ConstantUtil;

import java.util.ArrayList;

public class FileAdminActivity extends BaseActivity {

    private ActivityFileAdminBinding binding;
    private String title = "";
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private DirectoryFragment mDirectoryFragment;
    private String extStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_file_admin);
        initStatusBar(getResources().getColor(R.color.color_F));
        setStatusBarMode(this, 1);

        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra(ConstantUtil.RESOURCE_TITLE);
            extStorage = getIntent().getStringExtra(ConstantUtil.RESOURCE_EXTSTORAGE);
        }
        binding.includeTitle.tvTitle.setText(title);
    }

    @Override
    public void initData() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        mDirectoryFragment = new DirectoryFragment();
        mDirectoryFragment.setExtStorage(extStorage);
        mDirectoryFragment.setDelegate(new DirectoryFragment.DocumentSelectActivityDelegate() {

            @Override
            public void startDocumentSelectActivity() {

            }

            @Override
            public void didSelectFiles(DirectoryFragment activity, ArrayList<String> files) {
                displayfile(files);
//                mDirectoryFragment.showErrorBox(files.get(0).toString());
            }

            @Override
            public void updateToolBarName(String name) {
                binding.includeTitle.tvTitle.setText(name);

            }
        });
        fragmentTransaction.add(R.id.fragment_container, mDirectoryFragment, "" + mDirectoryFragment.toString());
        fragmentTransaction.commit();
    }

    private void displayfile(ArrayList<String> files) {
        int i = files.get(0).toString().lastIndexOf("/");
        String name = files.get(0).toString().substring(i + 1, files.get(0).toString().length());
        Intent intent = new Intent(FileAdminActivity.this, DisplayFileActivity.class);
        intent.putExtra(ConstantUtil.RESOURCE_TYPE, 2);
        intent.putExtra(ConstantUtil.RESOURCE_URL, files.get(0).toString());
        intent.putExtra(ConstantUtil.RESOURCE_TITLE, name);
        startActivity(intent);
    }

    @Override
    public void initListener() {
        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mDirectoryFragment.onFragmentDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mDirectoryFragment.onBackPressed_()) {
            super.onBackPressed();
        }
    }
}
