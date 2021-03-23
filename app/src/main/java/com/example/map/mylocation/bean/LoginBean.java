package com.example.map.mylocation.bean;

public class LoginBean {

    private String username;
    private String userpassword;
    private String createdtime;


    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreatedtime() {
        return createdtime;
    }

}
