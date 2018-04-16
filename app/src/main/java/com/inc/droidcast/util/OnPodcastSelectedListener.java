package com.inc.droidcast.util;

import com.inc.droidcast.models.Podcast;

import java.util.ArrayList;

public interface OnPodcastSelectedListener {
    public void onPodcastSelected(Integer position, ArrayList<Podcast> podcasts);
}
