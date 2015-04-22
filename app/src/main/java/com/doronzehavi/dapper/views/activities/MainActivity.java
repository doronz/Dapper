package com.doronzehavi.dapper.views.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.mvp.presenters.MainPresenter;
import com.doronzehavi.dapper.mvp.views.MainView;
import com.doronzehavi.model.entities.Watch;
import com.doronzehavi.model.entities.WatchesWrapper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Main activity showing watches and configs.
 */
public class MainActivity extends ActionBarActivity implements MainView {

    private MainPresenter mMainPresenter;

    @InjectView(R.id.activity_main_toolbar)     Toolbar mToolbar;
    @InjectView(R.id.activity_main_progress)    ProgressBar mProgressBar;
    @InjectView(R.id.activity_main_text)        TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {

            mMainPresenter = new MainPresenter(this);

        } else {
            // TODO: reuse our already loaded com.doronzehavi.model.data.
            mMainPresenter = new MainPresenter(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainPresenter.stop();
    }

    @Override
    protected void onStart() { // This is where the action starts.
        super.onStart();
        mMainPresenter.start();
    }

    @Override
    public void showWatches(WatchesWrapper watches) {
        List<Watch> watchesList = watches.getWatches();
        StringBuilder sb = new StringBuilder();
        for (Watch watch : watchesList) {
            sb.append(watch.getId());
            sb.append(" : ");
            sb.append(watch.getBackground());
            sb.append('\n');
        }
        mText.setText(sb.toString());
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
