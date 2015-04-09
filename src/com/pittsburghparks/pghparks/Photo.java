package com.pittsburghparks.pghparks;

import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Photo extends SherlockFragmentActivity {

	Context context;
	String parkId;
	String parkName;
	ImageLoader imageLoader = ImageLoader.getInstance();
	String photoUrl, photoTitle, photoNotes;
	ViewPager mViewPager;
	SlideShowPagerAdapter mSlideShowPagerAdapter;

	String[] imageArray;
	ArrayList<String> img = new ArrayList<String>();
	
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
		
		setContentView(R.layout.photo);
	
		Intent intent = getIntent();
		parkName = intent.getStringExtra("parkName");
		parkId = intent.getStringExtra("parkId");
		
		for(int i = 0; i<Data.photosArray.length(); i++)
		    {
		    	try 
		    	{
		    		Log.i("IMAGE", Data.photosArray.getJSONObject(i).toString());
					if(Data.photosArray.getJSONObject(i).get("park_id").toString().equals(parkId))
					{
						Log.i("IMAGE", Data.photosArray.getJSONObject(i).get("photo_url2x").toString());
						img.add(Data.photosArray.getJSONObject(i).get("photo_url2x").toString());
						Log.i("PLEASE PRINT", "NOOO");
					}
				} 
		    	catch (JSONException e) 
		    	{
					e.printStackTrace();
				}
		    }
		
		
		    imageArray = new String[img.size()];
		    img.toArray(imageArray);
		    
		    mViewPager = (ViewPager) findViewById(R.id.pager);
			mSlideShowPagerAdapter = new SlideShowPagerAdapter(this, imageArray);
			mViewPager.setAdapter(mSlideShowPagerAdapter);
		
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
	

}
