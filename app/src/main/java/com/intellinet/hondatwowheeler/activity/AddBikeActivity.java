package com.intellinet.hondatwowheeler.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intellinet.hondatwowheeler.R;

public class AddBikeActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText regNumberEditText, vinEditText;
    TextView submitButton;
    ImageView vInfoIcon, infoIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);
        initToolBar();
        regNumberEditText=(EditText)findViewById(R.id.regNumberEditText);
        vinEditText=(EditText)findViewById(R.id.vinEditText);
        submitButton=(TextView)findViewById(R.id.submitButton);
        vInfoIcon=(ImageView)findViewById(R.id.vInfoIcon);
        infoIcon=(ImageView)findViewById(R.id.infoIcon);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regNumberEditText.getText().toString().trim().equalsIgnoreCase("")){
                    Toast.makeText(AddBikeActivity.this, "Please Enter Vehicle Registration Number", Toast.LENGTH_SHORT).show();
                }else if(vinEditText.getText().toString().trim().equalsIgnoreCase("")){
                    Toast.makeText(AddBikeActivity.this, "Please Enter Vin Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Intent intent = new Intent(AddBikeActivity.this, MyDialogActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(AddBikeActivity.this,
                                    infoIcon, "vin");
                    startActivity(intent, options.toBundle());
                }else{
                    getVInfoImage();
                }
            }
        });

    }

    private void initToolBar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }



    public void getVInfoImage(){
        Dialog dialog= new Dialog(AddBikeActivity.this,  R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddBikeActivity.this, MyBikeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                AddBikeActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
