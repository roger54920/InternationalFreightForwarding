package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.custom.SystemPromptDialog;
import com.example.ysww.internationalfreightforwarding.model.SupplierQuotationListBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.AffirmChannelView;
import com.example.ysww.internationalfreightforwarding.net.view.SupplierQuotationListView;
import com.example.ysww.internationalfreightforwarding.presenter.AffirmChannelPresenter;
import com.example.ysww.internationalfreightforwarding.presenter.SupplierQuotationListPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MoveLocationUtil;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 渠道商报价
 */
public class IFF_Supplier_QuotationActivity extends Activity implements SupplierQuotationListView, AffirmChannelView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.supplier_quotation_rv)
    RecyclerView supplierQuotationRv;
    @InjectView(R.id.supplier_quotation_buttom_ll)
    LinearLayout supplierQuotationButtomLl;
    @InjectView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private CommonAdapter<SupplierQuotationListBean.PageBean.ListBean> supplierQuotationAdapter;
    private List<SupplierQuotationListBean.PageBean.ListBean> supplierQuotationList;
    private String orderId, affirmChannelId;
    private int page = 1;
    private int limit = 10;
    private int radioCheckPosition = 0;
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private SupplierQuotationListPresenter supplierQuotationListPresenter = new SupplierQuotationListPresenter();
    private AffirmChannelPresenter affirmChannelPresenter = new AffirmChannelPresenter();

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__supplier__quotation);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        orderId = getIntent().getStringExtra("orderId");
        iffTitleTv.setText(R.string.supplier_quotation);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);

        //添加头部
        SinaRefreshView headerView = new SinaRefreshView(this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setHeaderHeight(50);
        //添加底部
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setAutoLoadMore(true);

        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        supplierQuotationListMethod();


    }

    private void initAdapter() {
        for (int i = 0; i < supplierQuotationList.size(); i++) {
            supplierQuotationList.get(i).setIsChecked(1);
        }
        supplierQuotationList.get(radioCheckPosition).setIsChecked(0);
        linearLayoutManager = new LinearLayoutManager(this);
        supplierQuotationRv.setLayoutManager(linearLayoutManager);
        supplierQuotationAdapter = new CommonAdapter<SupplierQuotationListBean.PageBean.ListBean>(this, R.layout.item_supplier_quotation, supplierQuotationList) {
            @Override
            protected void convert(ViewHolder holder, SupplierQuotationListBean.PageBean.ListBean listBean, final int position) {
                orderId = getIntent().getStringExtra("orderId");
                String brand = getIntent().getStringExtra("brand");
                if (position+1==supplierQuotationList.size()) {
                    holder.setVisible(R.id.view, false);
                }
                if(position==0){
                    holder.setVisible(R.id.order_number_tv,true);
                    holder.setText(R.id.order_number_tv,orderId+"("+brand+")" + "渠道商竞标信息");
                }else{
                    holder.setVisible(R.id.order_number_tv,false);
                }
                holder.setText(R.id.supplier_quotation_tv, listBean.getChannelName()+" "+listBean.getChannelUserName()+ "     " + listBean.getSumChannelQuoteFrofit()+"元");
                holder.setText(R.id.note_information, listBean.getCompanyName() + "     " + listBean.getMarketUserName());
                final RadioButton supplier_quotation = holder.getView(R.id.supplier_quotation_rb);
                int isChecked = supplierQuotationList.get(position).getIsChecked();
                if (isChecked == 0) {
                    supplier_quotation.setChecked(true);
                } else {
                    supplier_quotation.setChecked(false);
                }
                holder.setOnClickListener(R.id.supplier_quotation_ll, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            for (int i = 0; i < supplierQuotationList.size(); i++) {
                                supplierQuotationList.get(i).setIsChecked(1);
                            }
                            radioCheckPosition = position;
                            supplierQuotationList.get(radioCheckPosition).setIsChecked(0);
                            affirmChannelId = supplierQuotationList.get(position).getId();
                            supplierQuotationAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        supplierQuotationRv.setAdapter(supplierQuotationAdapter);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = 1;
                refreshAndLoadDataRequests();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                page++;
                refreshAndLoadDataRequests();
            }
        });
    }

    /**
     * 刷新和加载数据请求
     */
    private void refreshAndLoadDataRequests() {
        supplierQuotationListMethod();
        SystemUtils.getInstance(IFF_Supplier_QuotationActivity.this).rvRefreshTimeout(refreshLayout);
    }

    /**
     * 获取渠道商报价列表
     */
    private void supplierQuotationListMethod() {
        new OkgoHttpResolve(this);
        supplierQuotationListPresenter.attach(this);
        supplierQuotationListPresenter.supplierQuotationListResult("orderId=" + orderId + "&page=" + page + "&limit=" + limit, this, lazyLoadProgressDialog);
    }

    /**
     * 认定渠道商
     */
    private void affirmChannelMethod() {
        new OkgoHttpResolve(this);
        affirmChannelPresenter.attach(this);
        affirmChannelPresenter.affirmChannelResult("{\"id\":\"" + affirmChannelId + "\"}", this, lazyLoadProgressDialog);
    }

    @OnClick({R.id.title_return_img, R.id.determine_the_quotation_btn, R.id.put_questions_to_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.determine_the_quotation_btn:
                onClickIsAffirmChannel();
                break;
            case R.id.put_questions_to_btn:
                orderId = getIntent().getStringExtra("orderId");
                SystemUtils.getInstance(this).referenceSourcePageorderIdChanneIdealerIntent(IFF_Quotation_InformationActivity.class,"supplier_quotation" ,orderId,"");
                break;
        }
    }

    @Override
    public void onSupplierQuotationListFinish(Object o) {
        SystemUtils.getInstance(this).rvRefreshSuccess(refreshLayout);
        SupplierQuotationListBean supplierQuotationListBean = (SupplierQuotationListBean) o;
        if (supplierQuotationList == null) {
            supplierQuotationList = new ArrayList<>();
        }
        List<SupplierQuotationListBean.PageBean.ListBean> list = supplierQuotationListBean.getPage().getList();
        if (supplierQuotationListBean.getPage().getCurrPage() == 1) {
            if (supplierQuotationListBean.getPage().getTotalCount() > 0 && list != null) {
                refreshLayout.setVisibility(View.VISIBLE);
                supplierQuotationList.clear();
                supplierQuotationList = list;
                initAdapter();
                if (supplierQuotationListBean.getPage().getTotalCount() > 10) {
                    refreshLayout.setEnableLoadmore(true);
                    refreshLayout.setAutoLoadMore(true);
                } else {
                    refreshLayout.setEnableLoadmore(false);
                    refreshLayout.setAutoLoadMore(false);
                }
            } else {
                //noContent.setVisibility(View.VISIBLE);
                refreshLayout.setVisibility(View.GONE);
            }
        } else {
            if (list != null) {
                MoveLocationUtil.MoveToPosition(linearLayoutManager, supplierQuotationRv, supplierQuotationList.size());
                for (int i = 0; i < list.size(); i++) {
                    supplierQuotationList.add(list.get(i));
                }
                initAdapter();
                if (list.size() == 10) {
                    refreshLayout.setEnableLoadmore(true);
                    refreshLayout.setAutoLoadMore(true);
                } else {
                    refreshLayout.setEnableLoadmore(false);
                    refreshLayout.setAutoLoadMore(false);
                }
            } else {
                ToastStopUtils.toastShow(this, "数据加载完成");
                refreshLayout.setEnableLoadmore(false);
                refreshLayout.setAutoLoadMore(false);
            }
        }
    }
    //点击是否确认渠道商
    private void onClickIsAffirmChannel() {
        SystemPromptDialog.Builder builder = new SystemPromptDialog.Builder(this, "您确定选择此渠道商吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作事项
                SystemUtils.getInstance(IFF_Supplier_QuotationActivity.this).showLazyLad0neMinute(lazyLoadProgressDialog);
                affirmChannelMethod();
            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().setCanceledOnTouchOutside(true);  //用户选择取消或者是点击屏幕空白部分时让dialog消失。
        builder.create().show();
    }
    @Override
    public void onAffirmChannelFinish(Object o) {
        SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "copy");
        MyActivityManager.getInstance().finishAllActivity();
    }
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        supplierQuotationListPresenter.dettach();
        affirmChannelPresenter.dettach();
    }


}
