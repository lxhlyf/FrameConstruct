package com.framelibrary.network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by 简言 on 2019/7/22  7:39.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.framelibrary.network
 * Description :
 */
class RetrofitClient private constructor(context: Context, baseUrl:String){

    var httpCacheDirectory : File? = null
    val mContext : Context = context
    var cache : Cache? = null
    var okHttpClient : OkHttpClient? = null
    var retrofit : Retrofit? = null
    val DEFAULT_TIMEOUT : Long = 20
    val url = baseUrl

    init {

        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory!!, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }

        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(BaseUrlChangeInterceptor())
                .addInterceptor(CacheIntercepter(context))
                .addNetworkInterceptor(CacheIntercepter(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    companion object{

        @SuppressLint("StaticFieldLeak")
        @Volatile
        var instence:RetrofitClient? = null

        fun getInstence(context: Context,baseUrl: String):RetrofitClient{
            if (instence == null){
                synchronized(RetrofitClient::class){
                    if (instence == null){
                        instence = RetrofitClient(context, baseUrl)
                    }
                }
            }
            return instence!!
        }
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }
}