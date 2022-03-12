package com.team6.quickcashteam6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.team6.quickcashteam6.databinding.ActivityViewLocationMapBinding;

public class ViewLocationMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityViewLocationMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewLocationMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    Job location is retrieved from an intent and is displayed as a marker on the map API for the
    employee to view.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double lng;
        double lat;
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            lng = Double.parseDouble(extras.getString("lng"));
            lat = Double.parseDouble(extras.getString("lat"));
            LatLng jobLocation = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(jobLocation).title("Job Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(jobLocation));
        }

    }
}