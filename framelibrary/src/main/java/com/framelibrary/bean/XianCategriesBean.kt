package com.framelibrary.bean

/**
 * Created by 简言 on 2019/7/23  7:19.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.framelibrary.bean
 * Description :
 */
data class XianCategriesBean constructor(var results: List<ResultsBean>? = null) {


    data class ResultsBean constructor(var id: String? = null, var en_name: String? = null, var name: String? = null, var rank: Int = 0)
}
