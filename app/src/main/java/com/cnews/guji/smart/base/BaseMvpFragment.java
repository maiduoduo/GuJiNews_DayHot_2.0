package com.cnews.guji.smart.base;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.Nullable;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @package: BaseMvpFragment
 * @author： JSYL-DCL
 * @describe： Fragment基类
 *                  支持MVP模式
 * @email： 11442865
 */
public abstract class BaseMvpFragment<K extends IBasePresenter> extends BaseLazyLoadFragment{
    @Nullable
    protected K mPresenter;
    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * 绑定生命周期，防止MVP内存泄漏
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }

}
