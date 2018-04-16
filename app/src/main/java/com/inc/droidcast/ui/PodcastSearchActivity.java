package com.inc.droidcast.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.inc.droidcast.Constants;
import com.inc.droidcast.R;
import com.inc.droidcast.adapters.PodcastListAdapter;
import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.services.AppleAPI;
import com.inc.droidcast.util.OnPodcastSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PodcastSearchActivity extends AppCompatActivity {
	public static final String TAG = PodcastSearchActivity.class.getSimpleName();

	public ArrayList<Podcast> podcasts = new ArrayList<>();
	@BindView(R.id.recyclerView) RecyclerView mRecyclerView;
	private PodcastListAdapter mAdapter;
	private OnPodcastSelectedListener mOnPodcastSelectedListener;
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor mEditor;

	private String mRecentPodcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podcast_search);
		ButterKnife.bind(this);

		Intent intent = getIntent();
		String podcast = intent.getStringExtra("podcast");
		getPodcasts(podcast);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//		mRecentPodcast = mSharedPreferences.getString(Constants.PREFERENCES_PODCAST_KEY, null);
		if (mRecentPodcast != null) {
			getPodcasts(mRecentPodcast);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_search, menu);
		ButterKnife.bind(this);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		mEditor = mSharedPreferences.edit();

		MenuItem menuItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
//				addToSharedPreferences(query);
				getPodcasts(query);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	private void getPodcasts(String podcast) {
		final AppleAPI appleAPI = new AppleAPI();

		AppleAPI.findPodcast(podcast, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) {
				podcasts = appleAPI.processResults(response);

				PodcastSearchActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mAdapter = new PodcastListAdapter(getApplicationContext(), podcasts, mOnPodcastSelectedListener);
						mRecyclerView.setAdapter(mAdapter);
						RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PodcastSearchActivity.this);
						mRecyclerView.setLayoutManager(layoutManager);
						mRecyclerView.setHasFixedSize(true);
					}
				});

			}
		});
	}

//	private void addToSharedPreferences(String podcast) {
//		mEditor.putString(Constants.PREFERENCES_PODCAST_KEY, podcast).apply();
//	}

}