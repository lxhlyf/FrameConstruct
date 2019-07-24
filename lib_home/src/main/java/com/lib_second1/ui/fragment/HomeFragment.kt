package com.lib_second1.ui.fragment

import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.base.BaseFragment
import com.baselibrary.Constant
import com.baselibrary.event.FreshToolBarEvent
import com.framelibrary.bean.HomeTodayBean
import com.framelibrary.bean.XianCategriesBean
import com.lib_second1.R
import com.lib_second1.ui.adapter.HomeAdapter
import com.lib_second1.ui.network.HomeConstract
import com.lib_second1.ui.network.presenter.HomePresenter
import com.ui.banner.BannerAdapter
import com.ui.banner.BannerView
import org.greenrobot.eventbus.EventBus

/**
 * Created by 简言 on 2019/6/16  12:28.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_home.ui.fragment
 * Description :
 */

@Route(path = Constant.HOME_FRAGMENT)
class HomeFragment : BaseFragment<HomePresenter>(), HomeConstract.View {

    private var tlTitle: android.support.v7.widget.Toolbar? = null
    private var ctlTitle: CollapsingToolbarLayout? = null
    private var homeRv: RecyclerView? = null
    private var adapter: HomeAdapter? = null
    private var mXianList = ArrayList<XianCategriesBean.ResultsBean>()
    private var mTodayBean:HomeTodayBean.ResultsBean? = null

    private val event:FreshToolBarEvent = FreshToolBarEvent()

    override fun setTodayData(bean: HomeTodayBean) {

        //Log.i("todady", ""+bean.results.android.get(0).desc)

        this.mTodayBean = bean.results
        Log.i("todady2", ""+mTodayBean?.android?.get(0)?.desc)
        adapter?.setmTodayBean(mTodayBean!!)
        adapter?.notifyDataSetChanged()
    }


    override fun setXianDuData(bean: XianCategriesBean) {

        bean.results?.forEach { mXianList.add(it) }

        adapter?.notifyDataSetChanged()
    }

    override fun initPresenter(): HomePresenter {
        return HomePresenter(context, this)
    }

    override fun provideContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(rootView: View) {
        ARouter.getInstance().inject(this)

        homeRv = rootView.findViewById(R.id.home_rv)
        homeRv!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = HomeAdapter(context, mXianList, mTodayBean)
        homeRv!!.adapter = adapter
    }

    override fun initData() {
        mPresenter.start()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().postSticky(event)
    }

    override fun initListener() {

        var isDown = false

        homeRv!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lm: LinearLayoutManager = homeRv!!.layoutManager as LinearLayoutManager

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                        || newState == RecyclerView.SCROLL_STATE_SETTLING) { //正在被拖动

                    if (lm.findFirstVisibleItemPosition() != 0) {

                        return
                    }

                    if (!isDown) {
                        //如果第一个显示，并且正在被向上拖动，就通知
                        //Log.i("scroll", "banner出")

                        homeRv!!.run {
                            postDelayed({
                                event.isTrans = true
                                event.isBannerShow = false
                                EventBus.getDefault().postSticky(event) //通知Toolbar不要透明了。
                            }, 200)
                        }

                    } else {

                        //发一个通知说要透明
                        homeRv!!.run {
                            postDelayed({
                                event.isTrans = false
                                event.isBannerShow = true
                                EventBus.getDefault().postSticky(event) //通知Toolbar不要透明了。
                            }, 200)
                        }
                    }


                }

                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                    if(lm.findFirstVisibleItemPosition() != 0){

                        homeRv!!.run {
                            postDelayed({
                                event.isTrans = false
                                EventBus.getDefault().postSticky(event) //通知Toolbar不要透明了。
                            }, 200)
                        }
                    }

                    if(lm.findFirstCompletelyVisibleItemPosition() == 0){

                        homeRv!!.run {
                            postDelayed({
                                event.isTrans = true
                                EventBus.getDefault().postSticky(event) //通知Toolbar要透明了。
                            }, 200)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                //将banner移动进来
                //true 向下滑动
                isDown = dy >= 0
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}
