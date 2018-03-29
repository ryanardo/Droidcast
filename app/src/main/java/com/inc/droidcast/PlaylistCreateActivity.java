package com.inc.droidcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistCreateActivity extends AppCompatActivity implements View.OnClickListener {

	@BindView(R.id.btn_addPlaylist)
	Button btnAddPlaylist;
	@BindView(R.id.txt_playlistName)
	EditText playlistName;
	@BindView(R.id.txt_playlistDescription) EditText playlistDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_playlist_create);
		ButterKnife.bind(this);
		btnAddPlaylist.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddPlaylist) {

			String name = playlistName.getText().toString();
			String description = playlistDescription.getText().toString();

			Intent intent = new Intent(PlaylistCreateActivity.this, PlaylistMenu.class);

			startActivity(intent);

		}
	}
}
