package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.MyActivityManager;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.example.ysww.internationalfreightforwarding.utils.ToastStopUtils;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 首页
 */
public class IFF_HomeActivity extends Activity {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.title_return_img)
    ImageView titleReturnImg;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.copy_rl)
    RelativeLayout copyRl;
    @InjectView(R.id.supplier_questions_number_tv)
    TextView supplierQuestionsNumberTv;
    @InjectView(R.id.comments_rl)
    RelativeLayout commentsRl;
    @InjectView(R.id.information_supplement_rl)
    RelativeLayout information_supplementRl;
    @InjectView(R.id.historical_order_rl)
    RelativeLayout historical_orderRl;
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
//    private StatisticalOrderNumPresenter statisticalOrderPresenter = new StatisticalOrderNumPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__home);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        titleReturnImg.setVisibility(View.GONE);
        iffTitleTv.setText(R.string.home);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
//        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
//        statisticalOrderMethod();
    }

    /**
     *
     */
    private void statisticalOrderMethod() {
        new OkgoHttpResolve(this);
//        statisticalOrderPresenter.attach(this);
//        statisticalOrderPresenter.statisticalOrderNumResult("{\"quoteStatus\":\"10\"}", this, lazyLoadProgressDialog);
    }

    @OnClick({R.id.copy_rl, R.id.comments_rl, R.id.information_supplement_rl, R.id.historical_order_rl, R.id.add_new_order_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.copy_rl:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "copy");
                break;
            case R.id.comments_rl:
                SystemUtils.getInstance(this).noReferenceIntent(IFF_Question_ListActivity.class);
                break;
            case R.id.information_supplement_rl:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "information_supplement");
                break;
            case R.id.historical_order_rl:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_Quotation_OrderActivity.class, "historical_order");
                break;
            case R.id.add_new_order_btn:
                SystemUtils.getInstance(this).referenceSourcePageIntent(IFF_New_0rderActivity.class, "add_new_order");
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
//        statisticalOrderPresenter.dettach();
    }

}
