package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.FlieUploadBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.FlieUploadView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 上传文件接口
 */

public class FlieUploadPresenter implements BasePresenter<FlieUploadView> {
    private FlieUploadView view;

    public void flieUploadResult(String category, String srcPath, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId = activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId", "null");
        OkgoHttpResolve.postJsonUpLoadResult(Constants.LINK_HEAD + "mananger/flie/upload", category, srcPath, tokenId, FlieUploadBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                FlieUploadBean flieUploadBean = (FlieUploadBean) result;
                if (flieUploadBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (flieUploadBean.getCode() == 0) {
                        if (view != null) {
                            view.onFlieUploadFinish(flieUploadBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(flieUploadBean.getCode());
                        ToastStopUtils.toastShow(activity, flieUploadBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(FlieUploadView view) {
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
