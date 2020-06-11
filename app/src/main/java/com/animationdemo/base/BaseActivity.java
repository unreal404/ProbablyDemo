package com.animationdemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.animationdemo.interf.BaseUI;
import com.animationdemo.util.ActivityManager;
import com.animationdemo.util.MUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.annotations.Nullable;


public abstract class BaseActivity extends AppCompatActivity implements BaseUI {
    public float pointX;
    public float pointY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        pointX = ev.getX();
        pointY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void initStatusBar(int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStatusBarMode(Activity context, int type) {
        switch (type) {
            case 0:
                MUIStatusBarHelper.setStatusBarDarkMode(context);
                break;
            case 1:
                MUIStatusBarHelper.setStatusBarLightMode(context);
                break;
        }

    }

    public void toBack(View view) {
        onBackPressed();
    }

    public void showMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }



}
