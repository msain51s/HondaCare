package com.intellinet.hondatwowheeler.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.utility.Util;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView stripLeft, stripRight, stripSmallTop,
    stripSmallBottom, stripBottomMiddle, stripBottomLeft,
    centerLogo;

    RelativeLayout triangleLayout;
    Animation scaleAnimation, stripLeftAnim, stripRightAnim,
              stripSmallTopAnim, stripSmallBottomAnim,
              stripBottomMiddleAnim, stripBottomLeftAnim;

    int maxX, maxY;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        stripLeft=(ImageView)findViewById(R.id.stripLeft);
        centerLogo=(ImageView)findViewById(R.id.splashLogo);
        centerLogo.setVisibility(View.GONE);
        handler=new Handler();
        stripRight=(ImageView)findViewById(R.id.stripRight);
        stripSmallTop=(ImageView)findViewById(R.id.stripSmallTop);
        stripSmallBottom=(ImageView)findViewById(R.id.stripBottomSmall);
        stripBottomMiddle=(ImageView)findViewById(R.id.stripBottomMiddle);
        stripBottomLeft=(ImageView)findViewById(R.id.stripBottom);
        stripBottomLeft.setVisibility(View.INVISIBLE);
        triangleLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        triangleLayout.setVisibility(View.INVISIBLE);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        scaleAnimation.setAnimationListener(this);
        stripAnimation();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                triangleLayout.setVisibility(View.VISIBLE);
                triangleLayout.setAnimation(scaleAnimation);
            }
        },1000);

    }


    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onAnimationEnd(Animation animation) {
        centerLogo.setVisibility(View.VISIBLE);
        Animation anim= Util.expand(centerLogo);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {applyPulseAnimation();}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

    }

    @Override
    public void onAnimationRepeat(Animation animation) {}


    /*Apply Translate Animation on all Strips*/
    private void stripAnimation(){

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        maxX = mdispSize.x;
        maxY = mdispSize.y;

        stripRightAnim=applyTranslateAnimation(maxX, stripRight.getX(),
                stripRight.getY()-Util.calculateSize(400, SplashActivity.this), stripRight.getY());
        stripLeftAnim=applyTranslateAnimation(-Util.calculateSize(100, SplashActivity.this), stripLeft.getX(), stripLeft.getY(), stripLeft.getY());
        stripSmallTopAnim=applyTranslateAnimation(maxX, stripSmallTop.getX(), stripSmallTop.getY(), stripSmallTop.getY());
        stripSmallBottomAnim=applyTranslateAnimation(maxX, stripSmallBottom.getX(), stripSmallBottom.getY(), stripSmallBottom.getY());
        stripBottomMiddleAnim=applyTranslateAnimation(stripBottomMiddle.getX()+Util.calculateSize(200, SplashActivity.this), stripBottomMiddle.getX(), maxY, stripBottomMiddle.getY());

        stripRight.setAnimation(stripRightAnim);
        stripLeft.setAnimation(stripLeftAnim);
        stripSmallTop.setAnimation(stripSmallTopAnim);
        stripSmallBottom.setAnimation(stripSmallBottomAnim);
        stripBottomMiddle.setAnimation(stripBottomMiddleAnim);

        /*stripBottomLeftAnim=applyTranslateAnimation(Util.calculateSize(50, SplashActivity.this), stripBottomLeft.getX(), maxY, stripBottomLeft.getY());
        stripBottomLeft.setAnimation(stripBottomLeftAnim);*/

        stripBottomMiddleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                stripBottomLeft.setVisibility(View.VISIBLE);
                Util.expandBottomLeft(stripBottomLeft);
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

        });

    }

    /*Translate view at particular coordinates*/
    private Animation applyTranslateAnimation(float fromX, float toX, float fromY, float toY){
        /*from X, To X, from Y, To Y*/
        TranslateAnimation transAnimation= new TranslateAnimation(fromX, toX, fromY, toY);
        transAnimation.setDuration(1000);
        transAnimation.setInterpolator(new LinearInterpolator());
        return transAnimation;

    }

    /*Apply Pulse Animation on Honda Logo*/
    private void applyPulseAnimation(){

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(centerLogo,
                PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                PropertyValuesHolder.ofFloat("scaleY", 0.8f));

        scaleDown.setDuration(600);
        scaleDown.setRepeatCount(3);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.setInterpolator(new FastOutSlowInInterpolator());
        scaleDown.setStartDelay(100);
        scaleDown.start();

        scaleDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_to_center, R.anim.slide_left);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
