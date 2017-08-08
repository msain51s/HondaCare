package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.MyBikeAdapter;
import com.intellinet.hondatwowheeler.model.MyBike;
import com.intellinet.hondatwowheeler.utility.Application;

import java.io.Serializable;
import java.util.ArrayList;

public class MyBikeActivity extends BaseActivity implements MyBikeAdapter.OnItemClickListener{

    String activityName;
    RecyclerView recyclerView;
    ArrayList<MyBike> bikeArrayList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MyBikeAdapter myBikeAdapter;
    String navigationFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_bike, containerLayout);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);

        recyclerView=(RecyclerView)findViewById(R.id.myBike_recycler);
        linearLayoutManager=new LinearLayoutManager(MyBikeActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myBikeAdapter=new MyBikeAdapter(MyBikeActivity.this, bikeArrayList);
        myBikeAdapter.setOnItemClickListener(MyBikeActivity.this);
        recyclerView.setAdapter(myBikeAdapter);
        initRecycler();
        initSwipe();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyBikeActivity.this, AddBikeActivity.class);
                startActivity(intent);
            }
        });


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            navigationFrom=bundle.getString("from");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        navigationFrom=intent.getStringExtra("from");
    }

    /*Add Data to List*/
    public void initRecycler() {
        MyBike myBike1 = new MyBike(R.drawable.honda_bike2, "Honda CB1000R", "HR26AT4648");
        MyBike myBike2 = new MyBike(R.drawable.honda_bike3, "Honda CBR300R", "RJ46AT4649");
        MyBike myBike3 = new MyBike(R.drawable.honda_bike4, "Honda CBR650F", "UP70AT4648");
        MyBike myBike4 = new MyBike(R.drawable.honda_bike5, "Honda Gold Wing GL1800", "MH26AT4648");
        MyBike myBike5 = new MyBike(R.drawable.honda_bike3, "Honda CBR300R", "CH04H4645");
        MyBike myBike6 = new MyBike(R.drawable.honda_bike4, "Honda CB1000R", "TN26AT4648");

        bikeArrayList.add(myBike1);
        bikeArrayList.add(myBike2);
        bikeArrayList.add(myBike3);
        bikeArrayList.add(myBike4);
        bikeArrayList.add(myBike5);
        bikeArrayList.add(myBike6);

        myBikeAdapter.notifyDataSetChanged();
    }


    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");

    }



    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MyBikeActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        MyBikeActivity.this.finish();
    }

    @Override
    public void onItemClick(View view, MyBike myBike) {
      if(navigationFrom!=null)
        if(navigationFrom.equalsIgnoreCase("ServiceBookingScreen")) {
            Intent intent = new Intent(MyBikeActivity.this, SBookingActivity.class);
            intent.putExtra("from","MyBikeScreen");
            intent.putExtra("model", myBike);
            Application.myBikeModelGlobal=myBike;
            startActivity(intent);
        }
    }

/*Delete Item from list on swipe*/
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT){
                    bikeArrayList.remove(position);
                    myBikeAdapter.notifyItemRemoved(position);
                    myBikeAdapter.notifyItemRangeChanged(position, bikeArrayList.size());
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                Paint p=new Paint();
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;
                    if(dX > 0){

                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_sweep_white_48dp);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }

                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
