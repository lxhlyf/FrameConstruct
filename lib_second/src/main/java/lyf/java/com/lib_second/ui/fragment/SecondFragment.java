package lyf.java.com.lib_second.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseFragment;
import com.baselibrary.Constant;

import lyf.java.com.lib_second.R;
import lyf.java.com.lib_second.ui.adapter.SecondAdapter;

/**
 * Created by 简言 on 2019/6/16  13:58.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : lyf.java.com.lib_second.ui.fragment
 * Description :
 */

@Route(path = Constant.SECOND_ACTIVITY)
public class SecondFragment extends BaseFragment {

    private RecyclerView secondRv;
    private SecondAdapter adapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView(View rootView) {

        ARouter.getInstance().inject(this);

        secondRv = rootView.findViewById(R.id.second_rv);
        secondRv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SecondAdapter(context, null);
        secondRv.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
