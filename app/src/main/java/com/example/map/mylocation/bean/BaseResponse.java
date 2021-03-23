package com.example.map.mylocation.bean;

public class BaseResponse {
    /**
     * 0 成功  1 不是 0 失败 其他另说
     */
    private int resultcode;
    /**
     * 结果描述
     */
    private String resultmsg;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }
}
