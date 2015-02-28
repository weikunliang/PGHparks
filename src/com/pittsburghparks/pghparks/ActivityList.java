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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ActivityList extends SherlockActivity {
	Context context;
	Activity activity;
	ViewFlipper flipper;
	String parkName, parkId;
	String[] imageUrls;
	String[] imageThumbUrls;
	String[] imageTitles;
	String[] imageNotes;
	ArrayList<String> imageThumbUrlsList = new ArrayList<String>();
	ArrayList<String> imageUrlsList = new ArrayList<String>();
	ArrayList<String> imageTitlesList = new ArrayList<String>();
	ArrayList<String> imageNotesList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		context = this;
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(context,ActivityDetail.class));

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
		
//		View rootView = inflater.inflate(R.layout.activity_list, container, false);
//        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);
//        TextView title = (TextView) actionBarView.findViewById(R.id.title);
//        title.setText("Activities");
        
        
        Intent intent = getIntent();
		parkName = intent.getStringExtra("parkName");
		parkId = intent.getStringExtra("parkId");

		ArrayList<String> singleParkArray = new ArrayList<String>();
		singleParkArray.add("Things To Do");
		

		ArrayList<String> activitiesSubArray = new ArrayList<String>();
		JSONObject currActivity;
		for(int i = 0; i<Data.activitiesArray.length(); i++)
		{
			try 
			{
				currActivity = Data.activitiesArray.getJSONObject(i);
				if(currActivity.get("park_id").toString().equals(parkId))
				{
					activitiesSubArray.add(currActivity.get("title").toString());
				}
			} 
			catch (JSONException e) 
			{
				//handle JSON problem
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
					Intent myIntent = new Intent(context, ActivityDetail.class);
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
