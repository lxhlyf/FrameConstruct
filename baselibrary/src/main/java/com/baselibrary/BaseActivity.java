package com.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.LinkedList;


/**
 * Created by 简言 on 2019/5/17  15:40.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.baselibrary
 * Description :
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Activity currentActivity;

    private static long mPreTime = 0;

    private LinkedList mActiviitys = new LinkedList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(ProviderView());

        synchronized (this) {
            mActiviitys.add(this);
        }

        initView();

        initData();

        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentActivity = null;
    }

    protected abstract void initView();

    protected abstract int ProviderView();

    protected void initData() {
    }

    protected void initListener() {
    }

    /**
     * 设置Setting 这个菜单项
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);  //toolBar 上的Menu setting

        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.search_view_example));
//        searchView.setIconifiedByDefault(false);
//        searchItem.expandActionView();

        return true;
    }

    /**
     * 设置 Setting的监听器
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            super.onBackPressed();
            return true;
        } else if (i == R.id.search) {//这里进行一些搜索的操作
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //判断是否注册了EventBus
//    public boolean isEventBusRegisted(Object subscribe) {
//        return EventBus.getDefault().isRegistered(subscribe);
//    }
//
//    //注册EventBus
//    public void registerEventBus(Object subscribe) {
//        if (!isEventBusRegisted(subscribe)) {
//            EventBus.getDefault().register(subscribe);
//        }
//    }
//
//    //取消注册EventBus
//    public void unregisterEventBus(Object subscribe) {
//        if (isEventBusRegisted(subscribe)) {
//            EventBus.getDefault().unregister(subscribe);
//        }
//    }

    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {

        //如果是主页面
        if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
            Toast.makeText(this, "再按一次，退出应用", Toast.LENGTH_SHORT).show();
            mPreTime = System.currentTimeMillis();
            return;
        }else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {

        synchronized (this) {
            mActiviitys.remove(this);
        }
        super.onDestroy();
    }
}
