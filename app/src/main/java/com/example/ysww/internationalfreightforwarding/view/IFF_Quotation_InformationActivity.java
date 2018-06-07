package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.Constants;
import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.custom.SystemPromptDialog;
import com.example.ysww.internationalfreightforwarding.model.QueryMarketlMsgBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.QueryMarketlMsgView;
import com.example.ysww.internationalfreightforwarding.net.view.TbordermsgSaveView;
import com.example.ysww.internationalfreightforwarding.presenter.QueryMarketlMsgPresenter;
import com.example.ysww.internationalfreightforwarding.presenter.TbordermsgSavePresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 报价信息 交流
 */
public class IFF_Quotation_InformationActivity extends Activity implements TbordermsgSaveView, QueryMarketlMsgView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_function_tv)
    TextView iffFunctionTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.freight_money_tv)
    TextView freightMoneyTv;
    @InjectView(R.id.incidental_money_tv)
    TextView incidentalMoneyTv;
    @InjectView(R.id.premium_money_tv)
    TextView premiumMoneyTv;
    @InjectView(R.id.quotation_information_rv)
    RecyclerView quotationInformationRv;
    @InjectView(R.id.question_information_et)
    EditText questionInformationEt;
    @InjectView(R.id.submission_reply_btn)
    Button submissionReplyBtn;

    private TbordermsgSavePresenter tbordermsgSavePresenter = new TbordermsgSavePresenter();
    private QueryMarketlMsgPresenter queryMarketlMsgPresenter = new QueryMarketlMsgPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载
    private CommonAdapter<QueryMarketlMsgBean.DataBean> quotationInformationAdapter;
    private List<QueryMarketlMsgBean.DataBean> quotationInformationList;
    private String content;
    private LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            queryMarketlMsgMethod();
            handler.postDelayed(this, 10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__quotation__information);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();

    }
    private void initViews() {
        iffTitleTv.setText(R.string.quotation_information);
        Constants.SOURCE_PAGE = getIntent().getStringExtra("source_page");
        if(Constants.SOURCE_PAGE.equals("order_details")){
            iffFunctionTv.setVisibility(View.VISIBLE);
        }
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        questionInformationEt.addTextChangedListener(new TextWatcher() {
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
        quotationInformationList = new ArrayList<>();
        SystemUtils.getInstance(this).showLazyLad0neMinute(lazyLoadProgressDialog);
        queryMarketlMsgMethod();
    }

    /**
     * 获取订单消息明细接口
     */
    private void queryMarketlMsgMethod() {
        new OkgoHttpResolve(this);
        queryMarketlMsgPresenter.attach(this);
        queryMarketlMsgPresenter.queryMarketlMsgResult("{\"msgType\":\"0\",\"orderNo\":\"" + getIntent().getStringExtra("orderNo") + "\"}", this, lazyLoadProgressDialog);
    }

    /**
     * 发送订单消息接口
     */
    private void tbordermsgSaveMethod() {
        new OkgoHttpResolve(this);
        tbordermsgSavePresenter.attach(this);
        tbordermsgSavePresenter.tbordermsgSaveResult("{\"content\":\"" + questionInformationEt.getText().toString().trim() + "\",\"msgType\":\"0\",\"orderNo\":\"" + getIntent().getStringExtra("orderNo") + "\"}", this, lazyLoadProgressDialog);
    }

    private void initAdapter() {
        linearLayoutManager = new LinearLayoutManager(this);
        quotationInformationRv.setLayoutManager(linearLayoutManager);
        quotationInformationAdapter = new CommonAdapter<QueryMarketlMsgBean.DataBean>(this, R.layout.item_quotation_information_chat, quotationInformationList) {
            @Override
            protected void convert(ViewHolder holder, QueryMarketlMsgBean.DataBean dataBean, int position) {
                String addrss = dataBean.getAddrss();
                holder.setText(R.id.chat_time_tv, dataBean.getCreateDate() + "");
                if (addrss.equals("left")) {
                    holder.setText(R.id.chat_left_tv, dataBean.getContent());
                    holder.setText(R.id.chat_left_name, dataBean.getUserName());
                    holder.setVisible(R.id.chat_right_rl, false);
                    holder.setVisible(R.id.chat_left_rl, true);
                } else {
                    holder.setText(R.id.chat_right_tv, dataBean.getContent());
                    holder.setText(R.id.chat_right_name, dataBean.getUserName());
                    holder.setVisible(R.id.chat_right_rl, true);
                    holder.setVisible(R.id.chat_left_rl, false);
                }
            }
        };
        quotationInformationRv.setAdapter(quotationInformationAdapter);
        linearLayoutManager.scrollToPosition(quotationInformationList.size() - 1);
    }

    private void isEditText() {
        content = questionInformationEt.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            submissionReplyBtn.setEnabled(true);
        } else {
            submissionReplyBtn.setEnabled(false);
        }
    }

    @OnClick({R.id.title_return_img, R.id.submission_reply_btn,R.id.iff_function_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_img:
                finish();
                break;
            case R.id.submission_reply_btn:
                SystemUtils.getInstance(IFF_Quotation_InformationActivity.this).showLazyLad0neMinute(lazyLoadProgressDialog);
                tbordermsgSaveMethod();
                break;
            case R.id.iff_function_tv:
                onClickIsDeliverGoods();
                break;
        }
    }
    //点击是否发货
    private void onClickIsDeliverGoods() {
        SystemPromptDialog.Builder builder = new SystemPromptDialog.Builder(this, "您确定要发货吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作事项
                SystemUtils.getInstance(IFF_Quotation_InformationActivity.this).referenceSourcePageOrderNoChanneIdealerIntent(IFF_Collect_Send_InformationActivity.class,"quotation_information",getIntent().getStringExtra("orderNo"),"");

            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().setCanceledOnTouchOutside(true);  //用户选择取消或者是点击屏幕空白部分时让dialog消失。
        builder.create().show();
    }
    @Override
    public void onTbordermsgSaveFinish(Object o) {
        questionInformationEt.setText("");
        queryMarketlMsgMethod();
    }

    @Override
    public void onQueryMarketlMsgFinish(Object o) {
        handler.removeCallbacks(runnable);
        QueryMarketlMsgBean queryMarketlMsgBean = (QueryMarketlMsgBean) o;
        List<QueryMarketlMsgBean.DataBean> data = queryMarketlMsgBean.getData();
        if(quotationInformationList.size()!=data.size()){
            quotationInformationList = data;
            initAdapter();
        }
        handler.postDelayed(runnable, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        tbordermsgSavePresenter.dettach();
        queryMarketlMsgPresenter.dettach();
        handler.removeCallbacks(runnable);
    }
}
