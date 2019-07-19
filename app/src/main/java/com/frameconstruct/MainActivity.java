package com.frameconstruct;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseActivity;
import com.baselibrary.BaseFragment;
import com.baselibrary.Constant;
import com.frameconstruct.ndk.NdkMethod;
import com.frameconstruct.utils.FragmentManagerHelper;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, NavigationView.OnNavigationItemSelectedListener {

    private NdkMethod ndkMethod = new NdkMethod();

    private RadioGroup mainRg;
    private DrawerLayout mDrawer;

    private BaseFragment homeFragment;
    private BaseFragment secondFragment;
    private BaseFragment thirdFragment;
    private BaseFragment fourthFragment;

    private FragmentManagerHelper fragmentManagerHelper;

    @Override
    protected int ProviderView() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        final Toolbar toolbar = findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        mDrawer = findViewById(R.id.drawer_layout);
        mDrawer.addDrawerListener(new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close));

        final NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(this);

        ARouter.getInstance().inject(this);
        mainRg = findViewById(R.id.main_rg);

        mainRg.setOnCheckedChangeListener(this);

        fragmentManagerHelper = new FragmentManagerHelper(this, getSupportFragmentManager(), R.id.main_tab_fl);
        if (homeFragment == null) {
            homeFragment = (BaseFragment) ARouter.getInstance().build("/lib_home/HomeFragment").navigation();
        }
        fragmentManagerHelper.switchFragment(homeFragment);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.home_rb:
                fragmentManagerHelper.switchFragment(homeFragment);
                break;
            case R.id.bo_rb:
                if (secondFragment == null){
                    secondFragment = (BaseFragment) ARouter.getInstance().build(Constant.SECOND_ACTIVITY).navigation();
                }
                fragmentManagerHelper.switchFragment(secondFragment);
                break;
            case R.id.ji_rb:
                if (thirdFragment == null){
                    thirdFragment = (BaseFragment) ARouter.getInstance().build(Constant.THIRD_FRAGMENT).navigation();
                }
                fragmentManagerHelper.switchFragment(thirdFragment);
                break;
            case R.id.shu_rb:
                if (fourthFragment == null){
                    fourthFragment= (BaseFragment) ARouter.getInstance().build(Constant.FOURTH_FRAGMENT).navigation();
                }
                fragmentManagerHelper.switchFragment(fourthFragment);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        mDrawer.closeDrawers();
        final int mItemId = menuItem.getItemId();
        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mItemId){

                    case R.id.drawer_home:
                        ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation();
                        break;
                    case R.id.drawer_professor:
                        ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation();
                        break;
                    case R.id.drawer_tec_news:
                        ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation();
                        break;
                    case R.id.drawer_settings:
                        ARouter.getInstance().build(Constant.SETTINGS_ACTIVITY).navigation();
                        break;
                }
            }
        }, 75);
        return true;
    }

    @Override
    protected void onDestroy() {
        fragmentManagerHelper.clearFragment();
        super.onDestroy();
    }
}
