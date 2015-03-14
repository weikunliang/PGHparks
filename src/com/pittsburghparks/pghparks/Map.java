package com.pittsburghparks.pghparks;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Map extends SherlockFragment {
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
				googleMap.addMarker(marker);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	      
	    CameraPosition cameraPosition = new CameraPosition.Builder()
	            .target(new LatLng(latitude, longitude)).zoom(12).build();
	    googleMap.animateCamera(CameraUpdateFactory
	            .newCameraPosition(cameraPosition));

	    // Perform any camera updates here
	    return v;
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