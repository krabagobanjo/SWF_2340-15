 package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

import edu.gatech.cs2340.willcode4money.shoppingwithfriend.R;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double latitude;
    private double longitude;
    private List<Address> geocodeMatches = null;

    private View tempV = null;
    private int tempId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        CheckBox hybrid_mode;
        CheckBox satellite_mode;
        CheckBox terrain_mode;
        CheckBox none_mode;
        String location;
        location = (String) intent.getSerializableExtra("Location_info");
        //location = "North AvNW Atlanta GA 30332";
        hybrid_mode = (CheckBox) findViewById(R.id.hybrid);
        satellite_mode = (CheckBox) findViewById(R.id.satellite);
        terrain_mode = (CheckBox) findViewById(R.id.terrain);
        none_mode = (CheckBox) findViewById(R.id.none);

        try {
            geocodeMatches = new Geocoder(this).getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((geocodeMatches == null) || (geocodeMatches.isEmpty())) {
            Toast.makeText(MapsActivity.this, "No matches were found or there is no backend service!",
                    Toast.LENGTH_SHORT).show();
        } else {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }

        setUpMapIfNeeded();

        hybrid_mode.setOnClickListener(CheckBoxOnClickListener);
        satellite_mode.setOnClickListener(CheckBoxOnClickListener);
        terrain_mode.setOnClickListener(CheckBoxOnClickListener);
        none_mode.setOnClickListener(CheckBoxOnClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng lat = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lat));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("HERE"));
    }

    private final CheckBox.OnClickListener CheckBoxOnClickListener = new CheckBox.OnClickListener() {
        public void onClick(View v) {
            setCheckB(v);
        }
    };

    private void setCheckB(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        if(tempId != v.getId() && tempV != null) ((CheckBox) tempV).setChecked(false);
        switch (v.getId()) {
            case R.id.hybrid:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.satellite:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.terrain:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.none:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
        }
        tempId = v.getId();
        tempV = v;
    }
}
