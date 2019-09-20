package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.ui.activity.MovieDetailActivity;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgSizeUtil;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 生活分类
 */

public class VideoLifeClassifySearchListAdapter extends BaseMultiItemQuickAdapter<ClassifySearchBean.ListBean, BaseViewHolder> {
    private Context mContext;
    public VideoLifeClassifySearchListAdapter(Context mContext) {
        super(null);
        this.mContext = mContext;
        addItemType(ApiConstant.TYPE_CLASSIFY_NORMAL, R.layout.item_classify_normal);
        addItemType(ApiConstant.TYPE_CLASSIFY_WISH, R.layout.item_classify_wish);
        addItemType(ApiConstant.TYPE_CLASSIFY_BUY, R.layout.item_classify_buy);
        addItemType(ApiConstant.TYPE_CLASSIFY_PRESALE, R.layout.item_classify_presale);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassifySearchBean.ListBean item) {
        String imgUrl = ImgSizeUtil.resetPicUrl(item.getImg(), ".webp@210w_285h_1e_1c_1l");
//        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_img));
        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_img),imgUrl);
        helper.setText(R.id.tv_movie_name, item.getNm())
                .setText(R.id.tv_movie_english_name, item.getEnm())
                .setText(R.id.tv_movie_type, item.getCat())
                .setText(R.id.tv_movie_publicDate, item.getPubDesc());

        //显示3D和IMAX的标签
        if (item.getVer().contains("IMAX 3D")) {
            helper.setImageResource(R.id.iv_ver, R.drawable.ic_3d_imax);

        } else if (item.getVer().contains("3D")) {
            helper.setImageResource(R.id.iv_ver, R.drawable.ic_3d);
        }
        switch (helper.getItemViewType()) {
            case ApiConstant.TYPE_CLASSIFY_NORMAL:
                if(item.getSc()==0){
                    helper.setText(R.id.tv_point,"暂无评分");
                    helper.getView(R.id.tv_movie_score).setVisibility(View.INVISIBLE);
                }else {
                    helper.setText(R.id.tv_movie_score, String.format("%s", item.getSc()));
                }
                break;
            case ApiConstant.TYPE_CLASSIFY_WISH:
                helper.setText(R.id.tv_movie_wish, String.format("%s", item.getWish()));
                break;
            case ApiConstant.TYPE_CLASSIFY_BUY:
                if(item.getSc()==0){
                    helper.setText(R.id.tv_point,"暂无评分");
                    helper.getView(R.id.tv_movie_score).setVisibility(View.INVISIBLE);
                }else {
                    helper.setText(R.id.tv_movie_score, String.format("%s", item.getSc()));
                }
                break;
            case ApiConstant.TYPE_CLASSIFY_PRESALE:
                if(item.getSc()==0){
                    TextView view = helper.getView(R.id.tv_point);
                    if (view != null){
                        view.setText("暂无评分");
                    }
                }else {
                    helper.getView(R.id.tv_movie_score).setVisibility(View.INVISIBLE);
                    helper.setText(R.id.tv_movie_score, String.format("%s", item.getSc()));
                }
                break;
        }
        helper.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.start(mContext,item.getId());
            }
        });
    }
}
