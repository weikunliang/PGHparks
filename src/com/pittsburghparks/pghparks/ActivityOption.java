package com.pittsburghparks.pghparks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ActivityOption extends SherlockFragmentActivity {
	Context context;
	ArrayList<String> objectsArray;
	String parkId, activityName, activityId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,ActivityOption.class));
		
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
		setContentView(R.layout.activity_option);
        
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);


		final Intent intent = getIntent();
		parkId = intent.getStringExtra("parkId");
		activityName = intent.getStringExtra("activityName");
		JSONObject currAct;
		for(int i = 0; i<Data.activitiesArray.length(); i++){
			try {
				currAct = Data.activitiesArray.getJSONObject(i);
				if(currAct.get("title").equals(activityName) && currAct.get("park_id").toString().equals(parkId)){
					activityId = Data.activitiesArray.getJSONObject(i).get("id").toString();
					break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		objectsArray = new ArrayList<String>();
		JSONObject currObject;
		for(int x = 0; x<Data.objectsArray.length(); x++){
			try {
					currObject = Data.objectsArray.getJSONObject(x);
					if(currObject.get("park_id").toString().equals(parkId) && currObject.get("activityid").toString().equals(activityId)){
						objectsArray.add(currObject.get("name").toString());
					}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		ListView objectsList = (ListView) findViewById(R.id.activities_objects_list);
		
		ArrayAdapter<String> parksAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, objectsArray);
		objectsList.setAdapter(parksAdapter);
		
		objectsList.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
				{
					TextView textView = (TextView) arg1.findViewById(R.id.sub_item_text);
					Intent myIntent = new Intent(context, ActivityDetail.class);
					myIntent.putExtra("activityName", textView.getText().toString());
					myIntent.putExtra("parkId", parkId);
					myIntent.putExtra("objectName", textView.getText().toString());
					context.startActivity(myIntent);
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
