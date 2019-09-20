package com.cnews.guji.smart.helper.player.listener;

/**
 * Created by Devlin_n on 2017/6/22.
 */

public interface VideoListener {

    //播放完成
    void onComplete();

    //准备完成
    void onPrepared();

    void onError();

    void onInfo(int what, int extra);

}
