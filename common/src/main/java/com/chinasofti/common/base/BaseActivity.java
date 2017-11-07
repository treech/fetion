package com.chinasofti.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.chinasofti.common.R;
import com.chinasofti.common.manager.ActivityManager;
import com.chinasofti.common.utils.CommonUtils;
import com.chinasofti.common.utils.statusbar.StatusBarUtil;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    //以下是所有Activity中可能会出现的控件
//    @Bind(R.id.appBar)
//    protected AppBarLayout mAppBar;
//    //    @Bind(R.id.toolbar)
//    //    protected Toolbar mToolbar;
//    @Bind(R.id.flToolbar)
//    public FrameLayout mToolbar;
//    @Bind(R.id.ivToolbarNavigation)
//    public ImageView mToolbarNavigation;
//    @Bind(R.id.vToolbarDivision)
//    public View mToolbarDivision;
//    @Bind(R.id.llToolbarTitle)
//    public AutoLinearLayout mLlToolbarTitle;
//    @Bind(R.id.tvToolbarTitle)
//    public TextView mToolbarTitle;
//    @Bind(R.id.tvToolbarSubTitle)
    public TextView mToolbarSubTitle;

    protected T mPresenter;
    private Unbinder mUnbinder;


    /**
     * 替代findviewById方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    public static <T extends View> T findView(View view, int id)
    {
        return (T) view.findViewById(id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        initWindowFeature();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        mUnbinder = ButterKnife.bind(this);
        setupAppBarAndToolbar();

        //沉浸式状态栏
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.colorPrimaryDark), 10);

        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        mUnbinder.unbind();
    }


    //在setContentView()调用之前调用，可以设置WindowFeature
    //如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    protected abstract void initWindowFeature();

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    public void initView() {}

    public void initData() {}

    public void initListener() {}

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }

    /**
     * 设置AppBar和Toolbar
     */
    private void setupAppBarAndToolbar() {
        //如果该应用运行在android 5.0以上设备，设置标题栏的z轴高度
//        if (mAppBar != null && Build.VERSION.SDK_INT > 21) {
//            mAppBar.setElevation(10.6f);
//        }

        //如果界面中有使用toolbar，则使用toolbar替代actionbar
        //默认不是使用NoActionBar主题，所以如果需要使用Toolbar，需要自定义NoActionBar主题后，在AndroidManifest.xml中对指定Activity设置theme
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            if (isToolbarCanBack()) {
//                ActionBar actionBar = getSupportActionBar();
//                if (actionBar != null) {
//                    actionBar.setDisplayHomeAsUpEnabled(true);
//                }
//            }
//        }

//        mToolbarNavigation.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
//        mToolbarDivision.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
//        mToolbarNavigation.setOnClickListener(v -> onBackPressed());
//        mLlToolbarTitle.setPadding(isToolbarCanBack() ? 0 : 40, 0, 0, 0);
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

//    public void jumpToWebViewActivity(String url) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra("url", url);
//        jumpToActivity(intent);
//    }


    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    /**
     * Setup the toolbar.
     *
     * @param toolbar   toolbar
     * @param hideTitle 是否隐藏Title
     */
    protected void setupToolBar(Toolbar toolbar, boolean hideTitle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            if (hideTitle) {
                //隐藏Title
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }


    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        CommonUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 替换fragment
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        CommonUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 显示fragment
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        CommonUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /*------------------ toolbar的一些视图操作 ------------------*/
    public void setToolbarTitle(String title) {
//        mToolbarTitle.setText(title);
    }

    public void setToolbarSubTitle(String subTitle) {
        mToolbarSubTitle.setText(subTitle);
        mToolbarSubTitle.setVisibility(subTitle.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
