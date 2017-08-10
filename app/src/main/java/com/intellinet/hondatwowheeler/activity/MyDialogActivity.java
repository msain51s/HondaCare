package com.intellinet.hondatwowheeler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.intellinet.hondatwowheeler.R;

public class MyDialogActivity extends AppCompatActivity {

    CardView vinCard;
    RelativeLayout vinLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        vinCard=(CardView)findViewById(R.id.vinCard);
        vinLayout=(RelativeLayout)findViewById(R.id.vinLayout);

        vinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        vinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });

    }
}
