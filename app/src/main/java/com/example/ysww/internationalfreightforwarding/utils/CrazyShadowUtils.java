package com.example.ysww.internationalfreightforwarding.utils;

import android.content.Context;
import android.view.View;

import com.hitomi.cslibrary.CrazyShadow;
import com.hitomi.cslibrary.base.CrazyShadowDirection;

/**
 * 阴影效果
 */
public class CrazyShadowUtils {
    private CrazyShadow titleCrazyShadow;
    private CrazyShadow drawCrazyShadow0;
    private Context context;
    private static CrazyShadowUtils crazyShadowUtils;
    // 构造函数私有化
    private CrazyShadowUtils(Context context){
        this.context = context;
    }
    // 提供一个全局的静态方法
    public static CrazyShadowUtils getCrazyShadowUtils(Context context) {
        if (crazyShadowUtils == null) {
            synchronized (CrazyShadowUtils.class) {
                if (crazyShadowUtils == null) {
                    crazyShadowUtils = new CrazyShadowUtils(context);
                }
            }
        }
        return crazyShadowUtils;
    }
    public void titleCrazyShadow(View view) {
        titleCrazyShadow = new CrazyShadow.Builder()
                .setContext(context)
                .setDirection(CrazyShadowDirection.BOTTOM)
                .setShadowRadius(SystemUtils.getInstance(context).dip2Px(4))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(view);
    }
}
