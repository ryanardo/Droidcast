package com.inc.droidcast.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class PlaylistMenuAdapter extends ArrayAdapter {
	private Context context;
	private String[] playlistName;
	private String[] playlistsDescription;

	public PlaylistMenuAdapter(Context context, int resource, String[] playlistName, String[] playlistsDescription) {
		super(context, resource);
		this.context = context;
		this.playlistName = playlistName;
		this.playlistsDescription = playlistsDescription;
	}

	@Override
	public Object getItem(int position) {
		String name = playlistName[position];
		String description = playlistsDescription[position];
		return String.format("%s \n%s", name, description);
	}

	@Override
	public int getCount() {
		return playlistName.length;
	}
}
