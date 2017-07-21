package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;

public class SettingsActivity extends BaseActivity {

    String activityName;
    TextView checkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_setting, containerLayout);
        checkText=(TextView)findViewById(R.id.checkText);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);
    }

    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");
        checkText.setText(activityName);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SettingsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        SettingsActivity.this.finish();
    }

}
