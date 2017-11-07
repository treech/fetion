package com.chinasofti.me.ui.fragment;

import com.chinasofti.common.base.BaseFragment;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.me.R;


public class MeFragment extends BaseFragment {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.me_layout;
    }
}
