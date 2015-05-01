package com.doronzehavi.dapper.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.views.fragments.WatchViewFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ViewPager adapter for the MainActivity.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Watch> mWatchList;
    private Map<Integer, WatchViewFragment> mWatchViewFragmentList = new HashMap();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    /**
     * Load the given watch configs into a map of WatchConfigFragments
     * @param watches watches loaded by the activity
     */
    //TODO: notifyDataSet()
    public void loadWatches(List<Watch> watches){
        mWatchList = watches;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
       Log.d(Constants.TAG, "Adapter.getItem(" + position + ")");
       mWatchViewFragmentList.put(position, WatchViewFragment.newInstance(mWatchList.get(position)));
       return mWatchViewFragmentList.get(position);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        WatchViewFragment fragment = (WatchViewFragment) super.instantiateItem(container, position);
        Log.d(Constants.TAG, "Adapter.instantiateItem(" + position + ")");
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Log.d(Constants.TAG, "Adapter.DestroyItem(" + position + ")");
        mWatchViewFragmentList.remove(position);
    }

    public WatchViewFragment getFragment(int position) {
        return mWatchViewFragmentList.get(position);
    }

    @Override
    public int getCount() {
        Log.d(Constants.TAG, "Adapter.getCount()");
        if (mWatchList != null)
            return mWatchList.size();
        return 0;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        Log.d(Constants.TAG, "Adapter.finishUpdate()");
    }

}
