package com.chinasofti.phone.ui.fragment;

import com.chinasofti.common.base.BaseFragment;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.phone.R;


public class PhoneFragment extends BaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.pho_layout;
    }
}
