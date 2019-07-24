package lyf.java.com.lib_second.network.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.baselibrary.utils.applySchedulers
import com.framelibrary.bean.HomeBean
import io.reactivex.Observable
import lyf.java.com.lib_second.network.SecondContract
import lyf.java.com.lib_second.network.model.SecondModel

/**
 * Created by 简言 on 2019/7/22  9:40.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : lyf.java.com.lib_second.network.presenter
 * Description :
 */
class SecondPresenter constructor(context: Context, view : SecondContract.View) : SecondContract.Presenter{

    var mContext : Context? = null
    var mView : SecondContract.View? = null

    val mModel : SecondModel by lazy {
        SecondModel()
    }
    init {
        mView = view
        mContext = context
    }

    override fun start() {

        requestData()
    }

    @SuppressLint("CheckResult")
    override fun requestData() {

        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,true,"0") }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            Log.i("homePresenter", homeBean.toString())
            mView?.setData(homeBean)
        }
    }

    @SuppressLint("CheckResult")
    fun moreData(data: String?){
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,false,data) }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }
}