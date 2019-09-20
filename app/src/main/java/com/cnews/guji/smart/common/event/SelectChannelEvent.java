package com.cnews.guji.smart.common.event;


/**
 * 选择事件
 */
public class SelectChannelEvent {

    public String channelName;

    public SelectChannelEvent(String channelName) {
        this.channelName = channelName;
    }
}
