package com.pittsburghparks.pghparks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class Tab extends SherlockFragmentActivity {
	ActionBar.Tab Tab1,Tab2,Tab3,Tab4,Tab5;
	Fragment fragmentTab1 = new Map();

	Fragment fragmentTab2 = new Parks();
	Fragment fragmentTab3 = new Events();
	Fragment fragmentTab4 = new Donate();
	Fragment fragmentTab5 = new Contact();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
 
		ActionBar actionBar = getSupportActionBar();
 
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);
 
		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);
 
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
 
		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		actionBar.addTab(Tab4);
		actionBar.addTab(Tab5);
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
