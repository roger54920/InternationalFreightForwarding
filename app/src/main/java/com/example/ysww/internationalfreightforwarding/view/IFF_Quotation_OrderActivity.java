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
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

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
    private CommonAdapter<QuotationOrderListBean.DataBean> quotationOrderAdapter;
    private List<QuotationOrderListBean.DataBean> quotationOrderList;

    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private QuotationOrderListPresenter statisticalOrderListPresenter = new QuotationOrderListPresenter();

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

        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        statisticalOrderListMethod();
    }

    /**
     * 报价订单 + 信息补录 + 历史订单 订单列表
     */
    private void statisticalOrderListMethod() {
        //分别处理
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        if (Constants.SOURCE_PAGE.equals("copy")) {
        } else if (Constants.SOURCE_PAGE.equals("information_supplement")) {
        } else if (Constants.SOURCE_PAGE.equals("historical_order")) {
        }
        new OkgoHttpResolve(this);
        statisticalOrderListPresenter.attach(this);
        statisticalOrderListPresenter.quotationOrderListResult("{\"quoteStatus\":\"10\"}", this, lazyLoadProgressDialog);
    }

    private void initAdapter() {
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        quotationOrderRv.setLayoutManager(new LinearLayoutManager(this));
        quotationOrderAdapter = new CommonAdapter<QuotationOrderListBean.DataBean>(this, R.layout.item_order_number_supplier, quotationOrderList) {
            @Override
            protected void convert(ViewHolder holder, QuotationOrderListBean.DataBean dataBean, int position) {
                holder.setText(R.id.supplier_tv, "订单号" + dataBean.getOrderId() + "("+dataBean.getBrand()+")");
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
        QuotationOrderListBean quotationListBean = (QuotationOrderListBean) o;
        List<QuotationOrderListBean.DataBean> data = quotationListBean.getData();
        if (data != null && data.size() > 0) {
            quotationOrderList = data;
            initAdapter();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        statisticalOrderListPresenter.dettach();
    }

}
