package com.cnews.guji.smart.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.helper.guide.ExtendedViewPager;
import com.cnews.guji.smart.helper.guide.indicator.CirclePageIndicator;
import com.cnews.guji.smart.ui.fragment.GuideVideoItemFragment;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.SharePrefUtil;

/**
 * CN:      UserGuideActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/3
 * Des:    引导页
 */
public class UserGuideActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = UserGuideActivity.class.getSimpleName();
    private ExtendedViewPager mVpVideo;
    private TextView mTvEnter;
    private CirclePageIndicator mViewPagerIndicator;
    private boolean mVisible;
    private ViewPagerAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_guide);
        mVisible = true;

        mVpVideo = (ExtendedViewPager) findViewById(R.id.vp_video);
        mVpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVpVideo.setAdapter(mVpAdapter);
        mVpVideo.setOffscreenPageLimit(4);

        mTvEnter = (TextView) findViewById(R.id.tv_enter);
        mTvEnter.setOnClickListener(this);

        mViewPagerIndicator = (CirclePageIndicator) findViewById(R.id.view_pager_indicator);
        mViewPagerIndicator.setViewPager(mVpVideo);

        /**
         * 滑动监听器OnPageChangeListener
         * 	OnPageChangeListener这个接口需要实现三个方法：onPageScrollStateChanged，onPageScrolled ，onPageSelected
         * 		1、onPageScrollStateChanged(int arg0) 此方法是在状态改变的时候调用。
         * 			其中arg0这个参数有三种状态（0，1，2）
         * 				arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做
         * 				当页面开始滑动的时候，三种状态的变化顺序为1-->2-->0
         * 		2、onPageScrolled(int arg0,float arg1,int arg2) 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直被调用。
         * 			其中三个参数的含义分别为：
         * 				arg0 :当前页面，及你点击滑动的页面
         * 				arg1:当前页面偏移的百分比
         * 				arg2:当前页面偏移的像素位置
         * 		3、onPageSelected(int arg0) 此方法是页面跳转完后被调用，arg0是你当前选中的页面的Position（位置编号）
         */
        mVpVideo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // arg0是当前选中的页面的Position
                ((GuideVideoItemFragment) (mVpAdapter.getItem(position))).play();
                mViewPagerIndicator.setCurrentItem(position);
                if (mVpAdapter.getCount()-1 == position){
                    mTvEnter.setVisibility(View.VISIBLE);
                }else {
                    mTvEnter.setVisibility(View.GONE);
                }
                Log.e(TAG, "onPageSelected------>"+position);
                Log.e(TAG, "onPageSelected------mVpAdapter.getCount()-1-->:"+(mVpAdapter.getCount()-1));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position :当前页面，及你点击滑动的页面；positionOffset:当前页面偏移的百分比；positionOffsetPixels:当前页面偏移的像素位置
                Log.e(TAG, "onPageScrolled------>position："+position+"\nonPageScrolled------>positionOffset:"+positionOffset+"\nonPageScrolled------>positionOffsetPixels:"+positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state ==1的时表示正在滑动，state==2的时表示滑动完毕了，state==0的时表示什么都没做。
                if(state == 0){
                    Log.e(TAG, "onPageScrollStateChanged------>0");
                }else if(state == 1){
                    Log.e(TAG, "onPageScrollStateChanged------>1");
                }else if(state == 2){
                    Log.e(TAG, "onPageScrollStateChanged------>2");
                }

            }
        });

    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final int[] videoRes;
        private final int[] slogonImgRes;

        public ViewPagerAdapter(FragmentManager paramFragmentManager) {
            super(paramFragmentManager);
            this.videoRes = new int[]{R.raw.splash_1, R.raw.splash_2, R.raw.splash_3, R.raw.splash_4};
            this.slogonImgRes = new int[]{R.drawable.slogan_1, R.drawable.slogan_2, R.drawable.slogan_3, R.drawable.slogan_4};
        }

        public int getCount() {
            return this.videoRes.length;
        }

        public Fragment getItem(int paramInt) {
            GuideVideoItemFragment videoItemFragment = new GuideVideoItemFragment();
            Bundle localBundle = new Bundle();
            localBundle.putInt("position", paramInt);
            localBundle.putInt("videoRes", this.videoRes[paramInt]);
            localBundle.putInt("imgRes", this.slogonImgRes[paramInt]);
            videoItemFragment.setArguments(localBundle);
            if (paramInt < getCount()) {
                return videoItemFragment;
            }
            throw new RuntimeException("Position out of range. Adapter has " + getCount() + " items");
        }
    }


    public void next(int position) {
        ILog.e(TAG,"next_POSITION:"+position);
        ILog.e(TAG,"next_(mVpAdapter.getCount()-1):"+(mVpAdapter.getCount()-1));
        int i = this.mVpVideo.getCurrentItem();
        if (position == i) {
            position += 1;
            this.mVpVideo.setCurrentItem(position, true);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_enter:
                Intent intent = new Intent();
                intent.setClass(UserGuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
