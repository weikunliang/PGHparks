package com.pittsburghparks.pghparks;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/* The About class displays the information about the park. It is called 
 * when the see more button for a specific park is pressed. */

public class About extends SherlockFragmentActivity {
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,Main.class));
		
		final Intent intent = getIntent();
		context = this;
		try {
			if(Data.activitiesArray.length() == 1) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
		
		super.onCreate(savedInstanceState);
		
		/* Sets up the action bar with the icon */
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		
		setContentView(R.layout.about);
		
		TextView park = (TextView) findViewById(R.id.park);
		TextView info = (TextView) findViewById(R.id.info);
		
		park.setText(intent.getStringExtra("parkName"));
		info.setText(intent.getStringExtra("info"));
		
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