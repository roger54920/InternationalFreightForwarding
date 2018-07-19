package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.app.photo.ImagePagerActivity;
import com.example.ysww.internationalfreightforwarding.app.photo.PhotoAdapter;
import com.example.ysww.internationalfreightforwarding.model.AddOrderBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.FlieUploadView;
import com.example.ysww.internationalfreightforwarding.presenter.FlieUploadPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 上传图片
 */
public class IFF_Upload_PictureActivity extends Activity implements FlieUploadView,TakePhoto.TakeResultListener, InvokeListener {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.order_picture_rv)
    RecyclerView recShow;
    @InjectView(R.id.next_step_btn)
    Button nextStepBtn;
    //保存相片名称和路径
    private List<String> flieUploadName;
    private List<String> flieUploadUrl;
    private AddOrderBean addOrderBean = new AddOrderBean();

    private PhotoAdapter photoAdapter;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private FlieUploadPresenter flieUploadPresenter = new FlieUploadPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__upload__picture);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        EventBus.getDefault().register(this);
        initViews();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNewOrderEvent(AddOrderBean addOrderBean) {
        this.addOrderBean = addOrderBean;
    }

    private void initViews() {
        iffTitleTv.setText(R.string.upload_picture);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        flieUploadName = new ArrayList<>();
        flieUploadUrl = new ArrayList<>();

    }

    private void initData() {
        //设置展示框中单行展示的图片个数
        recShow.setLayoutManager(new GridLayoutManager(this, 3));
        //初始化自定义Adapter，onAddPicListener是添加图片的点击监听器，onPicClickListener是添加图片成功以后，点击放大的监听器。
        photoAdapter = new PhotoAdapter(this, onAddPicListener, onPicClickListener);
        //设置多选时最多选择的图片张数
        photoAdapter.setSelectMax(9);
        recShow.setAdapter(photoAdapter);
    }

    //图片点击事件
    private PhotoAdapter.onPicClickListener onPicClickListener = new PhotoAdapter.onPicClickListener() {
        @Override
        public void onPicClick(View view, int position) {
            imageUrls.clear();
            for (int i = 0; i < selectMedia.size(); i++) {
                String compressPath = selectMedia.get(i).getCompressPath();
                imageUrls.add(compressPath);
            }
            imageBrower(position, imageUrls);
        }
    };

    private void imageBrower(int position, ArrayList<String> imageUrls) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, imageUrls);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        startActivity(intent);
    }

    /**
     * 获取TakePhoto实例
     * 没有继承TakePhotoActivity 所写
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    private List<TImage> selectMedia = new ArrayList<>();
    private List<TImage> updateMedia = new ArrayList<>();
    ArrayList<String> imageUrls = new ArrayList<>();
    private PhotoAdapter.onAddPicListener onAddPicListener = new PhotoAdapter.onAddPicListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0: //点击图片
                    new AlertView("上传图片", null, "取消", null,
                            new String[]{"拍照", "从相册中选择"},
                            IFF_Upload_PictureActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                            TakePhoto takePhoto = getTakePhoto();
                            //获取TakePhoto图片路径
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                File file = new File(Environment.getExternalStorageDirectory(), "/photo/" + System.currentTimeMillis() + ".jpg");
                                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                                Uri imageUri = Uri.fromFile(file);
                                //设置takephoto的照片使用压缩
                                configCompress(takePhoto);
                                //设置Takephoto 使用TakePhoto自带的相册   照片旋转角度纠正
                                configTakePhotoOption(takePhoto);
                                switch (position) {
                                    case 0:  //拍照
                                        takePhoto.onPickFromCapture(imageUri);
                                        break;
                                    case 1:  //相册选择
                                        //设置最多几张
                                        if (selectMedia.size() == 0) {
                                            takePhoto.onPickMultiple(9);
                                        } else if (selectMedia.size() == 1) {
                                            takePhoto.onPickMultiple(8);
                                        } else if (selectMedia.size() == 2) {
                                            takePhoto.onPickMultiple(7);
                                        } else if (selectMedia.size() == 3) {
                                            takePhoto.onPickMultiple(6);
                                        } else if (selectMedia.size() == 4) {
                                            takePhoto.onPickMultiple(5);
                                        } else if (selectMedia.size() == 5) {
                                            takePhoto.onPickMultiple(4);
                                        } else if (selectMedia.size() == 6) {
                                            takePhoto.onPickMultiple(3);
                                        } else if (selectMedia.size() == 7) {
                                            takePhoto.onPickMultiple(2);
                                        } else if (selectMedia.size() == 8) {
                                            takePhoto.onPickMultiple(1);
                                        }
                                        break;
                                }
                            }

                        }
                    }).show();
                    break;
                case 1:  //点击删除按钮
                    selectMedia.remove(position);
                    photoAdapter.notifyItemRemoved(position);
                    if (selectMedia.size() == 0) {
                        nextStepBtn.setEnabled(false);
                    } else {
                        nextStepBtn.setEnabled(true);
                    }
                    break;
            }
        }
    };

    //设置Takephoto 使用TakePhoto自带的相册   照片旋转角度纠正
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    //设置takephoto的照片使用压缩
    private void configCompress(TakePhoto takePhoto) {
        CompressConfig config;
        config = new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800)
                .enableReserveRaw(true)
                .create();
        takePhoto.onEnableCompress(config, false);
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public void takeFail(TResult result, String msg) {
        ArrayList<TImage> images = result.getImages();
        showImg(result.getImages());
    }

    @Override
    public void takeSuccess(TResult result) {
        showImg(result.getImages());
    }

    //没有继承TakePhotoActivity 所写
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //将拍照返回的结果交给takePhoto处理
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //没有继承TakePhotoActivity 所写
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    //没有继承TakePhotoActivity 所写
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    //没有继承TakePhotoActivity 所写
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    //图片成功后返回执行的方法
    private void showImg(ArrayList<TImage> images) {
        //目的是防止上传重复的图片
        updateMedia.clear();

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).getCompressPath() != null) {
                selectMedia.add(images.get(i));
                updateMedia.add(images.get(i));
                flieUploadName.add(getPicNameFromPath(images.get(i).getOriginalPath()));
                flieUploadUrl.add(images.get(i).getOriginalPath());
            }
        }
        if (selectMedia != null) {
            photoAdapter.setList(selectMedia);
            photoAdapter.notifyDataSetChanged();
        }
        if (selectMedia.size() > 0) {
            nextStepBtn.setEnabled(true);
        } else {
            nextStepBtn.setEnabled(false);
        }
        //需上传图片，请使用updateMedia数组。
    }
    /**
     * 根据图片路径获取图片名字
     *
     * @param picturePath
     * @return
     */
    public String getPicNameFromPath(String picturePath) {
        String temp[] = picturePath.replaceAll("\\\\", "/").split("/");
        String fileName = "";
        if (temp.length > 1) {
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }

    @OnClick({R.id.title_return_img, R.id.next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.next_step_btn:
                Log.e("====", "flieUploadName: " +new Gson().toJson(flieUploadName));
                Log.e("====", "flieUploadUrl: " +new Gson().toJson(flieUploadUrl));
                flieUploadMethod();
//                addOrderBean.setOrderPictureName(new Gson().toJson(flieUploadName));
//                addOrderBean.setOrderPictureUrl(new Gson().toJson(flieUploadUrl));
//                EventBus.getDefault().postSticky(addOrderBean);
//                SystemUtils.getInstance(this).noReferenceIntent(IFF_Select_Information1Activity.class);
                break;
        }
    }
    /**
     * 上传图片
     */
    private void flieUploadMethod() {
        new OkgoHttpResolve(this);
        flieUploadPresenter.attach(this);
        flieUploadPresenter.flieUploadResult(new Gson().toJson(flieUploadName),new Gson().toJson(flieUploadUrl),this,null);
    }
    @Override
    public void onFlieUploadFinish(Object o) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        flieUploadPresenter.dettach();

    }
}
