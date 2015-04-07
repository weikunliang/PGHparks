package com.pittsburghparks.pghparks;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Map extends SherlockFragment implements LocationListener, LocationSource, OnInfoWindowClickListener, android.location.LocationListener{
	MapView mMapView;
	private GoogleMap googleMap;
	private LocationManager locationManager;
	private OnLocationChangedListener mListener;
	private String provider;
	LatLng coordinate;
	boolean window = false;

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
	    double latitude = 40;
	    double longitude = 40;
	    
	    double latArg = 0;
	    double lonArg = 0;
	    
	    Bundle arguments = getArguments();
	    
	    if(arguments != null){
	    	latArg = Double.parseDouble(getArguments().getString("lat"));    
	    	lonArg = Double.parseDouble(getArguments().getString("lon")); 
	    }
	    
	    JSONObject currObj;
	    for(int i = 0; i<Data.objectsArray.length(); i++){
			try {
				currObj = Data.objectsArray.getJSONObject(i);
				latitude = (Double) currObj.get("lat");
				longitude = (Double) currObj.get("lon");
				
				MarkerOptions marker = new MarkerOptions().position(
			            new LatLng(latitude, longitude)).title(currObj.get("name").toString());
				
				marker.icon(BitmapDescriptorFactory
			            .defaultMarker(BitmapDescriptorFactory.HUE_RED));
				Marker m = googleMap.addMarker(marker);
				if(latitude == latArg && longitude == lonArg){
					m.showInfoWindow();
					window = true;
				}
				googleMap.setOnInfoWindowClickListener(this);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
	    
        if(locationManager != null)
        {
            boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
             
            if(gpsIsEnabled)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, this);
            }
            else if(networkIsEnabled)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 10F, this);
            }
            else
            {
                //Show an error dialog that GPS is disabled...
            }
        }
        else
        {
            //Show some generic error dialog because something must have gone wrong with location manager.
        }
        
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location current = locationManager.getLastKnownLocation(provider);
        
        if (current != null) {
            onLocationChanged(current);
        } else {

            //do something
        }

        setUpMap();
	    // Perform any camera updates here
        
        CameraPosition cameraPosition;
        googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
        
        if(window){
	        cameraPosition = new CameraPosition.Builder().target(new LatLng(latArg, lonArg)).zoom(15).build();
        } else {
        	cameraPosition = new CameraPosition.Builder().target(coordinate).zoom(15).build();
	        
        }
        
        googleMap.animateCamera(CameraUpdateFactory
    	        .newCameraPosition(cameraPosition));

	    return v;
	}
	
	private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
	    @Override
	    public void onMyLocationChange(Location location) {
	        LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
	        if(googleMap != null){
	        	googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 16.0f));
	        }
	    }
	};
	
	
	public void onInfoWindowClick(Marker marker){
		
		Intent intent = new Intent(getActivity(), ActivityDetail.class);
		intent.putExtra("objectName", marker.getTitle().toString());
		startActivity(intent);
	}
	
	private void setUpMap() 
    {
        googleMap.setMyLocationEnabled(true);
    }
	
	@Override
	public void onResume() {
	    super.onResume();
	    mMapView.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
        if(locationManager != null)
        {
            googleMap.setMyLocationEnabled(true);
        }
	}

	@Override
	public void onPause() {
		if(locationManager != null)
        {
            locationManager.removeUpdates(this);
        }
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
	
	@Override
    public void activate(OnLocationChangedListener listener) 
    {
        mListener = listener;
    }
     
    @Override
    public void deactivate() 
    {
        mListener = null;
    }
 
    @Override
    public void onLocationChanged(Location location) 
    {
        if( mListener != null )
        {
            mListener.onLocationChanged(location);
 
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        }
  
        coordinate = new LatLng(location.getLatitude(), location.getLongitude());
        if(googleMap != null){
        	googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 16.0f));
        }
    }
 
    @Override
    public void onProviderDisabled(String provider) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "provider disabled", Toast.LENGTH_SHORT).show();
    }
 
    @Override
    public void onProviderEnabled(String provider) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "provider enabled", Toast.LENGTH_SHORT).show();
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "status changed", Toast.LENGTH_SHORT).show();
    }

}