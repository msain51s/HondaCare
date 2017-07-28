package com.intellinet.hondatwowheeler.activity;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.DealerModel;
import com.intellinet.hondatwowheeler.utility.Util;

public class DealerInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;
    TextView dealerNameText,dealerAddress,dealerContactNo,dealerEmailAddress;
    TextView openHourText,monText,tueText,wedText,thurText,friText,satText,sunText,
                          monTextValue,tueTextValue,wedTextValue,thurTextValue,friTextValue,satTextValue,sunTextValue;

    DealerModel model;
    Typeface roboto_regular,roboto_light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_info);
        roboto_regular= Util.getCustomFont(FontType.ROBOTO_REGULAR);
        roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);
        initToolbar();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            model= (DealerModel) bundle.get("dealerModel");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dealerNameText= (TextView) findViewById(R.id.dealerNameTextview);
        dealerAddress= (TextView) findViewById(R.id.dealerAddressTextview);
        dealerContactNo= (TextView) findViewById(R.id.dealerContactNoTextview);
        dealerEmailAddress= (TextView) findViewById(R.id.dealerEmailAddressTextview);
        openHourText= (TextView) findViewById(R.id.open_hour_text);
        monText= (TextView) findViewById(R.id.mondayText);
        monTextValue= (TextView) findViewById(R.id.mondayTimeText);
        tueText= (TextView) findViewById(R.id.tuesdayText);
        tueTextValue= (TextView) findViewById(R.id.tuesdayTimeText);
        wedText= (TextView) findViewById(R.id.wednesdayText);
        wedTextValue= (TextView) findViewById(R.id.wednesdayTimeText);
        thurText= (TextView) findViewById(R.id.thursdayText);
        thurTextValue= (TextView) findViewById(R.id.thursdayTimeText);
        friText= (TextView) findViewById(R.id.fridayText);
        friTextValue= (TextView) findViewById(R.id.fridayTimeText);
        satText= (TextView) findViewById(R.id.saturdayText);
        satTextValue= (TextView) findViewById(R.id.saturdayTimeText);
        sunText= (TextView) findViewById(R.id.sundayText);
        sunTextValue= (TextView) findViewById(R.id.sundayTimeText);

        dealerNameText.setTypeface(roboto_regular);
        dealerAddress.setTypeface(roboto_light);
        dealerContactNo.setTypeface(roboto_light);
        dealerEmailAddress.setTypeface(roboto_light);
        monText.setTypeface(roboto_light);
        monTextValue.setTypeface(roboto_light);
        tueText.setTypeface(roboto_light);
        tueTextValue.setTypeface(roboto_light);
        wedText.setTypeface(roboto_light);
        wedTextValue.setTypeface(roboto_light);
        thurText.setTypeface(roboto_light);
        thurTextValue.setTypeface(roboto_light);
        friText.setTypeface(roboto_light);
        friTextValue.setTypeface(roboto_light);
        satText.setTypeface(roboto_light);
        satTextValue.setTypeface(roboto_light);
        sunText.setTypeface(roboto_light);
        sunTextValue.setTypeface(roboto_light);


        dealerNameText.setText(model.getName());
        dealerAddress.setText(model.getAddress());
        dealerContactNo.setText(model.getContactNo());
        dealerEmailAddress.setText(model.getEmailAddress());
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.7041, 77.1025);
        if(model!=null)
        mMap.addMarker(new MarkerOptions().position(sydney).title(model.getAddress()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

 public void initToolbar(){
     toolbar= (Toolbar) findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
     getSupportActionBar().setTitle("Dealer Info");
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
