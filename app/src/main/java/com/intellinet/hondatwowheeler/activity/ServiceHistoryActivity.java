package com.intellinet.hondatwowheeler.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.ItemHeaderViewBinder;
import com.intellinet.hondatwowheeler.adapter.ItemViewBinder;
import com.intellinet.hondatwowheeler.model.ItemHeader;
import com.intellinet.hondatwowheeler.model.ServiceHistoryModel;

import java.util.ArrayList;
import java.util.List;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

public class ServiceHistoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    StickyHeaderViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);
        initToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<DataBean> userList = new ArrayList<>();

        ItemHeader model1=new ItemHeader();
        model1.setHeaderText("Completed");
        userList.add(model1);

        ServiceHistoryModel model=new ServiceHistoryModel();
        model.setBikeModelName("Honda CBZ");
        model.setDealerName("Shri Khatri Honda Motors");
        model.setDealerAddress("Block A 10 Near Railway Road");
        model.setDealerContactNo("+91 (0)124-7712800");
        model.setEmailAddress("hondadealer@gmail.com");
        model.setProblemDescription("Engine Oil level is low.Engine making unwanted noise");
        userList.add(model);

        ServiceHistoryModel model_4=new ServiceHistoryModel();
        model_4.setBikeModelName("Honda CBZ");
        model_4.setDealerName("Shri Khatri Honda Motors");
        model_4.setDealerAddress("Block A 10 Near Railway Road");
        model_4.setDealerContactNo("+91 (0)124-7712800");
        model_4.setEmailAddress("hondadealer@gmail.com");
        model_4.setProblemDescription("Engine Oil level is low.Engine making unwanted noise");
        userList.add(model_4);

        ItemHeader model11=new ItemHeader();
        model11.setHeaderText("Pending");
        userList.add(model11);

        ServiceHistoryModel model_2=new ServiceHistoryModel();
        model_2.setBikeModelName("Honda CBR");
        model_2.setDealerName("Shri Khatri Honda Motors");
        model_2.setDealerAddress("Block A 10 Near Railway Road");
        model_2.setDealerContactNo("+91 (0)124-7712800");
        model_2.setEmailAddress("hondadealer@gmail.com");
        model_2.setProblemDescription("Engine Oil level is low.Engine making unwanted noise");
        userList.add(model_2);

        adapter = new StickyHeaderViewAdapter(userList)
                .RegisterItemType(new ItemViewBinder(this))
                .RegisterItemType(new ItemHeaderViewBinder());
        recyclerView.setAdapter(adapter);

        askPhoneCallPermission();
    }

    public void initToolbar(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Service History");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
