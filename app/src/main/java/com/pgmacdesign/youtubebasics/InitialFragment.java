package com.pgmacdesign.youtubebasics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

//Initial fragment is the first fragment used. It is designed as a 'lead in'
public class InitialFragment extends Fragment implements View.OnClickListener {
	//The fragment argument representing the section number for this fragment.
	private static final String ARG_SECTION_NUMBER = "section_number";

	//Returns a new instance of this fragment for the given section number
	public static InitialFragment newInstance(int sectionNumber) {
		InitialFragment fragment = new InitialFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	//Constructor
	public InitialFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_1, container, false);

		//Text view initialization
		TextView tv = (TextView) rootView.findViewById(R.id.textView4);
		TextView tv2 = (TextView) rootView.findViewById(R.id.textView3);

		//Simple animation for the textviews to fade in.
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
		AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
		tv.startAnimation(fadeIn);
		tv2.startAnimation(fadeIn);
		fadeIn.setDuration(2200);
		fadeIn.setFillAfter(true);

		tv.setOnClickListener(this);
		tv2.setOnClickListener(this);
		return rootView;
	}

	//Handles the onClick for the two text fields at the bottom
	public void onClick(View v) {

		switch (v.getId()){

			case R.id.textView4:
				//Dialer Intent
				Intent dialerIntent = new Intent(Intent.ACTION_DIAL);
				dialerIntent.setData(Uri.parse("tel:5625555555")); //Place number here for call out
				startActivity(dialerIntent);
				break;

			case R.id.textView3:
				//Email Intent
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				//Email to send out
				String email_to_send = "pgmacdesign@gmail.com";
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email_to_send});
				//Subject
				String subject = "You're Hired!";
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
				//Start the activity
				startActivity(emailIntent);
				break;
		}
	}
}
