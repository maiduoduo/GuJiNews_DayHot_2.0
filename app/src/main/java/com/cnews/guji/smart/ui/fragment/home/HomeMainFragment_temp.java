//package com.cnews.guji.smart.ui.fragment.home;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.cnews.guji.smart.R;
//import com.cnews.guji.smart.base.BaseFragment;
//import com.cnews.guji.smart.common.bean.ChannelBean;
//import com.cnews.guji.smart.common.listener.OnChannelListener;
//import com.cnews.guji.smart.common.net.ApiConstant;
//import com.cnews.guji.smart.ui.adapter.NewsChannelPagerAdapter;
//import com.cnews.guji.smart.ui.model.source.NewsSource;
//import com.cnews.guji.smart.util.ILog;
//import com.cnews.guji.smart.util.SharePrefUtil;
//import com.cnews.guji.smart.util.ToastUitl;
//import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
//import com.cnews.guji.smart.view.CustomViewPager;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import cn.jzvd.Jzvd;
//
///**
// * 咕唧首页
// * @author JSYL-DCL
// */
//public class HomeMainFragment_temp extends BaseFragment implements OnChannelListener {
//    @BindView(R.id.tablayout)
//    TabLayout mTabLayout;
//    @BindView(R.id.viewpager)
//    CustomViewPager mViewpager;
//    @BindView(R.id.ibnAddChannel)
//    ImageView ibnAddChannel;
//    private static final String TAG1 = HomeMainFragment_temp.class.getSimpleName();
//    private List<HomeListFragment> mChannelFragments = new ArrayList<>();
//    private NewsChannelPagerAdapter mNewsChannelPagerAdapter;
//    private Gson mGson = new Gson();
//    /**
//     * 选中的index
//     */
//    private int selectedIndex;
//    /**
//     * 未选中
//     */
//    private String selectedChannel;
//    /**
//     * 选择频道数据
//     */
//    private List<ChannelBean> mSelectedChannels = new ArrayList<>();
//    /**
//     * 未选择频道数据
//     */
//    private List<ChannelBean> mUnSelectedChannels = new ArrayList<>();
//    /**
//     * 本地已选频道
//     */
//    private List<ChannelBean> myChannel;
//    /**
//     * 本地未选频道
//     */
//    private List<ChannelBean> moreChannel;
//    public static HomeMainFragment_temp getInstance(String title) {
//        HomeMainFragment_temp sf = new HomeMainFragment_temp();
//        return sf;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//    @Override
//    public void intBase() {
//        StatusBarCompatUtils.setDarkMode(getActivity());
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout_comment_list_title.fragment_home;
//    }
//
//
//
//
//    @Override
//    public void bindView(View view, Bundle savedInstanceState) {
////        mSelectedDatas = new ArrayList<>();
////        mUnSelectedDatas = new ArrayList<>();
//        StatusBarCompatUtils.setDarkMode(mActivity);
//        myChannel = NewsSource.getTabCurrentShowData(0, getActivity());
//        moreChannel = NewsSource.getTabCurrentShowData(1, getActivity());
//        initChannelData();
//        initChannelFragments();
//
//
//
//
//
//
//
//
//
//
//
//
//        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                StatusBarCompatUtils.setDarkMode(mActivity);
//                selectedIndex = position;
//                selectedChannel = mSelectedDatas.get(position).name;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//    @Override
//    public void bindData() {
//        if (myChannel != null){
//            mSelectedDatas.clear();
//            mSelectedDatas.addAll(myChannel);
//            mUnSelectedDatas.clear();
//            mUnSelectedDatas.addAll(moreChannel);
//            mNewsChannelPagerAdapter = new NewsChannelPagerAdapter(getChildFragmentManager(), myChannel);
//            mViewpager.setAdapter(mNewsChannelPagerAdapter);
//            mViewpager.setOffscreenPageLimit(2);
//            mViewpager.setCurrentItem(0, false);
//            mTabLayout.setupWithViewPager(mViewpager);
//        }else {
//            ToastUitl.showShort("频道数据异常");
//        }
//    }
//
//
//    /**
//     * 初始化已选频道和未选频道的数据
//     */
//    private void initChannelData() {
//        String selectedChannelJson = SharePrefUtil.getString(getActivity(),ApiConstant.SELECTED_CHANNEL_JSON, "");
//        String unselectChannel = SharePrefUtil.getString(getActivity(),ApiConstant.UNSELECTED_CHANNEL_JSON, "");
//
//        if (TextUtils.isEmpty(selectedChannelJson) || TextUtils.isEmpty(unselectChannel)) {
//            //本地没有title
//            //默认添加了全部频道
//            for (int i = 0; i < myChannel.size(); i++) {
//                String title = myChannel.get(i).title;
//                String code = myChannel.get(i).channelcode;
//                mSelectedChannels.add(new ChannelBean(title, code));
//            }
//
//            selectedChannelJson = mGson.toJson(mSelectedChannels);//将集合转换成json字符串
//            ILog.i("selectedChannelJson:" + selectedChannelJson);
//            //保存到sp
//            SharePrefUtil.saveString(getActivity(),ApiConstant.SELECTED_CHANNEL_JSON, selectedChannelJson);
//        } else {
//            //之前添加过
//            List<ChannelBean> selectedChannel = mGson.fromJson(selectedChannelJson, new TypeToken<List<ChannelBean>>() {
//            }.getType());
//            List<ChannelBean> unselectedChannel = mGson.fromJson(unselectChannel, new TypeToken<List<ChannelBean>>() {
//            }.getType());
//            mSelectedChannels.addAll(selectedChannel);
//            mUnSelectedChannels.addAll(unselectedChannel);
//        }
//    }
//
//    /**
//     * 初始化已选频道的fragment的集合
//     */
//    private List<String> mChannelCodes;
//    private void initChannelFragments() {
//        ILog.e("initChannelFragments");
//        mChannelCodes = new ArrayList<>();
//        for (ChannelBean b : myChannel) {
//            mChannelCodes.add(b.channelcode);
//        }
//        for (ChannelBean channel : mSelectedChannels) {
//            HomeListFragment newsFragment = new HomeListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(ApiConstant.CHANNEL_CODE, channel.channelcode);
//            //是否是视频列表页面,根据判断频道号是否是视频
//            bundle.putBoolean(ApiConstant.IS_VIDEO_LIST, channel.channelcode.equals(ApiConstant.VIDEO_CHANNELCODE));
//            newsFragment.setArguments(bundle);
//            //添加到集合中
//            mChannelFragments.add(newsFragment);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    private void init() {
////        if (tabList != null) {tabList.clear();}
////        if (fragmentList != null){ fragmentList.clear();}
////        List<HomeNewsTabBean.Data.My> tabCurrentShowData = NewsSource.getTabCurrentShowData(0, getActivity());
////        if (tabCurrentShowData != null && tabCurrentShowData.size() > 0) {
////            for (int i = 0; i < tabCurrentShowData.size(); i++) {
////                if (0 == i) {
////                    fragmentList.add(HomeTopFragment.getInstance(0));
////                }else if (1 == i){
////                    fragmentList.add(OtherHomeFragment.getInstance(0));
////                }else {
////                    fragmentList.add(HomeNormalPublicFragment.getInstance(i));
////                }
////                //装载标签数据
////                tabList.add(tabCurrentShowData.get(i).name);
////            }
////        }
////        try {
////            MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getChildFragmentManager(), tabList, fragmentList);
////            mViewpager.setAdapter(myViewPageAdapter);
////            mViewpager.setOffscreenPageLimit(2);//设置ViewPage缓存界面数
////            mTabLayout.setupWithViewPager(mViewpager);
////            mTabLayout.getTabAt(0).select(); //默认选中某项放在加载viewpager之后
////            mTabLayout.setTabsFromPagerAdapter(myViewPageAdapter);
////            for (int i = 0; i < myViewPageAdapter.getCount(); i++) {
////                TabLayout.Tab tab = mTabLayout.getTabAt(i);//获得每一个tab
////                tab.setCustomView(R.layout_comment_list_title.view_tab);//给每一个tab设置view
////                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
////                if (i == 0) {
////                    textView.setSelected(true);//第一个tab被选中
////                    textView.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
////                    textView.setGravity(Gravity.TOP);
////                } else {
////                    textView.setSelected(false);
////                    textView.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
////                    textView.setGravity(Gravity.TOP);
////                }
////                textView.setText(tabList.get(i));//设置tab上的文字
////                //设置字体
////                textView.setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
////            }
////            for (int i = 0; i < fragmentList.size(); i++) {
////                swithOtherPages(i);
////            }
//////            FinanceHomeFragment fragment = (FinanceHomeFragment) fragmentList.get(0);
//////            fragment.setCurrentPageType(0);
////        }catch (Exception e){
////
////        }
////    }
//
//    @OnClick({R.id.ibnAddChannel})
//    public void bindViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.ibnAddChannel://频道
////                NewsChannelManagerActivity.startAction(getContext());
//                enterChannel();
//                break;
//        }
//    }
//
//    private void enterChannel() {
//        ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
//        dialogFragment.setOnChannelListener(this);
//        dialogFragment.show(getChildFragmentManager(), "CHANNEL");
//        dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                mNewsChannelPagerAdapter.notifyDataSetChanged();
//                mViewpager.setOffscreenPageLimit(mSelectedDatas.size());
//                mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()).select();
//                ViewGroup slidingTabStrip = (ViewGroup) mTabLayout.getChildAt(0);
//                //注意：因为最开始设置了最小宽度，所以重新测量宽度的时候一定要先将最小宽度设置为0
//                slidingTabStrip.setMinimumWidth(0);
//                slidingTabStrip.measure(0, 0);
//                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + ibnAddChannel.getMeasuredWidth());
//
//                //保存选中和未选中的channel
//                SharePrefUtil.saveString(getActivity(),ApiConstant.SELECTED_CHANNEL_JSON, mGson.toJson(mSelectedDatas));
//                SharePrefUtil.saveString(getActivity(),ApiConstant.UNSELECTED_CHANNEL_JSON, mGson.toJson(mUnSelectedDatas));
//            }
//        });
//    }
//
//
//    @Override
//    public void initListener() {
//        mChannelPagerAdapter = new ChannelPagerAdapter(mChannelFragments, mSelectedChannels,getChildFragmentManager());
//        mVpContent.setAdapter(mChannelPagerAdapter);
//        mVpContent.setOffscreenPageLimit(mSelectedChannels.size());
//
//        mTabChannel.setTabPaddingLeftAndRight(UIUtils.dip2Px(10), UIUtils.dip2Px(10));
//        mTabChannel.setupWithViewPager(mVpContent);
//        mTabChannel.post(new Runnable() {
//            @Override
//            public void run() {
//                //设置最小宽度，使其可以在滑动一部分距离
//                ViewGroup slidingTabStrip = (ViewGroup) mTabChannel.getChildAt(0);
//                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + ivAddChannel.getMeasuredWidth());
//            }
//        });
//        //隐藏指示器
//        mTabChannel.setSelectedTabIndicatorHeight(0);
//
//        mVpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                //当页签切换的时候，如果有播放视频，则释放资源
//                Jzvd.releaseAllVideos();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//
//
//
//
//
//
//
//
////        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
////            @Override
////            public void onTabSelected(TabLayout.Tab tab) {
////                ILog.e(TAG, "-----------onTabSelected-------------> ");
////                mViewpager.setCurrentItem(tab.getPosition());
////                TextView viewById = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
////                viewById.setSelected(true);
////                viewById.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
////                if (tab.getPosition() == 0) {
////                    StatusBarCompatUtils.setDarkMode(mActivity);
////                }
////                int position = tab.getPosition();
////                String text = (String)tab.getText();
////                Fragment fragment = fragmentList.get(position);
////                if (fragment instanceof HomeNormalPublicFragment){
////                    ((HomeNormalPublicFragment)fragment).setCurrentTabName(text);
////                }
////            }
////
////            @Override
////            public void onTabUnselected(TabLayout.Tab tab) {
////                ILog.e(TAG, "-----------onTabUnselected-------------> ");
////                TextView viewById = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
////                viewById.setSelected(false);
////                viewById.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
////                if (tab.getPosition() == 0) {
////                }
////            }
////
////            @Override
////            public void onTabReselected(TabLayout.Tab tab) {
////                ILog.e(TAG, "-----------onTabReselected-------------> ");
////                if (tab.getPosition() == 0){
////                    StatusBarCompatUtils.setDarkMode(mActivity);
////                }
////            }
////        });
//
////        mViewpager.addOnPageChangeListener(this);
//    }
//
//
//    /**
//     * 界面滑动
//     *
//     * @param position
//     * @param positionOffset
//     * @param positionOffsetPixels
//     */
////    @Override
////    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////
////    }
////
////    /**
////     * 界面滑动停止选中界面
////     *
////     * @param position
////     */
////    @Override
////    public void onPageSelected(int position) {
////        //页签选中
////        mTabLayout.getTabAt(position).select();
////        swithOtherPages(position);
////    }
////
////    private void swithOtherPages(int position) {
////        if (0 == position) {
////            //推荐
////            HomeTopFragment fragment = (HomeTopFragment) fragmentList.get(position);
////            StatusBarCompatUtils.setDarkMode(mActivity);
////            fragment.setCurrentPageType(0);
////        } else if (1 == position) {
////            //其他
////            OtherHomeFragment fragment = (OtherHomeFragment) fragmentList.get(position);
////            StatusBarCompatUtils.setDarkMode(mActivity);
////            fragment.setCurrentPageType(0);
////        }  else {
////            HomeNormalPublicFragment fragment = (HomeNormalPublicFragment) fragmentList.get(position);
////            fragment.setCurrentPageType(position);
////        }
////    }
//
//    /**
//     * 界面滑动状态改变
//     *
////     * @param state
//     */
////    @Override
////    public void onPageScrollStateChanged(int state) {
////
////    }
//
//
//    private View getTabView(int i) {
//        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout_comment_list_title.view_tab, null);
////        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
////        viewById.setTextSize(30);
////        viewById.setTextColor(getResources().getColor(R.color.white));
//        return inflate;
//    }
//
//    private View getTabViewUn(int i) {
//        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout_comment_list_title.view_tab, null);
////        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
////        viewById.setTextSize(10);
////        viewById.setTextColor(getResources().getColor(R.color.white));
//        return inflate;
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden){//展示，可见
//            ILog.e(TAG1,"onHiddenChanged newsmain:"+hidden);
//        }else {
//            ILog.e(TAG1,"[else]onHiddenChanged newsmain:"+hidden);
//        }
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){//展示，可见
//            ILog.e(TAG1,"isVisibleToUser newsmain:"+isVisibleToUser);
//        }else {
//            ILog.e(TAG1,"[else]isVisibleToUser newsmain:"+isVisibleToUser);
//        }
//    }
//
//    @Override
//    public void onItemMove(int starPos, int endPos) {
//        listMove(mSelectedDatas, starPos, endPos);
////        listMove(mChannelFragments, starPos, endPos);
//    }
//
//    @Override
//    public void onMoveToMyChannel(int starPos, int endPos) {
//        //移动到我的频道
//        ChannelBean channel = mUnSelectedDatas.remove(starPos);
//        mSelectedDatas.add(endPos, channel);
//
////        HomeListFragment newsFragment = new HomeListFragment();
////        Bundle bundle = new Bundle();
////        bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
////        bundle.putBoolean(Constant.IS_VIDEO_LIST, channel.channelCode.equals(mChannelCodes[1]));
////        newsFragment.setArguments(bundle);
////        mChannelFragments.add(newsFragment);
//    }
//
//    @Override
//    public void onMoveToOtherChannel(int starPos, int endPos) {
//        //移动到推荐频道
//        mUnSelectedDatas.add(endPos, mSelectedDatas.remove(starPos));
////        mChannelFragments.remove(starPos);
//    }
//
//    private void listMove(List datas, int starPos, int endPos) {
//        Object o = datas.get(starPos);
//        //先删除之前的位置
//        datas.remove(starPos);
//        //添加到现在的位置
//        datas.add(endPos, o);
//    }
//}
