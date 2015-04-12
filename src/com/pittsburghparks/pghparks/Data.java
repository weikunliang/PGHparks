package com.pittsburghparks.pghparks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* Loads all the data from the server */
public class Data 
{
	public static JSONArray parksArray;
	public static JSONArray activitiesArray;
	public static JSONArray eventsArray;
	public static JSONArray objectsArray;
	public static JSONArray trailsArray;
	public static JSONArray photosArray;

	public static boolean build(String jsonString, Context context) throws JSONException
	{
		try
		{
			if(jsonString == null) {
				//read in old data
		        FileInputStream fis = context.openFileInput("park_data.txt");
		        BufferedReader r = new BufferedReader(new InputStreamReader(fis));
		        jsonString = r.readLine();
		        r.close();
			}
			else {
				//save data here?
				FileOutputStream fos = context.openFileOutput("park_data.txt", Context.MODE_PRIVATE);
				Writer out = new OutputStreamWriter(fos);
				out.write(jsonString);
			    out.close();	
			}
			
			JSONArray allData = new JSONArray(jsonString);
			JSONObject parksObject = (JSONObject) allData.get(0);
			JSONObject activitiesObject = (JSONObject) allData.get(1);
			JSONObject eventsObject = (JSONObject) allData.get(2);
			JSONObject objectsObject = (JSONObject) allData.get(3);
			JSONObject trailsObject = (JSONObject) allData.get(4);
			JSONObject photosObject = (JSONObject) allData.get(5);
			
			parksArray = (JSONArray) parksObject.get("parks");
			activitiesArray = (JSONArray) activitiesObject.get("activities");
			eventsArray = (JSONArray) eventsObject.get("events");
			objectsArray = (JSONArray) objectsObject.get("objects");
			trailsArray = (JSONArray) trailsObject.get("trails");
			photosArray = (JSONArray) photosObject.get("photos");
			
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isOnline(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    if(cm != null) {
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
		    return false;
	    }
	    else {
	    	return false;
	    }
	}
}
