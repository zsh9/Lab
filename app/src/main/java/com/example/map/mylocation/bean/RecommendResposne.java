package com.example.map.mylocation.bean;

import java.util.List;

public class RecommendResposne extends BaseResponse {

    public List<Recom> resultobj;

    public List<Recom> getResultobj() {
        return resultobj;
    }

    public void setResultobj(List<Recom> resultobj) {
        this.resultobj = resultobj;
    }
}
