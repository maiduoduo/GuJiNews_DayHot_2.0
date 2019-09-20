package com.cnews.guji.smart.ui.adapter;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.ChannelBean;
import com.cnews.guji.smart.ui.fragment.home.HomeListFragment;
import com.cnews.guji.smart.ui.fragment.home.HomeTopFragment;

import java.util.List;

/**
 * NewsChannelPagerAdapter
 * @author JSYL-DCL
 */
public class NewsChannelPagerAdapter extends FragmentStatePagerAdapter {

    private List<ChannelBean> mChannels;
    private Activity activity;
    private TabLayout mTabLayout;

    public NewsChannelPagerAdapter(Activity activity, FragmentManager fm, List<ChannelBean> channels, TabLayout mTabLayout) {
        super(fm);
        this.mChannels = channels;
        this.activity = activity;
        this.mTabLayout = mTabLayout;
    }

    public void updateChannel(List<ChannelBean> channels){
        this.mChannels = channels;
        notifyDataSetChanged();
    }

    @Override
    public BaseMvpFragment getItem(int position) {
        if (position == 0){
            return HomeTopFragment.newInstance(0,mTabLayout);
        }else {
            return HomeListFragment.newInstance(mChannels.get(position).title,mChannels.get(position).channelcode,mTabLayout);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).title;
    }

    @Override
    public int getCount() {
        return mChannels != null ? mChannels.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
