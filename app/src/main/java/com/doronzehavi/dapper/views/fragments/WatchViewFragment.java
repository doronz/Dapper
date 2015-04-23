package com.doronzehavi.dapper.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.mvp.presenters.WatchViewFragmentPresenter;
import com.doronzehavi.dapper.mvp.views.WatchFaceView;
import com.doronzehavi.dapper.views.custom_views.WatchView;

/**
 * Holds a WatchView for the ViewPager
 */
public class WatchViewFragment extends Fragment implements WatchFaceView {
    private WatchViewFragmentPresenter mPresenter;
    private View mContainer;
    private Watch mWatch;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainer = inflater.inflate(R.layout.fragment_watch_view, container, false);
        mPresenter = new WatchViewFragmentPresenter(this);
        return mContainer;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    public void updateWatch(){
        WatchView mWatchView = (WatchView) mContainer.findViewById(R.id.watch_view);
        mWatchView.setWatch(mWatch);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mWatch = (Watch) args.getSerializable(Constants.KEY_WATCH);
        }
    }

    public static WatchViewFragment newInstance(Watch watch) {
        if (watch == null) throw new IllegalArgumentException("WatchView must have a non-null watch!");
        WatchViewFragment f = new WatchViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_WATCH, watch);
        f.setArguments(bundle);
        return f;
    }

    public WatchViewFragment() {
    }

    public Watch getWatch() {
        return mWatch;
    }
}
