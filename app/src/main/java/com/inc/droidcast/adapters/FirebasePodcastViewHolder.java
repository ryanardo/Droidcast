package com.inc.droidcast.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.droidcast.R;
import com.inc.droidcast.models.Podcast;
import com.inc.droidcast.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

public class FirebasePodcastViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static  final int MAX_WIDTH = 200;
    private static  final int MAX_HEIGHT = 200;
    public ImageView mPodcastImageView;

    View mView;
    Context mContext;

    public FirebasePodcastViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindPodcast(Podcast podcast) {
        mPodcastImageView = (ImageView) mView.findViewById(R.id.podcastArtworkUrl100);
        TextView podcastTitle = (TextView) mView.findViewById(R.id.podcastCollectionName);
        TextView podcastArtist = (TextView) mView.findViewById(R.id.podcastArtistName);
        TextView podcastprimaryGenre = (TextView) mView.findViewById(R.id.podcastPrimaryGenreName);

        Picasso.with(mContext)
                .load(podcast.getArtworkUrl100())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mPodcastImageView);

        podcastTitle.setText(podcast.getCollectionName());
//        podcastArtist.setText(podcast.getArtistName().get(0));
        podcastArtist.setText(podcast.getArtistName());
        podcastprimaryGenre.setText("Genre: " + podcast.getPrimaryGenreName());
    }

    //    @Override
//    public void onItemSelected() {
//        itemView.animate()
//                .alpha(0.7f)
//                .scaleX(0.9f)
//                .scaleY(0.9f)
//                .setDuration(500);
//    }

    @Override
    public void onItemSelected(){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_sacle_on);
        set.setTarget(itemView);
        set.start();
    }

    //    @Override
//    public void onItemClear() {
//        itemView.animate()
//                .alpha(1f)
//                .scaleX(1f)
//                .scaleY(1f);
//    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_sacle_off);
        set.setTarget(itemView);
        set.start();
    }
}
