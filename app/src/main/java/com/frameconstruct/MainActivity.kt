package com.frameconstruct

import android.graphics.Color
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.RadioGroup

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.baselibrary.base.BaseActivity
import com.baselibrary.base.BaseFragment
import com.baselibrary.Constant
import com.baselibrary.event.FreshToolBarEvent
import com.frameconstruct.ndk.NdkMethod
import com.frameconstruct.utils.FragmentManagerHelper
import com.lib_fouth.ui.fragment.FourthFragment
import com.lib_second1.ui.fragment.HomeFragment
import com.lib_third.ui.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*
import lyf.java.com.lib_second.ui.fragment.SecondFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = "/app/MainActivity")
class MainActivity : BaseActivity(), RadioGroup.OnCheckedChangeListener, NavigationView.OnNavigationItemSelectedListener {

    private val ndkMethod = NdkMethod()

    private var homeFragment: HomeFragment? = null
    private var secondFragment: SecondFragment? = null
    private var thirdFragment: ThirdFragment? = null
    private var fourthFragment: FourthFragment? = null

    private var homeBannerShow = true

    private var fragmentManagerHelper: FragmentManagerHelper? = null

    override fun ProviderView(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        //val toolbar = findViewById<Toolbar>(R.id.appbar_toolbar)
        setSupportActionBar(appbar_toolbar)
        appbar_toolbar.setTitle(R.string.app_name)
        appbar_toolbar.setNavigationIcon(R.drawable.ic_menu)

        //mDrawer = findViewById(R.id.drawer_layout)
        drawer_layout.addDrawerListener(ActionBarDrawerToggle(this, drawer_layout, appbar_toolbar, R.string.drawer_open, R.string.drawer_close))

        //val navView = findViewById<View>(R.id.navigation_view) as NavigationView
        navigation_view.setNavigationItemSelectedListener(this)

        ARouter.getInstance().inject(this)
        //mainRg = findViewById(R.id.main_rg)

        main_rg.setOnCheckedChangeListener(this)

        fragmentManagerHelper = FragmentManagerHelper(this, supportFragmentManager, R.id.main_tab_fl)
        if (homeFragment == null) {
            homeFragment = ARouter.getInstance().build(Constant.HOME_FRAGMENT).navigation() as HomeFragment?
        }
        fragmentManagerHelper!!.switchFragment(homeFragment)
    }


    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {

        when (checkedId) {
            R.id.home_rb -> {

                fragmentManagerHelper!!.switchFragment(homeFragment)
                // 这里从其他fragment跳到homeFragment的时候会再次设置，但是不一定处于Banner的位置。
                //方案： 将banner是否可见的boolean返回过来
                if (homeBannerShow){
                    //当banner是显示的状态就将toolbar设置称透明的。
                    appbar_toolbar.setBackgroundColor(Color.TRANSPARENT)
                }
                (main_tab_fl.layoutParams as FrameLayout.LayoutParams).let { it.topMargin = 0 }
            }
            R.id.bo_rb -> {
                if (secondFragment == null) {
                    secondFragment = ARouter.getInstance().build(Constant.SECOND_ACTIVITY).navigation() as SecondFragment?
                }
                fragmentManagerHelper!!.switchFragment(secondFragment)

                //对toolbar进行一些修改
                appbar_toolbar.setBackgroundColor(Color.WHITE)
                (main_tab_fl.layoutParams as FrameLayout.LayoutParams).let { it.topMargin = appbar_toolbar.height }

            }
            R.id.ji_rb -> {
                if (thirdFragment == null) {
                    thirdFragment = ARouter.getInstance().build(Constant.THIRD_FRAGMENT).navigation() as ThirdFragment
                }
                fragmentManagerHelper!!.switchFragment(thirdFragment)

                appbar_toolbar.setBackgroundColor(Color.WHITE)
                (main_tab_fl.layoutParams as FrameLayout.LayoutParams).let { it.topMargin = appbar_toolbar.height }
            }
            R.id.shu_rb -> {
                if (fourthFragment == null) {
                    fourthFragment = ARouter.getInstance().build(Constant.FOURTH_FRAGMENT).navigation() as FourthFragment
                }
                fragmentManagerHelper!!.switchFragment(fourthFragment)

                appbar_toolbar.setBackgroundColor(Color.WHITE)
                (main_tab_fl.layoutParams as FrameLayout.LayoutParams).let { it.topMargin = appbar_toolbar.height }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        drawer_layout!!.closeDrawers()
        val mItemId = menuItem.itemId
        drawer_layout!!.postDelayed({
            when (mItemId) {

                R.id.drawer_home -> ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation()
                R.id.drawer_professor -> ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation()
                R.id.drawer_tec_news -> ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation()
                R.id.drawer_settings -> ARouter.getInstance().build(Constant.SETTINGS_ACTIVITY).navigation()
            }
        }, 75)
        return true
    }

    var isFirstChange = false
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public fun freshTollbar(event: FreshToolBarEvent) {

        if (event.isTrans!!) {

            //toast("toolbar透明")
            if(isFirstChange){
                isFirstChange = false
                homeBannerShow = true
                appbar_toolbar.setBackgroundColor(Color.TRANSPARENT)
            }
        } else {

            //toast("toolbar不透明")
            if(!isFirstChange){
                isFirstChange = true
                homeBannerShow = false
                appbar_toolbar.setBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerEventBus(this)
    }

    override fun onStop() {
        super.onStop()
        unregisterEventBus(this)
    }

    override fun onDestroy() {
        fragmentManagerHelper!!.clearFragment()
        super.onDestroy()
    }
}


