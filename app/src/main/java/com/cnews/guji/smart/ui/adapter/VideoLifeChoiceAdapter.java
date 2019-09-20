package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.VideoLifeChoiceBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 单选RecyclerView的Adapter
 * 视频-生活
 */
public class VideoLifeChoiceAdapter extends RecyclerView.Adapter<VideoLifeChoiceAdapter.ViewHolder> {

    private int selectedPos = 0;
    private List<VideoLifeChoiceBean> mData;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public VideoLifeChoiceAdapter() {
        mData = new ArrayList<>();

    }

    public void addData(List<VideoLifeChoiceBean> data) {
        mData.addAll(data);
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelect) {
                selectedPos = i;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public VideoLifeChoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_single_choie_filter_videolife, parent, false));
    }

    @Override
    public void onBindViewHolder(final VideoLifeChoiceAdapter.ViewHolder holder, int position) {
        holder.tv.setText(mData.get(holder.getAdapterPosition()).content);
        if (mData.get(holder.getAdapterPosition()).isSelect) {
            holder.tv.setBackgroundResource(R.drawable.bg_tv_movie_type_pre);
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tv.setBackgroundResource(R.drawable.bg_tv_movie_type_nor);
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.text_primary));
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPos != holder.getAdapterPosition()) {
                    mData.get(selectedPos).isSelect = false;
                    notifyItemChanged(selectedPos);
                    selectedPos = holder.getAdapterPosition();
                    mData.get(selectedPos).isSelect = true;
                    notifyItemChanged(selectedPos);
                    if (onItemClickListener != null) {
                        onItemClickListener.click(mData.get(holder.getAdapterPosition()));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_movie_type);
        }
    }

    public interface OnItemClickListener {
        void click(VideoLifeChoiceBean bean);
    }
}
