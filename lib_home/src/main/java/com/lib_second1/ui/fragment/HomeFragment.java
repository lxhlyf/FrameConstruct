package com.lib_second1.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseFragment;
import com.lib_second1.R;
import com.lib_second1.ui.adapter.HomeAdapter;

/**
 * Created by 简言 on 2019/6/16  12:28.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_home.ui.fragment
 * Description :
 */

@Route(path = "/lib_home/HomeFragment")
public class HomeFragment extends BaseFragment {

    private RecyclerView homeRv;
    private HomeAdapter adapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {

        ARouter.getInstance().inject(this);

        homeRv = rootView.findViewById(R.id.home_rv);
        homeRv.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new HomeAdapter(context, null);
        homeRv.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
