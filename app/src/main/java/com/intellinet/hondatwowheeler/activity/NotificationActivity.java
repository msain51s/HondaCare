package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.NotificationAdapter;
import com.intellinet.hondatwowheeler.model.NotificationModel;

import java.util.ArrayList;

public class NotificationActivity extends BaseActivity implements NotificationAdapter.OnItemClickListener{

    String activityName;
    RecyclerView recyclerView;
    ArrayList<NotificationModel> notificationList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    NotificationAdapter notificationAdapter;

    public static String EXTRA_NOTIFICATION_ITEM="notification_model";
    public static String EXTRA_NOTIFICATION_IMAGE_TRANSITION_NAME="transition_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_notification, containerLayout);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);
        recyclerView=(RecyclerView)findViewById(R.id.nList_recycler);
        linearLayoutManager=new LinearLayoutManager(NotificationActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        notificationAdapter=new NotificationAdapter(notificationList, NotificationActivity.this);
        notificationAdapter.setOnItemClickListener(NotificationActivity.this);
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setMotionEventSplittingEnabled(false);
        initRecycler();
    }

    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NotificationActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        NotificationActivity.this.finish();
    }


    /*Add Data to List*/
    public void initRecycler() {
        NotificationModel nmodel1 = new NotificationModel("Welcome to Honda", "Today 10:55 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.feedback_honda_img);
        NotificationModel nmodel2 = new NotificationModel("Honda Notification", "Today 11:00 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.honda_bike3);
        NotificationModel nmodel3 = new NotificationModel("Honda Service", "Today 09:45 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.honda_bike5);
        NotificationModel nmodel4 = new NotificationModel("Honda New Launch", "Today 08:55 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.feedback_honda_img);
        NotificationModel nmodel5 = new NotificationModel("New Booking Offer", "Today 09:55 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 0);
        NotificationModel nmodel6 = new NotificationModel("Rate Our Service", "Today 11:55 AM",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 0);

        notificationList.add(nmodel1);
        notificationList.add(nmodel2);
        notificationList.add(nmodel3);
        notificationList.add(nmodel4);
        notificationList.add(nmodel5);
        notificationList.add(nmodel6);

        notificationAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, NotificationModel notificationModel) {
        ImageView sImage;
        if(notificationModel.getImg()!=0){
             sImage= (ImageView)view.findViewById(R.id.nImage);
        }else{
            sImage= (ImageView)view.findViewById(R.id.nTransImage);
        }
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        intent.putExtra(EXTRA_NOTIFICATION_ITEM, notificationModel);
        intent.putExtra(EXTRA_NOTIFICATION_IMAGE_TRANSITION_NAME,  notificationModel.getTitle()+notificationModel.getTime());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sImage,
                notificationModel.getTitle()+notificationModel.getTime());

        startActivity(intent, options.toBundle());
    }
}
