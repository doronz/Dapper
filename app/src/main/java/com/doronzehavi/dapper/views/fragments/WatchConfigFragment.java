package com.doronzehavi.dapper.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.mvp.presenters.ConfigFragmentPresenter;
import com.doronzehavi.dapper.mvp.views.ConfigView;

/**
 * This fragment will hold the configuration options for a watch.
 * ConfigView forces loadConfig() implementation
 */
public class WatchConfigFragment extends Fragment implements ConfigView {

    private ConfigFragmentPresenter mPresenter;
    private Watch mWatch;
    private View mContainer; // The fragment's view container


    // 1) Fragment created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mWatch = (Watch) args.getSerializable(Constants.KEY_WATCH);
        }
    }

    // 2) Creating view
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainer = inflater.inflate(R.layout.fragment_watch_config, container, false);
        mPresenter = new ConfigFragmentPresenter(this);
        return mContainer;
    }

    // 3) View created. Presenter for view created.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static WatchConfigFragment newInstance(Watch watch) {
        Log.d(Constants.TAG, "WatchConfigFragment: newInstance()");
        WatchConfigFragment f = new WatchConfigFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_WATCH, watch);
        f.setArguments(bundle);
        return f;
    }

    // Required to be public
    public WatchConfigFragment() {
        Log.d(Constants.TAG, "WatchConfigFragment: Constructor called.");
    }



    @Override
    public void loadConfig() {
        Log.d(Constants.TAG, "mWatch == null? " + String.valueOf(mWatch == null));



        /*Log.d(Constants.TAG, "WatchConfigFragment: loadFragmentLayout()");
        // 1) Get the grid layout for background options
        GridLayout watch_backgrounds_layout = (GridLayout) mContainer.findViewById(R.id.watch_background_grid_layout);
        // 2) For each background option...
        for (Map.Entry<String, Bitmap> entry : dataSource.getBackgrounds().entrySet()) {
            // 3) Create a config button
            ConfigButton bg_button = new ConfigButton(getActivity());
            // 4) Set that config button's background to the background option it represents
            bg_button.setBackground(new BitmapDrawable(getActivity().getResources(), entry.getValue()));
            // 5) If the background of the selected watch is the same as that of this button, disable the button so that it cannot be chosen.
            if (mWatch.getBackgroundKey().equals(entry.getKey())) {
                bg_button.setEnabled(false);
            } else {
                bg_button.setEnabled(true);
            }
            watch_backgrounds_layout.addView(bg_button);
        }*/
    }
}
