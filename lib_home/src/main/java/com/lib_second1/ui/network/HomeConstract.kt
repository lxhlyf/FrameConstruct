package com.lib_second1.ui.network

import com.baselibrary.base.BasePresenter
import com.baselibrary.base.BaseView
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean

/**
 * Created by 简言 on 2019/7/23  7:45.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : com.lib_second1.ui.network
 * Description :
 */
interface HomeConstract {

    interface View : BaseView<Presenter> {
        fun setXianDuData(bean : XianCategriesBean)
        fun setTodayData(bean: HomeTodayBean)
    }
    interface Presenter : BasePresenter {
        fun requestData()
    }
}