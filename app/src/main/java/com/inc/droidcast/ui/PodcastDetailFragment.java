package com.inc.droidcast.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inc.droidcast.Constants;
import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastDetailFragment extends android.support.v4.app.Fragment implements View.OnClickListener{
	@BindView(R.id.podcastArtworkUrl100) ImageView podcastArtwork100;
	@BindView(R.id.podcastCollectionName) TextView podcastTitle;
	@BindView(R.id.podcastArtistName) TextView podcastArtist;
	@BindView(R.id.podcastPrimaryGenreName) TextView podcastPrimaryGenre;
//	@BindView(R.id.podcast) TextView mWebsiteLabel;
//	@BindView(R.id.podcast) TextView mPhoneLabel;
//	@BindView(R.id.podcast) TextView mAddressLabel;
	@BindView(R.id.savePodcastButton) TextView mSavePodcastButton;

	private Podcast mPodcast;
	private ArrayList<Podcast> mPodcasts;
	private int mPosition;
	private static final int MAX_WIDTH = 400;
	private static final int MAX_HEIGHT = 300;
	private String mSource;

	public static PodcastDetailFragment newInstance(ArrayList<Podcast> podcasts, Integer position, String source) {
		PodcastDetailFragment podcastDetailFragment = new PodcastDetailFragment();
		Bundle args = new Bundle();

		args.putParcelable(Constants.EXTRA_KEY_PODCASTS, Parcels.wrap(podcasts));
		args.putInt(Constants.EXTRA_KEY_POSITION, position);
		args.putString(Constants.KEY_SOURCE, source);

		podcastDetailFragment.setArguments(args);
		return podcastDetailFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPodcasts = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_PODCASTS));
		mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
		mPodcast = mPodcasts.get(mPosition);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_podcast_detail, container, false);
		ButterKnife.bind(this, view);

		Picasso.with(view.getContext())
				.load(mPodcast.getTrackCoverArtwork())
				.resize(MAX_WIDTH, MAX_HEIGHT)
				.centerCrop()
				.into(podcastArtwork100);

		podcastTitle.setText(mPodcast.getTrackTitle());
		podcastArtist.setText(mPodcast.getTrackArtist());
		return view;
	}

	@Override
	public void onClick(View v) {
		if (v == mSavePodcastButton) {
			FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
			String uid = user.getUid();
			DatabaseReference podcastRef = FirebaseDatabase
					.getInstance()
					.getReference(Constants.FIREBASE_CHILD_PODCASTS)
					.child(uid);

			DatabaseReference pushRef = podcastRef.push();
			String pushId = pushRef.getKey();
			mPodcast.setPushId(pushId);
			pushRef.setValue(mPodcast);


			Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
		}
	}
}
