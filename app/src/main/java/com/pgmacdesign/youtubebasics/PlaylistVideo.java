package com.pgmacdesign.youtubebasics;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

//Plays a playlist of videos once inflated
public class PlaylistVideo extends YouTubePlayerSupportFragment {

	private String currentVideoID = "video_id";
	private YouTubePlayer activePlayer;

	public static PlaylistVideo newInstance(String url) {
		//Object of video fragment format
		PlaylistVideo playerYouTubeFrag = new PlaylistVideo();

		//Bundle to add details from the URL passed in
		Bundle bundle = new Bundle();
		bundle.putString("url", url);

		//Set the arguments into the bundle and then initiate it
		playerYouTubeFrag.setArguments(bundle);
		playerYouTubeFrag.init();

		return playerYouTubeFrag;
	}

	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) getActivity().findViewById(R.id.youtube_view);
	}

	//Initializes the youtube player
	private void init() {
		initialize(DeveloperKey.API_KEY, new YouTubePlayer.OnInitializedListener() {

			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
				activePlayer = player;
				activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
				if (!wasRestored) {
					//Plays a playlist of videos
					activePlayer.loadPlaylist(getArguments().getString("url"));

				}
			}

			@Override
			public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
			}
		});
	}

	public void onYouTubeVideoPaused() {
		activePlayer.pause();
	}
}