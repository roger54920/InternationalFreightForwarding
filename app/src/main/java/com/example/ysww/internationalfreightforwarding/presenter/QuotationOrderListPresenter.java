package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.QuotationOrderListBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.QuotationOrderListView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 报价订单 + 信息补录 + 历史订单 订单列表
 */

public class QuotationOrderListPresenter implements BasePresenter<QuotationOrderListView> {
    private QuotationOrderListView view;

    public void quotationOrderListResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postStringResult(Constants.LINK_HEAD + "mananger/tborder/list?"+str ,tokenId,QuotationOrderListBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                QuotationOrderListBean quotationListBean = (QuotationOrderListBean) result;
                if (quotationListBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (quotationListBean.getCode() == 0) {
                        if (view != null) {
                            view.onStatisticalOrderListFinish(quotationListBean);
                        }
                        if(quotationListBean.getPage().getTotalCount()==0){
                            SystemUtils.getInstance(activity).onClickNoData();
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(quotationListBean.getCode());
                        ToastStopUtils.toastShow(activity, quotationListBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(QuotationOrderListView view) {
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
