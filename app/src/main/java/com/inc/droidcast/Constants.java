package com.inc.droidcast;

public class Constants {
	public static final String API_TOKEN = BuildConfig.API_TOKEN;

	public static final String ITUNES_BASE_URL = "https://ws.audioscrobbler.com/2.0/?method=track.search";
	public static final String ITUNES_PODCAST_QUERY_PARAMETER = "track";
	public static final String RESPONSE_FORMAT = "json";

	public static final String FIREBASE_CHILD_PODCAST_SEARCHED = "podcastSearched";
	public static final String FIREBASE_CHILD_PODCAST_PLAYLIST = "podcastPlaylist";
	public static final String FIREBASE_CHILD_PODCASTS = "podcasts";
	public static final String FIREBASE_QUERY_INDEX = "index";

	public static final String EXTRA_KEY_POSITION = "position";
	public static final String EXTRA_KEY_PODCASTS = "podcasts";

	public static final String KEY_SOURCE = "source";
	public static final String SOURCE_SAVED = "saved";
	public static final String SOURCE_FIND = "find";
}