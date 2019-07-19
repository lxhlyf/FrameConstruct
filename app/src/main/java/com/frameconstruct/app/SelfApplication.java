package com.frameconstruct.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 简言 on 2019/6/15  10:06.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.frameconstruct.app
 * Description :
 */
public class SelfApplication extends Application {

    private final boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();


        if (isDebugARouter){

            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(SelfApplication.this);
    }
}
