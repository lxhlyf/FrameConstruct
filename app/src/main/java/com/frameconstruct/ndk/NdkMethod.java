package com.frameconstruct.ndk;

/**
 * Created by 简言 on 2019/6/15  9:20.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.frameconstruct.ndk
 * Description :
 */
public class NdkMethod {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
