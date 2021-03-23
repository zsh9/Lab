package com.example.map.mylocation.bean;


import com.example.map.mylocation.bean.BaseResponse;
import com.example.map.mylocation.bean.LoginBean;

public class LoginResponse extends BaseResponse {

    private LoginBean resultobj;

    public LoginBean getResultobj() {
        return resultobj;
    }

    public void setResultobj(LoginBean resultobj) {
        this.resultobj = resultobj;
    }
}
