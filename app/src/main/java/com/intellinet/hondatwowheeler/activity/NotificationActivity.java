package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;

public class NotificationActivity extends BaseActivity {

    String activityName;
    TextView checkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_notification, containerLayout);
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
        Intent intent=new Intent(NotificationActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        NotificationActivity.this.finish();
    }

}
