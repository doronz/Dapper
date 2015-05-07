package com.doronzehavi.dapper.views.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;
import com.doronzehavi.dapper.mvp.presenters.MainPresenter;
import com.doronzehavi.dapper.mvp.views.MainView;
import com.doronzehavi.dapper.views.adapters.MainViewPagerAdapter;
import com.doronzehavi.dapper.views.fragments.WatchConfigFragment;

import java.util.List;

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
            updateMainFrag(position);
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
        /**
         * This is used to populate the config fragment when watches are loaded.
         */
        mPager.post(new Runnable() {
            @Override
            public void run() {
                pageChangeListener.onPageSelected(mPager.getCurrentItem());
                Log.d(Constants.TAG, "Notifying pagechangelistner of the current page being selected.");
            }
        });
    }


    @Override
    public void updateMainFrag(int position) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_holder,
                WatchConfigFragment.newInstance(mAdapter.getFragment(position).getWatch()))
                .commit();
    }

    @Override
    public List<Watch> getWatches() {
        return mAdapter.getWatchList();
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

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
