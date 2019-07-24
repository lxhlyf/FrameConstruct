package lyf.java.com.lib_second.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.base.BaseFragment
import com.baselibrary.Constant
import com.framelibrary.bean.HomeBean

import lyf.java.com.lib_second.R
import lyf.java.com.lib_second.network.SecondContract
import lyf.java.com.lib_second.network.presenter.SecondPresenter
import lyf.java.com.lib_second.ui.adapter.SecondAdapter
import java.util.ArrayList
import java.util.regex.Pattern

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.SECOND_ACTIVITY)
class SecondFragment : BaseFragment<SecondPresenter>(), SecondContract.View, SwipeRefreshLayout.OnRefreshListener {


    var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
    private var refreshLayout:SwipeRefreshLayout? = null
    private var secondRv: RecyclerView? = null
    private var adapter: SecondAdapter? = null
    var data: String? = null
    var mIsRefresh: Boolean = false
    /**
     * 请求成功之后presenter返回数据给View层
     */
    override fun setData(bean: HomeBean) {

        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) { //如果是下拉刷新，就会将数据清空一下。
            mIsRefresh = false
            refreshLayout!!.isRefreshing = false  //把下拉刷新的圈取消掉
            if (mList.size > 0) {
                mList.clear()  //把集合清空
            }

        }

        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }

        Log.i("homeBean", mList.toString())
        adapter?.notifyDataSetChanged()
    }


    override fun provideContentViewId(): Int {
        return R.layout.fragment_second
    }

    override fun initPresenter(): SecondPresenter {
        return SecondPresenter(context, this)
    }

    override fun initView(rootView: View) {

        ARouter.getInstance().inject(this)

        refreshLayout = rootView.findViewById(R.id.refreshLayout)
        secondRv = rootView.findViewById(R.id.second_rv)
        secondRv!!.layoutManager = LinearLayoutManager(context)
        adapter = SecondAdapter(context, mList)
        secondRv!!.adapter = adapter
    }

    override fun onRefresh() {
        if (!mIsRefresh) {  //如果没有正在进行刷新，就进行刷新
            mIsRefresh = true
            mPresenter?.start()
        }
    }

    override fun initData() {

        mPresenter.start()
    }

    override fun initListener() {

        refreshLayout!!.setOnRefreshListener(this)
        //滚到底部进行加载更多。
        secondRv!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPositon = layoutManager.findLastVisibleItemPosition()

                //如果处于闲置状态，并且当前列表已经触底，就进行下一页数据的加载。
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    if (data != null) {
                        mPresenter?.moreData(data)
                    }
                }
            }
        })
    }
}
