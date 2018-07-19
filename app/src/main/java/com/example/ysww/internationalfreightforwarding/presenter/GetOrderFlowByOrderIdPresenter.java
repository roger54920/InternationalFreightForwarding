package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetOrderFlowByOrderIdBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetOrderFlowByOrderIdView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 通过订单获取物流信息接口
 */

public class GetOrderFlowByOrderIdPresenter implements BasePresenter<GetOrderFlowByOrderIdView> {
    private GetOrderFlowByOrderIdView view;

    public void getOrderFlowByOrderIdResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.getResult(Constants.LINK_HEAD + "mananger/tborder/getOrderFlowByOrderId/"+str ,tokenId,GetOrderFlowByOrderIdBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                GetOrderFlowByOrderIdBean getOrderFlowByOrderIdBean = (GetOrderFlowByOrderIdBean) result;
                if (getOrderFlowByOrderIdBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (getOrderFlowByOrderIdBean.getCode() == 0) {
                        if (view != null) {
                            view.onGetOrderFlowByOrderIdFinish(getOrderFlowByOrderIdBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(getOrderFlowByOrderIdBean.getCode());
                        ToastStopUtils.toastShow(activity, getOrderFlowByOrderIdBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(GetOrderFlowByOrderIdView view) {
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
