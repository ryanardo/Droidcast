package com.inc.droidcast.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inc.droidcast.Constants;
import com.inc.droidcast.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistCreateActivity extends AppCompatActivity implements View.OnClickListener {
	private DatabaseReference firebaseRef_podcastPlaylist;
	private ValueEventListener firebaseListener_podcastPlaylist;

	@BindView(R.id.btn_addPlaylist) Button btnAddPlaylist;
	@BindView(R.id.txt_playlistName) EditText playlistName;
//	@BindView(R.id.txt_playlistDescription) EditText playlistDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		firebaseRef_podcastPlaylist = FirebaseDatabase
				.getInstance()
				.getReference()
				.child(Constants.FIREBASE_CHILD_PODCAST_PLAYLIST);

		firebaseListener_podcastPlaylist = firebaseRef_podcastPlaylist.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot snapshot_podcastPlaylist : dataSnapshot.getChildren()) {
					String name = snapshot_podcastPlaylist.getValue().toString();
					Log.d("Updated: Playlist name", "Name: " + name);
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlist_create);
		ButterKnife.bind(this);
		btnAddPlaylist.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddPlaylist) {
			String name = playlistName.getText().toString();
//			String description = playlistDescription.getText().toString();

			firebaseSave_podcastPlaylist(name);

			Intent intent = new Intent(PlaylistCreateActivity.this, PlaylistMenuActivity.class);
			intent.putExtra("name", name);
			startActivity(intent);
		}
	}

	public void firebaseSave_podcastPlaylist(String name) {
		firebaseRef_podcastPlaylist.push().setValue(name);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		firebaseRef_podcastPlaylist.removeEventListener(firebaseListener_podcastPlaylist);
	}

}