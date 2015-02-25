package com.pittsburghparks.pghparks;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Donate extends SherlockFragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.donate, container, false);
        Uri uri = Uri.parse("https://www.pittsburghparks.org/donate");
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    	startActivity(intent);
        return rootView;
    }

}