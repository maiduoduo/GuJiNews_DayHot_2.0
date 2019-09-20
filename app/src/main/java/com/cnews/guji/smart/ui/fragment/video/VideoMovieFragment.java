package com.cnews.guji.smart.ui.fragment.video;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpSubcribeFragment;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
import com.cnews.guji.smart.common.bean.VideoLifeChoiceBean;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreViewVideo;
import com.cnews.guji.smart.helper.refresh.cyg.header.VideoCustomHeader;
import com.cnews.guji.smart.ui.adapter.VideoLifeAdapter;
import com.cnews.guji.smart.ui.adapter.VideoLifeChoiceAdapter;
import com.cnews.guji.smart.ui.adapter.VideoLifeClassifySearchListAdapter;
import com.cnews.guji.smart.ui.contract.VideoMovieContract;
import com.cnews.guji.smart.ui.presenter.VideoMoviePresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.view.widget.state.ProgressLayout;
import com.github.library.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static android.view.View.VISIBLE;


/**
 * Author： JSYL_Dingcl
 * Des  :   生活视频专区
 */
public class VideoMovieFragment extends BaseMvpSubcribeFragment<VideoMoviePresenterimpl> implements VideoMovieContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.progressLayout)
    ProgressLayout progressLayout;
    @BindView(R.id.tv_classify_title)
    TextView tvTitle;


    private static boolean isFirstEnter = true;
    private static final String TAG = VideoMovieFragment.class.getSimpleName();
    private static int delayTime = 1000;
    private static int refreshTime = 2000;
    private VideoCustomHeader mVideoCustomHeader;
    private VideoLifeAdapter mAdapter;
    private List<String> newDatas = new ArrayList<>();

    private static final String TYPE_ID = "type_id";
    private static final String NATION_ID = "nation_id";
    private static final String PERIOD_ID = "period_id";
    private static final String TYPE_TITLE = "type_title";
    private static final String NATION_TITLE = "nation_title";
    private static final String PERIOD_TITLE = "period_title";

    /**
     * 单选Adapter
     */
    private VideoLifeChoiceAdapter singleChoiceAdapter1;
    private VideoLifeChoiceAdapter singleChoiceAdapter2;
    private VideoLifeChoiceAdapter singleChoiceAdapter3;
    private VideoLifeChoiceAdapter singleChoiceAdapter4;
    /**
     * 筛选列表Adapter
     */
    private VideoLifeClassifySearchListAdapter classifySearchListAdapter;
    private int offset;//偏移量
    private int catId;//电影分类
    private int sourceId;//地区Id
    private int yearId;//年代Id
    private int sortId = 3;//好评、最新、热门
    private int mDistanceY = 0;//滑动距离
    private int showTitleHeight;//开始显示的高度
    private int titleHeight;//顶部标题的高度
    private boolean isFirst = true;//判断显示标题栏
    /**
     * 初次显示的数据
     */
    private int searchId;
    private int nationId;
    private int periodId;
    private String typeTitle;
    private String nationTitle;
    private String periodTitle;
    private String sortTitle = "好评";


    public static VideoMovieFragment getInstance() {
        return new VideoMovieFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragmnet_video_life;
    }

    private List<String> initData() {
        return Arrays.asList("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","");
    }

    @Override
    public void initPresenter() {
            mPresenter = new VideoMoviePresenterimpl();
            mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        newDatas.clear();
        mVideoCustomHeader = new VideoCustomHeader(mContext);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(mAdapter = new VideoLifeAdapter(android.R.layout.simple_list_item_2,initData(),mContext));
        mRefreshLayout.setRefreshHeader(mVideoCustomHeader);
        mRefreshLayout.setHeaderHeight(60);


        initSelectedData();
        initRecyclerView();
        mRefreshLayout.autoRefresh();
        //触发自动刷新
//        if (isFirstEnter) {
//            isFirstEnter = false;
//            mPresenter.getMovieTypeList(mContext);
//            mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
//        } else {
//        }

    }


    /**
     * 获取初始化选中的的id和title
     */
    private void initSelectedData() {
        searchId = catId =  0;
        nationId = sourceId = 0;
        periodId = yearId = 0;
        typeTitle = TYPE_TITLE == null ? "全部" : TYPE_TITLE;
        nationTitle = NATION_TITLE == null ? "全部" : NATION_TITLE;
        periodTitle = PERIOD_TITLE == null ? "全部" : PERIOD_TITLE;
    }

    /**
     * 各个RecyclerView的初始化
     */
    private void initRecyclerView() {
        final View header = getActivity().getLayoutInflater().inflate(R.layout.video_layout_life_header, (ViewGroup) mRecyclerView.getParent(), false);
        RecyclerView rvSearchType = (RecyclerView) header.findViewById(R.id.rv_search_type);
        RecyclerView rvSearchNation = (RecyclerView) header.findViewById(R.id.rv_search_nation);
        RecyclerView rvSearchPeriod = (RecyclerView) header.findViewById(R.id.rv_search_period);
        RecyclerView rvSearchPoint = (RecyclerView) header.findViewById(R.id.rv_search_point);
        //影片类型
        singleChoiceAdapter1 = new VideoLifeChoiceAdapter();
        rvSearchType.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSearchType.setAdapter(singleChoiceAdapter1);
        singleChoiceAdapter1.setOnItemClickListener(new VideoLifeChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(VideoLifeChoiceBean bean) {
                offset = 0;
                catId = bean.id;
                typeTitle = bean.content;
                singleChoiceAdapter1.notifyDataSetChanged();
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
            }
        });
        //地区
        singleChoiceAdapter2 = new VideoLifeChoiceAdapter();
        rvSearchNation.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSearchNation.setAdapter(singleChoiceAdapter2);
        singleChoiceAdapter2.setOnItemClickListener(new VideoLifeChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(VideoLifeChoiceBean bean) {
                offset = 0;
                sourceId = bean.id;
                nationTitle = bean.content;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
            }
        });
        //年代
        singleChoiceAdapter3 = new VideoLifeChoiceAdapter();
        rvSearchPeriod.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSearchPeriod.setAdapter(singleChoiceAdapter3);
        singleChoiceAdapter3.setOnItemClickListener(new VideoLifeChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(VideoLifeChoiceBean bean) {
                offset = 0;
                periodTitle = bean.content;
                yearId = bean.id;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
            }
        });
        //评分
        singleChoiceAdapter4 = new VideoLifeChoiceAdapter();
        rvSearchPoint.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSearchPoint.setAdapter(singleChoiceAdapter4);
        singleChoiceAdapter4.setOnItemClickListener(new VideoLifeChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(VideoLifeChoiceBean bean) {
                offset = 0;
                sortTitle = bean.content;
                sortId = bean.id;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
            }
        });
        //筛选结果
        classifySearchListAdapter = new VideoLifeClassifySearchListAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        //滑动监听，显示title
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                showTitleHeight = header.getHeight();
                titleHeight = tvTitle.getHeight();
                if (showTitleHeight < mDistanceY) {
                    //显示
                    tvTitle.setVisibility(View.VISIBLE);
                    if (isFirst) {
                        //第一次显示的时候才滑出来
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvTitle, "translationY", -titleHeight, 0);
                        objectAnimator.setDuration(500);
                        objectAnimator.start();
                        isFirst = false;
                        String titleContent =
                                (typeTitle.equals("全部") ? "" : typeTitle + "·") +
                                        (nationTitle.equals("全部") ? "" : nationTitle + "·") +
                                        (periodTitle.equals("全部") ? "" : periodTitle + "·") +
                                        (sortTitle);
                        tvTitle.setText(titleContent);

                    }
                } else {
                    //隐藏
                    tvTitle.setVisibility(View.INVISIBLE);
                    isFirst = true;
                }
                mDistanceY += dy;
            }
        });

        mRecyclerView.setAdapter(classifySearchListAdapter);
        classifySearchListAdapter.addHeaderView(header);
        classifySearchListAdapter.setEnableLoadMore(true);
        classifySearchListAdapter.setLoadMoreView(new CustomLoadMoreViewVideo());
        classifySearchListAdapter.openLoadAnimation();
        classifySearchListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);
            }
        });
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initListener() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh ( @NonNull final RefreshLayout refreshLayout){
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getMovieTypeList(mContext);
                        mPresenter.getClassifySearchList(mContext,offset, catId, sourceId, yearId, sortId);



//                        newDatas.clear();
//                        Log.e("custom", "------------onRefresh--------------");
//                        newDatas.addAll(initData());
//                        TextView tvtitle = mVideoCustomHeader.getTvtitle();
//                        tvtitle.setVisibility(VISIBLE);
////                        tvtitle.setText("更新28条数据");
//                        tvtitle.setText("更新"+newDatas.size()+"条数据");
//                        mAdapter.setNewData(newDatas);
//                        refreshLayout.finishRefresh();
                    }
                }, refreshTime);
            }
        });
    }



    @Override
    public void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        ILog.e(TAG,"-----------------addMovieType----------------------");
        final List<VideoLifeChoiceBean> choiceBeanList = new ArrayList<>();
        VideoLifeChoiceBean singleChoiceBean = new VideoLifeChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = searchId == 0;
        choiceBeanList.add(0, singleChoiceBean);

        Observable
                .from(tagList)
                .map(new Func1<MovieTypeBean.DataBean.TagListBean, VideoLifeChoiceBean>() {
                    @Override
                    public VideoLifeChoiceBean call(MovieTypeBean.DataBean.TagListBean tagListBean) {
                        VideoLifeChoiceBean bean = new VideoLifeChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = searchId == tagListBean.getTagId();//判断是否与传过来的ID相等

                        return bean;
                    }
                })
                .toList()
                .subscribe(new Subscriber<List<VideoLifeChoiceBean>>() {
                    @Override
                    public void onCompleted() {
                        singleChoiceAdapter1.addData(choiceBeanList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<VideoLifeChoiceBean> baseSingleChoiceBeen) {
                        choiceBeanList.addAll(baseSingleChoiceBeen);
                    }
                });
    }

    @Override
    public void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        ILog.e(TAG,"-----------------addMovieNation----------------------");
        final List<VideoLifeChoiceBean> choiceBeanList2 = new ArrayList<>();
        VideoLifeChoiceBean singleChoiceBean = new VideoLifeChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = nationId == 0;

        choiceBeanList2.add(0, singleChoiceBean);

        Observable
                .from(tagList)
                .map(new Func1<MovieTypeBean.DataBean.TagListBean, VideoLifeChoiceBean>() {
                    @Override
                    public VideoLifeChoiceBean call(MovieTypeBean.DataBean.TagListBean tagListBean) {
                        VideoLifeChoiceBean bean = new VideoLifeChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = nationId == tagListBean.getTagId();//判断是否与传过来的ID相等

                        return bean;
                    }
                })
                .toList()
                .subscribe(new Subscriber<List<VideoLifeChoiceBean>>() {
                    @Override
                    public void onCompleted() {
                        singleChoiceAdapter2.addData(choiceBeanList2);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<VideoLifeChoiceBean> baseSingleChoiceBeen) {
                        choiceBeanList2.addAll(baseSingleChoiceBeen);
                    }
                });
    }

    @Override
    public void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<VideoLifeChoiceBean> choiceBeanList3 = new ArrayList<>();
        VideoLifeChoiceBean singleChoiceBean = new VideoLifeChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = periodId == 0;
        choiceBeanList3.add(0, singleChoiceBean);

        Observable
                .from(tagList)
                .map(new Func1<MovieTypeBean.DataBean.TagListBean, VideoLifeChoiceBean>() {
                    @Override
                    public VideoLifeChoiceBean call(MovieTypeBean.DataBean.TagListBean tagListBean) {
                        VideoLifeChoiceBean bean = new VideoLifeChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = periodId == tagListBean.getTagId();//判断是否与传过来的ID相等
                        return bean;
                    }
                })
                .toList()
                .subscribe(new Subscriber<List<VideoLifeChoiceBean>>() {
                    @Override
                    public void onCompleted() {
                        singleChoiceAdapter3.addData(choiceBeanList3);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<VideoLifeChoiceBean> baseSingleChoiceBeen) {
                        choiceBeanList3.addAll(baseSingleChoiceBeen);
                    }
                });
    }

    @Override
    public void addMoviePoint(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<VideoLifeChoiceBean> choiceBeanList4 = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            VideoLifeChoiceBean bean = new VideoLifeChoiceBean();
            bean.id = tagList.get(i).getTagId();
            bean.content = tagList.get(i).getTagName();
            bean.isSelect = i == 0;
            choiceBeanList4.add(bean);
        }
        singleChoiceAdapter4.addData(choiceBeanList4);
    }

    @Override
    public void addClassifySearchData(List<ClassifySearchBean.ListBean> list) {
        if (list.size() > 0) {
            offset += 10;
            TextView tvtitle = mVideoCustomHeader.getTvtitle();
            tvtitle.setVisibility(VISIBLE);
            tvtitle.setText("发现"+list.size()+"条精选电影");
            classifySearchListAdapter.addData(list);
            classifySearchListAdapter.loadMoreComplete();
        } else {
            classifySearchListAdapter.loadMoreEnd();
        }
    }

    @Override
    public void showContent() {
        mRefreshLayout.finishRefresh();
        if (!progressLayout.isContent()) {
            progressLayout.showContent();
        }
    }

    @Override
    public void showError(String errorMsg) {
        mRefreshLayout.finishRefresh();
        progressLayout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getMovieTypeList(mContext);
            }
        });
    }

    @Override
    public void showLoading() {
        if (!progressLayout.isContent()) {
            progressLayout.showLoading();
        }
    }
}
