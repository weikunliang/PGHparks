package com.pittsburghparks.pghparks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

public class Main extends SherlockActivity {

	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        
		context = this;
		new GetData().execute("http://pghparks.deeplocal.com/package/");

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
		super.onStart();
	}
	
	public void noDataAlert()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 
		// set title
		alertDialogBuilder.setTitle("Error getting park data.");

		// set dialog message
		alertDialogBuilder
			.setMessage("We encountered a problem while getting the park data. If this is your first time using the app, you must have an internet connection.")
			.setCancelable(false)
			.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					dialog.cancel();
					finish();
				}
			});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
	}
	
	class GetData extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... url) 
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			String checkString = null;
			String dataString = null;
			boolean newData = false;
			try
			{
				if(Data.isOnline(context)) {
					
					response = httpclient.execute(new HttpGet(url[0]+"check"));
					StatusLine statusLine = response.getStatusLine();
					if(statusLine.getStatusCode() == HttpStatus.SC_OK)
					{
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						response.getEntity().writeTo(out);
						out.close();
						checkString = out.toString();
	
						
						SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
						String oldCheck = pref.getString("check",null);
						
						JSONArray check = new JSONArray(checkString);
						if(oldCheck == null) {
							newData = true;
							SharedPreferences.Editor editor = pref.edit();
							editor.putString("check", check.get(0).toString());
							editor.commit();
						}
						else {
							 if((Integer.parseInt(oldCheck)) < (Integer.parseInt(check.get(0).toString()))) {
								 newData = true;
							 }
						}
						 
					}
					else
					{
						response.getEntity().getContent().close();
						throw new IOException(statusLine.getReasonPhrase());
					}
					
					if(newData) {
						response = httpclient.execute(new HttpGet(url[0]+"/all"));
						statusLine = response.getStatusLine();
						if(statusLine.getStatusCode() == HttpStatus.SC_OK)
						{
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							response.getEntity().writeTo(out);
							out.close();
							dataString = out.toString();
						}
						else
						{
							response.getEntity().getContent().close();
							throw new IOException(statusLine.getReasonPhrase());
						}
					}
				}
				
				Boolean gotData = Data.build(dataString,context);
				if(!gotData) {
					return null;
				}
				else {
					Intent myIntent = new Intent(context, Tab.class);
					context.startActivity(myIntent);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return "true";
		}
	    
		@Override
		protected void onPostExecute(String result) {
			if(result == null) {
				noDataAlert();
			}
	    }
	}

}
