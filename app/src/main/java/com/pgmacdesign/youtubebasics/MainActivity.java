package com.pgmacdesign.youtubebasics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//Main Activity, houses all of the fragments under this activity
public class MainActivity extends ActionBarActivity
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	//Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	private NavigationDrawerFragment mNavigationDrawerFragment;

	//Used to store the last screen title. For use in {@link #restoreActionBar()}.
	private CharSequence mTitle;

	//Called immediately after Splash Screen
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		//Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	//Handles the clicking of the respective nav drawers
	public void onNavigationDrawerItemSelected(int position) {
		//Update the main content by replacing fragments
		Fragment fragment = new Fragment();
		//For playing ONE video
		OneVideo OneVideo_video_fragment;
		//For playing a playlist of videos
		PlaylistVideo playlistVideo_video_fragment;
		//Manages the respective fragments
		FragmentManager fragmentManager = getSupportFragmentManager();

		switch (position){

			case 0:
				fragment = new InitialFragment();
				fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
				break;

			case 1:
				Toast.makeText(getApplicationContext(), "Starting Video", Toast.LENGTH_SHORT).show();
				OneVideo_video_fragment = OneVideo.newInstance(getString(R.string.hishe_video));
				getSupportFragmentManager().beginTransaction().replace(R.id.container, OneVideo_video_fragment).commit();
				break;

			case 2:
				Toast.makeText(getApplicationContext(), "Starting Playlist", Toast.LENGTH_SHORT).show();
				playlistVideo_video_fragment = PlaylistVideo.newInstance(getString(R.string.hishe_classic_films_playlist_url));
				getSupportFragmentManager().beginTransaction().replace(R.id.container, playlistVideo_video_fragment).commit();
				break;

		}
	}

	//Shows the 'titles' of the respective nav drawer screens
	public void onSectionAttached(int number) {
		switch (number) {
			case 1:
				mTitle = getString(R.string.title_section1);
				break;
			case 2:
				mTitle = getString(R.string.title_section2);
				break;
			case 3:
				mTitle = getString(R.string.title_section3);
				break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			/*
			Only show items in the action bar relevant to this screen
			if the drawer is not showing. Otherwise, let the drawer
			decide what to show in the action bar.
			*/
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	//Handles the action bar item clicks
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Toast.makeText(getApplicationContext(), "If there were a need for settings, this would actually do something! " +
					"Speaking of doing something, why not try clicking on my name and number in the Home screen..."
					, Toast.LENGTH_LONG).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
