package com.doronzehavi.dapper.views.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;
import com.doronzehavi.dapper.mvp.presenters.MainPresenter;
import com.doronzehavi.dapper.mvp.views.MainView;
import com.doronzehavi.dapper.views.adapters.MainViewPagerAdapter;
import com.doronzehavi.dapper.views.fragments.WatchConfigFragment;
import com.doronzehavi.dapper.views.fragments.WatchViewFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Main activity showing watches and configs.
 * Activities are views and are only responsible for manipulating their UI.
 * The presenter calls it's methods.
 */
public class MainActivity extends ActionBarActivity implements MainView {

    private MainPresenter mMainPresenter;
    private MainViewPagerAdapter mAdapter;
    private WatchViewFragment mCurrWatchFragment;

    // Loads xml views
    @InjectView(R.id.activity_main_toolbar)                 Toolbar mToolbar;
    @InjectView(R.id.activity_main_progress)                ProgressBar mProgressBar;
    @InjectView(R.id.activity_main_view_pager)              ViewPager mPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(pageChangeListener);

        /**
         * 1) Loads up the presenter
         */
        if (savedInstanceState == null) {
            mMainPresenter = new MainPresenter(this);

        } else {
            // TODO: reuse our already loaded com.doronzehavi.dapper.model.data.
            mMainPresenter = new MainPresenter(this);
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mCurrWatchFragment = (WatchViewFragment) mAdapter.instantiateItem(mPager, position);
            loadConfigFragment();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * Called by the presenter once it receives new watches
     * @param watches the user's watches which are then displayed by the view
     */
    @Override
    public void showWatches(WatchesWrapper watches) {
        mAdapter.loadWatches(watches.getWatches());
        mCurrWatchFragment = (WatchViewFragment) mAdapter.instantiateItem(mPager, mPager.getCurrentItem());
        loadConfigFragment();
    }

    @Override
    protected void onStart() { // This is where the action starts.
        super.onStart();
        mMainPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainPresenter.stop();
    }

    public void loadConfigFragment(){
        if (mCurrWatchFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_main_fragment_config_holder,
                    WatchConfigFragment.newInstance(mCurrWatchFragment.getWatch()));
            ft.commit();
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
