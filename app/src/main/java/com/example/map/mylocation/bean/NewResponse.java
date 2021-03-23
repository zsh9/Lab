package com.example.map.mylocation.bean;

import java.io.Serializable;
import java.util.List;

public class NewResponse implements Serializable {


    private int code;
    private String msg;
    private List<Newslist> newslist;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setNewslist(List<Newslist> newslist) {
        this.newslist = newslist;
    }

    public List<Newslist> getNewslist() {
        return newslist;
    }
}
