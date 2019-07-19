package com.lib_fouth.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseFragment;
import com.baselibrary.Constant;
import com.lib_fouth.R;

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.FOURTH_FRAGMENT)
public class FourthFragment extends BaseFragment {
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_fourth;
    }

    @Override
    protected void initView(View rootView) {

        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
