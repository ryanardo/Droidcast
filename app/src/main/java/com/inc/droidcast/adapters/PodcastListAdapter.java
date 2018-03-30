package com.inc.droidcast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.PodcastViewHolder> {
	private ArrayList<Podcast> mPodcasts = new ArrayList<>();
	private Context mContext;

	public PodcastListAdapter(Context context, ArrayList<Podcast> podcasts) {
		mContext = context;
		mPodcasts = podcasts;
	}

	@Override
	public PodcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_list_item, parent, false);
		PodcastViewHolder viewHolder = new PodcastViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(PodcastViewHolder holder, int position) {
		holder.bindPodcast(mPodcasts.get(position));
	}

	@Override
	public int getItemCount() {
		return mPodcasts.size();
	}

	public class PodcastViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.podcastArtworkUrl100) ImageView mPodcastArtworkUrl100;
		@BindView(R.id.podcastCollectionName) TextView mPodcastCollectionName;
		@BindView(R.id.podcastArtistName) TextView mPodcastArtistName;
		@BindView(R.id.podcastPrimaryGenreName) TextView mPodcastPrimaryGenreName;

		private Context mContext;

		public PodcastViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			mContext = itemView.getContext();
		}

		public void bindPodcast(Podcast podcast) {
			mPodcastCollectionName.setText(podcast.getCollectionName());
			mPodcastArtistName.setText("Artist: " + podcast.getArtistName());
			mPodcastPrimaryGenreName.setText(podcast.getPrimaryGenreName());
		}
	}
}
