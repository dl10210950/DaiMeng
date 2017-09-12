package com.duanlian.daimeng.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.duanlian.daimeng.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by 段炼 on 2017/9/12.
 * Description : activity基类
 */

public abstract class BaseActivity extends FragmentActivity {
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化沉浸式状态栏
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
        setContentView(setContentView());
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
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
