package com.lib_person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.Constant;
import com.baselibrary.utils.SpCache;

/**
 * Created by 简言 on 2019/6/15  10:53.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_person
 * Description :
 */

@Route(path = Constant.SETTINGS_ACTIVITY)
public class SettingsAvtivity extends AppCompatActivity {

    private Button offlilne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_acctivity);
        ARouter.getInstance().inject(this);

        offlilne = findViewById(R.id.settlings_offline);

        offlilne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpCache.getInstance(SettingsAvtivity.this).putIsLogin(false);
                finish();
            }
        });
    }
}
