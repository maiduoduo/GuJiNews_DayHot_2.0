package com.cnews.guji.smart.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uber.autodispose.AutoDisposeConverter;

/**
 * 基类加载View
 */
public interface ILoadingView {
    void showLoading();
    void showContent();
    void showError(String errorMsg);
    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();

}
