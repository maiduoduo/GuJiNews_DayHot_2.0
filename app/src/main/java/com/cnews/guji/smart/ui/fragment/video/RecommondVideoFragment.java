package com.cnews.guji.smart.ui.fragment.video;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.helper.player.VideoViewManager;
import com.cnews.guji.smart.util.NoDoubleClickUtils;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.state.LoadFrameLayout;
//import com.flyco.tablayout.SlidingTabLayout;
import java.util.HashMap;

import com.cnews.guji.smart.view.widget.tablayout.SlidingTabLayout;

import java.util.Map;

import butterknife.BindView;

/**
 * CN:      RecommondVideoFragment
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/25
 * Des:    视频
 */
public class RecommondVideoFragment extends BaseFragment {
    private int bgColor = 0;
    @BindView(R.id.fake_status_bar) View mFakeStatusBar;
    @BindView(R.id.stl_home) SlidingTabLayout mTabLayout;
    @BindView(R.id.tab_search) ImageView mTabSearch;
    @BindView(R.id.vp_home) ViewPager mViewPager;
    @BindView(R.id.load_frameLayout) LoadFrameLayout loadFrameLayout;
    private String[] titles;
    private Map<String, Integer> mTabs = new HashMap<>();
    Handler mHandler = new Handler();
    TextView mRetry;

    public static RecommondVideoFragment getInstance(String title) {
        RecommondVideoFragment fragment = new RecommondVideoFragment();
        return fragment;
    }

    @Override
    public void intBase() {
        StatusBarCompatUtils.setLightMode(getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommond_video_news;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        titles = getResources().getStringArray(R.array.video_tab);
        mRetry = loadFrameLayout.findViewById(R.id.tv_retry);
        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NoDoubleClickUtils.isDoubleClick()) {
                }
            }
        });
        mViewPager.setAdapter(new ItemPageAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setViewPager(mViewPager); //初始化的绑定
        mTabLayout.setCurrentTab(1);
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initListener() {

    }

    public void setCurrentPos(int i) {
        if (mTabLayout != null && mViewPager != null){
            mViewPager.setCurrentItem(i);
            mTabLayout.setCurrentTab(i);
        }
    }

    class ItemPageAdapter extends FragmentStatePagerAdapter {
        SparseArray<Fragment> mFragments = new SparseArray<>();

        public ItemPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("title", titles[position]);
            switch (position){
                case 0:
                    mFragments.put(position,CarefullyChosenVideoFragment.getInstance());
                    break;
                case 1:
                    mFragments.put(position,SmallVideoFragment.getInstance());
                    break;
                case 2:
                case 3:
                    mFragments.put(position,VideoMovieFragment.getInstance());
                    break;

            }
//                mFragments.put(position, position == 1 ?
//                        SmallVideoFragment.getInstance()
//                        :
////                        VideoHomeFragment.getInstance("",0,0)
//                        CarefullyChosenVideoFragment.getInstance()
//                );

            return mFragments.get(position);
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            VideoViewManager.instance().releaseVideoPlayer();
        }
    }

    public void setTvTitleBackgroundColor(@ColorInt int color) {
        bgColor = color;
//        _toolbar.setBackgroundColor(color);
        mFakeStatusBar.setBackgroundColor(color);
    }
}
