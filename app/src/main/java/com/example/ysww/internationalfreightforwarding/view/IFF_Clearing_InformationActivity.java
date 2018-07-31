package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.ImageRadioButton;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.model.DetailsOfGoodsBean;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 结算信息
 */
public class IFF_Clearing_InformationActivity extends Activity {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.number_et)
    EditText numberEt;
    @InjectView(R.id.next_step_btn)
    Button nextStepBtn;
    @InjectView(R.id.payment_method_rb1)
    ImageRadioButton paymentMethodRb1;
    @InjectView(R.id.payment_method_rg)
    RadioGroup paymentMethodRg;
    @InjectView(R.id.details_of_goods_rv)
    RecyclerView detailsOfGoodsRv;
    @InjectView(R.id.weight_et)
    EditText weightEt;
    @InjectView(R.id.total_number)
    TextView totalNumber;
    @InjectView(R.id.total_weight)
    TextView totalWeight;
    @InjectView(R.id.inch_length_et)
    EditText inchLengthEt;
    @InjectView(R.id.inch_width_et)
    EditText inchWidthEt;
    @InjectView(R.id.inch_height_et)
    EditText inchHeightEt;
    @InjectView(R.id.import_export_power_rg)
    RadioGroup importExportPowerRg;
    @InjectView(R.id.import_export_power_rb1)
    ImageRadioButton importExportPowerRb1;
    @InjectView(R.id.islnsurance_rb1)
    ImageRadioButton islnsuranceRb1;
    @InjectView(R.id.islnsurance_rg)
    RadioGroup islnsuranceRg;
    private CommonAdapter<DetailsOfGoodsBean> detailsOfGoodsAdapter;
    private List<DetailsOfGoodsBean> detailsOfGoodsList;
    private String number, weight, inch_length, inch_width, inch_height;
    private AddOrderBean addOrderBean = new AddOrderBean();
    private int total_number;
    private double total_weight;
    private LinearLayoutManager linearLayoutManager;
    private int stateEt = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__clearing__information);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        EventBus.getDefault().register(this);
        initViews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNewOrderEvent(AddOrderBean addOrderBean) {
        this.addOrderBean = addOrderBean;
    }

    private void initViews() {
        iffTitleTv.setText(R.string.clearing_information);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        paymentMethodRb1.setChecked(true);
        addOrderBean.setPayType(paymentMethodRb1.getText().toString());
        numberEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchLengthEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchWidthEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchHeightEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        SystemUtils.getInstance(this).setPricePoint(weightEt);
        SystemUtils.getInstance(this).setPricePointText(totalWeight);
        importExportPowerRb1.setChecked(true);
        addOrderBean.setExportPower("1");
        islnsuranceRb1.setChecked(true);
        addOrderBean.setIslnsurance("1");
        islnsuranceRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(islnsuranceRg.getCheckedRadioButtonId());
                String islnsurance = rbId.getText().toString();
                if (islnsurance.equals("是")) {
                    addOrderBean.setIslnsurance("1");
                } else {
                    addOrderBean.setIslnsurance("0");
                }
            }
        });
        importExportPowerRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(importExportPowerRg.getCheckedRadioButtonId());
                String importExportPower = rbId.getText().toString();
                if (importExportPower.equals("是")) {
                    addOrderBean.setExportPower("1");
                } else {
                    addOrderBean.setExportPower("0");
                }
            }
        });
        paymentMethodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(paymentMethodRg.getCheckedRadioButtonId());
                addOrderBean.setPayType(rbId.getText().toString());

            }
        });

        detailsOfGoodsList = new ArrayList<>();
        isEditText();
    }

    private void initAdapter() {
        if (detailsOfGoodsList.size() > 0) {
            detailsOfGoodsRv.setVisibility(View.VISIBLE);
            linearLayoutManager = new LinearLayoutManager(this);
            detailsOfGoodsRv.setLayoutManager(linearLayoutManager);
            detailsOfGoodsAdapter = new CommonAdapter<DetailsOfGoodsBean>(this, R.layout.item_details_of_goods, detailsOfGoodsList) {
                @Override
                protected void convert(ViewHolder holder, final DetailsOfGoodsBean detailsOfGoodsBean, final int position) {
                    holder.setVisible(R.id.ibtn_number_weight_inch, false);
                    final EditText number_et = holder.getView(R.id.number_et);
                    final EditText weight_et = holder.getView(R.id.weight_et);
                    final EditText inch_length_et = holder.getView(R.id.inch_length_et);
                    final EditText inch_width_et = holder.getView(R.id.inch_width_et);
                    final EditText inch_height_et = holder.getView(R.id.inch_height_et);
                    number_et.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    inch_length_et.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    inch_width_et.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    inch_height_et.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    SystemUtils.getInstance(IFF_Clearing_InformationActivity.this).setPricePoint(weight_et);
                    number_et.setText(detailsOfGoodsBean.getNumber());
                    weight_et.setText(detailsOfGoodsBean.getWeight());
                    inch_length_et.setText(detailsOfGoodsBean.getLength());
                    inch_width_et.setText(detailsOfGoodsBean.getWidth());
                    inch_height_et.setText(detailsOfGoodsBean.getHeight());
                    /**
                     * edittext输入时计算总价
                     */
                    number_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            number = number_et.getText().toString();
                            detailsOfGoodsList.get(position).setNumber(number);
                            sumNumberWeight();
                            if (TextUtils.isEmpty(number) || Integer.parseInt(number) == 0) {
                                stateEt = 0;
                            } else {
                                stateEt = 1;
                            }
                            isEditText();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                    weight_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            weight = weight_et.getText().toString();
                            detailsOfGoodsList.get(position).setWeight(weight);
                            sumNumberWeight();
                            if (TextUtils.isEmpty(weight) || Double.parseDouble(weight) == 0 || Double.parseDouble(weight) == 0.0 || Double.parseDouble(weight) == 0.00 && Double.parseDouble(weight) > 0) {
                                stateEt = 0;
                            } else {
                                stateEt = 1;
                            }
                            isEditText();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                    inch_length_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            inch_length = inch_length_et.getText().toString();
                            detailsOfGoodsList.get(position).setLength(inch_length);
                            if (TextUtils.isEmpty(inch_length) || Integer.parseInt(inch_length) == 0) {
                                stateEt = 0;
                            } else {
                                stateEt = 1;
                            }
                            isEditText();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                    inch_width_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            inch_width = inch_width_et.getText().toString();
                            detailsOfGoodsList.get(position).setWidth(inch_width);
                            if (TextUtils.isEmpty(inch_width) || Integer.parseInt(inch_width) == 0) {
                                stateEt = 0;
                            } else {
                                stateEt = 1;
                            }
                            isEditText();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                    inch_height_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            inch_height = inch_height_et.getText().toString();
                            detailsOfGoodsList.get(position).setHeight(inch_height);
                            if (TextUtils.isEmpty(inch_height) || Integer.parseInt(inch_height) == 0) {
                                stateEt = 0;
                            } else {
                                stateEt = 1;
                            }
                            isEditText();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                }
            };
            detailsOfGoodsRv.setAdapter(detailsOfGoodsAdapter);
        }
    }

    private void isEditText() {
        if (stateEt == 1) {
            nextStepBtn.setEnabled(true);
        } else {
            nextStepBtn.setEnabled(false);
        }
    }

    @OnClick({R.id.title_return_img, R.id.next_step_btn, R.id.ibtn_number_weight_inch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.next_step_btn:
                addOrderBean.setOrderDetailList(detailsOfGoodsList);
                addOrderBean.setTotalNumber(totalNumber.getText().toString());
                EventBus.getDefault().postSticky(addOrderBean);
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Upload_PictureActivity.class);

                break;
            case R.id.ibtn_number_weight_inch:
                number = numberEt.getText().toString();
                weight = weightEt.getText().toString();
                inch_length = inchLengthEt.getText().toString();
                inch_width = inchWidthEt.getText().toString();
                inch_height = inchHeightEt.getText().toString();
                if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(weight) && !TextUtils.isEmpty(inch_length) && !TextUtils.isEmpty(inch_width) && !TextUtils.isEmpty(inch_height)) {
                    if (Integer.parseInt(inch_width) > 0 && Integer.parseInt(inch_height) > 0 && Integer.parseInt(number) > 0 && Integer.parseInt(inch_length) > 0 && Double.parseDouble(weight) > 0.00 && Double.parseDouble(weight) > 0.0 && Double.parseDouble(weight) > 0) {
                        if (stateEt == 0) {
                            ToastStopUtils.toastShow(this, "请正确输入货物信息");
                        } else {
                            stateEt = 1;
                            SystemUtils.getInstance(this).softKeyboard(view);
                            detailsOfGoodsList.add(new DetailsOfGoodsBean(number, weight, inch_length, inch_width, inch_height));
                            Collections.reverse(detailsOfGoodsList); //实现list集合逆序排列
                            sumNumberWeight();
                            if (detailsOfGoodsList.size() == 1) {
                                initAdapter();
                            } else if (detailsOfGoodsList.size() > 1) {
                                detailsOfGoodsAdapter.notifyDataSetChanged();
                                detailsOfGoodsRv.scrollToPosition(detailsOfGoodsList.size());
                            }
                            numberEt.setText("");
                            weightEt.setText("");
                            inchLengthEt.setText("");
                            inchWidthEt.setText("");
                            inchHeightEt.setText("");
                        }
                        isEditText();
                    } else {
                        ToastStopUtils.toastShow(this, "请正确输入货物信息");
                    }
                } else {
                    ToastStopUtils.toastShow(this, "请输入货物信息");
                }
                break;
        }
    }

    /**
     * 总和 件数和重量
     */
    private void sumNumberWeight() {
        total_number = 0;
        total_weight = 0;
        for (int i = 0; i < detailsOfGoodsList.size(); i++) {
            if (TextUtils.isEmpty(detailsOfGoodsList.get(i).getNumber())) {
                total_number += 0;
            } else {
                total_number += Integer.parseInt(detailsOfGoodsList.get(i).getNumber());
            }
            if (TextUtils.isEmpty(detailsOfGoodsList.get(i).getWeight())) {
                total_weight += 0;
            } else {
                total_weight += Double.parseDouble(detailsOfGoodsList.get(i).getWeight());
            }
        }
        totalNumber.setText(total_number + "");
        totalWeight.setText(total_weight + "");
    }

    @Override
    protected void onPause() {
        ToastStopUtils.toastStop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
