package com.duanlian.daimeng.base;

import android.content.Context;
import android.content.DialogInterface;

/**
 * View层接口基类
 *
 * @author Hunter
 */
public interface BaseView {
    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    /**
     * 根据字符串资源弹出toast
     */
    void showToast(int res);

    /**
     * 获取上下文对象
     */
    Context getContext();
}
