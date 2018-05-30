package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.QueryMarketlMsgBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.QueryMarketlMsgView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取订单消息明细接口
 */

public class QueryMarketlMsgPresenter implements BasePresenter<QueryMarketlMsgView> {
    private QueryMarketlMsgView view;

    public void queryMarketlMsgResult(String str,final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postJsonResult(Constants.LINK_HEAD + "mananger/tbordermsg/queryMarketlMsg" ,tokenId,str,QueryMarketlMsgBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                QueryMarketlMsgBean queryMarketlMsgBean = (QueryMarketlMsgBean) result;
                if (queryMarketlMsgBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (queryMarketlMsgBean.getCode() == 0) {
                        if (view != null) {
                            view.onQueryMarketlMsgFinish(queryMarketlMsgBean);
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(queryMarketlMsgBean.getCode());
                        ToastStopUtils.toastShow(activity, queryMarketlMsgBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(QueryMarketlMsgView view) {
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
