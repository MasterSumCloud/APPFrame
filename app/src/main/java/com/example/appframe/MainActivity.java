package com.example.appframe;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.appframe.base.BaseMvpActivity;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.test_knife)
    TextView mTvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //创建一个 SpannableString对象
        SpannableString spannableString = new SpannableString("字体测试1字体测试2字体测试3文本像素文本DP文本一半文本两倍前景色背景色正常粗体斜体粗斜体下划线删除线文本上标文本下标文本 (图片)表情电话-邮件-网址-短信-彩信-地图");
        //设置字体 我是没看出来效果("monospace", "serif", "sans-serif")
        spannableString.setSpan(new TypefaceSpan("monospace"), 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("serif"), 5, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("sans-serif"), 10, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体大小（绝对值 单位：像素）
        spannableString.setSpan(new AbsoluteSizeSpan(24), 15, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //第二个参数是否是 dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 19, 23, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //0.5f 表示默认字体大小的一半
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 23, 27, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //2.0f 表示默认字体大小的两倍
        spannableString.setSpan(new RelativeSizeSpan(2.0f), 27, 31, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体前景色(就是字体颜色)
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 31, 34, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体背景色为青色
        spannableString.setSpan(new BackgroundColorSpan(Color.CYAN), 34, 37, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体样式为正常
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 37, 39, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体样式为粗体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 39, 41, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体样式为斜体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 41, 43, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体样式为粗斜体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 43, 46, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置下划线
        spannableString.setSpan(new UnderlineSpan(), 46, 49, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置删除线
        spannableString.setSpan(new StrikethroughSpan(), 49, 52, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置上标
        spannableString.setSpan(new SuperscriptSpan(), 54, 56, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置下标
        spannableString.setSpan(new SubscriptSpan(), 58, 60, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置图标图片表情
        Drawable drawable = getResources().getDrawable(R.mipmap.emoji);
        drawable.setBounds(0, 0, 80, 80);
        spannableString.setSpan(new ImageSpan(drawable), 62, 63, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //超级链接（注意：需要添加setMovementMethod方法附加响应）
        //电话
        spannableString.setSpan(new URLSpan("tel:12345678900"), 69, 71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //邮件
        spannableString.setSpan(new URLSpan("mailto:ealge.py@xxx.com"), 72, 74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //网络
        spannableString.setSpan(new URLSpan("https://www.baidu.com/"), 75, 77, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //短信   使用sms:或者smsto:
        spannableString.setSpan(new URLSpan("sms:12345678901"), 78, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //彩信   使用mms:或者mmsto:
        spannableString.setSpan(new URLSpan("mms:12345678902"), 81, 83, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //地图
        spannableString.setSpan(new URLSpan("geo:121.484848,31.222222"), 84, 86, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvTest.setText(spannableString);
        mTvTest.setMovementMethod(LinkMovementMethod.getInstance());

//        最后附上直接对Textview的常用样式修改（主要用于商城类项目）
//        mTvTest.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        mTvTest.getPaint().setAntiAlias(true);//抗锯齿
//        mTvTest.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
//        mTvTest.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
//        mTvTest.getPaint().setFlags(0); // 取消设置的的划线
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
