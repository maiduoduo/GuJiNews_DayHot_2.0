package com.cnews.guji.smart.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * 基类方法接口
 * @author JSYL-DCL
 */
public interface IBase {
    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    void intBase();
    /**
     * 获取视图资源地址
     * @return
     */
    int getLayoutId();

    /**
     * 创建/获取子类视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    /**
     * 初始化presenter业务处理器
     */
    void initPresenter();

    /**
     * 获取根视图
     * @return
     */
    View getParentView();

    /**
     * 绑定控件
     * @param view
     * @param savedInstanceState
     */
    void bindView(View view, Bundle savedInstanceState);

    /**
     * 绑定数据
     */
    void bindData();

    /**
     * 绑定监听
     */
    void initListener();

}
