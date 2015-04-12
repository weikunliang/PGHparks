package com.pittsburghparks.pghparks;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/* ActivityDetail class is called when a specific object from a park is clicked.
 * It displays information about the park and also a see on map button which
 * centers the map on that object. */
public class ActivityDetail extends SherlockFragmentActivity {

	Context context;
	ArrayList<JSONObject> activityObjectsArray;
	
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
		String txt = ""; // the information about the object
		double lat = 0; // object latitude
		double lon = 0; // object longitude
		String objectName = intent.getStringExtra("objectName");
		JSONObject currObject;
		
		/* Loops through the objects array and finds the latitude, longitude,
		 * and description of the object */
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
		
		/* Sets up the button so that it passes in the object's latitude and longitude*/
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
