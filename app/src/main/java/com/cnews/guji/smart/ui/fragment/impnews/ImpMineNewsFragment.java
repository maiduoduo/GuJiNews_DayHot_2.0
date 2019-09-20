package com.cnews.guji.smart.ui.fragment.impnews;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 要闻-我的
 *
 * @author JSYL-DCL
 */
public class ImpMineNewsFragment extends BaseFragment {
    private static final String TAG = "MineFrontNewsFragment";

    @BindView(R.id.btnLoginin)
    Button btnLoginin;


    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_front_news;
    }

    @Override
    public void initPresenter() {

    }



    @Override
    public void bindView(View view, Bundle savedInstanceState) {


    }

    @Override
    public void bindData() {

    }

    @Override
    public void initListener() {
        StatusBarCompatUtils.setLightMode(getActivity());
    }

    @OnClick({R.id.btnLoginin})
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginin:
                ToastUitl.showShort("loginin");
                break;
        }
    }
}
