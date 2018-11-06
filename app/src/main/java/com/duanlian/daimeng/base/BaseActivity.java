package com.duanlian.daimeng.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.duanlian.daimeng.R;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by 段炼 on 2017/9/12.
 * Description : activity基类
 */

public abstract class BaseActivity extends FragmentActivity implements BaseView {
    private ImmersionBar immersionBar;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化沉浸式状态栏
        immersionBar = ImmersionBar.with(this);
        immersionBar
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
        setContentView(setContentView());
        // 初始化View注入
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    /**
     * 初始化布局
     *
     * @return int 类型的布局
     */
    public abstract int setContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract void initPresenter();

    @Override
    public void showToast(int res) {
        showToast(getString(res));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            if (toast == null) {
                toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }

            toast.show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }
}
