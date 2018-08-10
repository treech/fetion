package com.chinasofti.message.ui.activity;

import com.chinasofti.common.base.BaseActivity;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.message.R;

public class MessageActivity extends BaseActivity {

    @Override
    protected void initWindowFeature() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_message_layout;
    }
}
