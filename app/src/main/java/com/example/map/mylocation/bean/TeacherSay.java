package com.example.map.mylocation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 老师评价
 */
public class TeacherSay extends BmobObject implements Serializable {

    private String message;
    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
