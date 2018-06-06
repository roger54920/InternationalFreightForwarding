package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AffirmChannelBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.OrderCloseView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 关闭订单接口
 */

public class OrderClosePresenter implements BasePresenter<OrderCloseView> {
    private OrderCloseView view;

    public void orderCloseResult(String str,final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.getResult(Constants.LINK_HEAD + "mananger/tborder/orderClose/"+str ,tokenId,AffirmChannelBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                AffirmChannelBean orderClose = (AffirmChannelBean) result;
                if (orderClose != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (orderClose.getCode() == 0) {
                        if (view != null) {
                            view.onOrderCloseFinish(orderClose);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(orderClose.getCode());
                        ToastStopUtils.toastShow(activity, orderClose.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(OrderCloseView view) {
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
