package com.lib_second1.ui.network.model

import android.content.Context
import com.framelibrary.bean.HomeBean
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean
import com.framelibrary.network.ApiService
import com.framelibrary.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by 简言 on 2019/7/23  7:54.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.lib_second1.ui.network.model
 * Description :
 */
class HomeModel constructor(context: Context){

    val retrofitClient:RetrofitClient
    val apiService:ApiService?

    init{
          retrofitClient = RetrofitClient.getInstence(context, ApiService.BASE_URL)
          apiService = retrofitClient.create(ApiService::class.java)
    }

    fun loadXianDuData(): Observable<XianCategriesBean>? {

        return apiService?.getXianDuCategories()
    }

    fun loadTodayData():Observable<HomeTodayBean>?{

        return apiService?.getHomeTodayBean()
    }
}