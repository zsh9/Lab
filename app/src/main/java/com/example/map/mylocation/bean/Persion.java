package com.example.map.mylocation.bean;

import cn.bmob.v3.BmobUser;

public class Persion extends BmobUser {
    /**
     * 0  学生  1  是老师
     */
    private int flag;


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
