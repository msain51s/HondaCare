package com.intellinet.hondatwowheeler.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.DealerModel;
import com.intellinet.hondatwowheeler.utility.Application;
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

        dealerContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+model.getContactNo()));

                if (ActivityCompat.checkSelfPermission(Application.mContext,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        dealerEmailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(new String[]{model.getEmailAddress()});
            }
        });

        askPhoneCallPermission();
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


    public void askPhoneCallPermission(){
        // Here, thisActivity is the current activity
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.d("Call_Phone", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);

        } else {
            Log.d("Call_Phone", "Permission is granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Call_phone", "Permission has been granted");

            } else {
                Log.d("Call_phone", "Permission has been denied or request cancelled");
            }
        }
    }

    protected void sendEmail(String[] to) {
        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Application.mContext, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
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
