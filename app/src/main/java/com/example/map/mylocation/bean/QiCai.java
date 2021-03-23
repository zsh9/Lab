package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class QiCai extends BmobObject implements Serializable {


    private String name;
    private String size;
    private int number;
    private String beizhu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
