package com.doronzehavi.dapper.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.views.fragments.WatchViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * The ViewPager adapter for the MainActivity.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<WatchViewFragment> mWatchViewFragmentList = new ArrayList<>();


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public void loadWatches(List<Watch> watches){
        if (!mWatchViewFragmentList.isEmpty()){
            mWatchViewFragmentList.clear();
        }
        for (Watch watch : watches) {
            mWatchViewFragmentList.add(WatchViewFragment.newInstance(watch));
        }
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (mWatchViewFragmentList != null && mWatchViewFragmentList.size() > position)
            return mWatchViewFragmentList.get(position);
        else
            return null;
    }

    @Override
    public int getCount() {
        if (mWatchViewFragmentList != null)
            return mWatchViewFragmentList.size();
        return 0;
    }
}
