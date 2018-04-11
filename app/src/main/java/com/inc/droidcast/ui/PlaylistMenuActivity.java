package com.inc.droidcast.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.inc.droidcast.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistMenuActivity extends AppCompatActivity implements View.OnClickListener {

	@BindView(R.id.btn_createPlaylist)
	Button btnCreatePlaylist;
	@BindView(R.id.lst_playlists)
	ListView lstPlaylists;

//	private String[] playlistNames;
//	private String[] playlistDescriptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlist_menu);

		ButterKnife.bind(this);
		btnCreatePlaylist.setOnClickListener(this);

//		PlaylistMenuAdapter adapterPlaylist = new PlaylistMenuAdapter(this, android.R.layout.simple_list_item_1, playlistNames, playlistDescriptions);
//		lstPlaylists.setAdapter(adapterPlaylist);

	}

	@Override
	public void onClick(View v) {
		if (v == btnCreatePlaylist) {
			Intent intent = new Intent(PlaylistMenuActivity.this, PlaylistCreateActivity.class);
			startActivity(intent);
		}
	}
}