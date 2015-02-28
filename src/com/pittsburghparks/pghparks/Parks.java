package com.pittsburghparks.pghparks;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Parks extends SherlockFragment {
	Context context;
	ArrayList<String> parksIdArray;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		context = getActivity();
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(context,ActivityDetail.class));
		try {
			if(Data.parksArray == null) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
		
		View rootView = inflater.inflate(R.layout.parks, container, false);
        View actionBarView = inflater.inflate(R.layout.action_bar_title, null);
        TextView title = (TextView) actionBarView.findViewById(R.id.title);
        title.setText("Parks");
        
        ArrayList<String> parksArray = new ArrayList<String>();
		parksIdArray = new ArrayList<String>();
		JSONObject currPark;
		for(int i = 0; i<Data.parksArray.length(); i++)
		{
			try 
			{
				currPark = Data.parksArray.getJSONObject(i);
				parksArray.add(currPark.get("name").toString());
				parksIdArray.add(currPark.get("id").toString());
				
			} 
			catch (JSONException e) 
			{
				//handle JSON problem
				e.printStackTrace();
			}
			
		}
		ListView parksList = (ListView)rootView.findViewById(R.id.parks_list);
		
		ArrayAdapter<String> parksAdapter=new ArrayAdapter<String>(context, R.layout.parks_list, R.id.park_name, parksArray);
		parksList.setAdapter(parksAdapter);
		parksList.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
				{
					
					TextView textView = (TextView) arg1.findViewById(R.id.park_name); 
					Intent myIntent = new Intent(getActivity(), ActivityList.class);
					myIntent.putExtra("parkName", textView.getText().toString());
					myIntent.putExtra("parkId", parksIdArray.get(arg2));
					startActivity(myIntent);
				}
			});		
	
        return rootView;
    }
	
	public void onStart()
	{
		super.onStart();
		
		try {
			if(Data.parksArray == null) {
				Intent myIntent = new Intent(context, Main.class);
				context.startActivity(myIntent);
			}
		} catch(Exception e) {
			Intent myIntent = new Intent(context, Main.class);
			context.startActivity(myIntent);
		}
	}

}