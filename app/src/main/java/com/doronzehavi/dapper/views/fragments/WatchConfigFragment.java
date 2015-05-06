package com.doronzehavi.dapper.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.WatchComponent;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.mvp.presenters.ConfigFragmentPresenter;
import com.doronzehavi.dapper.mvp.views.ConfigView;
import com.doronzehavi.dapper.views.custom_views.BackgroundConfigButton;
import com.doronzehavi.dapper.views.custom_views.ConfigButton;

import java.util.Map;

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

    View.OnClickListener mConfigButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            // Find out which component of the watch needs to be modified
            ConfigButton button = (ConfigButton) v;
            String componentKey = button.getComponentKey();
            // Find out what resource it needs to be modified with
            String componentResourceKey = button.getComponentResourceKey();
            // Update the watch's configuration
            Log.d(Constants.TAG, "ConfigButton clicked: " + componentKey + ":" + componentResourceKey);
            mWatch.update(componentKey, componentResourceKey );
        }
    };

    @Override
    public void loadConfig() {
        GridLayout layout = (GridLayout) getView().findViewById(R.id.watch_background_grid_layout);
        TextView header = (TextView) getView().findViewById(R.id.watch_config_header_text);
        header.setText("Watch #" + mWatch.getPosition());
        for (Map.Entry<String, WatchComponent> bgComp : mWatch.getBackgroundComponents().entrySet()) {
            BackgroundConfigButton bg_button = new BackgroundConfigButton(getActivity());
            bg_button.setOnClickListener(mConfigButtonClickListener);
            bg_button.setComponentKey(Constants.KEY_BACKGROUND);
            bg_button.setComponentResourceKey(bgComp.getValue().getKey());
            // Makes the pictures fit perfectly in the center of the button
            bg_button.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // Gets the circular drawable version of the background
            bg_button.setImage(bgComp.getKey());
            if (mWatch.getBackgroundKey().equals(bgComp.getKey())) {
                bg_button.setEnabled(false); // disable already chosen background
            }
            else {
                bg_button.setEnabled(true); // enable other options
            }
            layout.addView(bg_button);
        }
    }
}
