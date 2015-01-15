package com.pgmacdesign.youtubebasics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

//Splash screen. To be played at the beginning of the app
public class Splash extends Activity {

	//For the intro music / song playing in the background
	MediaPlayer ourIntroSong;

	protected void onCreate(Bundle inputVariableToSendToSuperClass) {

		super.onCreate(inputVariableToSendToSuperClass);
		setContentView(R.layout.splash);

		//Opening Splash screen sound byte.
		ourIntroSong = MediaPlayer.create(Splash.this, R.raw.cinematic_impact);
		ourIntroSong.start();

		//Check for network connection. This is, after all, a web-based app and needs internet to run videos via youtube
		boolean do_we_have_network_connection = false;
		do_we_have_network_connection = haveNetworkConnection();

		if (do_we_have_network_connection){
			//Working network connection!
			Thread timer = new Thread() {
				public void run() {
					try {
						sleep(3100);
						Intent intent = new Intent(Splash.this, MainActivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			};
			timer.start();

		} else {
			//No network connection!
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
						//If they hit Close, the app will close until they have network connection
						case DialogInterface.BUTTON_NEGATIVE:
							try {
								dialog.dismiss();
								finish();
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
					}
				}
			};
			String confirm = "You currently do not have a network connection. Please connect to the internet before Running this application as it needs internet to play videos";
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(confirm).setNegativeButton("Close", dialogClickListener).show();
		}
	}


	@Override
	protected void onPause() {
		super.onPause();
		//This kills the music so it isn't carried over between splash screens
		ourIntroSong.release();

	}

	//Check for internet connection, either wifi or mobile (3g/ 4g)
	private boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}
}
