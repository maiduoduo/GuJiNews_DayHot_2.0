package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.view.TextViewStyleView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;

/**
 * @package: HomeCommentRecycleAdapter
 * @author： JSYL-DCL
 * @date: 2019/5/16
 * @describe： TODO
 * @email： 11442865
 */
public class HomeCommentRecycleAdapter extends BaseQuickAdapter<HomeCommentBean.CommentsBeanX,BaseViewHolder> {
    private static final String TAG1 = "HotNews";
    private Context mContext;
    public HomeCommentRecycleAdapter(Context context,int layoutResId, List<HomeCommentBean.CommentsBeanX> data) {
        super(layoutResId, data);
        mContext = context;
    }



    @Override
    protected void convert(BaseViewHolder holder, HomeCommentBean.CommentsBeanX data) {
        int adapterPosition = holder.getAdapterPosition();
        if (data != null) {
            if ("0001".equals(data.getOs())) {
                holder.getView(R.id.title_item).setVisibility(View.VISIBLE);
                holder.setText(R.id.titleName, "热门评论");
            } else if ("0002".equals(data.getOs())) {
                holder.getView(R.id.title_item).setVisibility(View.VISIBLE);
                holder.setText(R.id.titleName, "最新评论");
            } else {
                holder.getView(R.id.title_item).setVisibility(View.GONE);
                holder.setText(R.id.titleName, "%s评论");
            }
            holder.setText(R.id.tvName, data.getUname() == null ? "" : data.getUname());
            holder.setText(R.id.tvZanNum, data.getUptimes()+"");
            holder.setText(R.id.tvLocationTime, data.getIp_from() == null ? "" : data.getIp_from()+data.getAdd_time());
            holder.setText(R.id.tvmainComment, data.getComment_contents() == null ? "" : data.getComment_contents());
            ((ExpandImageView)holder.getView(R.id.header_ico)).setImageURI(data.getFaceurl() == null ? "" : data.getFaceurl());
            if (data.getChildren().getComments().size() > 0) {
                holder.getView(R.id.llChildComment).setVisibility(View.VISIBLE);
                holder.setText(R.id.tvAllSee, String.format(mContext.getResources().getString(R.string.see_all_child_comment), data.getChildren().getCount()));
                TextViewStyleView tvStyleUname1 = holder.getView(R.id.tvStyleUname1);
                TextViewStyleView tvStyleUname2 = holder.getView(R.id.tvStyleUname2);
                List<HomeCommentBean.CommentsBeanX.ChildrenBean.CommentsBean> comments = data.getChildren().getComments();
                String count = data.getChildren().getCount();
                for (int i = 0; i < comments.size(); i++) {
                    String uname = comments.get(i).getUname();
                    String reply_uname = comments.get(i).getReply_uname();
                    if (0 == i){
                        if (reply_uname != null && TextUtils.isEmpty(reply_uname)) {
                            tvStyleUname1.setTextStyle2(mContext, comments.get(i).getUname() + " 回复 " + reply_uname+":  "+ comments.get(i).getComment_contents(),
                                    comments.get(i).getUname(), reply_uname+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                        }else {
                            tvStyleUname1.setTextStyle(mContext, comments.get(i).getUname()+":  "+ comments.get(i).getComment_contents(),
                                    comments.get(i).getUname()+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                        }
                    }
                   if ("2".equals(count)){
                       tvStyleUname2.setVisibility(View.VISIBLE);
                       if (1 == i){
                           if (reply_uname != null && TextUtils.isEmpty(reply_uname)) {
                               tvStyleUname2.setTextStyle2(mContext, comments.get(i).getUname() + " 回复 " + reply_uname+":  "+ comments.get(i).getComment_contents(),
                                       comments.get(i).getUname(), reply_uname+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                           }else {
                               tvStyleUname2.setTextStyle(mContext, comments.get(i).getUname()+":  "+ comments.get(i).getComment_contents(),
                                       comments.get(i).getUname()+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                           }
                       }
                    }else {
                       tvStyleUname2.setVisibility(View.GONE);
                   }
                }
            }else {
                holder.getView(R.id.llChildComment).setVisibility(View.GONE);
            }
        }
    }
}
