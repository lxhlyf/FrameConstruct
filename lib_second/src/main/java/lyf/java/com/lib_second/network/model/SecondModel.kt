package lyf.java.com.lib_second.network.model

import android.content.Context
import com.framelibrary.bean.HomeBean
import com.framelibrary.network.ApiService
import com.framelibrary.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by 简言 on 2019/7/22  9:53.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 * @package : lyf.java.com.lib_second.network.model
 * Description :
 */
class SecondModel {


    fun loadData(context: Context, isFirst: Boolean, data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstence(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        when(isFirst) {
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}