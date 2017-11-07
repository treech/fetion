package com.chinasofti.message.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chinasofti.common.base.BaseFragment;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.message.R;


public class MessageFragment extends BaseFragment {

    public  MessageFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.message_layout;
    }
}
