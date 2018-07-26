package com.example.ysww.internationalfreightforwarding.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.model.FlieUploadBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.FlieUploadView;
import com.example.ysww.internationalfreightforwarding.net.view.NewOrderView;
import com.example.ysww.internationalfreightforwarding.presenter.FlieUploadPresenter;
import com.example.ysww.internationalfreightforwarding.presenter.NewOrderPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 选填信息-2
 */
public class IFF_Select_Information2Activity extends AppCompatActivity implements NewOrderView, FlieUploadView {

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
    @InjectView(R.id.volume_size_et)
    EditText volumeSizeEt;
    @InjectView(R.id.selection_receiving_address_et)
    EditText selectionReceivingAddressEt;
    @InjectView(R.id.money_type)
    EditText moneyType;
    private AddOrderBean addOrderBean;
    private NewOrderPresenter newOrderPresenter = new NewOrderPresenter();
    private FlieUploadPresenter flieUploadPresenter = new FlieUploadPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private List<AddOrderBean.TbOrderFileEntity> tbOrderFileEntityList;
    private int flieUploadIndex = 0, flieUploadCount = 0;
    private String[] photos, enclosures;

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

    /**
     * 上传文件
     */
    private void flieUploadMethod() {

        new OkgoHttpResolve(this);
        flieUploadPresenter.attach(this);

        String filePhoto = addOrderBean.getFilePhoto();
        String fileEnclosure = addOrderBean.getFileEnclosure();

        photos = fileUrl(filePhoto);
        flieUploadCount = photos.length;
        for (int i = 0; i < photos.length; i++) {
            String fileUrl = photos[i].substring(1, photos[i].length() - 1);
            flieUploadPresenter.flieUploadResult("30", fileUrl, this, lazyLoadProgressDialog);
        }

        enclosures = fileUrl(fileEnclosure);
        if (fileEnclosure.length()>2) {
            flieUploadCount = photos.length + enclosures.length;
            for (int i = 0; i < enclosures.length; i++) {
                String fileUrl = enclosures[i].substring(1, enclosures[i].length() - 1);
                flieUploadPresenter.flieUploadResult("10", fileUrl, this, lazyLoadProgressDialog);
            }
        }
    }

    private String[] fileUrl(String url) {
        url = url.substring(1, url.length() - 1);
        String[] split = url.split(",");
        return split;
    }

    private void initViews() {
        iffTitleTv.setText(R.string.select_information2);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        SystemUtils.getInstance(this).setPricePoint(valueOfGoodsEt);
        SystemUtils.getInstance(this).setPricePoint(offerEt);
        SystemUtils.getInstance(this).setPricePoint(volumeSizeEt);
        tbOrderFileEntityList = new ArrayList<>();
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
                String quote = offerEt.getText().toString();
                if (!TextUtils.isEmpty(quote)) {
                    addOrderBean.setQuote(Double.parseDouble(quote));
                }
                addOrderBean.setVolumeSize(volumeSizeEt.getText().toString());
                addOrderBean.setMoneyType(moneyType.getText().toString());
                addOrderBean.setSelectionReceivingAddress(selectionReceivingAddressEt.getText().toString());
                lazyLoadProgressDialog.setMessage("上传图片中...");
                SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                flieUploadMethod();
                break;
        }
    }

    @Override
    public void onNewOrderFinish(Object o) {
        SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "copy");
        MyActivityManager.getInstance().finishAllActivity();
    }

    @Override
    public void onFlieUploadFinish(Object o) {
        FlieUploadBean flieUploadBean = (FlieUploadBean) o;
        FlieUploadBean.DataBean data = flieUploadBean.getData();
        flieUploadIndex++;
        for (int i = 0; i < photos.length; i++) {
            if (photos[i].contains(data.getName())) {
                tbOrderFileEntityList.add(new AddOrderBean.TbOrderFileEntity(data.getName(), data.getUrl(), "30"));
            }
        }
        if (enclosures.length > 0) {
            for (int i = 0; i < enclosures.length; i++) {
                if (enclosures[i].contains(data.getName())) {
                    tbOrderFileEntityList.add(new AddOrderBean.TbOrderFileEntity(data.getName(), data.getUrl(), "10"));
                }
            }
        }
        if (flieUploadIndex == flieUploadCount) {
            addOrderBean.setFileList(tbOrderFileEntityList);
            lazyLoadProgressDialog.setMessage("加载中...");
            newOrderMethod();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        newOrderPresenter.dettach();
        flieUploadPresenter.dettach();
    }


}
