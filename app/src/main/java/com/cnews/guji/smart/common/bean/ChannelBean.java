package com.cnews.guji.smart.common.bean;


import com.github.library.entity.MultiItemEntity;

import java.io.Serializable;

public class ChannelBean implements MultiItemEntity, Serializable {
    public static final int TYPE_MY = 1;
    public static final int TYPE_OTHER = 2;
    public static final int TYPE_MY_CHANNEL = 3;
    public static final int TYPE_OTHER_CHANNEL = 4;

    /**
     * belongChannel : 1
     * id : 1
     * name : 推荐
     * needMark : false
     * sort : 1
     * type : 1
     */
    public String title;
    public int itemType;
    public String name;
    public String channelcode;

    /**
     * 0 可移除，1不可移除
     */
    private int channelType;

    /**
     * 0 未选中 1 选中
     */
    private boolean isChannelSelect;


    public ChannelBean() {
    }

    public ChannelBean(String title, String channelCode) {
        this(TYPE_MY_CHANNEL, title, channelCode);
    }

    public ChannelBean(int type, String title, String channelCode) {
        this.title = title;
        this.channelcode = channelCode;
        itemType = type;
    }



    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public boolean isChannelSelect() {
        return isChannelSelect;
    }

    public void setChannelSelect(boolean channelSelect) {
        isChannelSelect = channelSelect;
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }
}