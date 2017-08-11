package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.FeedbackDetailAdapter;
import com.intellinet.hondatwowheeler.model.FeedbackDetailModel;


import java.util.ArrayList;


public class FeedbackDetailActivity extends AppCompatActivity implements FeedbackDetailAdapter.OnItemClickListener{

    Toolbar toolbar;
    TextView titleText;
    RelativeLayout sadItemLayout, okItemLayout, happyItemLayout;
    ImageView sadImage, okImage, happyImage;
    TextView sadTextView, okTextView, happyTextView;
    Button saveButton;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<FeedbackDetailModel> fDetailArrayList=new ArrayList<>();
    FeedbackDetailAdapter fbDetailAdapter;

    boolean sad=false, ok=false, happy=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_detail);
        initToolBar();
        initLayoutItems();
    }

    private void initToolBar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        titleText=(TextView)findViewById(R.id.titleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        Intent intent=getIntent();
        String title = intent.getStringExtra("ftype");
        titleText.setText(title);
    }


    private void initLayoutItems(){
        sadItemLayout=(RelativeLayout)findViewById(R.id.sadItemLayout);
        okItemLayout=(RelativeLayout)findViewById(R.id.okItemLayout);
        happyItemLayout=(RelativeLayout)findViewById(R.id.happyItemLayout);

        sadImage=(ImageView)findViewById(R.id.sadImage);
        okImage=(ImageView)findViewById(R.id.okImage);
        happyImage=(ImageView)findViewById(R.id.happyImage);

        sadTextView=(TextView)findViewById(R.id.sadText);
        okTextView=(TextView)findViewById(R.id.okText);
        happyTextView=(TextView)findViewById(R.id.happyText);

        saveButton=(Button)findViewById(R.id.saveButton);

        recyclerView=(RecyclerView)findViewById(R.id.fDetail_recycler);
        linearLayoutManager=new LinearLayoutManager(FeedbackDetailActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        fbDetailAdapter=new FeedbackDetailAdapter(fDetailArrayList, FeedbackDetailActivity.this);
        fbDetailAdapter.setOnItemClickListener(FeedbackDetailActivity.this);
        recyclerView.setAdapter(fbDetailAdapter);
        initRecycler();


        sadItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sad){
                    sadImage.setImageResource(R.drawable.selected_sad_icon);
                    sadTextView.setTextColor(Color.parseColor("#fe0000"));
                    sad=true;

                    okImage.setImageResource(R.drawable.unselected_ok_icon);
                    okTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    ok=false;
                    happyImage.setImageResource(R.drawable.unselected_good_icon);
                    happyTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    happy=false;
                }
            }
        });


        okItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ok){
                    okImage.setImageResource(R.drawable.selected_ok_icon);
                    okTextView.setTextColor(Color.parseColor("#fdba13"));
                    ok=true;

                    sadImage.setImageResource(R.drawable.unselected_sad_icon);
                    sadTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    sad=false;
                    happyImage.setImageResource(R.drawable.unselected_good_icon);
                    happyTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    happy=false;
                }
            }
        });

        happyItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!happy){
                    happyImage.setImageResource(R.drawable.selected_good_icon);
                    happyTextView.setTextColor(Color.parseColor("#63b931"));
                    happy=true;

                    sadImage.setImageResource(R.drawable.unselected_sad_icon);
                    sadTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    sad=false;
                    okImage.setImageResource(R.drawable.unselected_ok_icon);
                    okTextView.setTextColor(Color.parseColor("#6c6b6b"));
                    ok=false;
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i< fDetailArrayList.size(); i++){
                    FeedbackDetailModel fbDetailModel=fDetailArrayList.get(i);
                    System.out.println("Cat Status "+ i+" "+fbDetailModel.getCatStatus());
                }
            }
        });
    }

    private void initRecycler(){
        FeedbackDetailModel feedbackDetailModel = new FeedbackDetailModel(R.drawable.cost, "Cost", "");
        FeedbackDetailModel feedbackDetailModel1 = new FeedbackDetailModel(R.drawable.service_icon, "Service", "");
        FeedbackDetailModel feedbackDetailModel2 = new FeedbackDetailModel(R.drawable.infra_icon, "Infrastructure", "");
        FeedbackDetailModel feedbackDetailModel3 = new FeedbackDetailModel(R.drawable.staff, "Staff, Salesperson and Attendant", "");
        FeedbackDetailModel feedbackDetailModel4 = new FeedbackDetailModel(R.drawable.enviroment, "Atmosphere, Seating and Restrooms", "");

        fDetailArrayList.add(feedbackDetailModel);
        fDetailArrayList.add(feedbackDetailModel1);
        fDetailArrayList.add(feedbackDetailModel2);
        fDetailArrayList.add(feedbackDetailModel3);
        fDetailArrayList.add(feedbackDetailModel4);
        fbDetailAdapter.notifyDataSetChanged();
    }



    @Override
    public void onItemClick(View view, FeedbackDetailModel fbDetailModel) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(FeedbackDetailActivity.this, FeedbackActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                FeedbackDetailActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
