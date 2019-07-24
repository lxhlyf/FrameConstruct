package com.framelibrary.network

import com.framelibrary.bean.HomeBean
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by 简言 on 2019/7/22  7:40.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.framelibrary.network
 * Description :
 */
interface ApiService {

    companion object{
        val BASE_URL : String
            get() = "http://baobab.kaiyanapp.com/api/"

        val XIAN_DU_CATEGORIES:String
            get() = " http://gank.io/api/"
    }

    //获取首页第一页数据
    @Headers("url_name:BASE_URL")
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @Headers("url_name:BASE_URL")
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date :String, @Query("num") num :String) : Observable<HomeBean>


    //获取闲读的分类
    @Headers("url_name:XIAN_DU_CATEGORIES")
    @GET("xiandu/categories")
    fun getXianDuCategories():Observable<XianCategriesBean>

    @Headers("url_name:XIAN_DU_CATEGORIES")
    @GET("today")
    fun getHomeTodayBean():Observable<HomeTodayBean>
}