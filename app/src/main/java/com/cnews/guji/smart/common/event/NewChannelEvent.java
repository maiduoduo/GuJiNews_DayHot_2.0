package com.cnews.guji.smart.common.event;



import com.cnews.guji.smart.common.bean.ChannelBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * NewChannelEvent
 */
public class NewChannelEvent {
    public List<ChannelBean> selectedDatas;

    public List<ChannelBean> unSelectedDatas;

    public List<ChannelBean> allChannels;

    /**
     * 添加的第一个频道名称
     */
    public String firstChannelName;

    public NewChannelEvent(List<ChannelBean> allChannels, String firstChannelName) {
        if (allChannels == null) return;
        this.allChannels = allChannels;
        this.firstChannelName = firstChannelName;

        selectedDatas = new ArrayList<>();
        unSelectedDatas = new ArrayList<>();

        Iterator iterator = allChannels.iterator();
        while (iterator.hasNext()) {
            ChannelBean channel = (ChannelBean) iterator.next();
            if (channel.getItemType() == ChannelBean.TYPE_MY || channel.getItemType() == ChannelBean.TYPE_OTHER) {
                iterator.remove();
            } else if (channel.getItemType() == ChannelBean.TYPE_MY_CHANNEL) {
                selectedDatas.add(channel);
            } else {
                unSelectedDatas.add(channel);
            }
        }
    }
}
