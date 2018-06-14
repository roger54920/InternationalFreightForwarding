package com.example.ysww.internationalfreightforwarding.view;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.ImageRadioButton;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 新订单
 */
public class IFF_New_0rderActivity extends AppCompatActivity implements OnDateSetListener {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.order_number_tv)
    TextView orderNumberTv;
    @InjectView(R.id.type_of_shipping_rg)
    RadioGroup typeOfShippingRg;
    @InjectView(R.id.customs_clearance_method_rg)
    RadioGroup customsClearanceMethodRg;
    @InjectView(R.id.service_mode_rg)
    RadioGroup serviceModeRg;
    @InjectView(R.id.forwarding_unit_et)
    EditText forwardingUnitEt;
    @InjectView(R.id.delivery_address_et)
    EditText deliveryAddressEt;
    @InjectView(R.id.port_of_export_et)
    EditText portOfExportEt;
    @InjectView(R.id.export_date_et)
    EditText exportDateEt;
    @InjectView(R.id.product_name_et)
    EditText productNameEt;
    @InjectView(R.id.insurance_rg)
    RadioGroup insuranceRg;
    @InjectView(R.id.next_step_btn)
    Button nextStepBtn;
    @InjectView(R.id.air_transport_rb)
    ImageRadioButton airTransportRb;
    @InjectView(R.id.customs_clearance_method_rb1)
    ImageRadioButton customsClearanceMethodRb1;
    @InjectView(R.id.service_mode_rb1)
    ImageRadioButton serviceModeRb1;
    @InjectView(R.id.insurance_rb1)
    ImageRadioButton insuranceRb1;
    @InjectView(R.id.new_order_ll)
    LinearLayout newOrderLl;
    @InjectView(R.id.ocean_shipping_rb)
    ImageRadioButton oceanShippingRb;
    @InjectView(R.id.other_rb)
    ImageRadioButton otherRb;
    @InjectView(R.id.other_et)
    EditText otherEt;
    @InjectView(R.id.other_rl)
    RelativeLayout otherRl;

    private String forwardingUnit, deliveryAddress, portOfExport, exportDate, productName;
    private AddOrderBean addOrderBean;
    private TimePickerDialog mDialogYearMonthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__new_0rder);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        initViews();
    }

    private void initViews() {
        iffTitleTv.setText(R.string.order_information);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        if (addOrderBean == null) {
            addOrderBean = new AddOrderBean();
        }

        airTransportRb.setChecked(true);
        addOrderBean.setTransType(airTransportRb.getText().toString());
        customsClearanceMethodRb1.setChecked(true);
        addOrderBean.setCleanCustomsType(customsClearanceMethodRb1.getText().toString());
        serviceModeRb1.setChecked(true);
        addOrderBean.setTransDemand(serviceModeRb1.getText().toString());
        insuranceRb1.setChecked(true);

        forwardingUnitEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        deliveryAddressEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        portOfExportEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        exportDateEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        productNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        typeOfShippingRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.air_transport_rb:
                        otherRb.setVisibility(View.VISIBLE);
                        otherRl.setVisibility(View.GONE);
                        break;
                    case R.id.ocean_shipping_rb:
                        otherRb.setVisibility(View.VISIBLE);
                        otherRl.setVisibility(View.GONE);
                        break;
                    case R.id.other_rb:
                        otherRb.setVisibility(View.GONE);
                        otherRl.setVisibility(View.VISIBLE);
                        break;
                }
                RadioButton rbId = (RadioButton) findViewById(typeOfShippingRg.getCheckedRadioButtonId());
                addOrderBean.setTransType(rbId.getText().toString());
            }
        });
        customsClearanceMethodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(customsClearanceMethodRg.getCheckedRadioButtonId());
                addOrderBean.setCleanCustomsType(rbId.getText().toString());

            }
        });
        serviceModeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(serviceModeRg.getCheckedRadioButtonId());
                addOrderBean.setTransDemand(rbId.getText().toString());
            }
        });
        insuranceRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(serviceModeRg.getCheckedRadioButtonId());
                addOrderBean.setIslnsurance(rbId.getTag().toString());
            }
        });
    }

    private void isEditText() {
        forwardingUnit = forwardingUnitEt.getText().toString();
        deliveryAddress = deliveryAddressEt.getText().toString();
        portOfExport = portOfExportEt.getText().toString();
        exportDate = exportDateEt.getText().toString();
        productName = productNameEt.getText().toString();
        if (!TextUtils.isEmpty(forwardingUnit) && !TextUtils.isEmpty(deliveryAddress) && !TextUtils.isEmpty(portOfExport) && !TextUtils.isEmpty(exportDate) && !TextUtils.isEmpty(productName)) {
            nextStepBtn.setEnabled(true);
        } else {
            nextStepBtn.setEnabled(false);
        }
    }

    @OnClick({R.id.title_return_img, R.id.forwarding_unit_btn, R.id.export_date_btn, R.id.next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.forwarding_unit_btn:
                SystemUtils.getInstance(this).softKeyboard(view);
                showPickerView("forwarding_unit");
                break;
            case R.id.export_date_btn:
                SystemUtils.getInstance(this).softKeyboard(view);
                datePickers();
                break;
            case R.id.next_step_btn:
                int userId = getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userId", 0);
                addOrderBean.setMarketUserId(userId);
                addOrderBean.setForwardingUnit(forwardingUnit);
                addOrderBean.setSourceAddress(deliveryAddress);
                addOrderBean.setExport(portOfExport);
                addOrderBean.setDepartureDate(exportDate);
                addOrderBean.setBrand(productName);
                if(otherRb.isChecked()==true){
                    String other = otherEt.getText().toString();
                    if(TextUtils.isEmpty(other)){
                        addOrderBean.setTransType("其他");
                    }else{
                        addOrderBean.setTransType(other);
                    }
                }
                EventBus.getDefault().postSticky(addOrderBean);
                SystemUtils.getInstance(this).noReferenceIntent(IFF_New_0rder_CopyActivity.class);
                break;
        }
    }

    private void showPickerView(final String type) {
        final ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("女1");
        list.add("女2");
        list.add("女3");
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String param = list.get(options1);
                if (type.equals("forwarding_unit")) {
                    forwardingUnitEt.setText(param);
                    forwardingUnitEt.setSelection(param.length());
                } else if (type.equals("export_date")) {
                    exportDateEt.setText(param);
                    exportDateEt.setSelection(param.length());
                }

            }
        })
                .setDividerColor(getResources().getColor(R.color.iff_F08430))
                .setSubmitText(getString(R.string.complete))
                .setCancelText(" ")
                .setCancelColor(getResources().getColor(R.color.iff_F08430))
                .setSubCalSize(15)
                .setTitleSize(30)
                .setLineSpacingMultiplier(2f)
                .setTitleBgColor(getResources().getColor(R.color.iff_F08430))
                .setSubmitColor(getResources().getColor(R.color.iff_FFFFFF))
                .setTextColorCenter(getResources().getColor(R.color.iff_333333)) //设置选中项文字颜色
                .setTextColorOut(getResources().getColor(R.color.iff_999999))
                .setContentTextSize(16)//设置文字大小
                .setOutSideCancelable(true)// default is true
                .build();


        pvOptions.setPicker(list);//条件选择器

        pvOptions.show();
    }

    /**
     * 日期选择器
     */
    private void datePickers() {
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择日期")
                .setWheelItemTextSize(15)
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.iff_333333))
                .setThemeColor(getResources().getColor(R.color.iff_F08430))
                .build();
        mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        exportDateEt.setText(getDateToString(millseconds));
    }

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
