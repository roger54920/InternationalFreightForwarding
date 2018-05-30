package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.TborderInfoBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.TborderInfoView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取订单详情接口
 */

public class TborderInfoPresenter implements BasePresenter<TborderInfoView> {
    private TborderInfoView view;
    public void tborderInfoResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.getResult(Constants.LINK_HEAD + "mananger/tborder/info/"+str ,tokenId, TborderInfoBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                TborderInfoBean tborderInfoBean = (TborderInfoBean) result;
                if (tborderInfoBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (tborderInfoBean.getCode() == 0) {
                        if (view != null) {
                            view.onTborderInfoFinish(tborderInfoBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(tborderInfoBean.getCode());
                        ToastStopUtils.toastShow(activity, tborderInfoBean.getMsg());
                    }
                }
            }
        });
    }
    @Override
    public void attach(TborderInfoView view) {
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
