package com.cnews.guji.smart.ui.fragment.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.ChannelBean;
import com.cnews.guji.smart.common.event.NewChannelEvent;
import com.cnews.guji.smart.common.event.SelectChannelEvent;
import com.cnews.guji.smart.common.listener.ItemDragHelperCallBack;
import com.cnews.guji.smart.common.listener.OnChannelListener;
import com.cnews.guji.smart.ui.adapter.ChannelAdapter;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import org.simple.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


/**
 * ChannelDialogFragment
 * @author JSYL-DCL
 */

public class ChannelDialogFragment extends DialogFragment implements OnChannelListener {
    private List<ChannelBean> mDatas = new ArrayList<>();
    RecyclerView mRecyclerView;
    private ItemTouchHelper mHelper;
    private ImageView miVClose;
    private boolean isUpdate = false;
    private ChannelAdapter mAdapter;
    List<ChannelBean> mSelectedDatas;
    List<ChannelBean> mUnSelectedDatas;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        StatusBarCompatUtils.setStatusTextColor(true,getActivity());

    }

    private OnChannelListener onChannelListener;

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.fragment_channel, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        miVClose = (ImageView) view.findViewById(R.id.icon_collapse);
        miVClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        processLogic();
    }

    public static ChannelDialogFragment newInstance(List<ChannelBean> selectedDatas, List<ChannelBean> unselectedDatas) {
        ChannelDialogFragment dialogFragment = new ChannelDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataSelected", (Serializable) selectedDatas);
        bundle.putSerializable("dataUnselected", (Serializable) unselectedDatas);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    private void setDataType(List<ChannelBean> datas, int type) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemType(type);
        }
    }

    private void processLogic() {
        ChannelBean channel = new ChannelBean();
        channel.setItemType(ChannelBean.TYPE_MY);
        channel.setTitle("我的频道");
        mDatas.add(channel);

        Bundle bundle = getArguments();
        mSelectedDatas = (List<ChannelBean>) bundle.getSerializable("dataSelected");
        mUnSelectedDatas = (List<ChannelBean>) bundle.getSerializable("dataUnselected");
        setDataType(mSelectedDatas, ChannelBean.TYPE_MY_CHANNEL);
        setDataType(mUnSelectedDatas, ChannelBean.TYPE_OTHER_CHANNEL);
        mDatas.addAll(mSelectedDatas);

        ChannelBean morechannel = new ChannelBean();
        morechannel.setItemType(ChannelBean.TYPE_OTHER);
        morechannel.setTitle("频道推荐");
        mDatas.add(morechannel);

        mDatas.addAll(mUnSelectedDatas);

        ItemDragHelperCallBack callback = new ItemDragHelperCallBack(this);
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        mAdapter = new ChannelAdapter(mDatas, helper);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == ChannelBean.TYPE_MY_CHANNEL || itemViewType == ChannelBean.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
//        mHelper = new ItemTouchHelper(callBack);
        mAdapter.OnChannelListener(this);
//        //attachRecyclerView
//        mHelper.attachToRecyclerView(mRecyclerView);
    }


    @OnClick(R.id.icon_collapse)
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        if (starPos < 0 || endPos < 0) return;
        if (mDatas.get(endPos).title.equals("头条")) return;
        //我的频道之间移动
        if (onChannelListener != null)
            onChannelListener.onItemMove(starPos - 1, endPos - 1);//去除标题所占的一个index
        onMove(starPos, endPos, false);
    }

    private String firstAddChannelName = "";


    private void onMove(int starPos, int endPos, boolean isAdd) {
        isUpdate = true;
        ChannelBean startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
        if (isAdd) {
            if (TextUtils.isEmpty(firstAddChannelName)) {
                firstAddChannelName = startChannel.title;
            }
        } else {
            if (startChannel.title.equals(firstAddChannelName)) {
                firstAddChannelName = "";
            }
        }
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        onMove(starPos, endPos, true);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        onMove(starPos, endPos, false);
    }

    @Override
    public void onFinish(String selectedChannelName) {
        EventBus.getDefault().post(new SelectChannelEvent(selectedChannelName));
        dismiss();
    }

    @Override
    public void onPause() {
        if (isUpdate) {
            NewChannelEvent newChannelEvent = new NewChannelEvent(mAdapter.getData(), firstAddChannelName);
            EventBus.getDefault().post(newChannelEvent);
        }
        super.onPause();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
