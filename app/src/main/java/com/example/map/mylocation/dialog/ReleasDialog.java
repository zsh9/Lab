package com.example.map.mylocation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.utils.DensityUtil;
import com.example.map.mylocation.utils.Event;
import com.example.map.mylocation.utils.EventBusUtil;


public class ReleasDialog extends Dialog {

    private Context mContext;
    private EditText editText;
    private Button sure, cancle;
    String number;
    int from = 0;

    public ReleasDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.dialog_vip);
        this.mContext = context;
    }

    public ReleasDialog(@NonNull Context context, int themeResId, int type) {
        super(context, R.style.dialog_vip);
        this.mContext = context;
        this.from = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_input_number, null);
        setContentView(view);
        editText = view.findViewById(R.id.edt_number);
        sure = view.findViewById(R.id.sure_btn);
        cancle = view.findViewById(R.id.cancle_btn);
        number = editText.getText().toString().trim();


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(editText.getText().toString().trim())) {
                    EventBusUtil.sendEvent(new Event(from, editText.getText().toString().trim()));
                } else {

                }

                dismiss();
            }
        });
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用

        lp.width = DensityUtil.dip2px(mContext, 300);// 高度设置为屏幕的0.8
        lp.height = DensityUtil.dip2px(mContext, 200);// 高度设置为屏幕的0.8
//        lp.height = d.heightPixels;
        setCanceledOnTouchOutside(true);
        dialogWindow.setAttributes(lp);
    }
}
