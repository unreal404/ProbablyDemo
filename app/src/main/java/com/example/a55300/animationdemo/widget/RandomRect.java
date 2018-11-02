package com.example.a55300.animationdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.a55300.animationdemo.R;

public class RandomRect extends View {
    private int mRectCount = 20;
    private int mRectWidth;
    private int mRectHeight;
    private int mWidth;
    private int offset = 2;
    private LinearGradient mLinearGradient;
    private Paint mPaint = new Paint();

    public RandomRect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            double mRandom = Math.random();
            float currentHeight = (float)(mRectHeight * mRandom);
            canvas.drawRect(
                    (float)(mWidth * 0.4 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float)(mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int)(mWidth * 0.6 / mRectCount);
        mLinearGradient = new LinearGradient(
                0,0,
                mRectWidth,
                mRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }

    public void setmRectCount(int mRectCount) {
        this.mRectCount = mRectCount;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
