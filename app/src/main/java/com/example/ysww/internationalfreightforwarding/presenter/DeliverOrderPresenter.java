package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.BaseBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.DeliverOrderView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 发货接口
 */

public class DeliverOrderPresenter implements BasePresenter<DeliverOrderView> {
    private DeliverOrderView view;

    public void deliverOrderResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId = activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId", "null");
        OkgoHttpResolve.getResult(Constants.LINK_HEAD + "mananger/tborder/deliverOrder/" + str, tokenId, BaseBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                BaseBean deliverOrder = (BaseBean) result;
                if (deliverOrder != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (deliverOrder.getCode() == 0) {
                        if (view != null) {
                            view.onDeliverOrderFinish(deliverOrder);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(deliverOrder.getCode());
                        ToastStopUtils.toastShow(activity, deliverOrder.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(DeliverOrderView view) {
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
