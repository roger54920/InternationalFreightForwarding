package com.example.ysww.internationalfreightforwarding.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.ActionSheetDialog;
import com.example.ysww.internationalfreightforwarding.custom.ImageRadioButton;
import com.example.ysww.internationalfreightforwarding.custom.MyScrollview;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.model.DetailsOfGoodsBean;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
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
    @InjectView(R.id.tariff_payment_rb1)
    ImageRadioButton tariffPaymentRb1;
    @InjectView(R.id.tariff_payment_rg)
    RadioGroup tariffPaymentRg;
    @InjectView(R.id.delivery_mode_rb1)
    ImageRadioButton deliveryModeRb1;
    @InjectView(R.id.delivery_mode_rg)
    RadioGroup deliveryModeRg;
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
    @InjectView(R.id.my_scroll_view)
    MyScrollview myScrollView;
    @InjectView(R.id.order_picture_rv)
    RecyclerView orderPictureRv;
    private CommonAdapter<DetailsOfGoodsBean> detailsOfGoodsAdapter;
    private List<DetailsOfGoodsBean> detailsOfGoodsList;
    private CommonAdapter<String> orderPictureAdapter;
    private List<String> orderPictureList;
    private String number, weight, inch_length, inch_width, inch_height;
    private AddOrderBean addOrderBean = new AddOrderBean();
    private int total_number;
    private double total_weight;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private int stateEt = 2;
    private List<String> photoNameList;//保存相片名称

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;

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
        myScrollView.smoothScrollTo(0, 0);
        iffTitleTv.setText(R.string.clearing_information);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        paymentMethodRb1.setChecked(true);
        addOrderBean.setPayType(paymentMethodRb1.getText().toString());
        tariffPaymentRb1.setChecked(true);
        addOrderBean.setTariffPayType(tariffPaymentRb1.getText().toString());
        deliveryModeRb1.setChecked(true);
        addOrderBean.setDeliveryType(deliveryModeRb1.getText().toString());
        numberEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchLengthEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchWidthEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        inchHeightEt.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        SystemUtils.getInstance(this).setPricePoint(weightEt);
        SystemUtils.getInstance(this).setPricePointText(totalWeight);
        paymentMethodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(paymentMethodRg.getCheckedRadioButtonId());
                addOrderBean.setPayType(rbId.getText().toString());

            }
        });
        tariffPaymentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(tariffPaymentRg.getCheckedRadioButtonId());
                addOrderBean.setTariffPayType(rbId.getText().toString());

            }
        });
        deliveryModeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbId = (RadioButton) findViewById(deliveryModeRg.getCheckedRadioButtonId());
                addOrderBean.setDeliveryType(rbId.getText().toString());

            }
        });
        detailsOfGoodsList = new ArrayList<>();
        orderPictureList = new ArrayList<>();
        photoNameList = new ArrayList<>();
        orderPictureList.add("img_add");
        initOrderPictureAdapter();
        isEditText();
    }

    /**
     * 订单图片
     */
    private void initOrderPictureAdapter() {
        5
        gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderPictureRv.setLayoutManager(gridLayoutManager);
        orderPictureAdapter = new CommonAdapter<String>(this, R.layout.item_order_picture, orderPictureList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

                if (s.equals("img_add")) {
                    Log.e("=====", "convert: "+s );
                    holder.setImageResource(R.id.order_picture_img, R.mipmap.photo_order_picture);
                    holder.setOnClickListener(R.id.order_picture_img, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            actionSheetDialog();
                        }
                    });
                }else{
                    Bitmap bitMap = BitmapFactory.decodeFile(s);
                    holder.setImageBitmap(R.id.order_picture_img, bitMap);
                }
                if(orderPictureList.size()>=9){
                    if(position==orderPictureList.size()-1){
                        holder.setVisible(R.id.order_picture_img,false);
                    }

                }else{
                    holder.setVisible(R.id.order_picture_img,true);
                }

            }
        };
        orderPictureRv.setAdapter(orderPictureAdapter);
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
                addOrderBean.setOrderPictureUrl(new Gson().toJson(photoNameList));
                EventBus.getDefault().postSticky(addOrderBean);
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Select_Information1Activity.class);

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

    /**
     * 底部弹窗
     */
    private void actionSheetDialog() {
        final ActionSheetDialog actionSheetDialog = new ActionSheetDialog(this, new ActionSheetDialog.ActionSheetClickListener() {
            @Override
            public void onClickTakePhoto(ActionSheetDialog actionSheetDialog) {
                //权限判断
                if (ContextCompat.checkSelfPermission(IFF_Clearing_InformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(IFF_Clearing_InformationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }
                actionSheetDialog.dismiss();

            }

            @Override
            public void onClickChoosePhoto(ActionSheetDialog actionSheetDialog) {
                //权限判断
                if (ContextCompat.checkSelfPermission(IFF_Clearing_InformationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(IFF_Clearing_InformationActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                actionSheetDialog.dismiss();
            }

            @Override
            public void onClickCancel(ActionSheetDialog actionSheetDialog) {
                actionSheetDialog.dismiss();
            }
        }).setActionSheetDialog();
        actionSheetDialog.show();
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/international/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.example.ysww.internationalfreightforwarding.fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoReturnResult(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    uri.getPath();
                    gotoReturnResult(uri);
                }
                break;
        }
    }

    /**
     * 相机返回结果
     */
    public void gotoReturnResult(Uri uri) {
        if (uri == null) {
            return;
        }
        String path = uri.getPath();
        photoNameList.add(getPicNameFromPath(path));
        for (int i = 0; i <orderPictureList.size() ; i++) {
            if(orderPictureList.get(i).equals("img_add")){
                orderPictureList.remove(i);
            }
        }
        orderPictureList.add(getRealFilePathFromUri(this, uri));
        orderPictureList.add("img_add");
        orderPictureAdapter.notifyDataSetChanged();

    }

    /**
     * 根据图片路径获取图片名字
     *
     * @param picturePath
     * @return
     */
    public static String getPicNameFromPath(String picturePath) {
        String temp[] = picturePath.replaceAll("\\\\", "/").split("/");
        String fileName = "";
        if (temp.length > 1) {
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
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
