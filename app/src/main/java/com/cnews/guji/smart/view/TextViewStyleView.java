package com.cnews.guji.smart.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @package: TextViewStyleView
 * @author： JSYL-DCL
 * @describe： 单TextView设置不同样式富文本
 * @email： 11442865
 */
public class TextViewStyleView extends AppCompatTextView {
    public TextViewStyleView(Context context) {
        super(context);
    }

    public TextViewStyleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewStyleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 将一串字符中的一部分改变字体样式
     *
     * @param context
     * @param originStr         本来的字符串
     * @param formatStr         需要改变的字符串
     * @param normalStyle       整体的字体样式
     * @param emphasizeStyle    需要改变部分的字体样式
     * @return
     */
    public boolean setTextStyle(Context context, String originStr, String formatStr, int normalStyle, int emphasizeStyle) {
        if (!TextUtils.isEmpty(originStr) && !TextUtils.isEmpty(formatStr)) {

            try {
                int formatStart = originStr.indexOf(formatStr);
                int formatlength = formatStr.length();

                SpannableString formatString = new SpannableString(originStr);
                formatString.setSpan(new TextAppearanceSpan(context, normalStyle), 0, originStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                formatString.setSpan(new TextAppearanceSpan(context, emphasizeStyle), formatStart, formatStart + formatlength, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                formatString.setSpan(new StyleSpan(Typeface.BOLD));

                setText(formatString);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将一串字符中的一部分改变字体样式
     *
     * @param context
     * @param originStr         本来的字符串
     * @param formatStr1         需要改变的字符串1
     * @param formatStr2         需要改变的字符串2
     * @param normalStyle       整体的字体样式
     * @param emphasizeStyle    需要改变部分的字体样式
     * @return
     */
//    public boolean setTextStyle2(Context context, String originStr, String formatStr, int normalStyle, int emphasizeStyle) {
    public boolean setTextStyle2(Context context, String originStr, String formatStr1,String formatStr2, int normalStyle, int emphasizeStyle) {
        if (!TextUtils.isEmpty(originStr) && !TextUtils.isEmpty(formatStr1)&& !TextUtils.isEmpty(formatStr2)) {

            try {
                int formatStart = originStr.indexOf(formatStr1);
                int formatlength = formatStr1.length();
                int formatStart2 = originStr.indexOf(formatStr2);
                int formatlength2 = formatStr2.length();

                SpannableString formatString = new SpannableString(originStr);
                formatString.setSpan(new TextAppearanceSpan(context, normalStyle), 0, originStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                formatString.setSpan(new TextAppearanceSpan(context, emphasizeStyle), formatStart, formatStart + formatlength, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                formatString.setSpan(new TextAppearanceSpan(context, emphasizeStyle), formatStart2, formatStart2 + formatlength2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                formatString.setSpan(new StyleSpan(Typeface.BOLD));

                setText(formatString);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
