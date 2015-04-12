package com.pittsburghparks.pghparks;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/* The Contact page shows the contact information for PPC along with
 * links to its Facebook, Twitter, and Instagram page */
public class Contact extends SherlockFragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact, container, false);
        
        Button facebook = (Button) rootView.findViewById(R.id.facebook);
		facebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) 
            {
            	Uri uri = Uri.parse("https://www.facebook.com/pittsburghparks");
            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            	startActivity(intent);
            }
        });
		
		Button twitter = (Button) rootView.findViewById(R.id.twitter);
		twitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) 
            {
            	Uri uri = Uri.parse("https://twitter.com/pittsburghparks");
            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            	startActivity(intent);
            }
        });
		
		Button instagram = (Button) rootView.findViewById(R.id.instagram);
		instagram.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) 
            {
            	Uri uri = Uri.parse("https://instagram.com/pittsburghparks/");
            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            	startActivity(intent);
            }
        });
		
        return rootView;
    }

}