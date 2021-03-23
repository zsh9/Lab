package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Report extends BmobObject implements Serializable {

    private String title;
    private String des;
    private String finallys;

    private String picurl;
    /**
     * 实验申请
     */
    private Apply apply;


    private int status;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFinallys() {
        return finallys;
    }

    public void setFinallys(String finallys) {
        this.finallys = finallys;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }
}
