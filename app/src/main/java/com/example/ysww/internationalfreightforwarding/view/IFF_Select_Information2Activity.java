package com.example.ysww.internationalfreightforwarding.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.NewOrderView;
import com.example.ysww.internationalfreightforwarding.presenter.NewOrderPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 选填信息-2
 */
public class IFF_Select_Information2Activity extends AppCompatActivity implements NewOrderView {

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
    @InjectView(R.id.value_of_goods_et)
    EditText valueOfGoodsEt;
    @InjectView(R.id.offer_et)
    EditText offerEt;
    @InjectView(R.id.transportation_time_et)
    EditText transportationTimeEt;
    @InjectView(R.id.volume_size_et)
    EditText volumeSizeEt;
    @InjectView(R.id.selection_receiving_address_et)
    EditText selectionReceivingAddressEt;
    private AddOrderBean addOrderBean;
    private NewOrderPresenter newOrderPresenter = new NewOrderPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__select__information2);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        EventBus.getDefault().register(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNewOrderEvent(AddOrderBean addOrderBean) {
        this.addOrderBean = addOrderBean;
    }

    /**
     * 新订单
     */
    private void newOrderMethod() {
        Gson gson = new Gson();
        String addOrderStr = gson.toJson(addOrderBean);
        new OkgoHttpResolve(this);
        newOrderPresenter.attach(this);
        newOrderPresenter.newOrderResult(addOrderStr, this, lazyLoadProgressDialog);
    }

    private void initViews() {
        iffTitleTv.setText(R.string.select_information2);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        SystemUtils.getInstance(this).setPricePoint(valueOfGoodsEt);
        SystemUtils.getInstance(this).setPricePoint(offerEt);
        transportationTimeEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        SystemUtils.getInstance(this).setPricePoint(volumeSizeEt);
    }

    @OnClick({R.id.title_return_img, R.id.complete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.complete_btn:
                addOrderBean.setManufacturer(manufacturerEt.getText().toString());
                addOrderBean.setLicenseKey(licenseKeyEt.getText().toString());
                addOrderBean.setApprovalNumber(approvalNumberEt.getText().toString());
                addOrderBean.setExemptionType(exemptedNatureEt.getText().toString());
                addOrderBean.setPriceValue(valueOfGoodsEt.getText().toString());
                addOrderBean.setQuote(offerEt.getText().toString());
                addOrderBean.setTransportationTime(transportationTimeEt.getText().toString());
                addOrderBean.setVolumeSize(volumeSizeEt.getText().toString());
                addOrderBean.setSelectionReceivingAddress(selectionReceivingAddressEt.getText().toString());
                SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                newOrderMethod();
                break;
        }
    }

    @Override
    public void onNewOrderFinish(Object o) {
        SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "copy");
        MyActivityManager.getInstance().finishAllActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        newOrderPresenter.dettach();
    }


}
