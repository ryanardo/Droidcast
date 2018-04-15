package com.inc.droidcast.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.ui.PodcastDetailFragment;

import java.util.ArrayList;

public class PodcastPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Podcast> mPodcasts;
    private String mSource;

    public PodcastPagerAdapter(FragmentManager fm, ArrayList<Podcast> podcasts, String source) {
        super(fm);
        mPodcasts = podcasts;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return PodcastDetailFragment.newInstance(mPodcasts, position, mSource);
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
