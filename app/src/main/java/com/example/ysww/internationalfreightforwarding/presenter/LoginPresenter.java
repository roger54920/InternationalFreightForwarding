package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.LoginBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.LoginView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 登陆
 */

public class LoginPresenter implements BasePresenter<LoginView> {
    private LoginView view;

    public void postLoginResult(String json, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        OkgoHttpResolve.postStringResult(Constants.LINK_HEAD + "sys/login?" + json,"", LoginBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                LoginBean loginBean = (LoginBean) result;
                if (loginBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (loginBean.getCode() == 0) {
                        if (view != null) {
                            view.onLoginFinish(loginBean);
                        }
                    } else {
                        ToastStopUtils.toastShow(activity, loginBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(LoginView view) {
        this.view = view;
    }

    /**
     * 不调用的时候进行 清空
     */
    @Override
    public void dettach() {
        if (view != null) {
            view = null;
        }
    }
}
