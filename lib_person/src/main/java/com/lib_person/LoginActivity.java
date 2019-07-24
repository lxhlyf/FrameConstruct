package com.lib_person;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.base.BaseActivity;
import com.baselibrary.Constant;
import com.baselibrary.utils.SpCache;

@Route(path = Constant.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {

    private Button btn_login;

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);

        btn_login = findViewById(R.id.btn_login);

        if (SpCache.getInstance(LoginActivity.this).getIsLogin()){
            //跳到主界面
            ARouter.getInstance().build("/app/MainActivity").navigation();
            finish();
            return;
        }

        //进行登录或注册的操作
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!SpCache.getInstance(LoginActivity.this).getIsLogin()) {
                    SpCache.getInstance(LoginActivity.this).putIsLogin(true);
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    //跳到主界面
                    ARouter.getInstance().build("/app/MainActivity").navigation();
                    finish();
                }
            }
        });

    }

    @Override
    protected int ProviderView() {
        return R.layout.activity_login;
    }
}
