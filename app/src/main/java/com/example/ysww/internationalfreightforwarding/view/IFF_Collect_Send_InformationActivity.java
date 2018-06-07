package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.DeliverOrderView;
import com.example.ysww.internationalfreightforwarding.presenter.DeliverOrderPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 信息补录
 */
public class IFF_Collect_Send_InformationActivity extends Activity implements DeliverOrderView {

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
    @InjectView(R.id.complete_btn)
    Button completeBtn;

    private DeliverOrderPresenter deliverOrderPresenter = new DeliverOrderPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private String freightSingleNumber, receivingSendingTime, freight, receivingAddress;

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
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        titleCloseOrder.setVisibility(View.VISIBLE);
        receivingSendingTimeEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        SystemUtils.getInstance(this).setPricePoint(freightEt);
        if (Constants.SOURCE_PAGE.equals("quotation_information")) {
            titleReturnImg.setVisibility(View.GONE);
        }
        iffTitleTv.setText(R.string.information_supplement);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);

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
     * 订单发货确认接口 信息补录
     */
    private void deliverOrderMethod() {
        new OkgoHttpResolve(this);
        deliverOrderPresenter.attach(this);
        deliverOrderPresenter.deliverOrderResult
                ("{\"orderId\":\"" + getIntent().getStringExtra("orderNo") + "\"," +
                                "\"freight\":\"" + freightSingleNumberEt.getText().toString() + "\"," +
                                "\"deliveryAddress\":\"" + receivingAddressEt.getText().toString() + "\"," +
                                "\"carriage\":" + Double.parseDouble(freightEt.getText().toString()) + "," +
                                "\"postingTime\":" + Integer.parseInt(receivingSendingTimeEt.getText().toString()) + "}"
                        , this, lazyLoadProgressDialog);
    }

    @OnClick({R.id.title_close_order, R.id.title_return_img, R.id.complete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.complete_btn:
                SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                deliverOrderMethod();
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
            completeBtn.setEnabled(true);
        } else {
            completeBtn.setEnabled(false);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Constants.SOURCE_PAGE.equals("quotation_information")) {
            SystemUtils.getInstance(this).returnHomeFinishAll();
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDeliverOrderFinish(Object o) {
        SystemUtils.getInstance(this).returnHomeFinishAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        deliverOrderPresenter.dettach();
    }
}

