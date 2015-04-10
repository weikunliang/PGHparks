package com.pittsburghparks.pghparks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class Tab extends SherlockFragmentActivity {
	ActionBar.Tab Tab1,Tab2,Tab3,Tab4,Tab5;
	Fragment fragmentTab1 = new Map();
	Fragment fragmentTab2 = new Parks();
	Fragment fragmentTab3 = new Events();
	Fragment fragmentTab4 = new Donate();
	Fragment fragmentTab5 = new Contact();
	
	@SuppressLint("NewApi") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab);
 		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		
 
		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Map");
		Tab2 = actionBar.newTab().setText("Places");
		Tab3 = actionBar.newTab().setText("Events");
		Tab4 = actionBar.newTab().setText("Donate");
		Tab5 = actionBar.newTab().setText("Contact");
		 
		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));
		Tab4.setTabListener(new TabListener(fragmentTab4));
		Tab5.setTabListener(new TabListener(fragmentTab5));
	

		final Intent intent = getIntent();
		Bundle bundle = new Bundle();
		bundle.putString("lat", intent.getStringExtra("lat"));
		bundle.putString("lon", intent.getStringExtra("lon"));
		// set Fragmentclass Arguments
		fragmentTab1.setArguments(bundle);
		
		// Add tabs to actionbar
		if (intent.getStringExtra("lat").equals(Double.toString(0))) {
			
			actionBar.addTab(Tab1, 0, false);
			actionBar.addTab(Tab2, 1, true);
			actionBar.addTab(Tab3, 2, false);
			actionBar.addTab(Tab4, 3, false);
			actionBar.addTab(Tab5, 4, false);
		} else {
			actionBar.addTab(Tab1, 0, true);
			actionBar.addTab(Tab2, 1, false);
			actionBar.addTab(Tab3, 2, false);
			actionBar.addTab(Tab4, 3, false);
			actionBar.addTab(Tab5, 4, false);
			
		}
			
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
