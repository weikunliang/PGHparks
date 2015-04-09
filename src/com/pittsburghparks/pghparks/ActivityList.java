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
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ActivityList extends SherlockActivity {
	Context context;
	Activity activity;
	String parkName, parkId;
	String activityId, activityName;
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String text = "";
	
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
        
        ScrollView mainScrollView = (ScrollView) findViewById(R.id.scroll);
        
        
        mainScrollView.fullScroll(ScrollView.FOCUS_UP);
        mainScrollView.pageScroll(View.FOCUS_UP);
        mainScrollView.smoothScrollTo(0,0);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        int count = listAdapter.getGroupCount();
        for (int position = 1; position <= count; position++){
        	expListView.expandGroup(position - 1);
        }
        
     // Listview Group click listener
 		expListView.setOnGroupClickListener(new OnGroupClickListener() {
 			@Override
 			public boolean onGroupClick(ExpandableListView parent, View v,
 					int groupPosition, long id) {
 				
 				expListView.expandGroup(groupPosition);
 		        return true;

 			}
 		});

 		// Listview Group expanded listener
 		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 			@Override
 			public void onGroupExpand(int groupPosition) {
 			}
 		});

 		// Listview Group collasped listener
 		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 			@Override
 			public void onGroupCollapse(int groupPosition) {
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
 		
 		Button more = (Button) findViewById(R.id.more);
		
		more.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(getApplicationContext(),About.class);
				myIntent.putExtra("parkName", parkName);
				myIntent.putExtra("info", text);
				startActivity(myIntent);
			}			
		});
		
		Button photo = (Button) findViewById(R.id.photo);
		photo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(getApplicationContext(),Photo.class);
				myIntent.putExtra("parkId", parkId);
				myIntent.putExtra("parkName", parkName);
				startActivity(myIntent);
			}			
		});
		
		
		}
	
	public int dpToPx(int dp) {
	    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
	    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}
	
	public int pxToDp(int px) {
	    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
	    int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	    return dp;
	}
		
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> child;
        int countChild = 0;
        int countParent = 0;
        
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
		
		countParent = activitiesSubArray.size();
		
		for(int i=0; i<activitiesSubArray.size(); i++){	
		
			activityName = activitiesSubArray.get(i);
			
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
							countChild += 1;
						}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			listDataChild.put(listDataHeader.get(i), child); // Header, Child data
			
		}
		
		// About text for the park
		
		JSONObject currPark;
		for(int y = 0; y < Data.parksArray.length(); y ++){
			try {
				currPark = Data.parksArray.getJSONObject(y);
				if(currPark.get("id").toString().equals(parkId)){
					text = currPark.get("notes").toString();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		TextView name = (TextView) findViewById(R.id.name);
		TextView content = (TextView) findViewById(R.id.content);
		name.setText(parkName);
		content.setText(text);
		
		// Finds the height of the Expandedlistview
		
		int h = 0;
		
		Rect bounds = new Rect();
		Rect bounds2 = new Rect();
		
		name.getPaint().getTextBounds(name.getText().toString(), 0, name.getText().length(), bounds);
		content.getPaint().getTextBounds(content.getText().toString(), 0, content.getText().length(), bounds2);
		int titleHeight = pxToDp(bounds.height());
		int contentHeight = pxToDp(bounds2.height() * 5);
		int padding = 37;
		
		int header = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics());
		int element = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 17, getResources().getDisplayMetrics());
		
		int headerText = pxToDp(header) * countParent;
		int contentText = pxToDp(element) * countChild;
		
		int headerPadding = (14 + 3) * countParent;
		int itemPadding = (20 + 3) * countChild;
		
		int button = titleHeight;
		
		h = titleHeight + contentHeight + headerText + contentText + headerPadding + itemPadding + padding + button + 20;
		
		//Convert from DP to PX
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		// Gets linearlayout
		ExpandableListView layout = (ExpandableListView)findViewById(R.id.lvExp);
		// Gets the layout params that will allow you to resize the layout
		LayoutParams params = layout.getLayoutParams();
		// Changes the height and width to the specified *pixels*
		params.height = height;
		

	
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
