package com.pittsburghparks.pghparks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityList extends SherlockActivity {
	Context context;
	Activity activity;
	String parkName, parkId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		context = this;
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(context,ActivityOption.class));

		try {
			if(Data.photosArray == null) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);   
        
        Intent intent = getIntent();
		parkName = intent.getStringExtra("parkName");
		parkId = intent.getStringExtra("parkId");
			
		ArrayList<String> activitiesSubArrayAll = new ArrayList<String>();
		JSONObject currActivity;
		for(int i = 0; i<Data.activitiesArray.length(); i++)
		{
			try 
			{
				currActivity = Data.activitiesArray.getJSONObject(i);
				if(currActivity.get("park_id").toString().equals(parkId))
				{
					activitiesSubArrayAll.add(currActivity.get("title").toString());
				}
			} 
			catch (JSONException e) 
			{
				//handle JSON problem
				e.printStackTrace();
			}
		}
		
		ArrayList<String> activitiesSubArray = new ArrayList<String>();
		JSONObject currObject;
		JSONObject currAct;
		for(int x = 0; x<activitiesSubArrayAll.size(); x++){
			try {
				JSONObject activity = null;
				for(int y = 0; y<Data.activitiesArray.length(); y++){
					currAct = Data.activitiesArray.getJSONObject(y);
					if(currAct.get("park_id").toString().equals(parkId) && currAct.get("title").toString().equals(activitiesSubArrayAll.get(x))){
						activity = currAct;
						break;
					}
				}
				
				for(int z = 0; z<Data.objectsArray.length(); z++){
					currObject = Data.objectsArray.getJSONObject(z);
					if(currObject.get("activityid").toString().equals(activity.get("id").toString())){
						activitiesSubArray.add(activitiesSubArrayAll.get(x));
						break;
					}
				}
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ListView parksList = (ListView) findViewById(R.id.park_activities_list);
		
		ArrayAdapter<String> parksAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, activitiesSubArray);
		parksList.setAdapter(parksAdapter);
		
		parksList.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
				{
					TextView textView = (TextView) arg1.findViewById(R.id.sub_item_text);
					Intent myIntent = new Intent(context, ActivityOption.class);
					myIntent.putExtra("activityName", textView.getText().toString());
					myIntent.putExtra("parkId", parkId);
					context.startActivity(myIntent);
				}
			});
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	public void onStart()
	{

		super.onStart();
		
		try {
			if(Data.photosArray == null) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
		
	}

}
