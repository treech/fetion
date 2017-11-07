package com.chinasofti.contacts.ui.fragment;

import com.chinasofti.common.base.BaseFragment;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.contacts.R;

public class ContactsFragment extends BaseFragment {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.contacts_layout;
    }
}
