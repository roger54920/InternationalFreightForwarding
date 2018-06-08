package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AffirmChannelBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.OrderSupplementView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 补录信息接口
 */

public class OrderSupplementPresenter implements BasePresenter<OrderSupplementView> {
    private OrderSupplementView view;

    public void orderSupplementResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tborder/orderSupplement" ,tokenId,str,AffirmChannelBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                AffirmChannelBean orderSupplement = (AffirmChannelBean) result;
                if (orderSupplement != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (orderSupplement.getCode() == 0) {
                        if (view != null) {
                            view.onDeliverOrderFinish(orderSupplement);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(orderSupplement.getCode());
                        ToastStopUtils.toastShow(activity, orderSupplement.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(OrderSupplementView view) {
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
