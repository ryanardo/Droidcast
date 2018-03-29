package com.inc.droidcast.services;

import com.inc.droidcast.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

}
