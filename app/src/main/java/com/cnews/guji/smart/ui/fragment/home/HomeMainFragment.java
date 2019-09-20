package com.cnews.guji.smart.ui.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.common.bean.ChannelBean;
import com.cnews.guji.smart.common.event.NewChannelEvent;
import com.cnews.guji.smart.common.event.SelectChannelEvent;
import com.cnews.guji.smart.ui.adapter.NewsChannelPagerAdapter;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.CustomViewPager;
import com.jaeger.library.StatusBarUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 咕唧首页
 *
 * @author JSYL-DCL
 */
public class HomeMainFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    private static final String TAG1 = HomeMainFragment.class.getSimpleName();
    private List<String> tabList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private NewsChannelPagerAdapter mNewsChannelPagerAdapter;
    /**
     * 选中的index
     */
    private int selectedIndex;
    /**
     * 选中
     */
    private String selectedChannel;
    /**
     * 选择频道数据
     */
    private List<ChannelBean> mSelectedChannels;
    /**
     * 未选择频道数据
     */
    private List<ChannelBean> mUnSelectedChannels;

    public static HomeMainFragment getInstance(String title) {
        HomeMainFragment fragment = new HomeMainFragment();
        return fragment;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mSelectedChannels = new ArrayList<>();
        mUnSelectedChannels = new ArrayList<>();
    }

    @Override
    public void bindData() {
        List<ChannelBean> myChannel = NewsSource.getTabCurrentShowData(0, getActivity());
        List<ChannelBean> moreChannel = NewsSource.getTabCurrentShowData(1, getActivity());

        //默认添加了全部频道
        for (int i = 0; i < myChannel.size(); i++) {
            String title = myChannel.get(i).title;
            String code = myChannel.get(i).channelcode;
            mSelectedChannels.add(new ChannelBean(title, code));
        }

        for (int i = 0; i < moreChannel.size(); i++) {
            String title = moreChannel.get(i).title;
            String code = moreChannel.get(i).channelcode;
            mUnSelectedChannels.add(new ChannelBean(title, code));
        }

        mNewsChannelPagerAdapter = new NewsChannelPagerAdapter(mActivity, getChildFragmentManager(), mSelectedChannels,mTabLayout);
        mViewpager.setAdapter(mNewsChannelPagerAdapter);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setCurrentItem(0, false);
        mTabLayout.setupWithViewPager(mViewpager);
        //默认选中某项放在加载viewpager之后
//        mTabLayout.getTabAt(0).select();

    }



    @OnClick({R.id.ibnAddChannel})
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.ibnAddChannel:
                //频道
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedChannels, mUnSelectedChannels);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                break;
        }
    }


    @Override
    public void initListener() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedIndex = position;
                selectedChannel = mSelectedChannels.get(position).name;
                if (0 == position){
                    StatusBarCompatUtils.setDarkMode(mActivity);
                }else {
                    StatusBarCompatUtils.setLightMode(mActivity);
                    mTabLayout.setTabTextColors(getResources().getColor(R.color.Black_Alpha80),getResources().getColor(R.color.red));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }


    @Subscriber
    private void updateChannel(NewChannelEvent event) {
        if (event == null) return;
        if (event.selectedDatas != null && event.unSelectedDatas != null) {
            mSelectedChannels = event.selectedDatas;
            mUnSelectedChannels = event.unSelectedDatas;
            mNewsChannelPagerAdapter.updateChannel(mSelectedChannels);
            //todo:------------------------
            mTabLayout.notify();
//            setTabStyle();

            List<String> integers = new ArrayList<>();
            for (ChannelBean channel : mSelectedChannels) {
                integers.add(channel.title);
            }
            if (TextUtils.isEmpty(event.firstChannelName)) {
                if (!integers.contains(selectedChannel)) {
                    selectedChannel = mSelectedChannels.get(selectedIndex).title;
                    mViewpager.setCurrentItem(selectedIndex, false);
                } else {
                    setViewpagerPosition(integers, selectedChannel);
                }
            } else {
                setViewpagerPosition(integers, event.firstChannelName);
            }
        }
    }

    @Subscriber
    private void selectChannel(SelectChannelEvent selectChannelEvent) {
        if (selectChannelEvent == null) return;
        List<String> integers = new ArrayList<>();
        for (ChannelBean channel : mSelectedChannels) {
            integers.add(channel.title);
        }
        setViewpagerPosition(integers, selectChannelEvent.channelName);
    }

    /**
     * 设置 当前选中页
     *
     * @param integers
     * @param channelName
     */
    private void setViewpagerPosition(List<String> integers, String channelName) {
        if (TextUtils.isEmpty(channelName) || integers == null) return;
        for (int j = 0; j < integers.size(); j++) {
            if (integers.get(j).equals(channelName)) {
                selectedChannel = integers.get(j);
                selectedIndex = j;
                break;
            }
        }
        mViewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewpager.setCurrentItem(selectedIndex, false);
            }
        }, 100);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }


    private View getTabView(int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
//        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
//        viewById.setTextSize(30);
//        viewById.setTextColor(getResources().getColor(R.color.white));
        return inflate;
    }

    private View getTabViewUn(int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
//        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
//        viewById.setTextSize(10);
//        viewById.setTextColor(getResources().getColor(R.color.white));
        return inflate;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //展示，可见
            ILog.e(TAG1, "onHiddenChanged newsmain:" + hidden);
        } else {
            ILog.e(TAG1, "[else]onHiddenChanged newsmain:" + hidden);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //展示，可见
            ILog.e(TAG1, "isVisibleToUser newsmain:" + isVisibleToUser);
        } else {
            ILog.e(TAG1, "[else]isVisibleToUser newsmain:" + isVisibleToUser);
        }
    }

    private void setTabStyle() {
        ILog.e(TAG1, "count-------------->:" + mNewsChannelPagerAdapter.getCount());
        for (int i = 0; i < mSelectedChannels.size(); i++) {
            //获得每一个tab
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            //给每一个tab设置view
            tab.setCustomView(R.layout.view_tab);
            TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
            if (i == 0) {
                //第一个tab被选中
                textView.setSelected(true);
                textView.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
                textView.setGravity(Gravity.TOP);
            } else {
                textView.setSelected(false);
                textView.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
                textView.setGravity(Gravity.TOP);
            }
            //设置tab上的文字
            textView.setText(mSelectedChannels.get(i).title);
            //设置字体
            textView.setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    mViewpager.setCurrentItem(tab.getPosition());
                    TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
                    textView.setTextColor(getResources().getColor(R.color.red));
                    textView.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
                    if (tab.getPosition() == 0) {
                    }
                } catch (NullPointerException e) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                try {
                    TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
                    textView.setTextColor(getResources().getColor(R.color.black_font));
                    textView.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
                    if (tab.getPosition() == 0) {
                    }
                } catch (NullPointerException e) {

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

}
