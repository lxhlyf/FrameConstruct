package lyf.java.com.lib_second.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.Constant
import com.baselibrary.utils.ImageLoadUtils
import com.baselibrary.view.RecyclerBaseAdapter
import com.framelibrary.bean.HomeBean
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_second.*
import lyf.java.com.lib_second.R

/**
 * Created by 简言 on 2019/6/17  11:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_second1.ui.adapter
 * Description :
 */
class SecondAdapter(context: Context, @JvmField private val mList: List<HomeBean.IssueListBean.ItemListBean>) : RecyclerBaseAdapter<SecondAdapter.ItemHolder>(context) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ItemHolder).bind(mList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_second, parent, false)
        return ItemHolder(context, view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemHolder(val context: Context, override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(item: HomeBean.IssueListBean.ItemListBean) {
            //因为运用了插件LayoutContainer，所以这里可以直接使用控件对象

            tv_title.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")

            var title = item.data?.title
            var category = item.data?.category
            var minute = item.data?.duration?.div(60)
            var second = item.data?.duration?.minus((minute?.times(60)) as Long )
            var realMinute : String
            var realSecond : String

            if(minute != null && minute < 10){
                realMinute = "0"+minute
            }else{
                realMinute = minute.toString()
            }
            if(second != null && second < 10){
                realSecond = "0"+second
            }else{
                realSecond = second.toString()
            }
            var playUrl = item.data?.playUrl
            var photo:String? = item.data?.cover?.feed
            var author = item.data?.author

            if(photo != null){
                ImageLoadUtils.display(context,iv_photo, photo)
            }
            tv_title?.text = title
            tv_detail?.text = "发布于 $category / $realMinute:$realSecond"
            if(author!=null){
                ImageLoadUtils.display(context,iv_user,author.icon)
            }else{
                iv_user.visibility = View.GONE
            }

            itemView.setOnClickListener { ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation() }
        }
    }
}
