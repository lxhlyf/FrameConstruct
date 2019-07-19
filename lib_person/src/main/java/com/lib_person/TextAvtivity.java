package com.lib_person;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.BaseActivity;
import com.baselibrary.Constant;

/**
 * Created by 简言 on 2019/6/15  10:53.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_person
 * Description :
 */

@Route(path = Constant.TEXT_ACTIVITY)
public class TextAvtivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("等待数据，有了数据才可以进行界面的填充");
        tv.setGravity(ViewGroup.TEXT_ALIGNMENT_CENTER);
        tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(tv);

        ARouter.getInstance().inject(this);
    }
}
