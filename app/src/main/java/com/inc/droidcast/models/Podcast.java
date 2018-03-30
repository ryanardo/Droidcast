package com.inc.droidcast.models;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Podcast {
	int artistId;
	int collectionId;
	int trackId;
	String artistName;
	String collectionName;
	String trackName;
	String feedUrl;
	String trackViewUrl;
	String releaseDate;
	String trackCount;
	String primaryGenreName;
	String artworkUrl30;
	String artworkUrl60;
	String artworkUrl100;
	String artworkUrl600;
	List<String> genreIds = new ArrayList<>();
	List<String> genres = new ArrayList<>();

	public Podcast() {
	}

	public Podcast(int artistId, int collectionId, int trackId, String artistName, String collectionName, String trackName, String feedUrl, String trackViewUrl, String releaseDate, String trackCount, String primaryGenreName, String artworkUrl30, String artworkUrl60, String artworkUrl100, String artworkUrl600, ArrayList<String> genreIds, ArrayList<String> genres) {
		this.artistId = artistId;
		this.collectionId = collectionId;
		this.trackId = trackId;
		this.artistName = artistName;
		this.collectionName = collectionName;
		this.trackName = trackName;
		this.feedUrl = feedUrl;
		this.trackViewUrl = trackViewUrl;
		this.releaseDate = releaseDate;
		this.trackCount = trackCount;
		this.primaryGenreName = primaryGenreName;
		this.artworkUrl30 = artworkUrl30;
		this.artworkUrl60 = artworkUrl60;
		this.artworkUrl100 = artworkUrl100;
		this.artworkUrl600 = artworkUrl600;
		this.genreIds = genreIds;
		this.genres = genres;
	}

	// GETTERS & SETTERS

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String getTrackViewUrl() {
		return trackViewUrl;
	}

	public void setTrackViewUrl(String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(String trackCount) {
		this.trackCount = trackCount;
	}

	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	public void setPrimaryGenreName(String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}

	public String getArtworkUrl30() {
		return artworkUrl30;
	}

	public void setArtworkUrl30(String artworkUrl30) {
		this.artworkUrl30 = artworkUrl30;
	}

	public String getArtworkUrl60() {
		return artworkUrl60;
	}

	public void setArtworkUrl60(String artworkUrl60) {
		this.artworkUrl60 = artworkUrl60;
	}

	public String getArtworkUrl100() {
		return artworkUrl100;
	}

	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}

	public String getArtworkUrl600() {
		return artworkUrl600;
	}

	public void setArtworkUrl600(String artworkUrl600) {
		this.artworkUrl600 = artworkUrl600;
	}

	public List<String> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<String> genreIds) {
		this.genreIds = genreIds;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
}