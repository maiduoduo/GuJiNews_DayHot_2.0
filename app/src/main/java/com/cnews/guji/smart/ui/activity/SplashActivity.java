package com.cnews.guji.smart.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.timer.CountDownView;
import com.cnews.guji.smart.ui.model.source.NewsQuerySourceHelper;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.SharePrefUtil;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * 启动页
 * @author JSYL-DCL
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_ad)
    ExpandImageView ivAd;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.fl_ad)
    FrameLayout flAd;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int duration = 10000;
    boolean isFirstIn = false;
    private String bing = "https://api.dujin.org/bing/1920.php";

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {
        AppConstant.COUNTDOWN_TIMER = true;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        startLoadGif();
        mCompositeDisposable.add(countDown(5).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                tvSkip.setText("跳过 5");
            }
        }).subscribeWith(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                tvSkip.setText("跳过 " + (integer + 1));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                toMain();
            }
        }));

    }

    private void startLoadGif() {
        ivAd.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.mipmap.ahn));
//        if (AppConstant.START_GIF) {//GIF
//            AppConstant.START_GIF = false;
////            StatusBarCompatUtils.setDarkMode(SplashActivity.this);
//            int splashGif = NewsQuerySourceHelper.getSplashGif();
//            ImageLoaderUtils.displayGlideGif(mContext, ivAd, splashGif);
//        } else {//静态图
//            AppConstant.START_GIF = true;
////            StatusBarCompatUtils.setDarkMode(SplashActivity.this);
//            Random random = new Random(10);
//            int i = random.nextInt();
//            if (i % 2 == 0){
//                ivAd.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.drawable.splash_quite_pick));
//            }else {
//                ivAd.setImageURI(bing);
//            }
//        }
    }

    @OnClick(R.id.fl_ad)
    public void onViewClicked() {
        toMain();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    @Override
    public void initBase() {

    }

    @Override
    protected int setImmersiveStatusBarColor() {
        return 0;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                moveTaskToBack(false);
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
                Log.i("main", "KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
                Log.i("main", "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
                Log.i("main", "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    public Observable<Integer> countDown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(@NonNull Long aLong) throws Exception {
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime + 1);
    }

    private void toMain() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        isFirstIn = SharePrefUtil.getBoolean(this,"isFirstIn",true);
        Intent intent = new Intent();
        if (isFirstIn) {
            intent.setClass(SplashActivity.this, UserGuideActivity.class);
            SharePrefUtil.saveBoolean(this,"isFirstIn",false);
        } else {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }
}
