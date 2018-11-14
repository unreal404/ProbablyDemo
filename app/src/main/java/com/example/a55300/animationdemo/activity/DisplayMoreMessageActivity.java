package com.example.a55300.animationdemo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a55300.animationdemo.R;
import com.example.a55300.animationdemo.databinding.ActivityDisplayMoreMessageBinding;

public class DisplayMoreMessageActivity extends AppCompatActivity {

    private ActivityDisplayMoreMessageBinding binding;
    private String str;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_more_message);

        initView();
        initListener();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            title = getIntent().getStringExtra("title");
        }
        binding.includeTitle.tvTitle.setText(title);

        str = "班级简介：\n" +
                "据《关于开展省管领导干部学习贯彻党的十九大精神集中轮训\n" +
                "工作的通知》(浙组电明字[2017]55号)精神，省委分4期对省管\n" +
                "领导干部进行党的十九大精神集中轮训，要求每位省管领导干\n" +
                "部都要参加一次集中轮训，确保党的十九大精神学......";

        limitStringTo140(str, binding.tvMessgae, null);
    }

    private void initListener() {
        binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static int getLastCharIndexForLimitTextView(TextView textView, String content, int width, int maxLine) {
        Log.i("Alex", "宽度是" + width);
        TextPaint textPaint = textView.getPaint();
        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        if (staticLayout.getLineCount() > maxLine)
            return staticLayout.getLineStart(maxLine) - 1;//exceed
        else return -1;//not exceed the max line
    }

    public static void limitStringTo140(String summerize, final TextView textView, final View.OnClickListener clickListener) {
        final long startTime = System.currentTimeMillis();
        if (textView == null) return;
        int width = textView.getWidth();//在recyclerView和ListView中，由于复用的原因，这个TextView可能以前就画好了，能获得宽度
        if (width == 0) width = 1000;//获取textview的实际宽度，这里可以用各种方式（一般是dp转px写死）填入TextView的宽度
        int lastCharIndex = getLastCharIndexForLimitTextView(textView, summerize, width, 10);
        if (lastCharIndex < 0 && summerize.length() <= 100) {//如果行数没超过限制
            textView.setText(summerize);
            return;
        }
        //如果超出了行数限制
        textView.setMovementMethod(LinkMovementMethod.getInstance());//this will deprive the recyclerView's focus
        if (lastCharIndex > 100 || lastCharIndex < 0) lastCharIndex = 100;
        String explicitText = null;
        if (summerize.charAt(lastCharIndex) == '\n') {//manual enter
            explicitText = summerize.substring(0, lastCharIndex);
        } else if (lastCharIndex > 12) {//TextView auto enter
            explicitText = summerize.substring(0, lastCharIndex - 12);
        }
        int sourceLength = explicitText.length();
        String showmore = "展开";
        String exitmore = "收起";
        explicitText = explicitText + "..." + showmore;
        final SpannableString mSpan = new SpannableString(explicitText);
        final String finalSummerize = summerize + exitmore;
        final SpannableString mSpan_exit = new SpannableString(finalSummerize);

        mSpan_exit.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(textView.getResources().getColor(R.color.color_66ccff));
                ds.setAntiAlias(true);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {
                textView.setText(mSpan);
            }
        },finalSummerize.length() - 4, finalSummerize.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mSpan.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(textView.getResources().getColor(R.color.color_66ccff));
                ds.setAntiAlias(true);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {//"...show more" click event
                Log.i("Alex", "click showmore");
                textView.setText(mSpan_exit);
                textView.setOnClickListener(null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (clickListener != null)
                            textView.setOnClickListener(clickListener);//prevent the double click
                    }
                }, 20);
            }
        }, sourceLength, explicitText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(mSpan);
        Log.i("Alex", "字符串处理耗时" + (System.currentTimeMillis() - startTime));
    }

}
