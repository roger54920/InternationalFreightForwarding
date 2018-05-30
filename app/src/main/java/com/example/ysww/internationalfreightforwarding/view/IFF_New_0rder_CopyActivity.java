package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.model.GetAllCountryBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetAllCountryView;
import com.example.ysww.internationalfreightforwarding.presenter.GetAllCountryPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
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
 * 新订单-copy
 */
public class IFF_New_0rder_CopyActivity extends Activity implements GetAllCountryView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.transport_to_country_region_et)
    EditText transportToCountryRegionEt;
    @InjectView(R.id.zip_code_et)
    EditText zipCodeEt;
    @InjectView(R.id.destination_city_et)
    EditText destinationCityEt;
    @InjectView(R.id.receiving_address_et)
    EditText receivingAddressEt;
    @InjectView(R.id.next_step_btn)
    Button nextStepBtn;
    private String transportToCountryRegion, zipCode, destinationCity, receivingAddress;
    private AddOrderBean addOrderBean;
    private GetAllCountryPresenter getAllCountryPresenter = new GetAllCountryPresenter();//获取国家
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private List<GetAllCountryBean.DataBean> getAllCountryList;//国家列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__new_0rder__copy);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        EventBus.getDefault().register(this);
        initViews();
    }

    /**
     * 获取国家
     */
    private void getAllCountry() {
        new OkgoHttpResolve(this);
        getAllCountryPresenter.attach(this);
        getAllCountryPresenter.getAllCountryResult(this, lazyLoadProgressDialog);
    }

    /**
     * EventBus的接收方法
     *
     * @param addOrderBean 传递类
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNewOrderEvent(AddOrderBean addOrderBean) {
        this.addOrderBean = addOrderBean;
    }

    private void initViews() {
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        EventBus.getDefault().toString();
        iffTitleTv.setText(R.string.order_information);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        transportToCountryRegionEt.addTextChangedListener(new TextWatcher() {
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
        zipCodeEt.addTextChangedListener(new TextWatcher() {
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
        destinationCityEt.addTextChangedListener(new TextWatcher() {
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
    }

    @OnClick({R.id.title_return_img, R.id.transport_to_country_region_btn, R.id.FBA_warehouse_btn, R.id.next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.transport_to_country_region_btn:
                SystemUtils.getInstance(this).softKeyboard(view);
                if (getAllCountryList != null && getAllCountryList.size() > 0) {
                    showPickerView("transport_to_country_region");
                } else {
                    getAllCountry();
                    SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
                }
                break;
            case R.id.FBA_warehouse_btn:
                SystemUtils.getInstance(this).softKeyboard(view);
                showPickerView("FBA_warehouse");
                break;
            case R.id.next_step_btn:
                addOrderBean.setDestinationCountry(transportToCountryRegion);
                addOrderBean.setPostcode(zipCode);
                addOrderBean.setDestinationCity(destinationCity);
                addOrderBean.setDeliveryAddress(receivingAddress);
                EventBus.getDefault().postSticky(addOrderBean);
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Clearing_InformationActivity.class);
                break;
        }
    }

    private void isEditText() {
        transportToCountryRegion = transportToCountryRegionEt.getText().toString();
        zipCode = zipCodeEt.getText().toString();
        destinationCity = destinationCityEt.getText().toString();
        receivingAddress = receivingAddressEt.getText().toString();
        if (!TextUtils.isEmpty(transportToCountryRegion) && !TextUtils.isEmpty(zipCode) && !TextUtils.isEmpty(destinationCity) && !TextUtils.isEmpty(receivingAddress)) {
            nextStepBtn.setEnabled(true);
        } else {
            nextStepBtn.setEnabled(false);
        }
    }

    private void showPickerView(final String type) {
        final ArrayList<String> list = new ArrayList<>();
        if (type.equals("transport_to_country_region")) {
            for (int i = 0; i < getAllCountryList.size(); i++) {
                list.add(getAllCountryList.get(i).getName());
            }
        } else if (type.equals("FBA_warehouse")) {
            list.add("男");
            list.add("女");
            list.add("女1");
            list.add("女2");
            list.add("女3");
            // 设置数据
        }
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String param = list.get(options1);
                if (type.equals("transport_to_country_region")) {
                    transportToCountryRegionEt.setText(param);
                    transportToCountryRegionEt.setSelection(param.length());
                } else if (type.equals("FBA_warehouse")) {
                    zipCodeEt.setText(param);
                    zipCodeEt.setSelection(param.length());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        getAllCountryPresenter.dettach();
    }

    @Override
    public void onGetAllCountryFinish(Object o) {
        GetAllCountryBean getAllCountryBean = (GetAllCountryBean) o;
        getAllCountryList = getAllCountryBean.getData();
        showPickerView("transport_to_country_region");
    }
}
