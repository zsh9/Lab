package com.example.map.mylocation.utils;

import android.graphics.Bitmap;
import android.view.View;

public class ViewToBitmapUtil {
    private static final String TAG = "ViewToBitmapUtil";

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        // 或者可以使用下面的方法
// view.setDrawingCacheEnabled(true);
        // Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache());

        return bitmap;
    }


}
