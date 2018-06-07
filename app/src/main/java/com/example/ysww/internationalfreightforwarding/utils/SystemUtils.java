package com.example.ysww.internationalfreightforwarding.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.custom.NoDataDialog;
import com.example.ysww.internationalfreightforwarding.view.IFF_HomeActivity;
import com.example.ysww.internationalfreightforwarding.view.IFF_LoginActivity;
import com.githang.statusbar.StatusBarCompat;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系統方面的工具类
 */

public class SystemUtils {
    private static SystemUtils systemUtils;
    private static Context mContext;

    // 构造函数私有化
    private SystemUtils() {
    }

    // 提供一个全局的静态方法
    public static SystemUtils getInstance(Context context) {
        mContext = context;
        if (systemUtils == null) {
            systemUtils = new SystemUtils();
        }
        return systemUtils;
    }

    /**
     * dip与dp 之间的转换
     *
     * @param dpValue
     * @return
     */
    public int dip2Px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public void softKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 获取软键盘的显示状态
        boolean isOpen = imm.isActive();
        if (isOpen == true) {
            //隐藏软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    /**
     * 1分钟以后关闭LazyLoadProgressDialog 延迟加载
     *
     * @param dialog
     */
    public void setLazyLad0neMinute(LazyLoadProgressDialog dialog) {
        final LazyLoadProgressDialog lazyLoadProgressDialog = dialog;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(OkGo.DEFAULT_MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 要执行的操作
                 */
                if (lazyLoadProgressDialog != null) {
                    lazyLoadProgressDialog.cancel();
                }
            }
        }.start();
    }

    /**
     * 关闭延迟加载
     *
     * @param dialog
     */
    public void setLazyLadResult(LazyLoadProgressDialog dialog) {
        final LazyLoadProgressDialog lazyLoadProgressDialog = dialog;
        if (lazyLoadProgressDialog != null) {
            lazyLoadProgressDialog.cancel();
        }
    }

    /**
     * 无参intent
     *
     * @param cls
     */
    public void noReferenceIntent(Class cls) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
    }

    /**
     * 来源页面intent
     *
     * @param cls
     */
    public void referenceSourcePageIntent(Class cls, String source_page) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("source_page", source_page);
        mContext.startActivity(intent);
    }
    /**
     * 来源订单状态和订单号id渠道商Id
     *
     * @param cls
     */
    public void orderStautsOrderNoChannelUserIdIntent(Class cls, int orderStatus, String orderNo,String channelUserId) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("orderStauts", orderStatus);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("channelUserId",channelUserId);
        mContext.startActivity(intent);
    }
    /**
     * 来源订单号Id和货物名称
     *
     * @param cls
     */
    public void orderNoBrandIntent(Class cls, String orderNo,String brand) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("brand", brand);
        mContext.startActivity(intent);
    }
    /**
     * 来源订单号Id
     *
     * @param cls
     */
    public void orderNoIntent(Class cls, String orderNo) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("orderNo", orderNo);
        mContext.startActivity(intent);
    }
    /**
     * 返回首页
     */
    public void returnHomeFinishAll() {
        noReferenceIntent(IFF_HomeActivity.class);
        MyActivityManager.getInstance().finishAllActivity();
    }
    /**
     * 来源页面intent和订单号,渠道商Id
     *
     * @param cls
     */
    public void referenceSourcePageOrderNoChanneIdealerIntent(Class cls, String source_page, String orderNo, String channelUserId) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("source_page", source_page);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("channelUserId",channelUserId);
        mContext.startActivity(intent);
    }
    /**
     * 判断电话号码是否符合格式.
     */
    public boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    /**
     * activity
     *
     * @param activity
     */
    public void mustCallActivity(Activity activity) {
        MyActivityManager.getInstance().pushOneActivity(activity);
        StatusBarCompat.setStatusBarColor(activity, Color.parseColor("#F08430"));
    }

    /**
     * 显示延迟加载
     */
    public void showLazyLad0neMinute(LazyLoadProgressDialog lazyLoadProgressDialog) {
        lazyLoadProgressDialog.show();
        setLazyLad0neMinute(lazyLoadProgressDialog);
    }

    /**
     * token失效返回登录页面
     *
     * @param code
     */
    public void tokenInvalid(int code) {
        if (code == 401) {
            noReferenceIntent(IFF_LoginActivity.class);
            MyActivityManager.getInstance().finishAllActivity();
        }
    }

    /**
     * RV列表刷新超时
     */
    public void rvRefreshTimeout(final TwinklingRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
            }
        }, OkGo.DEFAULT_MILLISECONDS);
    }

    /**
     * RV刷新列表成功
     *
     * @param refreshLayout
     */
    public void rvRefreshSuccess(TwinklingRefreshLayout refreshLayout) {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
    }

    /**
     * EditText监听小数点让用户只能输入小数点后两位
     *
     * @param editText
     */
    public void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //这部分是处理如果输入框内小数点后有俩位，那么舍弃最后一位赋值，光标移动到最后
                String str = s.toString();
                if (countStr(s.toString(), ".") > 1) {
                    str = str.substring(0, str.indexOf(".") + 1);
                    editText.setText(str);
                    editText.setSelection(str.length());
                }
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                //这部分是处理如果用户输入以.开头，在前面加上0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                //这里处理用户 多次输入.的处理 比如输入 1..6的形式，是不可以的
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param one 源字符串
     * @param two 要查找的字符串
     * @return
     */
    public int countStr(String one, String two) {
        int counter = 0;
        if (one.indexOf(two) == -1) {
            return 0;
        }
        while (one.indexOf(two) != -1) {
            counter++;
            one = one.substring(one.indexOf(two) + two.length());
        }
        return counter;
    }

    /**
     * 获取当前日期
     * @return
     */
    public String currentDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new java.util.Date())  ;
    }

    /**
     * 暂无数据弹窗
     */
    public void onClickNoData() {
        NoDataDialog.Builder builder = new NoDataDialog.Builder(mContext);
//        builder.setClickCloseTheOrder(new NoDataDialog.Builder.ClickCloseTheOrder() {
//            @Override
//            public void OnClickCloseTheOrder(DialogInterface dialog, int i) {
//                dialog.dismiss();
//                //设置你的操作事项
//            }
//        });
        builder.create().setCanceledOnTouchOutside(true);  //用户选择取消或者是点击屏幕空白部分时让dialog消失。
        builder.create().show();
    }
}
