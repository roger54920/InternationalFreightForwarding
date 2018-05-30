package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AffirmChannelBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.AffirmChannelView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 认定渠道商
 */

public class AffirmChannelPresenter implements BasePresenter<AffirmChannelView> {
    private AffirmChannelView view;

    public void affirmChannelResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tborder/affirmChannel" ,tokenId,str,AffirmChannelBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                AffirmChannelBean affirmChannelBean = (AffirmChannelBean) result;
                if (affirmChannelBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (affirmChannelBean.getCode() == 0) {
                        if (view != null) {
                            view.onAffirmChannelFinish(affirmChannelBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(affirmChannelBean.getCode());
                        ToastStopUtils.toastShow(activity, affirmChannelBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(AffirmChannelView view) {
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
