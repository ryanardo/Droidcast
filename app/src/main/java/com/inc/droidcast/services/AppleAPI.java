package com.inc.droidcast.services;

import com.inc.droidcast.Constants;
import com.inc.droidcast.models.Podcast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppleAPI {

	public static void findPodcast(String podcast, Callback callback) {

		OkHttpClient client = new OkHttpClient.Builder()
				.build();

		// building the api call/request
		HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ITUNES_BASE_URL).newBuilder();
		urlBuilder.addQueryParameter(Constants.ITUNES_PODCAST_QUERY_PARAMETER, podcast);
		String url = urlBuilder.build().toString();

		//create the api request
		Request request = new Request.Builder().url(url).build();

		// execute the api request
		Call call = client.newCall(request);
		call.enqueue(callback);
	}

	public ArrayList<Podcast> processResults(Response response) {
		ArrayList<Podcast> podcasts = new ArrayList<>();

		try {
			String jsonData = response.body().toString();
			JSONObject podcastJSON = new JSONObject(jsonData);
			JSONArray podcastsJSON = podcastJSON.getJSONArray("results");
			for (int i = 0; i < podcastsJSON.length(); i++) {
				JSONObject podcastJSON = podcastsJSON.getJSONObject(i);
				int artistId = podcastJSON.getInt("artistId");
				int collectionId = podcastJSON.getInt("collectionId");
				int trackId = podcastJSON.getString("trackId");
				String artistName = podcastJSON.getString("artistName");
				String collectionName = podcastJSON.getString("collectionName");
				String trackName = podcastJSON.getString("trackName");
				String feedUrl = podcastJSON.getString("feedUrl");
				String trackViewUrl = podcastJSON.getString("trackViewUrl");
				String releaseDate = podcastJSON.getString("releaseDate");
				String trackCount = podcastJSON.getString("trackCount");
				String primaryGenreName = podcastJSON.getString("primaryGenreName");
				String artworkUrl30 = podcastJSON.getString("artworkUrl30");
				String artworkUrl60 = podcastJSON.getString("artworkUrl60");
				String artworkUrl100 = podcastJSON.getString("artworkUrl100");
				String artworkUrl600 = podcastJSON.getString("artworkUrl600");

				ArrayList<String> genreIds = new ArrayList<>();
				JSONArray idGenreJSON = podcastJSON.getJSONArray("genreIds");
				for (int j = 0; j < idGenreJSON.length(); i++) {
					genreIds.add(idGenreJSON.get(j).toString());
				}

				ArrayList<String> genres = new ArrayList<>();
				JSONArray genresJSON = podcastJSON.getJSONArray("genres");
				for (int j = 0; j < genresJSON.length(); i++) {
					genres.add(genresJSON.get(j).toString());
				}
			}
		} catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
