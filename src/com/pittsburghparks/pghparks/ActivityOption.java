package com.pittsburghparks.pghparks;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityOption extends SherlockFragmentActivity {
//	Context context;
//	ViewFlipper flipper;
//	ArrayList<JSONObject> activityObjectsArray;
//	private GoogleMap mMap;
//	ImageLoader imageLoader = ImageLoader.getInstance();
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) 
//	{
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,ActivityOption.class));
//		
//		context = this;
//		try {
//			if(Data.activitiesArray.length() == 1) {
//				Intent myIntent = new Intent(context, Main.class);
//				context.startActivity(myIntent);
//			}
//		} catch(Exception e) {
//			Intent myIntent = new Intent(context, Main.class);
//			context.startActivity(myIntent);
//		}
//		
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_option);
//        
//		
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);
//        TextView title = (TextView) actionBarView.findViewById(R.id.title);
//        title.setText("Parks");
//        
//        DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		ImageView metricIV = (ImageView) findViewById(R.id.activity_main_image);
//		float heightRatio = metrics.heightPixels*(metricIV.getLayoutParams().height/metrics.density);
//		float newPX = (heightRatio/1184)*metrics.density;
//		metricIV.getLayoutParams().height = (int) (newPX+20.5f);
//        
//		
//		final Intent intent = getIntent();
//		if(intent.getStringExtra("activityName") != null)
//		{
//			TextView header = (TextView) findViewById(R.id.sel_activity_header_text);
//			header.setText(intent.getStringExtra("activityName"));
//			String parkId = intent.getStringExtra("parkId");
//			@SuppressWarnings("deprecation")
//			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//			.cacheInMemory()
//			.cacheOnDisc()
//			.build();
//			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//																			.defaultDisplayImageOptions(defaultOptions)
//																			.build();
//			imageLoader.init(config);
//			TextView descText = (TextView) findViewById(R.id.activity_text);
//			ImageView mainImageIV = (ImageView) findViewById(R.id.activity_main_image);
//			JSONObject currActivity;
//			for(int i = 0; i<Data.activitiesArray.length(); i++)
//			{
//				try 
//				{
//					currActivity = Data.activitiesArray.getJSONObject(i);
//					if(currActivity.get("title").toString().equals(intent.getStringExtra("activityName")) && currActivity.get("park_id").toString().equals(parkId))
//					{
//						//get id
//						String activityId = currActivity.get("id").toString();
//						imageLoader.displayImage(currActivity.get("picture_url").toString(), mainImageIV);
//						//for loop through object for activityid
//						JSONObject currObject;
//						ArrayList<String> activitySubArray = new ArrayList<String>();
//						activityObjectsArray = new ArrayList<JSONObject>();
//						for(int z = 0; z<Data.objectsArray.length(); z++)
//						{
//							currObject = Data.objectsArray.getJSONObject(z);
//							//add any we find to a list
//							if(currObject.get("activityid").toString().equals(activityId))
//							{					
//								activitySubArray.add(currObject.get("name").toString());
//								activityObjectsArray.add(currObject);
//							}
//						}
//						ListView activitySubList = (ListView)findViewById(R.id.activity_sub_list);
//						ArrayAdapter<String> activityAdapter = new ArrayAdapter<String>(context, R.layout.single_park_sub_lists, R.id.sub_item_text, activitySubArray);
//						activitySubList.setAdapter(activityAdapter);
//						activitySubList.setOnItemClickListener(new OnItemClickListener()
//							{
//								@Override
//								public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//								{
//									flipper.showNext();
//									TextView descNameText = (TextView) findViewById(R.id.activity_detail_name);
//									TextView descText = (TextView) findViewById(R.id.activity_detail_text);
//									try 
//									{
//										descNameText.setText(activityObjectsArray.get(arg2).get("name").toString());
//										descText.setText(activityObjectsArray.get(arg2).get("notes").toString());
//										LatLng pos = new LatLng(activityObjectsArray.get(arg2).getDouble("lat"),activityObjectsArray.get(arg2).getDouble("lon"));
//										setUpMapIfNeeded(activityObjectsArray.get(arg2).get("name").toString(),pos);
//									} 
//									catch (JSONException e) 
//									{
//										e.printStackTrace();
//									}
//								}
//							});
//						//if we find none - add the description
//						if(currActivity.get("park_id").toString().equals(parkId) && currActivity.get("title").toString().equals(intent.getStringExtra("activityName")) && activityObjectsArray.size() < 1)
//						{
//							//only if the activity doesn't have any "objects"
//							descText.setText(currActivity.get("description").toString()+"\n\n");
//							break;
//						}
//						else {
//							descText.setText("");
//						}
//						break;
//					}
//				} 
//				catch (JSONException e) 
//				{
//					// handle messed up data
//					e.printStackTrace();
//				}
//			}
//		}
//		else
//		{
//			//set activity header text to park name
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) 
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//        getSupportActionBar().setCustomView(R.layout.action_bar_title);
//		getSupportActionBar().setDisplayShowCustomEnabled(true);
//		getSupportMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//	
//	public boolean onOptionsItemSelected(MenuItem menuitem)
//	{
//		if(menuitem.getTitle().toString().equals("MyPGH Parks"))
//		{
//			finish();	
//		}
//		else
//		{}
//		return false;
//	}
//	
//	public void onStart()
//	{
//		super.onStart();
//	}
//	
//    private void setUpMapIfNeeded(String markerName, LatLng pos) 
//    {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) 
//        {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) 
//            {
//            	mMap.setMyLocationEnabled(true);
//        		mMap.addMarker(new MarkerOptions().position(pos).title(markerName));
//                mMap.getUiSettings().setZoomControlsEnabled(false);
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
//            }
//        }
//    }
	

}
