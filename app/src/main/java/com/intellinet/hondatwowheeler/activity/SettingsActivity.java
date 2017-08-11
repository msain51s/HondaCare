package com.intellinet.hondatwowheeler.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.utility.Util;

public class SettingsActivity extends BaseActivity {

    String activityName;
    SwitchCompat nSwitchButton;
    TextView rateTextView, pTextView, cuTextView,
             nTextView, vTextView;
    LinearLayout cuLayout, pLayout, rLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_setting, containerLayout);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);
        nSwitchButton=(SwitchCompat)findViewById(R.id.switchButton);
        rateTextView=(TextView)findViewById(R.id.rateTextView);
        pTextView=(TextView)findViewById(R.id.pTextView);
        cuTextView=(TextView)findViewById(R.id.cuTextView);
        nTextView=(TextView)findViewById(R.id.nTextView);
        vTextView=(TextView)findViewById(R.id.vTextView);

        cuLayout=(LinearLayout)findViewById(R.id.cLayout);
        pLayout=(LinearLayout)findViewById(R.id.pLayout);
        rLayout=(LinearLayout)findViewById(R.id.rLayout);

        setCustomFont();

        cuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        pLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, PolicyActivity.class);
                startActivity(intent);
            }
        });

        rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" +"com.intellischool");
                     //   SettingsActivity.this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + "com.intellischool")));
                                    //SettingsActivity.this.getPackageName())));
                }
            }
        });

    }




/*Implement CustomFont and Switch Button Feature*/
    public void setCustomFont(){
        rateTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        pTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        cuTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        nTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        vTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));


        nSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //  updateNotification("1", deviceId);
                    Toast.makeText(SettingsActivity.this, "Notification active", Toast.LENGTH_SHORT).show();
                    //   sessionManager.notificationStatus("true");
                } else {
                    // if (sessionManager.notificationUpdate().equalsIgnoreCase("true")) {
                    //   updateNotification("0", deviceId);
                    Toast.makeText(SettingsActivity.this, "Notification Deactivated", Toast.LENGTH_SHORT).show();
                    //     sessionManager.notificationStatus("false");
                    //   }

                }

            }
        });
    }

    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SettingsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        SettingsActivity.this.finish();
    }

}
