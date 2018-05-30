package com.example.ysww.internationalfreightforwarding.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.example.ysww.internationalfreightforwarding.R;


/**
 * 自定义弹窗 暂无数据提示
 */
public class NoDataDialog extends Dialog {

    public NoDataDialog(Context context) {
        super(context);
    }

    public NoDataDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private ClickCloseTheOrder clickCloseTheOrder;

        public void setClickCloseTheOrder(ClickCloseTheOrder clickCloseTheOrder) {
            this.clickCloseTheOrder = clickCloseTheOrder;
        }

        public interface ClickCloseTheOrder {
            void OnClickCloseTheOrder(DialogInterface dialogInterface, int i);
        }

        public Builder(Context context) {
            this.context = context;
        }

        public NoDataDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final NoDataDialog dialog = new NoDataDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_no_data, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the confirm button
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
