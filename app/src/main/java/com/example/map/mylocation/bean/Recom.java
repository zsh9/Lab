package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Recom extends BmobObject implements Serializable {


    private String title;

    /**
     * 简短描述
     */
    private String shortdes;
    private String content;

    /**
     *
     */
    private String picurl;

    private String videourl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdes() {
        return shortdes;
    }

    public void setShortdes(String shortdes) {
        this.shortdes = shortdes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
