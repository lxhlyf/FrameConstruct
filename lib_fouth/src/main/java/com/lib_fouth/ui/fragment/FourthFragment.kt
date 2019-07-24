package com.lib_fouth.ui.fragment

import android.graphics.Typeface
import android.view.View

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.base.BaseFragment
import com.baselibrary.Constant
import com.lib_fouth.R
import kotlinx.android.synthetic.main.fragment_fourth.view.*

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.FOURTH_FRAGMENT)
class FourthFragment : BaseFragment<String>(), View.OnClickListener {
    override fun onClick(v: View?) {

        when(v?.id){
//            R.id.tv_watch ->{
//                var intent = Intent(activity,::class.java)
//                startActivity(intent)
//            }
//            R.id.tv_advise ->{
//                var intent = Intent(activity,AdviseActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.tv_save ->{
//                var intent = Intent(activity,CacheActivity::class.java)
//                startActivity(intent)
//            }
        }
    }

    override fun provideContentViewId(): Int {
        return R.layout.fragment_fourth
    }

    override fun initView(rootView: View) {

        ARouter.getInstance().inject(this)

        rootView.tv_advise.setOnClickListener(this)
        rootView.tv_watch.setOnClickListener(this)
        rootView.tv_save.setOnClickListener(this)
        rootView.tv_advise.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        rootView.tv_watch.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        rootView.tv_save.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    override fun initData() {

    }

    override fun initListener() {

    }
}
