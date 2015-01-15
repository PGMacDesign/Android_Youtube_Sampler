package com.pgmacdesign.youtubebasics;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

//Plays the passed in video once inflated
public class OneVideo extends YouTubePlayerSupportFragment {

	private String currentVideoID = "video_id";
	private YouTubePlayer activePlayer;

	public static OneVideo newInstance(String url) {
		//Object of video fragment format
		OneVideo playerYouTubeFrag = new OneVideo();

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


	private void init() {
		initialize(DeveloperKey.API_KEY, new OnInitializedListener() {

			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
				activePlayer = player;
				activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
				if (!wasRestored) {
					activePlayer.loadVideo(getArguments().getString("url"), 0);

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