package com.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 简言 on 2019/6/16  11:30.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.baselibrary.utils
 * Description :
 */
public class SpCache {

    private Context context;

    private static SpCache instance;

    private SpCache(Context context){

        this.context = context;
    }

    public static SpCache getInstance(Context context){

        if (instance == null){

            synchronized (SpCache.class){
                if (instance == null){

                    instance = new SpCache(context);
                }
            }
        }
        return instance;
    }


    public final boolean getIsLogin() {
        SharedPreferences sp = context.getSharedPreferences("lib_person", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }

    public final void putIsLogin(boolean isLogin) {
        SharedPreferences sp = context.getSharedPreferences("lib_person", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isLogin", isLogin).apply();
    }
}
