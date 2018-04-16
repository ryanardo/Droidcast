package com.inc.droidcast.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inc.droidcast.Constants;
import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.util.OnPodcastSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class PodcastListActivity extends AppCompatActivity implements OnPodcastSelectedListener {
    private Integer mPosition;
    ArrayList<Podcast> mPodcasts;
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts);

        if(savedInstanceState != null) {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mPodcasts = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PODCASTS));
//                source = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mPodcasts != null) {
                    Intent intent = new Intent(this, PodcastDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(mPodcasts));
//                    intent.putExtra(Constants.KEY_SOURCE, source);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mPodcasts != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(mPodcasts));
//            outState.putString(Constants.KEY_SOURCE, source);
        }
    }

    @Override
    public void onPodcastSelected(Integer position, ArrayList<Podcast> podcasts) {
        mPosition = position;
        mPodcasts = podcasts;
    }
}
