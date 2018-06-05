package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.custom.MyScrollview;
import com.example.ysww.internationalfreightforwarding.model.TborderInfoBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.TborderInfoView;
import com.example.ysww.internationalfreightforwarding.presenter.TborderInfoPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class IFF_Order_DetailsActivity extends Activity implements TborderInfoView {

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
    @InjectView(R.id.my_scroll_view)
    MyScrollview myScrollView;
    @InjectView(R.id.supplier_quotation_btn)
    Button supplierQuotationBtn;
    @InjectView(R.id.fill_in_the_clearing_information_btn)
    Button fillInTheClearingInformationBtn;
    @InjectView(R.id.information_supplement_reply_problem_btn)
    Button informationSupplementReplyProblemBtn;
    private int orderStatus;

    private TborderInfoPresenter tborderInfoPresenter = new TborderInfoPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载

    private CommonAdapter<TborderInfoBean.TbOrderBean.OrderDetailListBean> orderDetailAdapter;
    private List<TborderInfoBean.TbOrderBean.OrderDetailListBean> orderDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__order__details);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        myScrollView.smoothScrollTo(0, 0);
        orderStatus = getIntent().getIntExtra("orderStatus", 0);
        switch (orderStatus) {
            case 1: //未报价
                break;
            case 2://已报价
                bottomLl.setVisibility(View.VISIBLE);
                supplierQuotationBtn.setVisibility(View.VISIBLE);
                fillInTheClearingInformationBtn.setVisibility(View.VISIBLE);
                break;
            case 3://已确认
                bottomLl.setVisibility(View.VISIBLE);
                informationSupplementReplyProblemBtn.setVisibility(View.VISIBLE);
                break;
            case 4://已发货
                bottomLl.setVisibility(View.VISIBLE);
                informationSupplementReplyProblemBtn.setVisibility(View.VISIBLE);
                informationSupplementReplyProblemBtn.setText(R.string.information_supplement);
                break;
            case 5://已关闭
                break;
            default:
                break;
        }
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        tborderInfoResultMethod();
        iffTitleTv.setText(R.string.order_details);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        titleCloseOrder.setVisibility(View.VISIBLE);
    }

    /**
     * 获取订单详情接口
     */
    private void tborderInfoResultMethod() {
        new OkgoHttpResolve(this);
        tborderInfoPresenter.attach(this);
        tborderInfoPresenter.tborderInfoResult(getIntent().getStringExtra("orderNo"), this, lazyLoadProgressDialog);
    }

    @OnClick({R.id.information_supplement_reply_problem_btn, R.id.title_close_order, R.id.title_return_img, R.id.supplier_quotation_btn, R.id.fill_in_the_clearing_information_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.supplier_quotation_btn:
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Supplier_QuotationActivity.class);
                break;
            case R.id.fill_in_the_clearing_information_btn:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Reply_ProblemActivity.class, "channel_quiz");
                break;
            case R.id.information_supplement_reply_problem_btn:
                orderStatus = getIntent().getIntExtra("orderStatus", 0);
                if (orderStatus == 4) {
                    SystemUtils.getInstance(this).noReferenceIntent(IFF_Collect_Send_InformationActivity.class);
                } else if (orderStatus == 3) {
                    SystemUtils.getInstance(this).orderNoIntent(IFF_Quotation_InformationActivity.class, getIntent().getStringExtra("orderNo"));
                }
                break;
            case R.id.title_close_order:
                SystemUtils.getInstance(this).referenceSourcePageOrderNoChanneldealerIntent(IFF_Close_The_OrderActivity.class, "close_order_details",getIntent().getStringExtra("orderNo"),"");
                break;
        }
    }

    @Override
    public void onTborderInfoFinish(Object o) {
        TborderInfoBean tborderInfoBean = (TborderInfoBean) o;
        TborderInfoBean.TbOrderBean dataBean = tborderInfoBean.getTbOrder();
        if (dataBean != null) {
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

            totalNumberTv.setText(dataBean.getTotalNumber() + "");
            orderDetailList = dataBean.getOrderDetailList();
            double totalWeight = 0;
            for (int i = 0; i < orderDetailList.size(); i++) {
                totalWeight += orderDetailList.get(i).getWeight();
            }
            totalWeightKgTv.setText(totalWeight + "");
            orderDetailAdapter();

            volumeWeightTv.setText(dataBean.getVolumeSize());
            productNameTv.setText(dataBean.getBrand());
            valueOfGoodsTv.setText(dataBean.getPriceValue());
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        tborderInfoPresenter.dettach();
    }
}
