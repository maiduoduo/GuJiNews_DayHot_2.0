package com.cnews.guji.smart.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uber.autodispose.AutoDisposeConverter;

/**
 * @author JSYL-DCL
 * @date 2018/5/24 14:18
 */
public interface IBaseView {
    //显示进度中
    void showLoading();

    //显示请求成功
    void showSuccess();

    //失败重试
    void showFaild(Throwable throwable, String error);

    //显示当前网络不可用
    void showNoNet();


    //重试
    void onRetry();
    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();
    <T> LifecycleTransformer<T> bindToLife();


}
