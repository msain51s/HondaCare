package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.FeedbackAdapter;
import com.intellinet.hondatwowheeler.adapter.MyBikeAdapter;
import com.intellinet.hondatwowheeler.model.FeedbackModel;
import com.intellinet.hondatwowheeler.model.MyBike;

import java.util.ArrayList;

public class FeedbackActivity extends BaseActivity implements FeedbackAdapter.OnItemClickListener{

    String activityName;
    RecyclerView recyclerView;
    ArrayList<FeedbackModel> fTypeList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_feedback, containerLayout);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);

        recyclerView=(RecyclerView)findViewById(R.id.feedback_recycler);
        linearLayoutManager=new LinearLayoutManager(FeedbackActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        feedbackAdapter=new FeedbackAdapter(fTypeList, FeedbackActivity.this);
        feedbackAdapter.setOnItemClickListener(FeedbackActivity.this);
        recyclerView.setAdapter(feedbackAdapter);
        initRecycler();
    }

    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");

    }

   private void initRecycler(){
       FeedbackModel feedbackModel = new FeedbackModel("Sales Feedback", R.drawable.sales_feedback_icon);
       FeedbackModel feedbackModel1 = new FeedbackModel("Service Feedback", R.drawable.service_feedback_icon);
       FeedbackModel feedbackModel2 = new FeedbackModel("General Feedback", R.drawable.general_feedback_icon);
       fTypeList.add(feedbackModel);
       fTypeList.add(feedbackModel1);
       fTypeList.add(feedbackModel2);
       feedbackAdapter.notifyDataSetChanged();
   }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(FeedbackActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        FeedbackActivity.this.finish();
    }


    @Override
    public void onItemClick(View view, FeedbackModel feedbackModel) {
        Intent intent = new Intent(FeedbackActivity.this, FeedbackDetailActivity.class);
        intent.putExtra("ftype", feedbackModel.getfTypeTitle());
        startActivity(intent);
    }
}
