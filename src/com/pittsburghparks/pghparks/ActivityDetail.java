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
import android.util.Log;
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
		setContentView(R.layout.activity_detail);
        
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);
        TextView title = (TextView) actionBarView.findViewById(R.id.title);
        title.setText("Parks");
        
//        DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		ImageView metricIV = (ImageView) findViewById(R.id.image);
//		float heightRatio = metrics.heightPixels*(metricIV.getLayoutParams().height/metrics.density);
//		float newPX = (heightRatio/1184)*metrics.density;
//		metricIV.getLayoutParams().height = (int) (newPX+20.5f);
//        
//		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//		.cacheInMemory()
//		.cacheOnDisc()
//		.build();
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//																		.defaultDisplayImageOptions(defaultOptions)
//																		.build();
//		imageLoader.init(config);
		
		final Intent intent = getIntent();
		String txt = "";
		String objectName = intent.getStringExtra("objectName");
		JSONObject currObject;
		for(int i = 0; i<Data.objectsArray.length(); i++){
			try {
				currObject = Data.objectsArray.getJSONObject(i);
				if(currObject.get("name").toString().equals(objectName)){
					txt = currObject.get("notes").toString();
					break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		TextView content = (TextView) findViewById(R.id.description);
		//ImageView mainImageIV = (ImageView) findViewById(R.id.image);
		Log.i("txt", txt);
		if(content == null){
			Log.i("CONTENT", "NULLL");
		}
		content.setText(txt);
		
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
