package com.hmc.firstlibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Description:
 * @Version: 1.0
 * @Author: samson
 * @Date: 2020/10/24 11:38
 */
public class ToastUtils {

    private static Toast toast;//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长


    /**
     * 短时间显示Toast【居下】
     * @param msg 显示的内容-字符串*/
    public static void showShortToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        //将自定义View覆盖Toast的View
        toast.setView(buildToastView(context,msg,Toast.LENGTH_SHORT));
        toast.show();
    }
    /**
     * 短时间显示Toast【居中】
     * @param msg 显示的内容-字符串*/
    public static void showShortCenter(Context context,String msg){
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        //将自定义View覆盖Toast的View
        toast.setView(buildToastView(context,msg,Toast.LENGTH_SHORT));
        toast.show();
    }


    public static View buildToastView(Context context, String msg, int duration){
        return buildToast(context,msg,duration,"#000000",15,dipTopx(context,8));
    }

    public static View buildToast(Context context, String msg, int duration, String bgColor, int textSp, int cornerRadius){
        //设置Toast文字
        TextView tv = new TextView(context);
        int dpPadding = dipTopx(context,10.0f);
        tv.setPadding(dpPadding, dpPadding, dpPadding, dpPadding);
        tv.setGravity(Gravity.CENTER);
        tv.setText(msg);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSp);

        //Toast文字TextView容器
        LinearLayout mLayout = new LinearLayout(context);
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.parseColor(bgColor));
        shape.setCornerRadius(cornerRadius);
        shape.setStroke(1, Color.parseColor(bgColor));
        shape.setAlpha(180);
        mLayout.setBackground(shape);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置layout_gravity
        params.gravity = Gravity.CENTER;
        mLayout.setLayoutParams(params);
        //设置gravity
        mLayout.setGravity(Gravity.CENTER);
        mLayout.addView(tv);
        return mLayout;
    }

    private static int dipTopx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}