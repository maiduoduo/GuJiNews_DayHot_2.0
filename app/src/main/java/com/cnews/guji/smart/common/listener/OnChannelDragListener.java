package com.cnews.guji.smart.common.listener;


import com.github.library.BaseViewHolder;

/**
 * OnChannelDragListener
 * 频道拖拽监听
 */

public interface OnChannelDragListener extends OnChannelListener {
    void onStarDrag(BaseViewHolder baseViewHolder);

}
