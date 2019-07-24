package com.lib_third.ui.fragment

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.base.BaseFragment
import com.baselibrary.Constant
import com.bean.RecyclerInfo
import com.lib_third.R
import com.lib_third.ui.adapter.ThirdAdapter

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.THIRD_FRAGMENT)
class ThirdFragment : BaseFragment<String>() {

    private var thirdRv: RecyclerView? = null
    private var adapter: ThirdAdapter? = null

    override fun provideContentViewId(): Int {
        return R.layout.fragment_third
    }

    override fun initView(rootView: View) {

        ARouter.getInstance().inject(this)

        thirdRv = rootView.findViewById(R.id.third_rv)
        thirdRv!!.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = ThirdAdapter(context, RecyclerInfo.defaultStag)
        thirdRv!!.adapter = adapter
    }

    override fun initData() {

    }

    override fun initListener() {

    }
}
