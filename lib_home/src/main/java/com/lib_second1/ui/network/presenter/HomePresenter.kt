package com.lib_second1.ui.network.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.baselibrary.utils.applySchedulers
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean
import com.lib_second1.ui.network.HomeConstract
import com.lib_second1.ui.network.model.HomeModel
import io.reactivex.Observable

/**
 * Created by 简言 on 2019/7/23  7:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.lib_second1.ui.network.presenter
 * Description :
 */
class HomePresenter constructor(val context: Context, val view: HomeConstract.View?) : HomeConstract.Presenter {

    val mModel by lazy {
            HomeModel(context)

    }

    override fun start() {
        requestData()
    }

    override fun requestData() {

        val observable1: Observable<XianCategriesBean>? = context?.let { mModel.loadXianDuData() }
        observable1?.applySchedulers()?.subscribe { bean: XianCategriesBean ->

            view?.setXianDuData(bean)
        }

        val observable2: Observable<HomeTodayBean>? = context?.let { mModel.loadTodayData() }
        observable2?.applySchedulers()?.subscribe { bean: HomeTodayBean ->

            view?.setTodayData(bean)
        }
    }


}