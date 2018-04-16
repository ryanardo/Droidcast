package com.inc.droidcast.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.inc.droidcast.Constants;
import com.inc.droidcast.R;
import com.inc.droidcast.adapters.PodcastPagerAdapter;
import com.inc.droidcast.models.Podcast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private PodcastPagerAdapter adapterViewPager;
    ArrayList<Podcast> mPodcasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_detail);
        ButterKnife.bind(this);

//        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        mPodcasts = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_PODCASTS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new PodcastPagerAdapter(getSupportFragmentManager(), mPodcasts);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}