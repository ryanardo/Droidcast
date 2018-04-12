package com.inc.droidcast.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.inc.droidcast.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@BindView(R.id.btn_podcasts) Button btnPodcasts;
	@BindView(R.id.btn_podcastSearch) Button btnPodcastSearch;

	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		btnPodcastSearch.setOnClickListener(this);
		btnPodcasts.setOnClickListener(this);

		mAuth = FirebaseAuth.getInstance();

		mAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				if (user != null) {
					getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
				} else {

				}
			}
		};

	}

	@Override
	public void onStart() {
		super.onStart();
		mAuth.addAuthStateListener(mAuthListener);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mAuthListener != null) {
			mAuth.removeAuthStateListener(mAuthListener);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.action_logout) {
			logout();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void logout() {
		FirebaseAuth.getInstance().signOut();
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		if(v == btnPodcastSearch) {
			Intent intent = new Intent(MainActivity.this, PodcastSearchActivity.class);
			startActivity(intent);
		}

		if(v == btnPlaylist) {
			Intent intent = new Intent(MainActivity.this, PlaylistMenuActivity.class);
			startActivity(intent);
		}

	}
}
