package com.example.map.mylocation.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map.mylocation.NbApplication;
import com.example.map.mylocation.R;


public class ToastUtil {
    private static Toast toast = null;

    /**
     * 显示 自定义居中Toast
     *
     * @param msg
     */
    public static void showTextToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
//        toast = Toast.makeText(NbApplication.getContext(), msg, Toast.LENGTH_SHORT);
//        toast.show();

        View toastRoot = LayoutInflater.from(NbApplication.getContext()).inflate(R.layout.taost_layout, null);
        toast = new Toast(NbApplication.getContext());
//填充物来自的xml文件,在这个改成一个view
        TextView toats_text = toastRoot.findViewById(R.id.toats_text);
        toats_text.setText(msg);
//把填充物放进toast
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * 图片Toast
     *
     * @param context
     * @param msg
     * @param imageRId
     */
    public static void showImageToast(Context context, String msg, int imageRId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context,
                msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context.getApplicationContext());
        imageCodeProject.setImageResource(imageRId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    /**
     * 弹出警告对话框
     *
     * @param context
     * @param title
     * @param msg
     */
    public static void showErrorAlret(Context context, String title, String msg) {
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.show();
    }
}
