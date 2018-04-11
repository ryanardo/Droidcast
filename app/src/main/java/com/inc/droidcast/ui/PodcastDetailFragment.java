package com.inc.droidcast.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;

import org.parceler.Parcels;

import butterknife.BindView;

public class PodcastDetailFragment extends Fragment implements View.OnClickListener {
	@BindView(R.id.podcastArtworkUrl100) ImageView podcastArtworkCover;
	@BindView(R.id.podcastCollectionName) TextView podcastTitle;
	@BindView(R.id.podcastArtistName) TextView podcastArtist;
	@BindView(R.id.podcastPrimaryGenreName) TextView podcastGenre;

	private Podcast podcast;

	public static PodcastDetailFragment newInstance(Podcast podcast) {
		PodcastDetailFragment podcastDetailFragment = new PodcastDetailFragment();
		Bundle args = new Bundle();
		args.putParcelable("podcast", Parcels.wrap(podcast));
		podcastDetailFragment.setArguments(args);
		return podcastDetailFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		podcast = Parcels.unwrap(getArguments().getParcelable("podcast"));
	}

	@Override
	public void onClick(View v) {

	}
} // End of the 'PodcastDetailFragment'