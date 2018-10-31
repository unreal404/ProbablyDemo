package com.example.a55300.animationdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a55300.animationdemo.R;


public class TopBar extends RelativeLayout  {
    private Button leftBtn;
    private TextView textView;
    private Button rightBtn;


    private String leftText;
    private Drawable leftBackground;
    private int leftTextColor;
    private String title;

    private float titleTextSize;
    private int titleTextColor;
    private String rightText;

    private Drawable rightBackground;
    private int rightTextColor;

    private LayoutParams leftBtnLayoutParams;
    private LayoutParams textViewLayoutParams;
    private LayoutParams rightBtnLayoutParams;

    private TopBarBtnsOnClickListener listener;

    public interface TopBarBtnsOnClickListener{
        public void leftBtnOnClick();//左按钮被点击的事件
        public void rightBtnOnClick();//右按钮被点击的事件
    }

    public void setOnTopBarBtnsClick(TopBarBtnsOnClickListener listener){
        this.listener = listener;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.topBar);

        leftText = ta.getString(R.styleable.topBar_leftText);
        leftBackground = ta.getDrawable(R.styleable.topBar_leftBackground);
        leftTextColor = ta.getInt(R.styleable.topBar_leftTextColor,0);

        title = ta.getString(R.styleable.topBar_title);
        titleTextSize = ta.getDimension(R.styleable.topBar_titleTextSize,0);
        titleTextColor = ta.getInt(R.styleable.topBar_titleTextColor,0);

        rightText = ta.getString(R.styleable.topBar_rightText);
        rightBackground = ta.getDrawable(R.styleable.topBar_rightBackground);
        rightTextColor = ta.getInt(R.styleable.topBar_rightTextColor,0);

        ta.recycle();

        leftBtn = new Button(context);
        textView = new TextView(context);
        rightBtn = new Button(context);

        leftBtn.setText(leftText);
        leftBtn.setBackground(leftBackground);
        leftBtn.setTextColor(leftTextColor);

        textView.setText(title);
        textView.setTextSize(titleTextSize);
        textView.setTextColor(titleTextColor);
        textView.setGravity(Gravity.CENTER);

        rightBtn.setText(rightText);
        rightBtn.setBackground(rightBackground);
        rightBtn.setTextColor(rightTextColor);


        leftBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftBtn,leftBtnLayoutParams);

        rightBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightBtn,rightBtnLayoutParams);

        textViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(textView,textViewLayoutParams);

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftBtnOnClick();
            }
        });

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightBtnOnClick();
            }
        });

    }

    public void setLeftBtnVisible(boolean isVisible){
        leftBtn.setVisibility(isVisible ? VISIBLE : INVISIBLE);
    }

    public void setRightBtnVisible(boolean isVisible){
        rightBtn.setVisibility(isVisible ? VISIBLE : INVISIBLE);
    }

    public void setTitle(String title) {
        textView.setText(title);
    }
}
