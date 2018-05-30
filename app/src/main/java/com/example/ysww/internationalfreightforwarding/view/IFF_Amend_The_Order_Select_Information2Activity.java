package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.ImageRadioButton;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 修改订单 选填信息-2
 */
public class IFF_Amend_The_Order_Select_Information2Activity extends Activity {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.manufacturer_et)
    EditText manufacturerEt;
    @InjectView(R.id.license_key_et)
    EditText licenseKeyEt;
    @InjectView(R.id.approval_number_et)
    EditText approvalNumberEt;
    @InjectView(R.id.exempted_nature_et)
    EditText exemptedNatureEt;
    @InjectView(R.id.tariff_payment_rb1)
    ImageRadioButton tariffPaymentRb1;
    @InjectView(R.id.tariff_payment_rg)
    RadioGroup tariffPaymentRg;
    @InjectView(R.id.delivery_mode_rb1)
    ImageRadioButton deliveryModeRb1;
    @InjectView(R.id.delivery_mode_rg)
    RadioGroup deliveryModeRg;
    @InjectView(R.id.freight_single_number_et)
    EditText freightSingleNumberEt;
    @InjectView(R.id.receiving_sending_time_et)
    EditText receivingSendingTimeEt;
    @InjectView(R.id.freight_et)
    EditText freightEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__amend__the__order__select__information2);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        initViews();
    }

    private void initViews() {
        iffTitleTv.setText(R.string.select_information2);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        tariffPaymentRb1.setChecked(true);
        deliveryModeRb1.setChecked(true);
    }
    @OnClick({R.id.title_return_img, R.id.complete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.complete_btn:
                SystemUtils.getInstance(this).returnHomeFinishAll();
                break;
        }
    }
}
