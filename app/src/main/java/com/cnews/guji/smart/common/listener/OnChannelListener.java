package com.cnews.guji.smart.common.listener;

/**
 * 频道监听器
 * @author JSYL-DCL
 */
public interface OnChannelListener {
    void onItemMove(int starPos, int endPos);
    void onMoveToMyChannel(int starPos, int endPos);
    void onMoveToOtherChannel(int starPos, int endPos);
    void onFinish(String selectedChannelName);
}
