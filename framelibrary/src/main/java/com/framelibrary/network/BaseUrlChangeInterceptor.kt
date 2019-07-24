package com.framelibrary.network

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by 简言 on 2019/7/22  8:36.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.framelibrary.network
 * Description :
 */
class BaseUrlChangeInterceptor constructor() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

//获取request
        val request = chain.request()
        //获取request的创建者builder
        val builder = request.newBuilder()
        //从request中获取headers，通过给定的键url_name
        val headerValues:List<String> = request.headers("url_name")
        if (headerValues.size > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");

            //匹配获得新的BaseUrl
            val headerValue = headerValues.get(0);
            Log.i("----headerValue----", "------" + headerValue);
            var newBaseUrl:HttpUrl? = null
            if ("BASE_URL".equals(headerValue)) {
                newBaseUrl = ApiService.BASE_URL.toHttpUrlOrNull()
            } else if ("XIAN_DU_CATEGORIES".equals(headerValue)) {
                newBaseUrl = ApiService.XIAN_DU_CATEGORIES.toHttpUrlOrNull()
            }

            //从request中获取原有的HttpUrl实例oldHttpUrl
             val oldHttpUrl:HttpUrl = request.url
            //重建新的HttpUrl，修改需要修改的url部分
            val newFullUrl:HttpUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl!!.scheme)
                    .host(newBaseUrl.host)
                    .port(newBaseUrl.port)
                    .build()

            Log.i("--------全路径-------", "------" + newFullUrl)
            //重建这个request，通过builder.url(newFullUrl).build()；
            //然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build())
        } else {
            return chain.proceed(request)
        }
    }

}