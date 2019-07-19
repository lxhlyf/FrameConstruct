package com.lib_third.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseFragment;
import com.baselibrary.Constant;
import com.lib_third.R;
import com.lib_third.ui.adapter.ThirdAdapter;

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.THIRD_FRAGMENT)
public class ThirdFragment extends BaseFragment {

    private RecyclerView thirdRv;
    private ThirdAdapter adapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_third;
    }

    @Override
    protected void initView(View rootView) {

        ARouter.getInstance().inject(this);

        thirdRv = rootView.findViewById(R.id.third_rv);
        thirdRv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ThirdAdapter(context, null);
        thirdRv.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
