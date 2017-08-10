package com.intellinet.hondatwowheeler.activity;


import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.NotificationModel;
import com.intellinet.hondatwowheeler.utility.Util;

public class NotificationDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView scaleImage;
    TextView titleTextView;
    TextView descTextView;
    TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        initToolBar();
        scaleImage=(ImageView)findViewById(R.id.scaleImage);
        titleTextView=(TextView)findViewById(R.id.titleTextView);
        descTextView=(TextView)findViewById(R.id.descTextView);
        timeTextView=(TextView)findViewById(R.id.timeTextView);

        titleTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        descTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        timeTextView.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));

        getIntentData();
    }


    public void getIntentData(){
        Bundle extras = getIntent().getExtras();
        NotificationModel notificationModel = (NotificationModel)extras.get(NotificationActivity.EXTRA_NOTIFICATION_ITEM);

        titleTextView.setText(notificationModel.getTitle());
        timeTextView.setText(notificationModel.getTime());
        descTextView.setText(notificationModel.getDesc());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(NotificationActivity.EXTRA_NOTIFICATION_IMAGE_TRANSITION_NAME);
            scaleImage.setTransitionName(imageTransitionName);
        }

        scaleImage.setImageResource(notificationModel.getImg());
    }



    private void initToolBar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                 supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
