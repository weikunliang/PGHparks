package com.pittsburghparks.pghparks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SinglePark extends SherlockActivity {
//	Context context;
//	Activity activity;
//	ViewFlipper flipper;
//	String parkName, parkId;
//	ImageLoader imageLoader = ImageLoader.getInstance();
//	String[] imageUrls;
//	String[] imageThumbUrls;
//	String[] imageTitles;
//	String[] imageNotes;
//	ArrayList<String> imageThumbUrlsList = new ArrayList<String>();
//	ArrayList<String> imageUrlsList = new ArrayList<String>();
//	ArrayList<String> imageTitlesList = new ArrayList<String>();
//	ArrayList<String> imageNotesList = new ArrayList<String>();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) 
//	{
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,SingleThingToDo.class));
//		context = this;
//		try {
//			if(Data.photosArray == null) {
//				Intent myIntent = new Intent(context, Splash.class);
//				context.startActivity(myIntent);
//			}
//		} catch(Exception e) {
//			Intent myIntent = new Intent(context, Splash.class);
//			context.startActivity(myIntent);
//		}
//		
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.single_park);
//		
//        setUpBottomNav();
//        
//        DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//
//	    com.actionbarsherlock.app.ActionBar bar = getSupportActionBar();
//	    bar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_TABS);
//	    bar.setDisplayOptions(0, com.actionbarsherlock.app.ActionBar.DISPLAY_SHOW_TITLE);
//	    
////	    flipper = (ViewFlipper) findViewById(R.id.park_tab_switcher);
////	    TabListener listener1 = new ParkTabListener(flipper);
////	    
////	    bar.addTab(bar.newTab().setText("Park Home").setTabListener(listener1));
////	    bar.addTab(bar.newTab().setText("View Photos").setTabListener(listener1));
////	    bar.addTab(bar.newTab().setText("About").setTabListener(listener1));
////	    bar.addTab(bar.newTab().setText("Donate").setTabListener(listener1));
//	    
//		Intent intent = getIntent();
//		parkName = intent.getStringExtra("parkName");
//		parkId = intent.getStringExtra("parkId");
//		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//		.cacheInMemory()
//		.cacheOnDisc()
//		.build();
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//																	.defaultDisplayImageOptions(defaultOptions)
//																	.build();
//		imageLoader.init(config);
//		    
////	    GridView grid = (GridView) findViewById(R.id.park_images_grid);
////	    
////	    for(int i = 0; i<Data.photosArray.length(); i++)
////	    {
////	    	try 
////	    	{
////				if(Data.photosArray.getJSONObject(i).get("park_id").toString().equals(parkId))
////				{
////					imageThumbUrlsList.add(Data.photosArray.getJSONObject(i).get("thumb_url2x").toString());
////					imageUrlsList.add(Data.photosArray.getJSONObject(i).get("photo_url2x").toString());
////					imageTitlesList.add(Data.photosArray.getJSONObject(i).get("title").toString());
////					imageNotesList.add(Data.photosArray.getJSONObject(i).get("notes").toString());
////				}
////			} 
////	    	catch (JSONException e) 
////	    	{
////				e.printStackTrace();
////			}
////	    }
////	    imageUrls = new String[imageUrlsList.size()];
////	    imageUrlsList.toArray(imageUrls);
////	    
////	    imageThumbUrls = new String[imageThumbUrlsList.size()];
////	    imageThumbUrlsList.toArray(imageThumbUrls);
////	    
////	    imageTitles = new String[imageTitlesList.size()];
////	    imageTitlesList.toArray(imageTitles);
////	    
////	    imageNotes = new String[imageNotesList.size()];
////	    imageNotesList.toArray(imageNotes);
////	    
////	    grid.setAdapter(new ImageAdapter());
////
////	    grid.setOnItemClickListener(new OnItemClickListener() 
////	    {
////	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
////	        {
////	        	Intent myIntent = new Intent(context, SinglePhoto.class);
////	        	myIntent.putExtra("photoUrl", imageUrls[position]);
////	        	myIntent.putExtra("photoTitle", imageTitles[position]);
////	        	myIntent.putExtra("photoNotes", imageNotes[position]);
////				context.startActivity(myIntent);
////	        }
////	    });
//		
////		//get about data
////		for(int i = 0; i<Data.parksArray.length(); i++)
////		{
////			try 
////			{
////				if(Data.parksArray.getJSONObject(i).get("id").toString().equals(parkId))
////				{
////					TextView aboutSection = (TextView)findViewById(R.id.about_text);
////					aboutSection.setText(Data.parksArray.getJSONObject(i).get("notes").toString());
////					break;
////				}
////			} 
////			catch (JSONException e) 
////			{
////				//handle problems
////				e.printStackTrace();
////			}
////		}
//		
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) 
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getSupportActionBar().setCustomView(R.layout.action_bar_title);
//		getSupportActionBar().setDisplayShowCustomEnabled(true);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		getSupportMenuInflater().inflate(R.menu.home, menu);
//		return true;
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem menuitem)
//	{
//		if(menuitem.getTitle().toString().equals("MyPGH Parks"))
//		{
//			finish();	
//		}
//		else
//		{
//			Intent myIntent = new Intent(context, ReportIssue.class);
//			myIntent.putExtra("parkId", parkId);
//			context.startActivity(myIntent);
//		}
//		return false;
//	}
//	
//	public void onStart()
//	{
//		super.onStart();
//		
//		try {
//			if(Data.photosArray == null) {
//				Intent myIntent = new Intent(context, Splash.class);
//				context.startActivity(myIntent);
//			}
//		} catch(Exception e) {
//			Intent myIntent = new Intent(context, Splash.class);
//			context.startActivity(myIntent);
//		}
//		
//		ArrayList<String> singleParkArray = new ArrayList<String>();
//		singleParkArray.add("Things To Do");
//		singleParkArray.add("Bathrooms");
//		singleParkArray.add("Maps and Trails");
//		ListView parksList = (ListView)findViewById(R.id.park_item_list);
//		
//		DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		
//		SingleParkArrayAdapter parksAdapter=new SingleParkArrayAdapter(this, R.layout.park_item_list, R.id.park_item, singleParkArray);
//		parksList.setAdapter(parksAdapter);
//		parksList.setOnItemClickListener(new OnItemClickListener()
//			{
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//				{
//					TextView textView = (TextView) arg1.findViewById(R.id.park_item);
//					
//					if(textView.getText().toString().equals("Things To Do"))
//					{
//						findViewById(R.id.park_item_list).setVisibility(View.GONE);
//						findViewById(R.id.activities_selected).setVisibility(View.VISIBLE);
//						ImageView cancelbutton = (ImageView) findViewById(R.id.activities_header_icon);
//						cancelbutton.setClickable(true);
//						cancelbutton.setOnClickListener(new OnClickListener() {
//				            @Override
//				            public void onClick(View v) 
//				            {
//				            	findViewById(R.id.activities_selected).setVisibility(View.GONE);
//				            	findViewById(R.id.park_item_list).setVisibility(View.VISIBLE);
//				            }
//				        });
//						
//						ArrayList<String> activitiesSubArray = new ArrayList<String>();
//						JSONObject currActivity;
//						for(int i = 0; i<Data.activitiesArray.length(); i++)
//						{
//							try 
//							{
//								currActivity = Data.activitiesArray.getJSONObject(i);
//								if(currActivity.get("park_id").toString().equals(parkId))
//								{
//									activitiesSubArray.add(currActivity.get("title").toString());
//								}
//							} 
//							catch (JSONException e) 
//							{
//								//handle JSON problem
//								e.printStackTrace();
//							}
//						}
//						ListView parksList = (ListView)findViewById(R.id.park_activities_list);
//						
//						ArrayAdapter<String> parksAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, activitiesSubArray);
//						parksList.setAdapter(parksAdapter);
//						parksList.setOnItemClickListener(new OnItemClickListener()
//							{
//								@Override
//								public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//								{
//									TextView textView = (TextView) arg1.findViewById(R.id.sub_item_text);
//									Intent myIntent = new Intent(context, SingleThingToDo.class);
//									myIntent.putExtra("activityName", textView.getText().toString());
//									myIntent.putExtra("parkId", parkId);
//									context.startActivity(myIntent);
//								}
//							});
//					}
//					else if(textView.getText().toString().equals("Bathrooms & Facilities"))
//					{
//						findViewById(R.id.park_item_list).setVisibility(View.GONE);
//						findViewById(R.id.bathrooms_selected).setVisibility(View.VISIBLE);
//						ImageView cancelbutton = (ImageView) findViewById(R.id.bathrooms_header_icon);
//						cancelbutton.setClickable(true);
//						cancelbutton.setOnClickListener(new OnClickListener() {
//				            @Override
//				            public void onClick(View v) 
//				            {
//				            	findViewById(R.id.bathrooms_selected).setVisibility(View.GONE);
//				            	findViewById(R.id.park_item_list).setVisibility(View.VISIBLE);
//				            }
//				        });
//						
//						ArrayList<String> bathroomsSubArray = new ArrayList<String>();
//						final ArrayList<String> bathroomsIdSubArray = new ArrayList<String>();
//						JSONObject currObject;
//						for(int i = 0; i<Data.objectsArray.length(); i++)
//						{
//							try 
//							{
//								currObject = Data.objectsArray.getJSONObject(i);
//								if(currObject.get("park_id").toString().equals(parkId) && (currObject.get("ptype").toString().toLowerCase().equals("bathroom") || currObject.get("is_facility").toString().toLowerCase().equals("true")))
//								{
//									bathroomsSubArray.add(currObject.get("name").toString());
//									bathroomsIdSubArray.add(currObject.get("id").toString());
//								}
//							} 
//							catch (JSONException e) 
//							{
//								//handle JSON problem
//								e.printStackTrace();
//							}
//						}
//						ListView parksList = (ListView)findViewById(R.id.park_bathrooms_list);
//						
//						ArrayAdapter<String> parksAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, bathroomsSubArray);
//						parksList.setAdapter(parksAdapter);
//						parksList.setOnItemClickListener(new OnItemClickListener()
//							{
//								@Override
//								public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//								{
//									Intent myIntent = new Intent(context, SingleObject.class);
//									myIntent.putExtra("id", bathroomsIdSubArray.get(arg2));
//									myIntent.putExtra("type", "bathroom");
//									context.startActivity(myIntent);
//								}
//							});
//					}
//					else if(textView.getText().toString().equals("Maps and Trails"))
//					{
//						findViewById(R.id.park_item_list).setVisibility(View.GONE);
//						findViewById(R.id.maps_selected).setVisibility(View.VISIBLE);
//						ImageView cancelbutton = (ImageView) findViewById(R.id.maps_header_icon);
//						cancelbutton.setClickable(true);
//						cancelbutton.setOnClickListener(new OnClickListener() {
//				            @Override
//				            public void onClick(View v) 
//				            {
//				            	findViewById(R.id.maps_selected).setVisibility(View.GONE);
//				            	findViewById(R.id.park_item_list).setVisibility(View.VISIBLE);
//				            }
//				        });
//						
//						ArrayList<String> mapsSubArray = new ArrayList<String>();
//						mapsSubArray.add("Park Map");
//						mapsSubArray.add("Trails");
//						//mapsSubArray.add("All Trails");
//						ListView parksList = (ListView)findViewById(R.id.park_maps_list);
//						
//						ArrayAdapter<String> parksAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, mapsSubArray);
//						parksList.setAdapter(parksAdapter);
//						parksList.setOnItemClickListener(new OnItemClickListener()
//							{
//								@Override
//								public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//								{
//									if(arg2 == 0)
//									{
//										Intent myIntent = new Intent(context, SingleMap.class);
//										myIntent.putExtra("parkId", parkId);
//										context.startActivity(myIntent);
//									}
//									else if(arg2 == 1)
//									{
//										Intent myIntent = new Intent(context, Trails.class);
//										myIntent.putExtra("parkId", parkId);
//										context.startActivity(myIntent);
//									}
//									else 
//									{
//										Intent myIntent = new Intent(context, SingleObject.class);
//										myIntent.putExtra("type","alltrails");
//										myIntent.putExtra("id", parkId);
//										context.startActivity(myIntent);
//									}
//								}
//							});
//						
//					}
//				}
//			});		
//	}
//
//	
//	private class ParkTabListener implements TabListener
//	{
//		ViewFlipper flipper;
//	    public ParkTabListener(ViewFlipper inFlipper) 
//	    {
//	    	flipper = inFlipper;
//	    }
//
//		@Override
//		public void onTabSelected(Tab tab, FragmentTransaction ft) 
//		{
//			if(tab.getText().toString().equals("Park Home"))
//			{
//				flipper.setDisplayedChild(0);
//			}
//			else if(tab.getText().toString().equals("View Photos"))
//			{
//				flipper.setDisplayedChild(1);
//				ImageView cancelbutton = (ImageView) findViewById(R.id.photos_header_icon);
//				cancelbutton.setClickable(true);
//				cancelbutton.setOnClickListener(new OnClickListener() {
//		            @Override
//		            public void onClick(View v) 
//		            {
//		            	getSupportActionBar().setSelectedNavigationItem(0);
//		            }
//		        });
//			}
//			else if(tab.getText().toString().equals("About"))
//			{
//				flipper.setDisplayedChild(2);
//				ImageView cancelbutton = (ImageView) findViewById(R.id.about_header_icon);
//				cancelbutton.setClickable(true);
//				cancelbutton.setOnClickListener(new OnClickListener() {
//		            @Override
//		            public void onClick(View v) 
//		            {
//		            	getSupportActionBar().setSelectedNavigationItem(0);
//		            }
//		        });
//			}
//			else if(tab.getText().toString().equals("Donate"))
//			{
//				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pittsburghparks.org/donate_mobile/"));
//				startActivity(browserIntent);
//			}
//		}
//
//		@Override
//		public void onTabUnselected(Tab tab, FragmentTransaction ft) 
//		{
//			// TODO Auto-generated method stub	
//		}
//
//		@Override
//		public void onTabReselected(Tab tab, FragmentTransaction ft) 
//		{
//			// TODO Auto-generated method stub	
//		}
//		
//	}
//	
//	public class ImageAdapter extends BaseAdapter {
//		@Override
//		public int getCount() 
//		{
//			return imageThumbUrls.length;
//		}
//
//		@Override
//		public Object getItem(int position) 
//		{
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) 
//		{
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) 
//		{
//			final ImageView imageView;
//			if (convertView == null) 
//			{
//				imageView = (ImageView) getLayoutInflater().inflate(R.layout.single_park_grid_image, parent, false);
//			} 
//			else 
//			{
//				imageView = (ImageView) convertView;
//			}
//
//			imageLoader.displayImage(imageThumbUrls[position], imageView);
//
//			return imageView;
//		}
//	}
}
