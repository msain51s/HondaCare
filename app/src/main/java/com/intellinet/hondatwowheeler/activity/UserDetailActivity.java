package com.intellinet.hondatwowheeler.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.utility.Util;
import com.uniquestudio.library.CircleCheckBox;

public class UserDetailActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, phoneEditText;
    TextView continueButtonText;

    String mobileNumber="";
    CircleCheckBox pCheckBox, nCheckBox, eCheckBox;
    boolean pCheck=false;
    boolean nCheck=false;
    boolean eCheck=false;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    RelativeLayout buttonLayout;
    RelativeLayout rotateLayout;
    Animation rotateAnimation;

    AnimatorSet animatorSet;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        nameEditText=(EditText)findViewById(R.id.nameEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        phoneEditText=(EditText)findViewById(R.id.phEditText);
        buttonLayout=(RelativeLayout) findViewById(R.id.buttonLayout);
        rotateLayout=(RelativeLayout) findViewById(R.id.rotateLayout);
        continueButtonText=(TextView)findViewById(R.id.continueButtonText);
        pCheckBox=(CircleCheckBox)findViewById(R.id.circle_check_box_phone);
        nCheckBox=(CircleCheckBox)findViewById(R.id.circle_check_box_name);
        eCheckBox=(CircleCheckBox)findViewById(R.id.circle_check_box_email);
        handler=new Handler();
        rotateAnimation= AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        setCustomFonts();
        getIntentData();

        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pCheckBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==10){
                    pCheckBox.setChecked(true);
                    pCheck=true;
                }else if(pCheck==true && s.length()==9) {
                    pCheckBox.setChecked(false);
                    pCheck=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nCheckBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(nCheck==false && s.length()==1){
                    nCheckBox.setChecked(true);
                    nCheck=true;
                }else if(nCheck==true && s.length()==0) {
                    nCheckBox.setChecked(false);
                    nCheck=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                eCheckBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(eCheck==false && s.toString().matches(emailPattern)){
                    eCheckBox.setChecked(true);
                    eCheck=true;
                }else if(eCheck==true && !s.toString().matches(emailPattern)) {
                    eCheckBox.setChecked(false);
                    eCheck=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber=phoneEditText.getText().toString().trim();
                if(mobileNumber.length()!=10){
                    Toast.makeText(UserDetailActivity.this, "Please Enter Registered Mobile Number", Toast.LENGTH_SHORT).show();
                }else if(!emailEditText.getText().toString().trim().matches(emailPattern)){
                     Toast.makeText(UserDetailActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                }else if(nameEditText.getText().toString().trim().length()<=0){
                    Toast.makeText(UserDetailActivity.this, "Please Enter Valid Name", Toast.LENGTH_SHORT).show();
                } else{
                    initNetworkOps();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserDetailActivity.this, OTPVerifyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("from", "UserScreen");
        startActivity(intent);
        UserDetailActivity.this.finish();
        overridePendingTransition(R.anim.slide_left_to_center, R.anim.slide_right);
    }


    private void setCustomFonts(){
        continueButtonText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        nameEditText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        emailEditText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        phoneEditText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
    }


    private void getIntentData(){
        Intent intent=getIntent();
        mobileNumber=intent.getStringExtra("mobileNumber");
        if(mobileNumber!=null && mobileNumber.length()==10){
            pCheckBox.setVisibility(View.VISIBLE);
            pCheckBox.setChecked(true);
            pCheck=true;
            phoneEditText.setText(mobileNumber);
        }
    }

    /*Initialize network operation*/
    private void initNetworkOps(){
        animatorSet=Util.reduceSize(UserDetailActivity.this, buttonLayout);
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

    /*Navigate to Home Activity with ripple effect*/
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
                Util.regainSize(UserDetailActivity.this, buttonLayout);
                buttonLayout.setVisibility(View.VISIBLE);
                Intent intent = new Intent(UserDetailActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("mobileNumber", mobileNumber);
                startActivity(intent);
                UserDetailActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}

        });
    }

}
