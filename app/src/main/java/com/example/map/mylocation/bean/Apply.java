package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Apply extends BmobObject implements Serializable {

    private Persion user;

    private String kecheng;

    private String teacher;

    private String title;


    private String mubiao;

    private String qicai;

    private String time;

    private String username;

    /**
     * 0 申请中 1 同意 2 拒绝
     */
    private int status;

    private String roomname;

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Persion getUser() {
        return user;
    }

    public void setUser(Persion user) {
        this.user = user;
    }

    public String getKecheng() {
        return kecheng;
    }

    public void setKecheng(String kecheng) {
        this.kecheng = kecheng;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMubiao() {
        return mubiao;
    }

    public void setMubiao(String mubiao) {
        this.mubiao = mubiao;
    }

    public String getQicai() {
        return qicai;
    }

    public void setQicai(String qicai) {
        this.qicai = qicai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
