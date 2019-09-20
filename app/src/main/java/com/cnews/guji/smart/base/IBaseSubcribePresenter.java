package com.cnews.guji.smart.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @package: IBasePresenter
 * @author： JSYL-DCL
 * @describe： Presenter的基类
 * @email： 11442865
 */
public class IBaseSubcribePresenter<V extends ILoadingView> {
    protected V mView;
    protected CompositeSubscription mCompositeSubscription;


    /**
     * 绑定View,一般在初始化的时候初始化
     * @param view
     */
    public void attachView(V view){
        this.mView = view;
    }

    /**
     * 解绑View,一般在ondestroy()执行的时候销毁
     */
    public void detachView(){
        this.mView = null;
    }

    /**
     * View是否绑定
     * @return
     */
    public boolean isViewAttached(){
        return this.mView != null;
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }




    /*
        public Context mContext;
        public E mModel;
        public T mView;

        public void setVM(T v, E m) {
            this.mView = v;
            this.mModel = m;
            this.onStart();
        }
        public void onStart(){
        };
        public void onDestroy() {
        }
    */
}
