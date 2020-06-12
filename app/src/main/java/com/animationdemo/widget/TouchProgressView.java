package com.animationdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorRes;

public class TouchProgressView extends View {
    private static final String TAG = "TouchProgressView";

    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint proPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint pointPaint = new Paint();
    private Paint textPaint = new Paint();

    private int pointRadius = 40;//圆点默认半径,单位px
    private int pointColor = R.color.color_red;//圆点默认颜色

    private int lineHeight = 20;//线默认高度,单位px
    private int lineClor = R.color.color_e6;//线默认颜色

    private final int PROGRESS_MIN = 0;
    private final int PROGRESS_MAX = 15;
    private int progress = PROGRESS_MAX / 2 + 3;
    private int level = 15;//档位
    private float spacing = 0;//间距
    private boolean isFirst = true;
    private List<Float> levelList = new ArrayList<>();

    private Bitmap originImg;
    private int type = 0;

    private OnProgressChangedListener progressChangedListener;

    public interface OnProgressChangedListener {
        void onProgressChanged(View view, int progress);
    }

    public TouchProgressView(Context context) {
        super(context, null);
    }

    public TouchProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TouchProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TouchProgressView);
        type = ta.getInt(R.styleable.TouchProgressView_selectMode, 0);

        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeWidth(lineHeight);
        linePaint.setColor(getResources().getColor(lineClor));

        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setColor(getResources().getColor(pointColor));

        proPaint.setAntiAlias(true);
        proPaint.setStyle(Paint.Style.STROKE);
        proPaint.setStrokeWidth(lineHeight);
        proPaint.setStrokeCap(Paint.Cap.ROUND);
        proPaint.setColor(getResources().getColor(R.color.color_red));

        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(28f);
        textPaint.setColor(getResources().getColor(R.color.color_3));

        originImg = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_pro_btn);

        isFirst = true;
    }

    /**
     * 设置圆点半径
     *
     * @param radius
     */
    public void setPointRadius(final int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius 不可以小于等于0");
        }

        if (getWidth() == 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    if (radius * 2 > getWidth()) {
                        throw new IllegalArgumentException("radius*2 必须小于 view.getWidth() == " + getWidth());
                    }
                    pointRadius = radius;
                }
            });
        } else {
            if (radius * 2 > getWidth()) {
                throw new IllegalArgumentException("radius*2 必须小于 view.getWidth() == " + getWidth());
            }
            this.pointRadius = radius;
        }
    }

    /**
     * 设置圆点颜色
     *
     * @param color
     */
    public void setPointColor(@ColorRes int color) {
        this.pointColor = color;
    }

    /**
     * 设置直线高度
     *
     * @param height
     */
    public void setLineHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("height 不可以小于等于0");
        }

        this.lineHeight = height;
    }

    /**
     * 设置直线颜色
     *
     * @param color
     */
    public void setLineColor(@ColorRes int color) {
        this.lineClor = color;
    }

    /**
     * 设置百分比
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < 0 || progress > PROGRESS_MAX) {
            throw new IllegalArgumentException("progress 不可以小于0 或大于100");
//            Log.e("ArgumentException", "progress 不可以小于0 或大于100");
        }
        this.progress = progress;
        invalidate();

        if (progressChangedListener != null) {
            progressChangedListener.onProgressChanged(this, progress);
        }
    }

    /**
     * 设置进度变化监听器
     *
     * @param onProgressChangedListener
     */
    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.progressChangedListener = onProgressChangedListener;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < pointRadius) {
            setProgress(PROGRESS_MIN);
            return true;
        } else if (event.getX() > getWidth() - pointRadius) {
            setProgress(PROGRESS_MAX);
            return true;
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    setProgress(calculProgress(event.getX()));
                    return true;
                case MotionEvent.ACTION_MOVE:
                    setProgress(calculProgress(event.getX()));
                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.d(TAG, "[draw] .. in .. ");
        super.draw(canvas);

        //因为是以画布Canvas 为draw对象，所以RectF构造函数内的参数是以canvas为边界，而不是屏幕
        canvas.drawLine(20, getHeight() / 2, getWidth() - 20, getHeight() / 2, linePaint);

        if (type == 1)
            canvas.drawLine(20, getHeight() / 2, getCx(), getHeight() / 2, proPaint);
        else  canvas.drawLine(getCx(), getHeight() / 2, getCx(PROGRESS_MAX/2), getHeight() / 2, proPaint);


        canvas.drawBitmap(originImg, getCx() - originImg.getWidth() / 2, getHeight() / 2 - originImg.getHeight() / 2, new Paint());
        displayText(canvas);
        displayLevel(canvas);
//        pointPaint = new Paint();
//        pointPaint.setAntiAlias(true);
//        pointPaint.setStyle(Paint.Style.FILL);
//        pointPaint.setColor(getResources().getColor(pointColor));
//        canvas.drawCircle(getCx(), getHeight() / 2, pointRadius - 10, pointPaint);
    }

    private void displayLevel(Canvas canvas) {
        if (isFirst) {
            spacing = PROGRESS_MAX / 15;
            int effectiveWidth = getWidth() - 40 - 36;
            levelList.clear();
            for (int x = 0; x <= PROGRESS_MAX; x += spacing) {
                levelList.add(getCx(x));
            }
            isFirst = false;
        }
        float min = getCx();
        float max = getCx(PROGRESS_MAX/2);
        if (type == 1) {
            min = 20;
            max = getCx();
        } else {
            if (min > max) {
                max = getCx();
                min = getCx(PROGRESS_MAX/2);
            }
        }
        if (levelList.size() > 0) {
            for (int i = 0; i < levelList.size(); i++) {
                if (levelList.get(i) >= min && levelList.get(i) <= max) {
                    pointPaint.setColor(getResources().getColor(R.color.color_red));
                    canvas.drawCircle(levelList.get(i), getHeight() / 2 + 50, 6, pointPaint);
                } else {
                    pointPaint.setColor(getResources().getColor(R.color.color_e6));
                    canvas.drawCircle(levelList.get(i), getHeight() / 2 + 50, 6, pointPaint);
                }
            }
        }
    }

    private void displayText(Canvas canvas) {
//        Paint.FontMetrics fm = textPaint.getFontMetrics();
//        int textHeight = (int) Math.ceil(fm.descent - fm.ascent);
//        int mXCentre = getWidth() / 2;
//        int mYCentre = getHeight() / 2;
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        int tmpPro = progress;
        if (type == 0) {
            tmpPro = progress - PROGRESS_MAX / 2;
        }
        float textWidth = textPaint.measureText(tmpPro + "");
        canvas.drawText(tmpPro+"", getCx() - textWidth / 2, getHeight() / 2 - 50, textPaint);
    }

    /**
     * 获取圆点的x轴坐标
     *
     * @return
     */
    private float getCx() {
        float cx = 0.0f;
        cx = (getWidth() - pointRadius * 2);
        if (cx < 0) {
            throw new IllegalArgumentException("TouchProgressView 宽度不可以小于 2 倍 pointRadius");
        }
        return cx / PROGRESS_MAX * progress + pointRadius;
    }

    private float getCx(int pro) {
        float cx = 0.0f;
        cx = (getWidth() - pointRadius * 2);
        if (cx < 0) {
            throw new IllegalArgumentException("TouchProgressView 宽度不可以小于 2 倍 pointRadius");
        }
        return cx / PROGRESS_MAX * pro + pointRadius;
    }

    /**
     * 计算触摸点的百分比
     *
     * @param eventX
     * @return
     */
    private int calculProgress(float eventX) {
        float proResult = (eventX - pointRadius) / (getWidth() - pointRadius * 2);
        return (int) (proResult * PROGRESS_MAX);
    }
}
