package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.MenuItem;
import com.intellinet.hondatwowheeler.menu.DrawerAdapter;
import com.intellinet.hondatwowheeler.menu.DrawerItem;
import com.intellinet.hondatwowheeler.menu.SimpleItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;


import java.util.Arrays;



/**
 * Created by Chandan Dwivedi on 12.07.2017.
 */

public class BaseActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    String[] screenTitles;
    private Drawable[] screenIcons;
    SlidingRootNav slidingRootNav;
    Handler handler;
    FrameLayout containerLayout;
    TextView titleText;
    RelativeLayout blurLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        handler=new Handler();
        containerLayout=(FrameLayout)findViewById(R.id.container);
        titleText=(TextView) findViewById(R.id.titleText);
        blurLayout=(RelativeLayout)findViewById(R.id.blurLayout);

        blurLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        SlidingRootNavBuilder builder=new SlidingRootNavBuilder(this);
        builder.addDragListener(new DragListener() {
            @Override
            public void onDrag(float progress) {
                if(progress>0){
                    blurLayout.setVisibility(View.VISIBLE);
                }else if(progress==0){
                    blurLayout.setVisibility(View.GONE);
                }
            }
        });
        slidingRootNav=builder.withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(MenuItem.POS_MY_BIKES).setChecked(false),
                createItemFor(MenuItem.POS_NOTIFICATION),
                createItemFor(MenuItem.POS_D_LOCATION),
                createItemFor(MenuItem.POS_FEEDBACK),
                createItemFor(MenuItem.POS_SERVICE_BOOKING),
                createItemFor(MenuItem.POS_SETTINGS)));

        adapter.setListener(this);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setNestedScrollingEnabled(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(final int position) {
        slidingRootNav.closeMenu(true);
        if (position == MenuItem.POS_MY_BIKES) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, MyBikeActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);

        } else if (position == MenuItem.POS_NOTIFICATION) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, NotificationActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);

        }else if (position == MenuItem.POS_D_LOCATION) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, DealerLocationActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);
        }else if (position == MenuItem.POS_FEEDBACK) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, FeedbackActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);
        }else if (position == MenuItem.POS_SERVICE_BOOKING) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, SBookingActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);
        }else if (position == MenuItem.POS_SETTINGS) {
                // provide some delay to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(BaseActivity.this, SettingsActivity.class);
                    intent.putExtra("ScreenName", screenTitles[position]);
                    startActivity(intent);
                }
            }, 350);
        }

    }


    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorPrimaryDark))
                .withTextTint(color(R.color.colorPrimaryDark))
                .withSelectedIconTint(color(R.color.colorPrimaryDark))
                .withSelectedTextTint(color(R.color.colorPrimaryDark));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }


}
