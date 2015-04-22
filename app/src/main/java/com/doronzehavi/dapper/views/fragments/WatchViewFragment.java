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
import com.doronzehavi.dapper.views.custom_views.WatchView;

/**
 * Holds a WatchView for the ViewPager
 */
public class WatchViewFragment extends Fragment{

    private Watch mWatch;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_watch_view, container, false);
        WatchView mWatchView = (WatchView) v.findViewById(R.id.watch_view);
        mWatchView.setWatch(mWatch);
        return v;
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
        WatchViewFragment f = new WatchViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_WATCH, watch);
        f.setArguments(bundle);
        return f;
    }

    public WatchViewFragment() {
    }

}
