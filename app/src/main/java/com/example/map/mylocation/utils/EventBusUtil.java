package com.example.map.mylocation.utils;



import org.greenrobot.eventbus.EventBus;

/**事件发送的工具类
 * Created by Android开发 on 2018/8/28.
 */

public class EventBusUtil {


    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
