package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.BaseBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.TbordermsgSaveView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 发送订单消息接口
 */

public class TbordermsgSavePresenter implements BasePresenter<TbordermsgSaveView> {
    private TbordermsgSaveView view;

    /**
     * 发送订单消息接口
     * @param str
     * @param activity
     * @param lazyLoadProgressDialog
     */
    public void tbordermsgSaveResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tbordermsg/save" ,tokenId,str, BaseBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                BaseBean tbordermsgSendOut = (BaseBean) result;
                if (tbordermsgSendOut != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (tbordermsgSendOut.getCode() == 0) {
                        if (view != null) {
                            view.onTbordermsgSaveFinish(tbordermsgSendOut);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(tbordermsgSendOut.getCode());
                        ToastStopUtils.toastShow(activity, tbordermsgSendOut.getMsg());
                    }
                }
            }
        });
    }
    @Override
    public void attach(TbordermsgSaveView view) {
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
