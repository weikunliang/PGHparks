package com.pittsburghparks.pghparks;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ActivityDetail extends SherlockFragmentActivity {

	Context context;
	ViewFlipper flipper;
	ArrayList<JSONObject> activityObjectsArray;
	ImageLoader imageLoader = ImageLoader.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,Main.class));
		
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
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		
		setContentView(R.layout.activity_detail);
        
		final Intent intent = getIntent();
		String txt = "";
		double lat = 0;
		double lon = 0;
		String objectName = intent.getStringExtra("objectName");
		JSONObject currObject;
		for(int i = 0; i<Data.objectsArray.length(); i++){
			try {
				currObject = Data.objectsArray.getJSONObject(i);
				if(currObject.get("name").toString().equals(objectName)){
					txt = currObject.get("notes").toString();
					lat = (Double) currObject.get("lat");
					lon = (Double) currObject.get("lon");
					break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		final double latitude = lat;
		final double longitude = lon;
		
		TextView content = (TextView) findViewById(R.id.description);
		TextView object = (TextView) findViewById(R.id.title);
		
		object.setText(objectName);
		content.setText(txt);
		
		Button open = (Button) findViewById(R.id.open);
		
		open.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(getApplicationContext(),Tab.class);
				myIntent.putExtra("lat", Double.toString(latitude));
				myIntent.putExtra("lon", Double.toString(longitude));
				startActivity(myIntent);
			}			
		});
		
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
	
	public boolean onOptionsItemSelected(MenuItem menuitem)
	{
		if(menuitem.getTitle().toString().equals("MyPGH Parks"))
		{
			finish();	
		}
		else
		{}
		return false;
	}
	
	public void onStart()
	{
		super.onStart();
	}

}
