package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetChannelUerIdQuoteInfoBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetChannelUerIdQuoteInfoView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取订单渠道报价信息详情接口
 */

public class GetChannelUerIdQuoteInfoPresenter implements BasePresenter<GetChannelUerIdQuoteInfoView> {
    private GetChannelUerIdQuoteInfoView view;

    public void getChannelUerIdQuoteInfoResult(String str,final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tborderchanneluserquote/getChannelUerIdQuoteInfo" ,tokenId,str,GetChannelUerIdQuoteInfoBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                GetChannelUerIdQuoteInfoBean getChannelUerIdQuoteInfoBean = (GetChannelUerIdQuoteInfoBean) result;
                if (getChannelUerIdQuoteInfoBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (getChannelUerIdQuoteInfoBean.getCode() == 0) {
                        if (view != null) {
                            view.onGetChannelUerIdQuoteInfoFinish(getChannelUerIdQuoteInfoBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(getChannelUerIdQuoteInfoBean.getCode());
                        ToastStopUtils.toastShow(activity, getChannelUerIdQuoteInfoBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(GetChannelUerIdQuoteInfoView view) {
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
