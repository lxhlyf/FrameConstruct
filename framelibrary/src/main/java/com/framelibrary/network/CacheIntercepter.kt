package com.framelibrary.network

import android.content.Context
import android.util.Log
import com.framelibrary.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by 简言 on 2019/7/22  7:40.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.framelibrary.network
 * Description :
 */
class CacheIntercepter constructor(context:Context):Interceptor {
    val context = context
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkUtils.isNetConneted(context)) {
            //如果是联网状态
            val response = chain.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            val cacheControl = request.cacheControl.toString()
            Log.e("CacheInterceptor", "6s load cahe" + cacheControl)
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + maxAge).build()
        } else {
            //如果是没有网络的状态
            Log.e("CacheInterceptor", " no network load cahe")
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
            val response = chain.proceed(request)
            //set cahe times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).build()
        }
    }

}