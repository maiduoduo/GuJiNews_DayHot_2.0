package com.cnews.guji.smart.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.Base2Activity;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.adapter.SmallVideoItemAdapter;
import com.cnews.guji.smart.ui.holder.WeakDataHolder;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.FullWindowVideoView;
import com.cnews.guji.smart.view.widget.linearlayoutmanager.CustomLayoutManager;
import com.cnews.guji.smart.view.widget.linearlayoutmanager.OnViewPagerListener;
import com.jaeger.library.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SmallVideoDetailActivity extends Base2Activity {
    @BindView(R.id.flBack)
    FrameLayout flBack;
    private static final String TAG = SmallVideoDetailActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private SmallVideoItemAdapter mAdapter;
    private CustomLayoutManager myLayoutManager;
    private List<SmallVideoBean.DataBean> mList;
    private int mCurrentItem;
    private int height = 0;
    private int width = 0;
    private RotateAnimation rotate;

    public void getInstance() {
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }


    @Override
    public void intBase() {
        StatusBarCompatUtils.setDarkMode(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_small_video_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        initRotateAnim();
        mList = (List<SmallVideoBean.DataBean>) WeakDataHolder.getInstance().getData("videoUrlList");
        int position = getIntent().getIntExtra("position", -1);
        mCurrentItem = position;
        mRecyclerView = findViewById(R.id.recycler);
        myLayoutManager = new CustomLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new SmallVideoItemAdapter(mList,mContext,mCurrentItem);
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initListener() {
        myLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean bottom) {
                Log.e(TAG, "选择位置:" + position + " 下一页:" + bottom);
                playVideo(0);
            }
        });
    }


    @Override
    public void onRetry() {

    }

    @OnClick({
            R.id.flBack
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.flBack:
                finish();
                break;
        }
    }

    private void initRotateAnim() {
        rotate = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(2000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
    }

    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void playVideo(int position) {
        final int[] whatInfo = {300};
        final int[] extraInfo = {1000};
        View itemView = mRecyclerView.getChildAt(position);
        final FullWindowVideoView videoView = itemView.findViewById(R.id.video_view);
        final FullWindowVideoView horivideoView = itemView.findViewById(R.id.video_view_hori);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView musicCard = itemView.findViewById(R.id.musicCard);
        final RelativeLayout root_view = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        //设置唱片旋转
        if (rotate != null && musicCard != null && rotate.hasStarted()) {
            musicCard.clearAnimation();
            musicCard.startAnimation(rotate);
        } else {
            musicCard.setAnimation(rotate);
            musicCard.startAnimation(rotate);
        }


        //横向
        horivideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        horivideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                whatInfo[0] = what;
                extraInfo[0] = extra;
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        horivideoView.start();
        horivideoView.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (horivideoView.isPlaying()) {
                    imgPlay.animate().alpha(0.7f).start();
                    horivideoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    horivideoView.start();
                    isPlaying = true;
                }
            }
        });

        //纵向
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                whatInfo[0] = what;
                extraInfo[0] = extra;
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.start();
        videoView.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imgPlay.animate().alpha(0.7f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }


    @Override
    protected void setStatusBar() {
        int mStatusBarColor = getResources().getColor(R.color.black);
        StatusBarUtil.setColor(this, mStatusBarColor, 0);
//        mToolbar.setBackgroundColor(mStatusBarColor);
        StatusBarCompatUtils.setDarkMode(this);
    }

}