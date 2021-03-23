package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

public class RoomBean extends BmobObject implements Serializable {


    private String roomname;
    private String loaction;

    private String beizhu;


    private int used;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getLoaction() {
        return loaction;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
