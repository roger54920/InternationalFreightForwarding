package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.BaseBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.NewOrderView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 创建订单
 */

public class NewOrderPresenter implements BasePresenter<NewOrderView> {
    private NewOrderView view;

    public void newOrderResult(String str,final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tborder/save" ,tokenId,str,BaseBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                BaseBean newOrder = (BaseBean) result;
                if (newOrder != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (newOrder.getCode() == 0) {
                        if (view != null) {
                            view.onNewOrderFinish(newOrder);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(newOrder.getCode());
                        ToastStopUtils.toastShow(activity, newOrder.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(NewOrderView view) {
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
