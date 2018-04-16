package com.inc.droidcast.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.ui.PodcastDetailFragment;

import java.util.ArrayList;

public class PodcastPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Podcast> mPodcasts;

    public PodcastPagerAdapter(FragmentManager fm, ArrayList<Podcast> podcasts) {
        super(fm);
        mPodcasts = podcasts;
    }

    @Override
    public Fragment getItem(int position) {
        return PodcastDetailFragment.newInstance(mPodcasts, position);
    }

    @Override
    public int getCount() {
        return mPodcasts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPodcasts.get(position).getTrackTitle();
    }
}
