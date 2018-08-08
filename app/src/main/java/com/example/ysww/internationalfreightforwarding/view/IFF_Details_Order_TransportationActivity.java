package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetOrderFlowByOrderIdBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetOrderFlowByOrderIdView;
import com.example.ysww.internationalfreightforwarding.presenter.GetOrderFlowByOrderIdPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订单运输详情
 */
public class IFF_Details_Order_TransportationActivity extends Activity implements GetOrderFlowByOrderIdView {
    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.details_order_tv)
    TextView detailsOrderTv;
    @InjectView(R.id.details_order_img)
    ImageView detailsOrderImg;
    @InjectView(R.id.details_order_rv)
    RecyclerView detailsOrderRv;
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private CommonAdapter<GetOrderFlowByOrderIdBean.DataBean> detailsOrderAdapter;
    private GetOrderFlowByOrderIdPresenter getOrderFlowByOrderIdPresenter = new GetOrderFlowByOrderIdPresenter();
    private List<GetOrderFlowByOrderIdBean.DataBean> detailsOrderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__details__order__transportation);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        int orderStauts = getIntent().getIntExtra("orderStauts", 0);
        if(orderStauts==5){
            detailsOrderTv.setText("努力前进中~~~");
            detailsOrderImg.setImageResource(R.drawable.truck_icon);
        }else if(orderStauts==6){
            detailsOrderTv.setText("运输完成");
            detailsOrderImg.setImageResource(R.drawable.sign_in_icon);
        }
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        iffTitleTv.setText(R.string.details_order_transportation);
        detailsOrderList = new ArrayList<>();
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        getOrderFlowByOrderIdMethod();
    }
    /**
     * 通过订单获取物流信息接口
     */
    private void getOrderFlowByOrderIdMethod() {
        new OkgoHttpResolve(this);
        getOrderFlowByOrderIdPresenter.attach(this);
        getOrderFlowByOrderIdPresenter.getOrderFlowByOrderIdResult(getIntent().getStringExtra("orderId"), this, lazyLoadProgressDialog);
    }
    private void initAdapter(){
        detailsOrderRv.setLayoutManager(new LinearLayoutManager(this));
        detailsOrderAdapter = new CommonAdapter<GetOrderFlowByOrderIdBean.DataBean>(this,R.layout.item_detail_order_transportation,detailsOrderList) {
            @Override
            protected void convert(ViewHolder holder, GetOrderFlowByOrderIdBean.DataBean dataBean, int position) {
                if (position + 1 == detailsOrderList.size()) {
                    holder.setVisible(R.id.view, false);
                }else{
                    holder.setVisible(R.id.view, true);
                }
                holder.setText(R.id.dot_datetime,dataBean.getFlowDate());
                TextView dotContext=holder.getView(R.id.dot_context);
                dotContext.setText(Html.fromHtml("<font color=#999999>处理站着</font>"+"<font color=#2A2A2A>【"+dataBean.getFlowStation()+"】</font>" +
                        "<font color=#999999>处理动作</font>"+"<font color=#2A2A2A>【" + dataBean.getFlowPlay() + "】</font>" +
                        "<font color=#999999>处理说明</font>"+"<font color=#2A2A2A>【" + dataBean.getFlowExplain() + "】</font>" +
                        "<font color=#999999>备注</font>"+"<font color=#2A2A2A>【" + dataBean.getFlowRemark() + "】</font>"));
            }
        };
        detailsOrderRv.setAdapter(detailsOrderAdapter);
    }
    @OnClick(R.id.title_return_img)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetOrderFlowByOrderIdFinish(Object o) {
        GetOrderFlowByOrderIdBean getOrderFlowByOrderIdBean = (GetOrderFlowByOrderIdBean) o;
        detailsOrderList=getOrderFlowByOrderIdBean.getData();
        initAdapter();
    }
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        getOrderFlowByOrderIdPresenter.dettach();
    }
}
