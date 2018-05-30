package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;

/**
 * 引导页
 */
public class IFF_SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtils.getInstance(this).noReferenceIntent(IFF_LoginActivity.class);
        finish();
    }
}
