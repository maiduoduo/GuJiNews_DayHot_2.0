package com.cnews.guji.smart.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.app.bugly.BuglyHelper;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.common.bean.TabEntity;
import com.cnews.guji.smart.ui.fragment.home.HomeMainFragment;
import com.cnews.guji.smart.ui.fragment.impnews.ImpMainFragment;
import com.cnews.guji.smart.ui.fragment.me.ProfileMainFragment;
import com.cnews.guji.smart.ui.fragment.square.SquareMainFragment;
import com.cnews.guji.smart.ui.fragment.video.RecommondVideoFragment;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * 主界面
 * @author JSYL-DCL
 */
public class MainActivity extends BaseActivity {
    private static final String TAG1 = MainActivity.class.getSimpleName();
    @BindView(R.id.commonTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.mainViewpager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"咕唧","广场","视频", "要闻", "我的"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //R.mipmap.tab_home_normal   R.mipmap.tab_home_selected  R.mipmap.tab_me_normal R.mipmap.tab_me_selected
    private int[] mIconUnselectIds = {
            R.mipmap.home_tab_normal_mofiy, R.mipmap.tab_square_normal, R.mipmap.tab_video_normal,
            R.mipmap.tab_importnews_nornal, R.mipmap.mine_tab_normal_motify};
    private int[] mIconSelectIds = {
            R.mipmap.home_tab_select_mofiy, R.mipmap.tab_square_select, R.mipmap.tab_video_selected,
            R.mipmap.tab_importnews_selected, R.mipmap.mine_tab_select_mofiy};


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //检测更新
        BuglyHelper.getInstance().doCheckUpgrade(this);
        initMainPageSetting();
    }

    /**
     * 初始化配置
     */
    private void initMainPageSetting() {
        for (int i = 0; i < mTitles.length; i++) {
            if (0 == i) {mFragments.add(HomeMainFragment.getInstance(mTitles[i]));}
            if (1 == i) {mFragments.add(SquareMainFragment.getInstance(mTitles[i]));}
            if (2 == i) {mFragments.add(RecommondVideoFragment.getInstance(mTitles[i]));}
            if (3 == i) {mFragments.add(ImpMainFragment.getInstance(mTitles[i]));}
            if (4 == i) {mFragments.add(ProfileMainFragment.getInstance(mTitles[i]));}

            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        tl_2();
        //两位数
//        mTabLayout.showMsg(0, 55);
        mTabLayout.setMsgMargin(0, -5, 5);

        //三位数
//        mTabLayout.showMsg(1, 100);
        mTabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout.showDot(2);
        //设置未读消息背景
        mTabLayout.showMsg(3, 5);
        mTabLayout.setMsgMargin(3, -2, 5);
        MsgView rtv_2_3 = mTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#F85959"));
        }


    }


    private void tl_2() {
        int color = getResources().getColor(R.color.white);
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if (0 == position) {
                    StatusBarCompatUtils.setLightMode(MainActivity.this);
                    return;
                } else if (1 == position) {
                    SquareMainFragment fragment = (SquareMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        StatusBarCompatUtils.setLightMode(MainActivity.this);
                    }
                } else if (2 == position) {

//                    VideoMainFragment fragment = (VideoMainFragment) mFragments.get(position);
                    RecommondVideoFragment fragment = (RecommondVideoFragment) mFragments.get(position);
                    if (fragment != null) {
                        fragment.setTvTitleBackgroundColor(color);
                        StatusBarCompatUtils.setLightMode(MainActivity.this);
                    }
                } else if (3 == position){
                    ImpMainFragment fragment = (ImpMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        fragment.setTvTitleBackgroundColor(color);
                        StatusBarCompatUtils.setLightMode(MainActivity.this);
                    }
                } else if (4 == position){
                    ProfileMainFragment fragment = (ProfileMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        StatusBarCompatUtils.setDarkMode(MainActivity.this);
                    }
                }
                //如果点击了其他条目
                ImageView iconView = mTabLayout.getIconView(0);
                iconView.setImageResource(R.mipmap.home_tab_normal_mofiy);
                //停止旋转动画
                cancelTabLoading(iconView);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    ImageView iconView = mTabLayout.getIconView(0);
                    //更换成首页原来选中图标
                    iconView.setImageResource(R.mipmap.home_tab_select_mofiy);
                    refreshTab(position);
//                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    /**
     * 刷新界面Tab动画
     */
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();
    private void refreshTab(int position) {
        ImageView iconView = mTabLayout.getIconView(position);
        //如果是在原来位置上点击,更换首页图标并播放旋转动画
        if (mRotateAnimation != null && !mRotateAnimation.hasEnded()){
            //如果当前动画正在执行
            return;
        }
        iconView.setImageResource(R.mipmap.tab_loading);//更换成加载图标

        //播放旋转动画
        if (mRotateAnimation == null) {
            mRotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            mRotateAnimation.setDuration(800);
            mRotateAnimation.setRepeatCount(-1);
        }
//        ImageView bottomImageView = iconView.getImageView();
        iconView.setAnimation(mRotateAnimation);
        iconView.startAnimation(mRotateAnimation);//播放旋转动画

        //模拟数据刷新完毕
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iconView.setImageResource(R.mipmap.home_tab_select_mofiy);//更换成首页原来选中图标
                cancelTabLoading(iconView);
            }
        }, 3000);
        return;

    }

    /**
     * 停止首页页签的旋转动画
     * @param currentTabIv
     */
    private void cancelTabLoading(ImageView currentTabIv) {
        Animation animation = currentTabIv.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }


    @Override
    public void initPresenter() {
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
        return R.color.blue;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }

    /**
     * 监听全屏视频时返回键
     */
    @Override
    public void onBackPressedSupport() {
        if (Jzvd.backPress()) {
            return;
        }
        //清除缓存
        BaseApplication.frescoClearMem();
        finishThis();
        super.onBackPressedSupport();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
