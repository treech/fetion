package com.chinasofti.rcs.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.chinasofti.common.base.BaseActivity;
import com.chinasofti.common.base.BaseFragment;
import com.chinasofti.common.base.BasePresenter;
import com.chinasofti.common.manager.ViewManager;
import com.chinasofti.rcs.R;
import com.chinasofti.rcs.view.CommonTabLayout;
import com.chinasofti.rcs.view.CustomTabEntity;
import com.chinasofti.rcs.view.OnTabSelectListener;
import com.chinasofti.rcs.view.TabEntity;
import com.chinasofti.rcs.view.widget.MsgView;
import com.chinasofti.rcs.view.widget.UnreadMsgUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.chinasofti.common.utils.ScreenUtils.dp2px;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private Context mContext;
    private List<BaseFragment> mFragments;

    Random mRandom = new Random();
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};


    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWindowFeature() {

    }

    @Override
    public void initView() {
        super.initView();
        mContext = this;
        mFragments = ViewManager.getInstance().getAllFragment();
        mTitles = new String[]{
                getString(R.string.tab_message),
                getString(R.string.tab_contacts),
                getString(R.string.tab_phone),
                getString(R.string.tab_me)};
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = findView(mDecorView, R.id.vp);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        /** with ViewPager */
        mTabLayout = findView(mDecorView, R.id.tl);

        initViewPager();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.main_activity_layout;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void initViewPager() {
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);

        //角标数字
        initAngleNumber();
    }

    private void initAngleNumber() {
        //两位数
        mTabLayout.showMsg(0, 55);
        mTabLayout.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout.showMsg(1, 100);
        mTabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout.showDot(2);
        MsgView rtv_2 = mTabLayout.getMsgView(2);
        if (rtv_2 != null) {
            UnreadMsgUtils.setSize(rtv_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout.showMsg(3, 5);
        mTabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_3 = mTabLayout.getMsgView(3);
        if (rtv_3 != null) {
            rtv_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }
}
