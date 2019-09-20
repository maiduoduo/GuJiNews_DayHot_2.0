package com.cnews.guji.smart.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cnews.guji.smart.util.ILog;

/**
 * @package: WrapContentLinearLayoutManager
 * @author： JSYL-DCL
 * @date: 2019/5/6
 * @describe： TODO
 * @email： 11442865
 */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {
    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    //... constructor
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            ILog.e("fragment", "meet a IOOBE in RecyclerView");
        }
    }
}
