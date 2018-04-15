package com.inc.droidcast.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.droidcast.Constants;
import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.ui.PodcastDetailFragment;
import com.inc.droidcast.util.OnPodcastSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.PodcastViewHolder> {
	private static final int MAX_WIDTH = 200;
	private static final int MAX_HEIGHT = 200;

	private ArrayList<Podcast> mPodcasts = new ArrayList<>();
	private Context mContext;
	private OnPodcastSelectedListener mOnPodcastSelectedListener;


	public PodcastListAdapter(Context context, ArrayList<Podcast> podcasts, OnPodcastSelectedListener podcastSelectedListener) {
		mContext = context;
		mPodcasts = podcasts;
		mOnPodcastSelectedListener = podcastSelectedListener;
	}

	public PodcastListAdapter(Context applicationContext, ArrayList<Podcast> podcasts) {
	}

	@Override
	public PodcastListAdapter.PodcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_list_item, parent, false);
		PodcastViewHolder viewHolder = new PodcastViewHolder(view, mPodcasts, mOnPodcastSelectedListener);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(PodcastListAdapter.PodcastViewHolder holder, int position) {
		holder.bindPodcast(mPodcasts.get(position));
	}

	@Override
	public int getItemCount() {
		return mPodcasts.size();
	}

	public class PodcastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@BindView(R.id.podcastArtworkUrl100) ImageView podcastCoverArtwork100;
		@BindView(R.id.podcastCollectionName) TextView podcastTitle;
		@BindView(R.id.podcastPrimaryGenreName) TextView podcastGenre;
		@BindView(R.id.podcastArtistName) TextView podcastArtist;

		private Context mContext;
		private int mOrientation;
		private ArrayList<Podcast> mPodcast = new ArrayList<>();
		private OnPodcastSelectedListener mPodcastSelectedListener;


		public PodcastViewHolder(View itemView, ArrayList<Podcast> podcasts, OnPodcastSelectedListener podcastSelectedListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);

			mContext = itemView.getContext();
			mOrientation = itemView.getResources().getConfiguration().orientation;
			mPodcasts = podcasts;
			mPodcastSelectedListener = podcastSelectedListener;

//			if(mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//				createDetailFragment(0);
//			}

			itemView.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
			int itemPosition = getLayoutPosition();
			mPodcastSelectedListener.onPodcastSelected(itemPosition, mPodcast, Constants.SOURCE_FIND);

			Intent intent = new Intent(mContext, PodcastDetailFragment.class);
			intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
			intent.putExtra(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(mPodcast));
			intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
			mContext.startActivity(intent);


			//			if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//				createDetailFragment(itemPosition);
//			} else {
//				Intent intent = new Intent(mContext, PodcastDetailFragment.class);
//				intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
//				intent.putExtra(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(mPodcast));
//				intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
//				mContext.startActivity(intent);
//			}
		}

//		private void createDetailFragment(int position) {
//			PodcastDetailFragment detailFragment = PodcastDetailFragment.newInstance(mPodcast, position, Constants.SOURCE_FIND);
//			FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
//			ft.replace(R.id.podcastDetailContainer, detailFragment);
//			ft.commit();
//
//		}

		public void bindPodcast(Podcast podcast) {
//			Picasso.with(mContext)
//					.load(podcast.getArtworkUrl100())
//					.resize(MAX_WIDTH, MAX_HEIGHT)
//					.centerCrop()
//					.into(podcastCoverArtwork100);
			podcastTitle.setText(podcast.getCollectionName());
//			podcastGenre.setText(podcast.getPrimaryGenreName());
//			podcastArtist.setText(podcast.getArtistName());
		}
	}
}