package com.example.map.mylocation;

import android.app.Application;
import android.content.Context;

import com.example.map.mylocation.common.Commons;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;


/**
 * Created by Android开发 on 2018/8/28.
 */

public class NbApplication extends Application {
    private static Context context;
    private static NbApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
        //第一：默认初始化
//        Bmob.initialize(this, Commons.APPID);
/**
 * TODO 2.2、SDK初始化方式二
 * 设置BmobConfig，允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间
 */
/**
 * 重置域名
 *
 */
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        BmobConfig config = new BmobConfig.Builder(this)
                //设置APPID
                .setApplicationId(Commons.APPID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(60)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(5500)
                .build();
        Bmob.initialize(config);
    }


    //全局获取context
    public static Context getContext() {
        return context;
    }


}
