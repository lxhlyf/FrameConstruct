package com.lib_third.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.Constant
import com.baselibrary.view.RecyclerBaseAdapter
import com.bean.RecyclerInfo
import com.lib_third.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_third.*


/**
 * Created by 简言 on 2019/6/17  11:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.li_second1.ui.adapter
 * Description :
 */
class ThirdAdapter(context: Context, @JvmField private val infos: MutableList<RecyclerInfo>) : RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.adapter_third, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(infos[position])
    }

    //注意这里要去掉inner，否则运行报错“java.lang.NoSuchMethodError: No virtual method _$_findCachedViewById”
    class ItemHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: RecyclerInfo) {
            //因为运用了插件LayoutContainer，所以这里可以直接使用控件对象
            iv_pic.setImageResource(item.pic_id)
            tv_title.text = item.title
        }
    }

}