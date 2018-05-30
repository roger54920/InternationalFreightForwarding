package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetOrderMsgCountBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetOrderMsgCountView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取营销员订单未读消息列表接口
 */

public class GetOrderMsgCountPresenter implements BasePresenter<GetOrderMsgCountView> {
    private GetOrderMsgCountView view;

    public void getOrderMsgCountResult(String str,final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tbordermsg/getOrderMsgCount" ,tokenId,str,GetOrderMsgCountBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                GetOrderMsgCountBean getOrderMsgCountBean = (GetOrderMsgCountBean) result;
                if (getOrderMsgCountBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (getOrderMsgCountBean.getCode() == 0) {
                        if (view != null) {
                            view.onGetOrderMsgCountFinish(getOrderMsgCountBean);
                        }
                        if(getOrderMsgCountBean.getData()!=null && getOrderMsgCountBean.getData().size()==0){
                            SystemUtils.getInstance(activity).onClickNoData();
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(getOrderMsgCountBean.getCode());
                        ToastStopUtils.toastShow(activity, getOrderMsgCountBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(GetOrderMsgCountView view) {
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
