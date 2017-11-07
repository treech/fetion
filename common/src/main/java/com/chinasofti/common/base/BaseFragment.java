package com.chinasofti.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chinasofti.common.utils.CommonUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;
    protected BaseActivity mActivity;
    private View mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        mRootView = inflater.inflate(provideContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public void init() {}

    public void initView(View rootView) {}

    public void initData() {}

    public void initListener() {}

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

            /*------------------ 模拟fragment堆栈管理的操作 ------------------*/

    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        CommonUtils.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment, frameId);
    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        CommonUtils.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);

    }

    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }

}
