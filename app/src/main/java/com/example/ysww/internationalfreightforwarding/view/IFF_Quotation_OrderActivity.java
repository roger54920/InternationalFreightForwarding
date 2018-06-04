package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.QuotationOrderListBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.QuotationOrderListView;
import com.example.ysww.internationalfreightforwarding.presenter.QuotationOrderListPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MoveLocationUtil;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订单列表
 */
public class IFF_Quotation_OrderActivity extends Activity implements QuotationOrderListView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.title_return_img)
    ImageView titleReturnImg;
    @InjectView(R.id.quotation_order_rv)
    RecyclerView quotationOrderRv;
    @InjectView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private CommonAdapter<QuotationOrderListBean.PageBean.ListBean> quotationOrderAdapter;
    private List<QuotationOrderListBean.PageBean.ListBean> quotationOrderList;

    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private QuotationOrderListPresenter statisticalOrderListPresenter = new QuotationOrderListPresenter();

    private int page = 1;
    private int limit = 10;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__quotation__order);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        if (Constants.SOURCE_PAGE.equals("copy")) {
            iffTitleTv.setText(R.string.order_list);
        } else if (Constants.SOURCE_PAGE.equals("information_supplement")) {
            iffTitleTv.setText(R.string.information_supplement);
        } else if (Constants.SOURCE_PAGE.equals("historical_order")) {
            iffTitleTv.setText(R.string.historical_order);
        }

        //添加头部
        SinaRefreshView headerView = new SinaRefreshView(this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setHeaderHeight(50);
        //添加底部
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setAutoLoadMore(true);

        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        statisticalOrderListMethod();
    }

    /**
     * 报价订单 + 信息补录 + 历史订单 订单列表
     */
    private void statisticalOrderListMethod() {
//        String userId=getSharedPreferences("login", Context.MODE_PRIVATE).getString("userId","null");
        //分别处理
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        if (Constants.SOURCE_PAGE.equals("copy")) {
        } else if (Constants.SOURCE_PAGE.equals("information_supplement")) {
        } else if (Constants.SOURCE_PAGE.equals("historical_order")) {
        }
        new OkgoHttpResolve(this);
        statisticalOrderListPresenter.attach(this);
        statisticalOrderListPresenter.quotationOrderListResult("page=" + page + "&limit=" + limit, this, lazyLoadProgressDialog);
    }

    private void initAdapter() {
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        linearLayoutManager = new LinearLayoutManager(this);
        quotationOrderRv.setLayoutManager(linearLayoutManager);
        quotationOrderAdapter = new CommonAdapter<QuotationOrderListBean.PageBean.ListBean>(this, R.layout.item_order_number_supplier, quotationOrderList) {
            @Override
            protected void convert(ViewHolder holder, QuotationOrderListBean.PageBean.ListBean dataBean, int position) {
                holder.setText(R.id.supplier_tv, "订单号" + dataBean.getOrderId() + "(" + dataBean.getBrand() + ")");
                holder.setText(R.id.order_state, dataBean.getOrderStatus());
                if (quotationOrderList.size() == position + 1) {
                    holder.setVisible(R.id.view, false);
                }
            }
        };
        quotationOrderRv.setAdapter(quotationOrderAdapter);
        quotationOrderAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (Constants.SOURCE_PAGE.equals("copy")) {
                    //三种状态 未报价 已报价 已确认
                    EventBus.getDefault().postSticky(quotationOrderList.get(position));
                    SystemUtils.getInstance(IFF_Quotation_OrderActivity.this).referenceSourcePageIntent(IFF_Order_DetailsActivity.class, Constants.SOURCE_PAGE);

                } else if (Constants.SOURCE_PAGE.equals("information_supplement")) {
                    SystemUtils.getInstance(IFF_Quotation_OrderActivity.this).noReferenceIntent(IFF_Collect_Send_InformationActivity.class);

                } else if (Constants.SOURCE_PAGE.equals("historical_order")) {
                    SystemUtils.getInstance(IFF_Quotation_OrderActivity.this).referenceSourcePageIntent(IFF_Order_DetailsActivity.class, Constants.SOURCE_PAGE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

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
        statisticalOrderListMethod();
        SystemUtils.getInstance(this).rvRefreshTimeout(refreshLayout);
    }
    @OnClick(R.id.title_return_img)
    public void onViewClicked() {
        SystemUtils.getInstance(this).returnHomeFinishAll();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        SystemUtils.getInstance(this).returnHomeFinishAll();
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onStatisticalOrderListFinish(Object o) {
        SystemUtils.getInstance(this).rvRefreshSuccess(refreshLayout);
        QuotationOrderListBean quotationListBean = (QuotationOrderListBean) o;
        if (quotationOrderList == null) {
            quotationOrderList = new ArrayList<>();
        }
        List<QuotationOrderListBean.PageBean.ListBean> list = quotationListBean.getPage().getList();
        if (quotationListBean.getPage().getCurrPage() == 1) {
            if (quotationListBean.getPage().getTotalCount() > 0 && list != null) {
                refreshLayout.setVisibility(View.VISIBLE);
                //noContent.setVisibility(View.GONE);
                quotationOrderList.clear();
                quotationOrderList = list;
                initAdapter();
                if (quotationListBean.getPage().getTotalCount() > 10) {
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
                MoveLocationUtil.MoveToPosition(linearLayoutManager, quotationOrderRv, quotationOrderList.size());
                for (int i = 0; i < list.size(); i++) {
                    quotationOrderList.add(list.get(i));
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

    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        statisticalOrderListPresenter.dettach();
    }

}
