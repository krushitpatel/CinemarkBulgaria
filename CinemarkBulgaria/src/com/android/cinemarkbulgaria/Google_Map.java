package com.android.cinemarkbulgaria;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.cinemarkbulgaria.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class Google_Map extends FragmentActivity {
	private GoogleMap googleMap;
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap); 
        try {
            // Loading map
            initilizeMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap=((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // check if map is created successfully or not
            try{
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }catch(Exception e){
            	e.printStackTrace();
            }
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    
}
