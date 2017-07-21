package com.intellinet.hondatwowheeler.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
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

public class RegisterActivity extends AppCompatActivity {

    RelativeLayout buttonLayout;
    RelativeLayout rotateLayout;
    EditText mobileEditText;
    TextView sendOtpButtonText;
    Handler handler;
    CircleCheckBox checkBox;
    boolean check=false;
    Animation rotateAnimation;

    String mobileNumber="";
    AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonLayout=(RelativeLayout) findViewById(R.id.buttonLayout);
        rotateLayout=(RelativeLayout) findViewById(R.id.rotateLayout);
        mobileEditText=(EditText)findViewById(R.id.mobileEditText);
        sendOtpButtonText=(TextView)findViewById(R.id.sendOtpButton);
        mobileEditText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        sendOtpButtonText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        handler=new Handler();
        rotateAnimation= AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        checkBox=(CircleCheckBox)findViewById(R.id.circle_check_box);
        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber=mobileEditText.getText().toString().trim();
                if(mobileNumber.length()==10){
                    initNetworkOps();
                }else{
                    Toast.makeText(RegisterActivity.this, "Please Enter Registered Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==10){
                    checkBox.setChecked(true);
                    check=true;
                }else if(check==true && s.length()==9) {
                    checkBox.setChecked(false);
                    check=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_to_center, R.anim.slide_right);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String screen=intent.getStringExtra("from");
        if(screen!=null && screen.equalsIgnoreCase("OTPScreen")){
            findViewById(R.id.colorLayout).setVisibility(View.INVISIBLE);
        }
    }


    /*Initialize network operation*/
    private void initNetworkOps(){
        animatorSet=Util.reduceSize(RegisterActivity.this, buttonLayout);
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

/*Navigate to OTP Verification Activity with ripple effect*/
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
              Util.regainSize(RegisterActivity.this, buttonLayout);
              buttonLayout.setVisibility(View.VISIBLE);
              Intent intent = new Intent(RegisterActivity.this, OTPVerifyActivity.class);
              intent.putExtra("mobileNumber", mobileNumber);
              startActivity(intent);
          }

          @Override
          public void onAnimationCancel(Animator animation) {}
          @Override
          public void onAnimationRepeat(Animator animation) {}

      });
  }

}
