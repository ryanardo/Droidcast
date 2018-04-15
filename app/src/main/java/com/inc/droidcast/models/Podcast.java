package com.inc.droidcast.models;

import java.util.ArrayList;

import org.parceler.Parcel;
import java.util.List;

@Parcel
public class Podcast {

	String trackTitle;
	String trackArtist;
	String trackCoverArtwork;
	ArrayList<String>images = new ArrayList<>();
	String index;
	String pushId;

	public Podcast() {
	}

	public Podcast(String trackTitle, String trackArtist, String trackCoverArtwork) {
		this.trackTitle = trackTitle;
		this.trackArtist = trackArtist;
		this.trackCoverArtwork = trackCoverArtwork;
	}

	// GETTERS & SETTERS
	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public String getTrackArtist() {
		return trackArtist;
	}

	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}

	public String getTrackCoverArtwork() {
		return trackCoverArtwork;
	}

	public void setTrackCoverArtwork(String trackCoverArtwork) {
		this.trackCoverArtwork = trackCoverArtwork;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
}