package com.example.ysww.internationalfreightforwarding.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ysww.internationalfreightforwarding.R;
import com.example.ysww.internationalfreightforwarding.custom.LazyLoadProgressDialog;
import com.example.ysww.internationalfreightforwarding.model.GetOrderMsgCountBean;
import com.example.ysww.internationalfreightforwarding.net.OkgoHttpResolve;
import com.example.ysww.internationalfreightforwarding.net.view.GetOrderMsgCountView;
import com.example.ysww.internationalfreightforwarding.presenter.GetOrderMsgCountPresenter;
import com.example.ysww.internationalfreightforwarding.utils.CrazyShadowUtils;
import com.example.ysww.internationalfreightforwarding.utils.SystemUtils;
import com.lzy.okgo.OkGo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 提问列表
 */
public class IFF_Question_ListActivity extends Activity implements GetOrderMsgCountView {

    @InjectView(R.id.iff_title_tv)
    TextView iffTitleTv;
    @InjectView(R.id.iff_title_cl)
    ConstraintLayout iffTitleCl;
    @InjectView(R.id.question_list_rv)
    RecyclerView questionListRv;
    @InjectView(R.id.channel_quiz_rb)
    RadioButton channelQuizRb;
    @InjectView(R.id.communication_rb)
    RadioButton communicationRb;
    @InjectView(R.id.question_list_rg)
    RadioGroup questionListRg;
    private CommonAdapter<GetOrderMsgCountBean.DataBean> questionListAdapter;
    private List<GetOrderMsgCountBean.DataBean> questionList;
    private GetOrderMsgCountPresenter getOrderMsgCountPresenter = new GetOrderMsgCountPresenter();
    private LazyLoadProgressDialog lazyLoadProgressDialog;//延迟加载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iff__question__list);
        ButterKnife.inject(this);
        SystemUtils.getInstance(this).mustCallActivity(this);
        lazyLoadProgressDialog = lazyLoadProgressDialog.createDialog(this);
        initViews();
    }

    private void initViews() {
        iffTitleTv.setText(R.string.question_list);
        CrazyShadowUtils.getCrazyShadowUtils(this).titleCrazyShadow(iffTitleCl);
        questionList = new ArrayList<>();
        channelQuizRb.setChecked(true);
        SystemUtils.getInstance(IFF_Question_ListActivity.this).showLazyLad0neMinute(lazyLoadProgressDialog);
        getOrderMsgCountMethod();

        questionSwitching();


    }

    /**
     * 提问分开请求
     */
    private void questionSwitching() {
        questionListRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.channel_quiz_rb:
                        SystemUtils.getInstance(IFF_Question_ListActivity.this).showLazyLad0neMinute(lazyLoadProgressDialog);
                        getOrderMsgCountMethod();
                        break;
                    case R.id.communication_rb:
                        SystemUtils.getInstance(IFF_Question_ListActivity.this).showLazyLad0neMinute(lazyLoadProgressDialog);
                        getOrderMsgCountMethod();
                        break;
                }
            }
        });
    }

    /**
     * 获取营销员订单未读消息列表接口
     */
    private void getOrderMsgCountMethod() {
        new OkgoHttpResolve(this);
        getOrderMsgCountPresenter.attach(this);
        getOrderMsgCountPresenter.getOrderMsgCountResult("", this, lazyLoadProgressDialog);
    }

    private void initAdapter() {
        questionListRv.setLayoutManager(new LinearLayoutManager(this));
        questionListAdapter = new CommonAdapter<GetOrderMsgCountBean.DataBean>(this, R.layout.item_order_number_supplier, questionList) {
            @Override
            protected void convert(ViewHolder holder, GetOrderMsgCountBean.DataBean dataBean, int position) {
                holder.setText(R.id.supplier_tv, "订单号" + dataBean.getOrderNo() + "有新的渠道商提问");
                if (questionList.size() == position + 1) {
                    holder.setVisible(R.id.view, false);
                }
            }
        };
        questionListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                SystemUtils.getInstance(IFF_Question_ListActivity.this).referenceSourcePageOrderNoIntent
                        (IFF_Close_The_OrderActivity.class, "question_list", questionList.get(position).getOrderNo());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        questionListRv.setAdapter(questionListAdapter);
    }

    @OnClick(R.id.title_return_img)
    public void onViewClicked() {
        SystemUtils.getInstance(this).returnHomeFinishAll();
    }

    @Override
    public void onGetOrderMsgCountFinish(Object o) {
        GetOrderMsgCountBean getOrderMsgCountBean = (GetOrderMsgCountBean) o;
        questionList = getOrderMsgCountBean.getData();
        initAdapter();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        SystemUtils.getInstance(this).returnHomeFinishAll();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
        getOrderMsgCountPresenter.dettach();
    }
}
