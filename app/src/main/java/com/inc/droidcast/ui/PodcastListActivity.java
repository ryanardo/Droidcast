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
    private Integer position;
    ArrayList<Podcast> podcasts;
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts);

        if(savedInstanceState != null) {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                position = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                podcasts = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PODCASTS));
                source = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (position != null && podcasts != null) {
                    Intent intent = new Intent(this, PodcastDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, position);
                    intent.putExtra(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(podcasts));
                    intent.putExtra(Constants.KEY_SOURCE, source);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (position != null && podcasts != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, position);
            outState.putParcelable(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(podcasts));
            outState.putString(Constants.KEY_SOURCE, source);
        }
    }

    @Override
    public void onPodcastSelected(Integer position, ArrayList<Podcast> podcasts, String source) {
        this.position = position;
        this.podcasts = podcasts;
        this.source = source;
    }
}
