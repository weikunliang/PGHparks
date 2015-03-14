package com.pittsburghparks.pghparks;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Map extends SherlockFragment implements OnInfoWindowClickListener{
	MapView mMapView;
	private GoogleMap googleMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    // inflate and return the layout
	    View v = inflater.inflate(R.layout.map, container,
	            false);
	    mMapView = (MapView) v.findViewById(R.id.mapView);
	    mMapView.onCreate(savedInstanceState);

	    mMapView.onResume();// needed to get the map to display immediately

	    try {
	        MapsInitializer.initialize(getActivity().getApplicationContext());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    googleMap = mMapView.getMap();
	    // latitude and longitude
	    double latitude = 40.4433;
	    double longitude = -79.9436;
	    
	    JSONObject currObj;
	    for(int i = 0; i<Data.objectsArray.length(); i++){
			try {
				currObj = Data.objectsArray.getJSONObject(i);
				latitude = (Double) currObj.get("lat");
				longitude = (Double) currObj.get("lon");
				
				MarkerOptions marker = new MarkerOptions().position(
			            new LatLng(latitude, longitude)).title(currObj.get("name").toString());
				
				marker.icon(BitmapDescriptorFactory
			            .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
				Marker m = googleMap.addMarker(marker);
				googleMap.setOnInfoWindowClickListener(this);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    double lat = latitude;
	    double lon = longitude;
	    
	    if(this.getArguments() != null){
		    Bundle bundle = this.getArguments();
		    lat = bundle.getDouble("lat", 0);
		    lon = bundle.getDouble("lon", 0);
	    }
	    
	    CameraPosition cameraPosition = new CameraPosition.Builder()
	            .target(new LatLng(lat, lon)).zoom(20).build();
	    googleMap.animateCamera(CameraUpdateFactory
	            .newCameraPosition(cameraPosition));


	    // Perform any camera updates here
	    return v;
	}
	
	public void onInfoWindowClick(Marker marker){
		
		Intent intent = new Intent(getActivity(), ActivityDetail.class);
		intent.putExtra("objectName", marker.getTitle().toString());
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    mMapView.onResume();
	}

	@Override
	public void onPause() {
	    super.onPause();
	    mMapView.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
	    super.onLowMemory();
	    mMapView.onLowMemory();
	}

}