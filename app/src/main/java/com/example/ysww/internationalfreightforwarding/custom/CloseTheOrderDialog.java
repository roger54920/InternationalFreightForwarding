package com.example.ysww.internationalfreightforwarding.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

import com.example.ysww.internationalfreightforwarding.R;


/**
 * 自定义弹窗 关闭订单
 */
public class CloseTheOrderDialog extends Dialog {

    public CloseTheOrderDialog(Context context) {
        super(context);
    }

    public CloseTheOrderDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private ClickCloseTheOrder clickCloseTheOrder;

        public void setClickCloseTheOrder(ClickCloseTheOrder clickCloseTheOrder) {
            this.clickCloseTheOrder = clickCloseTheOrder;
        }

        public interface ClickCloseTheOrder {
            void OnClickCloseTheOrder(android.content.DialogInterface dialogInterface, int i, String msg);
        }

        public Builder(Context context) {
            this.context = context;
        }

        public CloseTheOrderDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CloseTheOrderDialog dialog = new CloseTheOrderDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_close_the_order, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the confirm button

            final Button btn_determine = (Button) layout.findViewById(R.id.btn_determine);
            final EditText close_the_order = layout.findViewById(R.id.close_the_order_et);
            close_the_order.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!TextUtils.isEmpty(close_the_order.getText().toString().trim())) {
                        btn_determine.setEnabled(true);
                    } else {
                        btn_determine.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            // set the confirm button
            if (clickCloseTheOrder != null) {
                btn_determine.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        clickCloseTheOrder.OnClickCloseTheOrder(dialog,
                                DialogInterface.BUTTON_POSITIVE, close_the_order.getText().toString());
                    }
                });
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
