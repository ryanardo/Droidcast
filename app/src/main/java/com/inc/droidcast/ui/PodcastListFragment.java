package com.inc.droidcast.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

public class PodcastListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private PodcastListAdapter mAdapter;
    public ArrayList<Podcast> mPodcasts = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private OnPodcastSelectedListener mOnPodcastSelectedListener;

    public PodcastListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnPodcastSelectedListener = (OnPodcastSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_podcast_list, container, false);
        ButterKnife.bind(this, view);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_PODCAST_KEY, null);

        if (mRecentAddress != null) {
            getRestaurants(mRecentAddress);
        }

        return view;
    }

    public void getRestaurants(String podcast) {
        final AppleAPI appleAPI = new AppleAPI();

        appleAPI.findPodcast(podcast, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mPodcasts = appleAPI.processResults(response);

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new PodcastListAdapter(getActivity(), mPodcasts, mOnPodcastSelectedListener);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
                getRestaurants(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//    private void addToSharedPreferences(String podcast) {
//        mEditor.putString(Constants.PREFERENCES_PODCAST_KEY, podcast).apply();
//    }

}