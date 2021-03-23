package com.example.map.mylocation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;


import com.example.map.mylocation.R;

import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

/**
 * Created by Android开发 on 2019/1/17.
 */

public class NewNormalView extends BaseTabItem {

    private ImageView mIcon;
    private final TextView mTitle;
//    private final RoundMessageView mMessages;

    private int mDefaultDrawable;
    private int mCheckedDrawable;

    private int mDefaultTextColor = 0x56000000;
    private int mCheckedTextColor = 0x56000000;

    public NewNormalView(Context context) {
        this(context, null);
    }

    public NewNormalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewNormalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_bottm_normal, this, true);
        mIcon = (ImageView) findViewById(R.id.top_icon);
        mTitle = (TextView) findViewById(R.id.bottom_icon);
//        mMessages = (RoundMessageView) findViewById(me.majiajie.pagerbottomtabstrip.R.id.messages);
    }

    /**
     * 方便初始化的方法
     *
     * @param drawableRes        默认状态的图标
     * @param checkedDrawableRes 选中状态的图标
     * @param title              标题
     */
    public void initialize(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, String title) {
        mDefaultDrawable = drawableRes;
        mCheckedDrawable = checkedDrawableRes;
        mTitle.setText(title);
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
//            mIcon.setBackground(mCheckedDrawable);
            mIcon.setImageResource(mCheckedDrawable);
            mTitle.setTextColor(mCheckedTextColor);
        } else {
            mIcon.setImageResource(mDefaultDrawable);
            mTitle.setTextColor(mDefaultTextColor);
        }
    }

    @Override
    public void setMessageNumber(int number) {
//        mMessages.setMessageNumber(number);
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
//        mMessages.setHasMessage(hasMessage);
    }

    @Override
    public String getTitle() {
        return mTitle.getText().toString();
    }

    public void setTextDefaultColor(@ColorInt int color) {
        mDefaultTextColor = color;
    }

    public void setTextCheckedColor(@ColorInt int color) {
        mCheckedTextColor = color;
    }
}
