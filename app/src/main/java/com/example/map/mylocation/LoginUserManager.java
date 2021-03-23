package com.example.map.mylocation;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

/**
 * 用户管理类
 * Created by Android开发 on 2018/9/19.
 */

public class LoginUserManager {

    /**
     * 保存用户信息
     *
     * @param pwd
     */
    public static void saveUserMsg(String account, String pwd, String sex, int id) {
        SPUtils.getInstance().put("ACCOUNT", account);
        SPUtils.getInstance().put("PWD", pwd);
        SPUtils.getInstance().put("SEX", sex);
        SPUtils.getInstance().put("USERID", id);


    }

    /**
     * 获取用户的账号
     *
     * @return
     */
    public static boolean getIsTeacher() {

        if (SPUtils.getInstance().getInt("YEZHUWUYE") == 0) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * 获取用户的账号
     *
     * @return
     */
    public static void setUserIsTeOrStu(int type) {

        SPUtils.getInstance().put("YEZHUWUYE", type);


    }
    /**
     * 用户id
     *
     * @param id
     */
    public static void setuserId(String id) {
        SPUtils.getInstance().put("USERID", id);
    }


    public static String getuserId() {
        return SPUtils.getInstance().getString("USERID");
    }


    /**
     * 获取用户的账号
     *
     * @return
     */
    public static String getUserPwd() {
        /**
         * 每次保存先清空
         */
        return SPUtils.getInstance().getString("PWD");

    }

    /**
     * 获取用户的账号
     *
     * @return
     */
    public static String getUserAccount() {
        /**
         * 每次保存先清空
         */
        return SPUtils.getInstance().getString("ACCOUNT");

    }

    /**
     * 退出登录
     */
    public static void loginOut() {
        SPUtils.getInstance().remove("LOGIN_IS");
        clearAll(NbApplication.getContext());
    }

    /**
     * 登录
     */
    public static void login() {
        SPUtils.getInstance().put("LOGIN_IS", "0");
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (!StringUtils.isEmpty(SPUtils.getInstance().getString("LOGIN_IS"))) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 保存性别
     *
     * @param msg
     */
    public static void setSex(String msg) {
        /**
         * 每次保存先清空
         */
        SPUtils.getInstance().remove("SEX");
        if (!StringUtils.isEmpty(String.valueOf(msg))) {
            SPUtils.getInstance().put("SEX", msg);
        }
    }

    /**
     * 获取用户的性别
     *
     * @return
     */
    public static String getUserSex() {
        /**
         * 每次保存先清空
         */
        return SPUtils.getInstance().getString("SEX");

    }


    /**
     * 保存所在地
     *
     * @param msg
     */
    public static void setWhere(String msg) {
        /**
         * 每次保存先清空
         */
        SPUtils.getInstance().remove("WHERE");
        if (!StringUtils.isEmpty(String.valueOf(msg))) {
            SPUtils.getInstance().put("WHERE", msg);
        }
    }

    /**
     * 获取用户的所在地
     *
     * @return
     */
    public static String getWhere() {
        /**
         * 每次保存先清空
         */
        return SPUtils.getInstance().getString("WHERE");

    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static void setNickName(String name) {
        /**
         * 每次保存先清空
         */
        SPUtils.getInstance().remove("NAME");
        if (!StringUtils.isEmpty(String.valueOf(name))) {
            SPUtils.getInstance().put("NAME", name);
        }
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getNickName() {
        String nianname = "输入用户名";
        if (!StringUtils.isEmpty(SPUtils.getInstance().getString("NAME"))) {
            nianname = SPUtils.getInstance().getString("NAME");
        }
        return nianname;
    }

    /**
     * 保存签名
     *
     * @param msg
     */
    public static void setSign(String msg) {
        /**
         * 每次保存先清空
         */
        SPUtils.getInstance().remove("SIGN");
        if (!StringUtils.isEmpty(String.valueOf(msg))) {
            SPUtils.getInstance().put("SIGN", msg);
        }
    }

    /**
     * 获取用户签名
     *
     * @return
     */
    public static String getSign() {
        String nianname = "输入签名";
        if (!StringUtils.isEmpty(SPUtils.getInstance().getString("SIGN"))) {
            nianname = SPUtils.getInstance().getString("SIGN");
        }
        return nianname;
    }


    /**
     * 清空用户登陆信息
     */
    public static void clearAll(Context mContext) {
        SPUtils.getInstance().clear();

    }


}
