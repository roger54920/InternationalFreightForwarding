package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.LoginBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.LoginView;
import com.example.ysww.internationalfreightforwarding.presenter.LoginPresenter;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.githang.statusbar.StatusBarCompat;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登陆
 */
public class IFF_LoginActivity extends Activity implements LoginView {

    @InjectView(R.id.phone_number_et)
    EditText phoneNumberEt;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.phone_number_tv)
    TextView phoneNumberTv;
    @InjectView(R.id.login_btn)
    Button loginBtn;

    private String phoneNumber;
    private String password;
    private LoginPresenter loginPresenter = new LoginPresenter();//登录
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__login);
        ButterKnife.inject(this);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#F7F7F7"));
        MyActivityManager.getInstance().pushOneActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        phoneNumberEt.addTextChangedListener(new TextWatcher() {
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
        passwordEt.addTextChangedListener(new TextWatcher() {
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

    @OnClick(R.id.login_btn)
    public void onViewClicked() {
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        login();
    }

    private void isEditText() {
        phoneNumber = phoneNumberEt.getText().toString();
        password = passwordEt.getText().toString();
        if (!TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(password)) {
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setEnabled(false);
        }
    }

    /**
     * Android中的“再按一次返回键退出程序”实现
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastStopUtils.toastShow(this, "再按一次退出国际货代");
                exitTime = System.currentTimeMillis();
            } else {
                MyActivityManager.getInstance().finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        ToastStopUtils.toastStop();
        super.onPause();
    }

    /**
     * 登陆
     */
    private void login() {
        new OkgoHttpResolve(this);
        loginPresenter.attach(this);
        loginPresenter.postLoginResult("phone=" + phoneNumber + "&password=" + password + "&isApp=true", this, lazyLoadProgressDialog);
    }

    @Override
    public void onLoginFinish(Object o) {
        LoginBean loginBean = (LoginBean) o;
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("tokenId",loginBean.getToken());
        editor.putInt("userId",loginBean.getUserId());
        editor.commit();
        SystemUtils.getInstance(this).noReferenceIntent(IFF_HomeActivity.class);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        loginPresenter.dettach();
    }
}
