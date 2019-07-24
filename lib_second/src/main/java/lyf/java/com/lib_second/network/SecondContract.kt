package lyf.java.com.lib_second.network

import com.baselibrary.base.BasePresenter
import com.baselibrary.base.BaseView
import com.framelibrary.bean.HomeBean

/**
 * Created by lvruheng on 2017/7/5.
 */
interface SecondContract{
    interface View : BaseView<Presenter> {
        fun setData(bean : HomeBean)
    }
    interface Presenter : BasePresenter {
        fun requestData()
    }
}