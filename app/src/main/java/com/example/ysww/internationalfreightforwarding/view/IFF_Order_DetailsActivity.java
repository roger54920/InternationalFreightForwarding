package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.model.QuotationOrderListBean;
import com.example.ysww.internationalfreightforwarding.model.TborderInfoBean;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class IFF_Order_DetailsActivity extends Activity {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.title_return_img)
    ImageView titleReturnImg;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.order_number_tv)
    TextView orderNumberTv;
    @InjectView(R.id.operating_company_tv)
    TextView operatingCompanyTv;
    @InjectView(R.id.forwarding_unit_tv)
    TextView forwardingUnitTv;
    @InjectView(R.id.source_of_the_goods_tv)
    TextView sourceOfTheGoodsTv;
    @InjectView(R.id.export_ports_tv)
    TextView exportPortsTv;
    @InjectView(R.id.export_date_tv)
    TextView exportDateTv;
    @InjectView(R.id.type_of_shipping_tv)
    TextView typeOfShippingTv;
    @InjectView(R.id.payment_method_tv)
    TextView paymentMethodTv;
    @InjectView(R.id.customs_clearance_method_tv)
    TextView customsClearanceMethodTv;
    @InjectView(R.id.transport_to_the_country_region_tv)
    TextView transportToTheCountryRegionTv;
    @InjectView(R.id.destination_city_tv)
    TextView destinationCityTv;
    @InjectView(R.id.zip_code)
    TextView zipCode;
    @InjectView(R.id.receiving_address_tv)
    TextView receivingAddressTv;
    @InjectView(R.id.volume_weight_tv)
    TextView volumeWeightTv;
    @InjectView(R.id.product_name_tv)
    TextView productNameTv;
    @InjectView(R.id.value_of_goods_tv)
    TextView valueOfGoodsTv;
    @InjectView(R.id.number_weight_size_rv)
    RecyclerView numberWeightSizeRv;
    @InjectView(R.id.total_number_tv)
    TextView totalNumberTv;
    @InjectView(R.id.total_weight_kg_tv)
    TextView totalWeightKgTv;
    @InjectView(R.id.title_close_order)
    ImageView titleCloseOrder;
    @InjectView(R.id.bottom_ll)
    LinearLayout bottomLl;

    private QuotationOrderListBean.PageBean.ListBean dataBean;
    private CommonAdapter<TborderInfoBean.TbOrderBean.OrderDetailListBean> orderDetailAdapter;
    private List<TborderInfoBean.TbOrderBean.OrderDetailListBean> orderDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__order__details);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        EventBus.getDefault().register(this);
        initViews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onOrderDetailsEvent(QuotationOrderListBean.PageBean.ListBean dataBean) {
        this.dataBean = dataBean;
    }

    private void initViews() {
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        if (Constants.SOURCE_PAGE.equals("historical_order")) {
            bottomLl.setVisibility(View.GONE);
            titleCloseOrder.setVisibility(View.GONE);
        }else{
            titleCloseOrder.setVisibility(View.VISIBLE);
        }


        if(dataBean==null){
            dataBean = new QuotationOrderListBean.PageBean.ListBean();
        }
        iffTitleTv.setText(R.string.order_details);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        orderNumberTv.setText(dataBean.getOrderId());
        operatingCompanyTv.setText(dataBean.getCompanyName());
        forwardingUnitTv.setText(dataBean.getForwardingUnit());
        sourceOfTheGoodsTv.setText(dataBean.getSourceAddress());
        exportPortsTv.setText(dataBean.getExport());
        String departureDate = dataBean.getDepartureDate();
        if (departureDate.contains("00:00")) {
            exportDateTv.setText(departureDate.split("00:00")[0]);
        } else {
            exportDateTv.setText(departureDate);
        }
        typeOfShippingTv.setText(dataBean.getTransType());
        paymentMethodTv.setText(dataBean.getPayType());
        customsClearanceMethodTv.setText(dataBean.getCleanCustomsType());
        transportToTheCountryRegionTv.setText(dataBean.getDestinationCountry());
        destinationCityTv.setText(dataBean.getDestinationCity());
        zipCode.setText(dataBean.getPostcode());
        receivingAddressTv.setText(dataBean.getDeliveryAddress());

//        totalNumberTv.setText(dataBean.getTotalNumber() + "");
//        orderDetailList = dataBean.getOrderDetailList();
//        double totalWeight = 0;
//        for (int i = 0; i < orderDetailList.size(); i++) {
//            totalWeight += orderDetailList.get(i).getWeight();
//        }
//        totalWeightKgTv.setText(totalWeight + "");
//        orderDetailAdapter();
        volumeWeightTv.setText(dataBean.getVolumeSize()+"");
        productNameTv.setText(dataBean.getBrand());
        valueOfGoodsTv.setText(dataBean.getPriceValue());
    }

    private void orderDetailAdapter() {
        numberWeightSizeRv.setLayoutManager(new LinearLayoutManager(this));
        orderDetailAdapter = new CommonAdapter<TborderInfoBean.TbOrderBean.OrderDetailListBean>(this, R.layout.item_order_detail, orderDetailList) {
            @Override
            protected void convert(ViewHolder holder, TborderInfoBean.TbOrderBean.OrderDetailListBean orderDetailListBean, int position) {
                holder.setText(R.id.number_tv, orderDetailListBean.getNumber() + "");
                holder.setText(R.id.weight_kg_tv, orderDetailListBean.getWeight() + "");
                holder.setText(R.id.size_cm_tv, orderDetailListBean.getLength() + "x" + orderDetailListBean.getWidth() + "x" + orderDetailListBean.getHeight());
            }
        };
        numberWeightSizeRv.setAdapter(orderDetailAdapter);
    }

    @OnClick({R.id.title_close_order,R.id.title_return_img, R.id.supplier_quotation_btn, R.id.fill_in_the_clearing_information_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.supplier_quotation_btn:
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Supplier_QuotationActivity.class);
                break;
            case R.id.fill_in_the_clearing_information_btn:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Reply_ProblemActivity.class,"channel_quiz");
                break;
            case R.id.title_close_order:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Close_The_OrderActivity.class,"close_order_details");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
