package com.intellinet.hondatwowheeler.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.utility.Util;
import com.uniquestudio.library.CircleCheckBox;

public class OTPVerifyActivity extends AppCompatActivity {

    RelativeLayout buttonLayout, rotateLayout;
    TextView otpText1, otpText2, verifyButtonText,
             regenText, termText, acceptText;
    EditText otpEditText;
    CircleCheckBox checkBox;
    boolean check=false;
    String mobileNumber="";
    Animation rotateAnimation;
    AnimatorSet animatorSet;

    Handler handler;
    ImageView termCheckImage;
    boolean termCheck=false;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);
        initToolBar();
        buttonLayout=(RelativeLayout)findViewById(R.id.buttonLayout);
        rotateLayout=(RelativeLayout) findViewById(R.id.rotateLayout);
        otpText1=(TextView)findViewById(R.id.otpText1);
        otpText2=(TextView)findViewById(R.id.otpText2);
        verifyButtonText=(TextView)findViewById(R.id.verifyOtpButton);
        regenText=(TextView)findViewById(R.id.regenText);
        termText=(TextView)findViewById(R.id.termText);
        acceptText=(TextView)findViewById(R.id.acceptText);
        otpEditText=(EditText)findViewById(R.id.otpEditText);
        checkBox=(CircleCheckBox)findViewById(R.id.circle_check_box);
        termCheckImage=(ImageView)findViewById(R.id.termImage);
        rotateAnimation= AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        handler=new Handler();
        getIntentData();
        setCustomFonts();
        termText.setPaintFlags(termText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=otpEditText.getText().toString().trim();
                if(!termCheck){
                    Toast.makeText(OTPVerifyActivity.this, "Please Accept Term & Condition", Toast.LENGTH_SHORT).show();
                }else if(otp.length()==4){
                    initNetworkOps();
                }else{
                    Toast.makeText(OTPVerifyActivity.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==4){
                    checkBox.setChecked(true);
                    check=true;
                }else if(check==true && s.length()==3) {
                    checkBox.setChecked(false);
                    check=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        termCheckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(termCheck==false){
                    termCheckImage.setImageDrawable(getResources().getDrawable(R.drawable.check_box));
                    termCheck=true;
                }else if(termCheck==true){
                    termCheckImage.setImageDrawable(getResources().getDrawable(R.drawable.uncheck_box));
                    termCheck=false;
                }
            }
        });

    }


    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void getIntentData(){
        Intent intent=getIntent();
        mobileNumber=intent.getStringExtra("mobileNumber");
        otpText2.setText("Mob No.- "+mobileNumber);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String screen=intent.getStringExtra("from");
        if(screen!=null && screen.equalsIgnoreCase("UserScreen")){
            findViewById(R.id.colorLayout).setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OTPVerifyActivity.this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("from", "OTPScreen");
        startActivity(intent);
        OTPVerifyActivity.this.finish();
        overridePendingTransition(R.anim.slide_left_to_center, R.anim.slide_right);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(OTPVerifyActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("from", "OTPScreen");
                startActivity(intent);
                OTPVerifyActivity.this.finish();
                overridePendingTransition(R.anim.slide_left_to_center, R.anim.slide_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setCustomFonts(){
        otpText1.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        otpText2.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        verifyButtonText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        regenText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        termText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        otpEditText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
    }


    /*Initialize network operation*/
    private void initNetworkOps(){
        animatorSet=Util.reduceSize(OTPVerifyActivity.this, buttonLayout);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                buttonLayout.setVisibility(View.INVISIBLE);
                rotateLayout.setVisibility(View.VISIBLE);
                rotateLayout.setAnimation(rotateAnimation);
                handler.postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        navigateWithRipple();
                    }
                },600);
            }
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

    }

    /*Navigate to UserDetail Activity with ripple effect*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void navigateWithRipple(){
        View view = findViewById(R.id.colorLayout);
        Animator rippleAnimator =Util.rippleEffect(view);

        rippleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                rotateLayout.clearAnimation();
                rotateLayout.setVisibility(View.INVISIBLE);
                Util.regainSize(OTPVerifyActivity.this, buttonLayout);
                buttonLayout.setVisibility(View.VISIBLE);
                Intent intent = new Intent(OTPVerifyActivity.this, UserDetailActivity.class);
                intent.putExtra("mobileNumber", mobileNumber);
                startActivity(intent);
                OTPVerifyActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}

        });
    }

}
