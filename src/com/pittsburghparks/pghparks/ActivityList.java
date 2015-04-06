package com.pittsburghparks.pghparks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityList extends SherlockActivity {
	Context context;
	Activity activity;
	String parkName, parkId;
	String activityId, activityName;
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
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
		
		Intent intent = getIntent();
		parkName = intent.getStringExtra("parkName");
		parkId = intent.getStringExtra("parkId");
		
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);	
		setContentView(R.layout.activity_list); 
		
		// get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
     // Listview Group click listener
 		expListView.setOnGroupClickListener(new OnGroupClickListener() {

 			@Override
 			public boolean onGroupClick(ExpandableListView parent, View v,
 					int groupPosition, long id) {
 				// Toast.makeText(getApplicationContext(),
 				// "Group Clicked " + listDataHeader.get(groupPosition),
 				// Toast.LENGTH_SHORT).show();
 				return false;
 			}
 		});

 		// Listview Group expanded listener
 		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

 			@Override
 			public void onGroupExpand(int groupPosition) {
 				Toast.makeText(getApplicationContext(),
 						listDataHeader.get(groupPosition) + " Expanded",
 						Toast.LENGTH_SHORT).show();
 			}
 		});

 		// Listview Group collasped listener
 		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

 			@Override
 			public void onGroupCollapse(int groupPosition) {
 				Toast.makeText(getApplicationContext(),
 						listDataHeader.get(groupPosition) + " Collapsed",
 						Toast.LENGTH_SHORT).show();

 			}
 		});

 		// Listview on child click listener
 		expListView.setOnChildClickListener(new OnChildClickListener() {

 			@Override
 			public boolean onChildClick(ExpandableListView parent, View v,
 					int groupPosition, int childPosition, long id) {
 				// TODO Auto-generated method stub
 				
 				Intent myIntent = new Intent(context, ActivityDetail.class);
				myIntent.putExtra("activityName", listDataHeader.get(groupPosition));
				myIntent.putExtra("parkId", parkId);
				myIntent.putExtra("objectName", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
				context.startActivity(myIntent);
 				return false;
 			}
 		});
		
		// ABOUT TEXT
		
//		String text = "";
//		JSONObject currPark;
//		for(int y = 0; y < Data.parksArray.length(); y ++){
//			try {
//				currPark = Data.parksArray.getJSONObject(y);
//				if(currPark.get("id").toString().equals(parkId)){
//					text = currPark.get("notes").toString();
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
//		TextView parkTitle = (TextView) findViewById(R.id.park_name);
//		parkTitle.setText(parkName);
//		TextView about = (TextView) findViewById(R.id.about);
//		about.setText(text);
		//ListView parksList = (ListView) findViewById(R.id.park_activities_list);	

			
		}
		
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> child;
        
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
						listDataHeader.add(activitiesSubArrayAll.get(x));
						break;
					}
				}
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0; i<activitiesSubArray.size(); i++){
			//TextView title = (TextView) findViewById(R.id.title);
			//ListView objectsList = (ListView) findViewById(R.id.park_activities_list);	
		
			activityName = activitiesSubArray.get(i);
			//title.setText(activitiesSubArray.get(i));
			
			child = new ArrayList<String>();
			
			// Figures out the activity id given the activity name and park id
			JSONObject currAct2;
			for(int j = 0; j<Data.activitiesArray.length(); j++){
				try {
					currAct2 = Data.activitiesArray.getJSONObject(j);
					if(currAct2.get("title").equals(activityName) && currAct2.get("park_id").toString().equals(parkId)){
						activityId = Data.activitiesArray.getJSONObject(j).get("id").toString();
						break;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Adds the objects into a new array based on the given park id and activity id
			ArrayList<String> objectsArray = new ArrayList<String>();
			JSONObject currObj;
			for(int x = 0; x<Data.objectsArray.length(); x++){
				try {
						currObj = Data.objectsArray.getJSONObject(x);
						if(currObj.get("park_id").toString().equals(parkId) && currObj.get("activityid").toString().equals(activityId)){
							objectsArray.add(currObj.get("name").toString());
							child.add(currObj.get("name").toString());
						}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			listDataChild.put(listDataHeader.get(i), child); // Header, Child data
			
		}

	
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
