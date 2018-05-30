package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetAllCountryBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetAllCountryView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取国家
 */

public class GetAllCountryPresenter implements BasePresenter<GetAllCountryView> {
    private GetAllCountryView view;

    public void getAllCountryResult(final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.getResult(Constants.LINK_HEAD + "sys/dicdetail/getAllCountry" ,tokenId ,GetAllCountryBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                GetAllCountryBean getAllCountryBean = (GetAllCountryBean) result;
                if (getAllCountryBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (getAllCountryBean.getCode() == 0) {
                        if (view != null) {
                            view.onGetAllCountryFinish(getAllCountryBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(getAllCountryBean.getCode());
                        ToastStopUtils.toastShow(activity, getAllCountryBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(GetAllCountryView view) {
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
