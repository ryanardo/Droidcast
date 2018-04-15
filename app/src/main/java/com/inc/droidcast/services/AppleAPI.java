package com.inc.droidcast.services;

import android.util.Log;

import com.inc.droidcast.Constants;
import com.inc.droidcast.models.Podcast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppleAPI {

	public static void findPodcast(String trackTitle, Callback callback) {

		OkHttpClient client = new OkHttpClient.Builder()
				.build();

		HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ITUNES_BASE_URL).newBuilder();
		urlBuilder.addQueryParameter(Constants.ITUNES_PODCAST_QUERY_PARAMETER, trackTitle);
		urlBuilder.addQueryParameter("api_key",Constants.API_TOKEN);
		urlBuilder.addQueryParameter("format", Constants.RESPONSE_FORMAT);
		String url = urlBuilder.build().toString();
		Log.d("URL Test :", url);

		Request request= new Request.Builder()
				.url(url)
				.build();

		Call call = client.newCall(request);
		call.enqueue(callback);
	}

	public ArrayList<Podcast> processResults(Response response) {
		ArrayList<Podcast> podcasts = new ArrayList<>();

		try {
			String jsonData = response.body().string();
			JSONObject lastJSON = new JSONObject(jsonData);
			JSONObject resultsJSON = lastJSON.getJSONObject("results");
			JSONObject trackmatchesJSON = resultsJSON.getJSONObject("trackmatches");
			JSONArray trackJSON = trackmatchesJSON.getJSONArray("track");
			for (int i = 0; i < trackJSON.length(); i++) {
				JSONObject singleTrackJSON = trackJSON.getJSONObject(i);
				String trackTitle = singleTrackJSON.getString("name");
				String trackArtist = singleTrackJSON.getString("artist");
				ArrayList<String>images = new ArrayList<>();
				JSONArray imageJSON = singleTrackJSON.getJSONArray("image");
				for(int x = 0; x < imageJSON.length(); x++) {
					images.add(imageJSON.getJSONObject(x).getString("#text"));
				}
				int imageCount = images.size();
				if(imageCount == 4 && "".equals(images.get(0))){
					String img = "https://i.imgur.com/nqCuNyv.png";
					Podcast podcast = new Podcast(trackTitle, trackArtist, img);
					podcasts.add(podcast);
				}else{
					String img = images.get(3);
					Podcast podcast = new Podcast(trackTitle, trackArtist, img);
					podcasts.add(podcast);
					Log.d("STUFF", podcasts.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return podcasts;
	}
}