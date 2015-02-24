package com.pittsburghparks.pghparks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class Parks extends SherlockActivity {

	Context context;
	ArrayList<String> parksIdArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		//Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this,SingleThingToDo.class));
		context = this;
		try {
			if(Data.parksArray == null) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parks);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);
        TextView title = (TextView) actionBarView.findViewById(R.id.title);
        title.setText("Parks");

        setUpBottomNav();
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
	
	public void onStart()
	{
//		super.onStart();
//		
//		try {
//			if(Data.parksArray == null) {
//				Intent myIntent = new Intent(context, Main.class);
//				context.startActivity(myIntent);
//			}
//		} catch(Exception e) {
//			Intent myIntent = new Intent(context, Main.class);
//			context.startActivity(myIntent);
//		}
//		
//		ArrayList<String> parksArray = new ArrayList<String>();
//		parksIdArray = new ArrayList<String>();
//		JSONObject currPark;
//		for(int i = 0; i<Data.parksArray.length(); i++)
//		{
//			try 
//			{
//				currPark = Data.parksArray.getJSONObject(i);
//				parksArray.add(currPark.get("name").toString());
//				parksIdArray.add(currPark.get("id").toString());
//				
//			} 
//			catch (JSONException e) 
//			{
//				//handle JSON problem
//				e.printStackTrace();
//			}
//			
//		}
//		ListView parksList = (ListView)findViewById(R.id.parks_list);
//		
//		ArrayAdapter<String> parksAdapter=new ArrayAdapter<String>(this, R.layout.parks_list, R.id.park_name, parksArray);
//		parksList.setAdapter(parksAdapter);
//		parksList.setOnItemClickListener(new OnItemClickListener()
//			{
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
//				{
//					TextView textView = (TextView) arg1.findViewById(R.id.park_name); 
//					Intent myIntent = new Intent(context, SinglePark.class);
//					myIntent.putExtra("parkName", textView.getText().toString());
//					myIntent.putExtra("parkId", parksIdArray.get(arg2));
//					context.startActivity(myIntent);
//				}
//			});		
	}
	public void setUpBottomNav()
	{
//		Button maps = (Button) findViewById(R.id.button1);
//		maps.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) 
//            {
//            	Intent myIntent = new Intent(context, Parks.class);
//				context.startActivity(myIntent);
//            }
//        });
//        Button things = (Button) findViewById(R.id.button2);
//		things.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) 
//            {
//            	Intent myIntent = new Intent(context, Parks.class);
//				context.startActivity(myIntent);
//            }
//        });
//        Button events = (Button) findViewById(R.id.button3);
//		events.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) 
//            {
//            	Uri uri = Uri.parse("http://www.pittsburghparks.org/events");
//            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            	startActivity(intent);
//
//            }
//        });
//        Button donate = (Button) findViewById(R.id.button4);
//		donate.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) 
//            {
//            	Uri uri = Uri.parse("https://www.pittsburghparks.org/donate");
//            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            	startActivity(intent);
//            }
//        });
//		
//		Button info = (Button) findViewById(R.id.button5);
//		info.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) 
//            {
//            	Intent myIntent = new Intent(context, Info.class);
//				context.startActivity(myIntent);
//            }
//        });
	}
}
