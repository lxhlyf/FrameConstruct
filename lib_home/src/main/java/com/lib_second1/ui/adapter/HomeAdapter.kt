package com.lib_second1.ui.adapter

import android.content.Context
import android.media.Image
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.Constant
import com.baselibrary.utils.ImageLoadUtils
import com.bean.RecyclerInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean
import com.lib_second1.R
import com.lib_second1.bean.HomePageTable
import com.lib_second1.ui.network.HomeConstract
import com.ui.banner.BannerAdapter
import com.ui.banner.BannerView
import com.ui.banner.BannerViewPager
import org.jetbrains.annotations.NotNull
import java.util.Objects
import java.util.Random

/**
 * Created by 简言 on 2019/6/17  11:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_second1.ui.adapter
 * Description :
 */
class HomeAdapter(private val context: Context,val mXianList: List<XianCategriesBean.ResultsBean>,var mTodayBean: HomeTodayBean.ResultsBean?)//this.size = mTodayBean.getAndroid().size()+mTodayBean.getApp().size()+mTodayBean.getIOS().size()+mTodayBean.get休息视频().size()+mTodayBean.get前端().size()+mTodayBean.get拓展资源().size()+mTodayBean.get瞎推荐().size();
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    //Banner点击监听器
//    override fun click(position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    private val size: Int = 0

    fun setmTodayBean(bean: HomeTodayBean.ResultsBean) {

        this.mTodayBean = bean

        //Log.i("todady3", ""+mTodayBean.getAndroid().get(0).getDesc());
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {

            return TYPE_BANNER
        } else if (position == 1) {

            return TYPE_TABLE
        }
        return TYPE_CONTENT
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeViewHolder {
        var view: View? = null
        if (getItemViewType(i) == TYPE_BANNER) {

            view = LayoutInflater.from(context).inflate(R.layout.home_banner_item, viewGroup, false)
            initBanner(view)
            Log.i("banner", "haha")
        } else if (getItemViewType(i) == TYPE_TABLE) {

            view = LayoutInflater.from(context).inflate(R.layout.home_table_item, viewGroup, false)
        } else if (getItemViewType(i) == TYPE_CONTENT) {

            view = LayoutInflater.from(context).inflate(R.layout.adapter_home, viewGroup, false)
        }
        return HomeViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return if (mTodayBean != null) mTodayBean!!.android.size else 50
    }

    override fun onBindViewHolder(homeViewHolder: HomeViewHolder, i: Int) {

        if (getItemViewType(i) == TYPE_BANNER) {

            //homeViewHolder.bannerView?.let { initBanner(it) }
        } else if (getItemViewType(i) == TYPE_TABLE) {

            if (mXianList.size > 0) {

                for (j in mXianList.indices) {

                    if (j == 0)
                        homeViewHolder.tablefirst?.text = mXianList[j].name
                    if (j == 1)
                        homeViewHolder.tablesecond?.text = mXianList[j].name
                    if (j == 2)
                        homeViewHolder.tablethird?.text = mXianList[j].name
                    if (j == 3)
                        homeViewHolder.tablefourth?.text = mXianList[j].name
                    if (j == 4)
                        homeViewHolder.tablefifth?.text = mXianList[j].name
                }
            }
        } else {

            if (mTodayBean != null) {

                if (mTodayBean!!.android[i].images != null){

                    ImageLoadUtils.display(context, homeViewHolder.itemImage, mTodayBean!!.android[i].images[0])
                }else{

                    homeViewHolder.itemImage?.visibility = View.GONE
                }
                homeViewHolder.itemImage?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.skirt15))
                homeViewHolder.itemTitle?.text = mTodayBean!!.android[i].desc
                homeViewHolder.itemPrice?.text = mTodayBean!!.android[i].who
                homeViewHolder.itemPayed?.text = mTodayBean!!.android[i].publishedAt
            }

            homeViewHolder.itemView.setOnClickListener { ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation() }
        }
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var bannerView: View? = null

        var tablefirst: TextView? = null
        var tablesecond: TextView? = null
        var tablethird: TextView? = null
        var tablefourth: TextView? = null
        var tablefifth: TextView? = null

        var tableIcon: ImageView? = null

        var itemImage: ImageView? = null
        var itemTitle: TextView? = null
        var itemPrice: TextView? = null
        var itemPayed: TextView? = null

        init {

            if(itemViewType == TYPE_BANNER){

                bannerView = itemView
                Log.i("TYPE_TABLE", "" + itemViewType)
            }else if(itemViewType == TYPE_TABLE){

                tableIcon = itemView.findViewById(R.id.ib_first)
                tablefirst = itemView.findViewById(R.id.tv_first)
                tablesecond = itemView.findViewById(R.id.tv_second)
                tablethird = itemView.findViewById(R.id.tv_third)
                tablefourth = itemView.findViewById(R.id.tv_fourth)
                tablefifth = itemView.findViewById(R.id.tv_fifth)
            }else{

                itemImage = itemView.findViewById(R.id.item_image)
                itemTitle = itemView.findViewById(R.id.item_title)
                itemPrice = itemView.findViewById(R.id.item_price)
                itemPayed = itemView.findViewById(R.id.item_payed)
            }
        }
    }

    companion object {

        private val TYPE_BANNER = 0
        private val TYPE_TABLE = 1
        private val TYPE_CONTENT = 2
    }

    fun initBanner(itemView: View) {

        // 后台没有轮播那就不添加
        if (RecyclerInfo.defaultStag.size < 0) {
            return
        }
        val bannerView = LayoutInflater.from(context).inflate(R.layout.layout_banner_view, itemView as ViewGroup, false) as BannerView

        // 自己把万能的无限轮播看一下
        bannerView.setAdapter(object : BannerAdapter() {

            override fun getView(position: Int, convertView: View?): View {
                var convertView = convertView
                if (convertView == null) {
                    convertView = ImageView(context)
                }
                (convertView as ImageView).scaleType = ImageView.ScaleType.CENTER_CROP
                val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                convertView.layoutParams = params

                convertView.setImageResource(RecyclerInfo.defaultStag.get(position).pic_id)
                return convertView
            }

            override fun getBannerDesc(position: Int): String {
                return RecyclerInfo.defaultStag.get(position).desc
            }

            override fun getCount(): Int {
                return 6
            }
        })

        //bannerView.setOnBannerItemClickListener(this)
        // 开启滚动
        bannerView.startRoll()

        Log.i("banner", "我进到这个方法里面了")

        itemView.addView(bannerView, 0)
    }

}

