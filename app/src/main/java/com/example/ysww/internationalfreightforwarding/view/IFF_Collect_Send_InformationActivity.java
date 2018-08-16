package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.TborderInfoBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.OrderSupplementView;
import com.example.ysww.internationalfreightforwarding.net.view.TborderInfoView;
import com.example.ysww.internationalfreightforwarding.presenter.OrderSupplementPresenter;
import com.example.ysww.internationalfreightforwarding.presenter.TborderInfoPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 信息补录
 */
public class IFF_Collect_Send_InformationActivity extends Activity implements TborderInfoView,OrderSupplementView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.freight_single_number_et)
    EditText freightSingleNumberEt;
    @InjectView(R.id.receiving_sending_time_et)
    EditText receivingSendingTimeEt;
    @InjectView(R.id.freight_et)
    EditText freightEt;
    @InjectView(R.id.receiving_address_et)
    EditText receivingAddressEt;
    @InjectView(R.id.title_return_img)
    ImageView titleReturnImg;
    @InjectView(R.id.title_close_order)
    ImageView titleCloseOrder;
    @InjectView(R.id.temporary_storage_btn)
    Button temporaryStorageBtn;
    @InjectView(R.id.end_btn)
    Button endBtn;
    @InjectView(R.id.look_at_logistics_btn)
    Button lookAtLogisticsBtn;

    private OrderSupplementPresenter orderSupplementPresenter = new OrderSupplementPresenter();
    private TborderInfoPresenter tborderInfoPresenter = new TborderInfoPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private String freightSingleNumber, receivingSendingTime, freight, receivingAddress;
    private int orderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__collect__send__information);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        titleCloseOrder.setVisibility(View.VISIBLE);
        titleReturnImg.setVisibility(View.VISIBLE);
        receivingSendingTimeEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        SystemUtils.getInstance(this).setPricePoint(freightEt);
        iffTitleTv.setText(R.string.information_supplement);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        orderStatus = getIntent().getIntExtra("orderStauts", 0);


        tborderInfoResultMethod();
        if (orderStatus == 4) {
            lookAtLogisticsBtn.setEnabled(false);
        } else {
            lookAtLogisticsBtn.setEnabled(true);
        }
        receivingAddressEt.addTextChangedListener(new TextWatcher() {
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
        freightEt.addTextChangedListener(new TextWatcher() {
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
        receivingSendingTimeEt.addTextChangedListener(new TextWatcher() {
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
        freightSingleNumberEt.addTextChangedListener(new TextWatcher() {
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
    }
    /**
     * 获取订单详情接口
     */
    private void tborderInfoResultMethod() {
        new OkgoHttpResolve(this);
        tborderInfoPresenter.attach(this);
        tborderInfoPresenter.tborderInfoResult(getIntent().getStringExtra("orderId"), this, lazyLoadProgressDialog);
    }
    /**
     * 补录信息接口
     */
    private void orderSupplementMethod(String flag) {
        new OkgoHttpResolve(this);
        orderSupplementPresenter.attach(this);
        orderSupplementPresenter.orderSupplementResult
                ("{\"orderId\":\"" + getIntent().getStringExtra("orderId") + "\"," +
                                "\"freight\":\"" + freightSingleNumberEt.getText().toString() + "\"," +
                                "\"flag\":\"" + flag + "\"," +
                                "\"selectionReceivingAddress\":\"" + receivingAddressEt.getText().toString() + "\"," +
                                "\"carriage\":" + Double.parseDouble(freightEt.getText().toString()) + "," +
                                "\"postingTime\":" + Integer.parseInt(receivingSendingTimeEt.getText().toString()) + "}"
                        , this, lazyLoadProgressDialog);
    }

    @OnClick({R.id.look_at_logistics_btn, R.id.end_btn, R.id.temporary_storage_btn, R.id.title_close_order, R.id.title_return_img})
    public void onViewClicked(View view) {
        orderStatus = getIntent().getIntExtra("orderStauts", 0);
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.temporary_storage_btn:
                SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                orderSupplementMethod("1");
                break;
            case R.id.end_btn:
                SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                orderSupplementMethod("2");
                break;
            case R.id.look_at_logistics_btn:
                SystemUtils.getInstance(IFF_Collect_Send_InformationActivity.this).orderStautsorderIdChannelUserIdIntent(IFF_Details_Order_TransportationActivity.class, orderStatus, getIntent().getStringExtra("orderId"), "");
                break;
            case R.id.title_close_order:
                SystemUtils.getInstance(this).returnHomeFinishAll();
                break;
        }
    }

    private void isEditText() {
        freightSingleNumber = freightSingleNumberEt.getText().toString();
        receivingSendingTime = receivingSendingTimeEt.getText().toString();
        freight = freightEt.getText().toString();
        receivingAddress = receivingAddressEt.getText().toString();
        if (!TextUtils.isEmpty(freight) && !TextUtils.isEmpty(receivingAddress) && !TextUtils.isEmpty(freightSingleNumber) && !TextUtils.isEmpty(receivingSendingTime)) {
            temporaryStorageBtn.setEnabled(true);
            if (orderStatus == 6) {
                endBtn.setEnabled(true);
            } else {
                endBtn.setEnabled(false);
            }
        } else {
            temporaryStorageBtn.setEnabled(false);
            endBtn.setEnabled(false);
        }
    }

    @Override
    public void onDeliverOrderFinish(Object o) {
        if(orderStatus==5){
            SystemUtils.getInstance(this).returnHomeFinishAll();
        }else{
            SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "history_order");
        }
    }
    @Override
    public void onTborderInfoFinish(Object o) {
        TborderInfoBean tborderInfoBean = (TborderInfoBean) o;
        TborderInfoBean.TbOrderBean tbOrder = tborderInfoBean.getTbOrder();
        String freight = tbOrder.getFreight();
        freightSingleNumberEt.setText(freight);
        freightSingleNumberEt.setSelection(freight.length());
        receivingAddressEt.setText(tbOrder.getSelectionReceivingAddress());
        freightEt.setText(tbOrder.getCarriage()+"");
        receivingSendingTimeEt.setText(tbOrder.getPostingTime()+"");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        orderSupplementPresenter.dettach();
        tborderInfoPresenter.dettach();
    }


}

