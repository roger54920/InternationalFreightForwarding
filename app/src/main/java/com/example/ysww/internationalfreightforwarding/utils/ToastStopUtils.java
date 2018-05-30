package com.example.ysww.internationalfreightforwarding.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by ysww on 2018/4/26.
 */

public class ToastStopUtils {
    /**
     * 对toast进行一个简单的封装
     * @param activity
     * @param msg
     */
    private static Toast mToast;
    public static void toastShow(Activity activity, String msg){
        if (null == mToast) {
            mToast = Toast.makeText(activity, msg,
                    Toast.LENGTH_SHORT);
//            mToast.setGravity(Gravity.CENTER|Gravity.BOTTOM, 40, 0);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
    //取消Toast
    public static void toastStop() {
        if (null != mToast) {
            mToast.cancel();
        }
    }
}
