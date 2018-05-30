package com.example.ysww.internationalfreightforwarding.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.SupplierQuotationListBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.SupplierQuotationListView;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;

/**
 * 获取渠道商报价列表
 */

public class SupplierQuotationListPresenter implements BasePresenter<SupplierQuotationListView> {
    private SupplierQuotationListView view;

    public void supplierQuotationListResult(String str, final Activity activity, final LazyLoadProgressDialog lazyLoadProgressDialog) {
        String tokenId=activity.getSharedPreferences("login", Context.MODE_PRIVATE).getString("tokenId","null");
        OkgoHttpResolve.postStringResult(Constants.LINK_HEAD + "mananger/tborderchanneluserquote/list?"+str ,tokenId,SupplierQuotationListBean.class, new OkgoHttpResolve.HttpCallBack() {
            @Override
            public void finish(Object result) {
                SupplierQuotationListBean supplierQuotationListBean = (SupplierQuotationListBean) result;
                if (supplierQuotationListBean != null) {
                    SystemUtils.getInstance(activity).setLazyLadResult(lazyLoadProgressDialog);
                    if (supplierQuotationListBean.getCode() == 0) {
                        if (view != null) {
                            view.onSupplierQuotationListFinish(supplierQuotationListBean);
                        }
                        if(supplierQuotationListBean.getPage().getTotalCount()==0){
                            SystemUtils.getInstance(activity).onClickNoData();
                        }
                    } else {
                        SystemUtils.getInstance(activity).tokenInvalid(supplierQuotationListBean.getCode());
                        ToastStopUtils.toastShow(activity, supplierQuotationListBean.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void attach(SupplierQuotationListView view) {
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
