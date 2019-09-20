package com.cnews.guji.smart.ui.fragment.me;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.app.bugly.BuglyHelper;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseRxFragment;
import com.cnews.guji.smart.base.baserx.ServerException;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.common.bean.ShareBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.righttopmenu.MenuItem;
import com.cnews.guji.smart.helper.righttopmenu.TopRightMenu;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.adapter.ProfileAdapter;
import com.cnews.guji.smart.ui.contract.MeContract;
import com.cnews.guji.smart.ui.model.ProfileCareModel;
import com.cnews.guji.smart.ui.presenter.ProfileCarePresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 * @author JSYL-DCL
 */
public class ProfileMainFragment extends BaseRxFragment<ProfileCarePresenterimpl,ProfileCareModel> implements MeContract.View {
    private static final String TAG2 = "mainFragment";
    private static int SIZE = 20;
    private int mStartPage = 1;
    private TopRightMenu mTopRightMenu;
    private ShareBean shareBean;
    private  List<ProfileCareBean.Profile_data_list> profileList;
    private ProfileAdapter mAdapter;
    @BindView(R.id.comment_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.article_img1)
    ExpandImageView mHeaderImg;
    @BindView(R.id.header_menu)
    AppCompatImageView mHeaderMenu;
    @BindView(R.id.collapse_menu)
    AppCompatImageView mCollapseMenu;
    @BindView(R.id.collapseHeaderIco)
    ExpandImageView mCollapseHeaderIco;
    @BindView(R.id.back_ima)
    ImageView mBackIma;
    @BindView(R.id.btnUpgradeCheck)
    Button mBtnUpgradeCheck;

    public static ProfileMainFragment getInstance(String title) {
        ProfileMainFragment sf = new ProfileMainFragment();
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_care_profile;
    }

    @Override
    public void intBase() {
        if (profileList != null) profileList.clear();
        else profileList = new ArrayList<>();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        mAdapter = new ProfileAdapter(getActivity(),profileList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getData(mActivity);

        //设置圆形本地头像
        mHeaderImg.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.mipmap.avtar_header_b));
        mCollapseHeaderIco.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.mipmap.avtar_header_b));
        mBackIma.setAlpha(150);
    }


    @OnClick({
            R.id.header_menu
            , R.id.collapse_menu
            , R.id.btnUpgradeCheck
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.header_menu:
                initPopupWindow(0);
                break;
            case R.id.collapse_menu:
                initPopupWindow(1);
                break;
            case R.id.btnUpgradeCheck:
                //检测更新
//                ToastUitl.showShort("检查更新");
                BuglyHelper.getInstance().doCheckUpgrade(getActivity());
                loadUpgradeInfo();
                break;
        }
    }

    private void loadUpgradeInfo() {
//		if (upgradeInfoTv == null)
//			return;

        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();

        if (upgradeInfo == null) {
            ILog.e("upgrade","[bugly]升级：无升级信息");
//			upgradeInfoTv.setText("无升级信息");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType).append("\n");
        info.append("图片地址：").append(upgradeInfo.imageUrl);

        ILog.e("upgrade","[bugly]升级info=============>：\n"+info);
//		upgradeInfoTv.setText(info);
    }

    @Override
    protected void initListener() {

    }

    /**
     * 个人导航数据
     * @param data
     */
    @Override
    public void setData(ProfileCareBean data) {
        if (data == null) {
            return;
        }
        stopProgressDialog();
        List<ProfileCareBean.Profile_data_list> profile_data_list = data.profile_data_list;
        mAdapter.getData().clear();
        if (profile_data_list != null) {
            mAdapter.setNewData(profile_data_list);
        }else {
            ToastUitl.showShort("未查询到信息");
        }
    }

    @Override
    public void setDataWares(ProfileCareBean data) {

    }

    @Override
    public void setDataMoreWares(ProfileCareBean data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFaild(Throwable throwable, String error) {
        throwable.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetworkAvailable(BaseApplication.getAppContext())) {
            ToastUitl.showShort(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (throwable instanceof ServerException) {
            ToastUitl.showShort(throwable.getMessage());
        }
        //其它
        else {
            ToastUitl.showShort(BaseApplication.getAppContext().getString(R.string.net_error));
        }
    }


    @Override
    public void showNoNet() {

    }



    @Override
    public void onRetry() {

    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }


    public class TypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private int[] c;

        public TypeAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
            c = new int[]{Color.parseColor("#33FF0000"),
                    Color.parseColor("#3300FF00"),
                    Color.parseColor("#330000FF")};
        }

        @Override
        protected void convert(BaseViewHolder helper, String s) {
            int position = helper.getAdapterPosition();
            helper.setText(R.id.item_tv, "item" + s);
        }
    }


    /**
     * 当前界面是否隐藏
     * 这种方法适用于界面替换用到hide()和show()方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//展示，可见  // 相当于Fragment的onResume()
            ILog.e(TAG2, "onHiddenChanged ProfileMainFragment:" + hidden);
            // 相当于Fragment的onResume()
            StatusBarCompatUtils.setDarkMode(getActivity());
        } else {
            //相当于Fragment的onPause()
            ILog.e(TAG2, "[else]onHiddenChanged ProfileMainFragment:" + hidden);
        }
    }

    /**
     * 当前界面是否对用户可见
     * 这种党法适用于FragmentTransaction的replace()
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (getUserVisibleHint()) {//界面可见时
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isVisibleToUser) {//展示，可见   // 相当于onResume()方法--获取焦点
        } else {
            // 相当于onpause()方法---失去焦点
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 初始化右上角弹窗
     */
    private void initPopupWindow(int posType) {
        mTopRightMenu = new TopRightMenu(getActivity());
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.mipmap.profile_ico_top_share, "分享app"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "加入官方群"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "设置"));

        mTopRightMenu
                .showIcon(false)
                .dimBackground(true)
                .needAnimationStyle(true)
                .showIcon(false)
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                .addMenuList(menuItems)
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        switch (position) {
                            case 0:
                                shareApp();
                                break;
                            case 1:
                                if (!TextUtils.isEmpty(AppConstant.QQ_KEY)) {
                                    BaseApplication.joinQQGroup(getActivity(), AppConstant.QQ_KEY);//加入QQ群
                                }
                                break;
                            case 2:
                                startActivity(new Intent(mActivity, GJSettingActivity.class));
                                break;
                        }


                    }
                })
                .showAsDropDown(posType == 0 ? mHeaderMenu : mCollapseMenu, posType == 0 ? -mHeaderMenu.getWidth() - 10 : mCollapseMenu.getWidth() - 10, -10);

    }

    private void shareApp() {
        ToastUitl.showShort("分享");
        if (shareBean == null) {
            shareBean = new ShareBean("咕唧新闻客户端", "咕唧新闻，给您最好的体验，最多最用心的新闻资讯", "https://www.lanzous.com/b311881/", "");
        }
    }

}
