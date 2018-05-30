package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 修改订单（订单详情）
 */
public class IFF_Amend_The_OrderActivity extends Activity {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
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
    @InjectView(R.id.number_tv)
    TextView numberTv;
    @InjectView(R.id.weight_kg_tv)
    TextView weightKgTv;
    @InjectView(R.id.size_cm_tv)
    TextView sizeCmTv;
    @InjectView(R.id.volume_weight_tv)
    TextView volumeWeightTv;
    @InjectView(R.id.product_name_tv)
    TextView productNameTv;
    @InjectView(R.id.value_of_goods_tv)
    TextView valueOfGoodsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__amend__the__order);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        initViews();
    }

    private void initViews() {
        iffTitleTv.setText(R.string.order_information);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
    }

    @OnClick({R.id.title_return_img, R.id.deliver_goods_btn, R.id.modify_btn, R.id.remarks_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                reutrnSupplierQuotation();
                break;
            case R.id.deliver_goods_btn:
                SystemUtils.getInstance(this).returnHomeFinishAll();
                break;
            case R.id.modify_btn:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_New_0rderActivity.class,"modify");
                break;
            case R.id.remarks_btn:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_InformationActivity.class,"remarks");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        reutrnSupplierQuotation();
        return super.onKeyDown(keyCode, event);

    }
    private void reutrnSupplierQuotation(){
        SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Supplier_QuotationActivity.class,"survey");
        MyActivityManager.getInstance().finishAllActivity();
    }
}
