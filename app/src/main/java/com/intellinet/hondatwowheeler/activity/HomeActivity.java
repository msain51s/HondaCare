package com.intellinet.hondatwowheeler.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.callback.MenuItem;
import com.intellinet.hondatwowheeler.utility.Util;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class HomeActivity extends BaseActivity {

    RelativeLayout circleLayout, rootLayout;
    int maxX, maxY;
    float radius;
    float xCord;
    float yCord;
    RelativeLayout.LayoutParams lp;
    int leftMargin;
    TextView powerBy;
    Handler handler;
    RelativeLayout  myBikeMenu, notificationMenu, dLocationMenu,
                    feedbackMenu, sBookingMenu, settingMenu;

    RelativeLayout myBikeView, notificationView, dlView, fbView, sbView, stView;

    TextView myBikeText, notificationText, dText, lText, feedbackText,
             sBookingText, settingText;

    ImageView myBikeImage, notificationImage, dlImage, feedbackImage,
              sBookingImage, settingImage;

    Animation mbFadeInAnimation, nFadeInAnimation, dlFadeInAnimation,
              fbFadeInAnimation, sbFadeInAnimation, stFadeInAnimation;

    private PathAnimation myBikePathAnimation, nPathAnimation, dlPathAnimation,
            fPathAnimation, sBPathAnimation, settingPathAnimation;

    private Path  myBikePath, notificationPath, dlPath, feedbackPath, sBookingPath,
            settingPath;

    final int delay=200;

    boolean willDraw=false;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, containerLayout);
        circleLayout=(RelativeLayout)findViewById(R.id.roundCircle);
        rootLayout=(RelativeLayout)findViewById(R.id.rootLayout);
        powerBy=(TextView)findViewById(R.id.poweredByText);

        myBikeMenu = (RelativeLayout)findViewById(R.id.myBikeMenu);
        notificationMenu = (RelativeLayout)findViewById(R.id.notificationMenu);
        dLocationMenu = (RelativeLayout)findViewById(R.id.dLocationMenu);
        feedbackMenu = (RelativeLayout)findViewById(R.id.feedbackMenu);
        sBookingMenu = (RelativeLayout)findViewById(R.id.sBookingMenu);
        settingMenu = (RelativeLayout)findViewById(R.id.settingMenu);

        myBikeText = (TextView)findViewById(R.id.myBikeText);
        notificationText = (TextView) findViewById(R.id.notificationText);
        dText =(TextView)findViewById(R.id.dText);
        lText =(TextView)findViewById(R.id.lText);
        feedbackText =(TextView)findViewById(R.id.feedbackText);
        sBookingText =(TextView)findViewById(R.id.sBookingText);
        settingText =(TextView)findViewById(R.id.settingsText);
        setCustomFonts();

        myBikeImage = (ImageView)findViewById(R.id.myBikeImage);
        notificationImage = (ImageView)findViewById(R.id.notificationImage);
        dlImage = (ImageView)findViewById(R.id.dlImage);
        feedbackImage = (ImageView)findViewById(R.id.feedbackImage);
        sBookingImage = (ImageView)findViewById(R.id.sBookingImage);
        settingImage = (ImageView)findViewById(R.id.settingImage);

        myBikeView = (RelativeLayout)findViewById(R.id.myBikeView);
        notificationView = (RelativeLayout)findViewById(R.id.notificationView);
        dlView = (RelativeLayout)findViewById(R.id.dlView);
        fbView = (RelativeLayout)findViewById(R.id.fbView);
        sbView = (RelativeLayout)findViewById(R.id.sbView);
        stView = (RelativeLayout)findViewById(R.id.stView);


        mbFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);
        nFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);
        dlFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);
        fbFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);
        sbFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);
        stFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_anim);

        handler=new Handler();

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        maxX = mdispSize.x;
        maxY = mdispSize.y;

        yCord = maxY/2 - getResources().getDimension(R.dimen.y_margin);

        lp= (RelativeLayout.LayoutParams) circleLayout.getLayoutParams();
        radius = lp.width/2;
        leftMargin=lp.leftMargin;
        xCord = radius - (-leftMargin);

        circleMenuNavigation();
        slidingRootNav.setMenuLocked(true);
        rootLayout.addView(new MenuCircle(HomeActivity.this));

    }

    /*Navigation on Circle Menu*/
    private void circleMenuNavigation(){

        myBikeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, MyBikeActivity.class);
                        intent.putExtra("ScreenName", screenTitles[MenuItem.POS_MY_BIKES]);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }, 600);

            }
        });

        notificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                        intent.putExtra("ScreenName", screenTitles[MenuItem.POS_NOTIFICATION]);
                        startActivity(intent);
                    }
                }, 600);
            }
        });


        dlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, DealerLocationActivity.class);
                        intent.putExtra("ScreenName", screenTitles[MenuItem.POS_D_LOCATION]);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }, 600);
            }
        });

        fbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
                        intent.putExtra("ScreenName", screenTitles[MenuItem.POS_FEEDBACK]);
                        startActivity(intent);
                    }
                }, 600);
            }
        });

        sbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, SBookingActivity.class);
                        intent.putExtra("ScreenName", screenTitles[MenuItem.POS_SERVICE_BOOKING]);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }, 600);
            }
        });

        stView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                        intent.putExtra("ScreenName",  screenTitles[MenuItem.POS_SETTINGS]);
                        startActivity(intent);
                    }
                }, 600);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*Set Custom Font in circle menu text*/
    private void setCustomFonts(){
        myBikeText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        notificationText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        dText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        lText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        feedbackText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        sBookingText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        settingText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
    }


    /*Draw Path to set menu on the circle*/
    public class MenuCircle extends View {

        private Paint paint, paint1, paint2, paint3, paint4, paint5, paint6;
        private RectF rect;

        public MenuCircle(Context context) {
            super(context);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(4.5f);

            paint1=new Paint();
            paint1.setColor(Color.GREEN);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setAntiAlias(true);
            paint1.setStrokeWidth(4.5f);

            paint2=new Paint();
            paint2.setColor(Color.BLUE);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setAntiAlias(true);
            paint2.setStrokeWidth(4.5f);

            paint3=new Paint();
            paint3.setColor(Color.YELLOW);
            paint3.setStyle(Paint.Style.STROKE);
            paint3.setAntiAlias(true);
            paint3.setStrokeWidth(4.5f);

            paint4=new Paint();
            paint4.setColor(Color.BLACK);
            paint4.setStyle(Paint.Style.STROKE);
            paint4.setAntiAlias(true);
            paint4.setStrokeWidth(4.5f);

            paint5=new Paint();
            paint5.setColor(Color.MAGENTA);
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setAntiAlias(true);
            paint5.setStrokeWidth(4.5f);

            paint6=new Paint();
            paint6.setColor(Color.GRAY);
            paint6.setStyle(Paint.Style.STROKE);
            paint6.setAntiAlias(true);
            paint6.setStrokeWidth(4.5f);

            rect = new RectF();

            myBikePath = new Path();
            notificationPath = new Path();
            dlPath = new Path();
            feedbackPath = new Path();
            sBookingPath = new Path();
            settingPath = new Path();
            willDraw=true;
        }



        @Override
        protected void onDraw(Canvas canvas) {
         // canvas.drawCircle(xCord, yCord, radius, paint);
            rect.set(xCord - radius, yCord-radius, xCord+radius, yCord +radius);

          /*  canvas.drawArc(rect, 180, -270, false, paint1);
            canvas.drawArc(rect, 180, -234, false, paint2);
            canvas.drawArc(rect, 180, -198, false, paint3);
            canvas.drawArc(rect, 180, -162, false, paint4);
            canvas.drawArc(rect, 180, -126, false, paint5);
            canvas.drawArc(rect, 180, -90,  false, paint6);*/

            myBikePath.addArc(rect, 140, -230);
            notificationPath.addArc(rect, 140, -194);
            dlPath.addArc(rect, 140, -158);
            feedbackPath.addArc(rect, 140, -122);
            sBookingPath.addArc(rect, 140, -86);
            settingPath.addArc(rect, 140, -50);

            if(willDraw){
                circleMenuAnimation();
                willDraw=false;
            }
        }
    }


/*Animate Half Circular Menu*/
    public void circleMenuAnimation(){

        myBikePathAnimation = new PathAnimation(myBikePath);
        myBikePathAnimation.setDuration(1800);

        myBikeMenu.setVisibility(View.VISIBLE);
        mbFadeInAnimation.setDuration(425);

        AnimationSet mbAnimation = new AnimationSet(false);
        mbAnimation.setFillAfter(true);
        mbAnimation.setFillEnabled(true);
        mbAnimation.addAnimation(mbFadeInAnimation);
        mbAnimation.addAnimation(myBikePathAnimation);
        myBikeMenu.setAnimation(mbAnimation);
     // audioPlayer();
        mbAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nPathAnimation = new PathAnimation(notificationPath);
                        nPathAnimation.setDuration(1600);

                        notificationMenu.setVisibility(View.VISIBLE);
                        nFadeInAnimation.setDuration(400);

                        AnimationSet nAnimation = new AnimationSet(false);
                        nAnimation.setFillAfter(true);
                        nAnimation.setFillEnabled(true);
                        nAnimation.addAnimation(nFadeInAnimation);
                        nAnimation.addAnimation(nPathAnimation);
                        notificationMenu.setAnimation(nAnimation);

                       nAnimation.setAnimationListener(new Animation.AnimationListener() {
                          @Override
                          public void onAnimationStart(Animation animation) {
                              new Handler().postDelayed(new Runnable() {
                                  @Override
                                  public void run() {
                                      dlPathAnimation = new PathAnimation(dlPath);
                                      dlPathAnimation.setDuration(1400);

                                      dLocationMenu.setVisibility(View.VISIBLE);
                                      dlFadeInAnimation.setDuration(350);

                                      AnimationSet dlAnimation = new AnimationSet(false);
                                      dlAnimation.setFillAfter(true);
                                      dlAnimation.setFillEnabled(true);
                                      dlAnimation.addAnimation(dlFadeInAnimation);
                                      dlAnimation.addAnimation(dlPathAnimation);
                                      dLocationMenu.setAnimation(dlAnimation);
                                      dlAnimation.setAnimationListener(new Animation.AnimationListener() {
                                          @Override
                                          public void onAnimationStart(Animation animation) {
                                              new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      fPathAnimation = new PathAnimation(feedbackPath);
                                                      fPathAnimation.setDuration(1200);
                                                      feedbackMenu.setVisibility(View.VISIBLE);
                                                      fbFadeInAnimation.setDuration(300);
                                                      AnimationSet fbAnimation = new AnimationSet(false);
                                                      fbAnimation.setFillAfter(true);
                                                      fbAnimation.setFillEnabled(true);
                                                      fbAnimation.addAnimation(fbFadeInAnimation);
                                                      fbAnimation.addAnimation(fPathAnimation);
                                                      feedbackMenu.setAnimation(fbAnimation);
                                                      fbAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                          @Override
                                                          public void onAnimationStart(Animation animation) {
                                                             new Handler().postDelayed(new Runnable() {
                                                                 @Override
                                                                 public void run() {
                                                                     sBPathAnimation = new PathAnimation(sBookingPath);
                                                                     sBPathAnimation.setDuration(1000);
                                                                     sBookingMenu.setVisibility(View.VISIBLE);
                                                                     sbFadeInAnimation.setDuration(250);
                                                                     AnimationSet sbAnimation = new AnimationSet(false);
                                                                     sbAnimation.setFillAfter(true);
                                                                     sbAnimation.setFillEnabled(true);
                                                                     sbAnimation.addAnimation(sbFadeInAnimation);
                                                                     sbAnimation.addAnimation(sBPathAnimation);
                                                                     sBookingMenu.setAnimation(sbAnimation);
                                                                     sbAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                                         @Override
                                                                         public void onAnimationStart(Animation animation) {
                                                                             new Handler().postDelayed(new Runnable() {
                                                                                 @Override
                                                                                 public void run() {
                                                                                     settingPathAnimation = new PathAnimation(settingPath);
                                                                                     settingPathAnimation.setDuration(800);
                                                                                     settingMenu.setVisibility(View.VISIBLE);
                                                                                     stFadeInAnimation.setDuration(200);
                                                                                     AnimationSet settingAnimation = new AnimationSet(false);
                                                                                     settingAnimation.setFillAfter(true);
                                                                                     settingAnimation.setFillEnabled(true);
                                                                                     settingAnimation.addAnimation(stFadeInAnimation);
                                                                                     settingAnimation.addAnimation(settingPathAnimation);
                                                                                     settingMenu.setAnimation(settingAnimation);
                                                                                     settingAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                                                         @Override
                                                                                         public void onAnimationStart(Animation animation) {}

                                                                                         @Override
                                                                                         public void onAnimationEnd(Animation animation) {
                                                                                             new Handler().postDelayed(new Runnable() {
                                                                                                 @Override
                                                                                                 public void run() {
                                                                                                     float perimiter_X_coord = (float) (xCord + (radius * cos(90*(Math.PI / 180))));
                                                                                                     float perimiter_Y_coord = (float) (yCord + (radius * sin(90*(Math.PI / 180))));
                                                                                                     stView.setX(perimiter_X_coord - ((float)(settingImage.getWidth()/2.5)));
                                                                                                     stView.setY(perimiter_Y_coord - ((stView.getHeight()/2)+((int)(stView.getHeight()/2.4))));
                                                                                                 }
                                                                                             }, 100);

                                                                                         }

                                                                                         @Override
                                                                                         public void onAnimationRepeat(Animation animation) {}
                                                                                     });

                                                                                 }
                                                                             },delay);
                                                                         }

                                                                         @Override
                                                                         public void onAnimationEnd(Animation animation) {
                                                                             new Handler().postDelayed(new Runnable() {
                                                                                 @Override
                                                                                 public void run() {
                                                                                     float perimiter_X_coord = (float) (xCord + (radius * cos(54*(Math.PI / 180))));
                                                                                     float perimiter_Y_coord = (float) (yCord + (radius * sin(54*(Math.PI / 180))));
                                                                                     sbView.setX(perimiter_X_coord - ((float)(sBookingImage.getWidth()/2.5)));
                                                                                     sbView.setY(perimiter_Y_coord - ((sbView.getHeight()/2)+((int)(sbView.getHeight()/2.4))));
                                                                                 }
                                                                             }, 100);

                                                                         }

                                                                         @Override
                                                                         public void onAnimationRepeat(Animation animation) {}
                                                                     });
                                                                 }
                                                             },delay);
                                                          }

                                                          @Override
                                                          public void onAnimationEnd(Animation animation) {
                                                              new Handler().postDelayed(new Runnable() {
                                                                  @Override
                                                                  public void run() {
                                                                      float perimiter_X_coord = (float) (xCord + (radius * cos(18*(Math.PI / 180))));
                                                                      float perimiter_Y_coord = (float) (yCord + (radius * sin(18*(Math.PI / 180))));
                                                                      fbView.setX(perimiter_X_coord - ((float)(feedbackImage.getWidth()/2.5)));
                                                                      fbView.setY(perimiter_Y_coord - ((fbView.getHeight()/2)+((int)(fbView.getHeight()/2.4))));
                                                                  }
                                                              }, 100);

                                                          }

                                                          @Override
                                                          public void onAnimationRepeat(Animation animation) {}
                                                      });
                                                  }
                                              },delay);
                                          }

                                          @Override
                                          public void onAnimationEnd(Animation animation) {
                                              new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  float perimiter_X_coord = (float) (xCord + (radius * cos(342*(Math.PI / 180))));
                                                  float perimiter_Y_coord = (float) (yCord + (radius * sin(342*(Math.PI / 180))));
                                                  dlView.setX(perimiter_X_coord - ((float)(dlImage.getWidth()/2.5)));
                                                  dlView.setY(perimiter_Y_coord - ((dlView.getHeight()/2)+((int)(dlView.getHeight()/2.4))));
                                              }
                                          }, 100);
                                      }

                                          @Override
                                          public void onAnimationRepeat(Animation animation) {}
                                      });
                                  }
                              },delay);
                          }

                          @Override
                          public void onAnimationEnd(Animation animation) {
                              new Handler().postDelayed(new Runnable() {
                                  @Override
                                  public void run() {
                                      float perimiter_X_coord = (float) (xCord + (radius * cos(306*(Math.PI / 180))));
                                      float perimiter_Y_coord = (float) (yCord + (radius * sin(306*(Math.PI / 180))));
                                      notificationView.setX(perimiter_X_coord - ((float)(notificationImage.getWidth()/2.5)));
                                      notificationView.setY(perimiter_Y_coord - ((notificationView.getHeight()/2)+((int)(notificationView.getHeight()/2.4))));
                                  }
                              }, 100);
                          }

                          @Override
                          public void onAnimationRepeat(Animation animation) {}
                      });

                    }
                }, delay);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
             //    mediaPlayer.stop();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        slidingRootNav.setMenuLocked(false);
                        float perimiter_X_coord = (float) (xCord + (radius * cos(270*(Math.PI / 180))));
                        float perimiter_Y_coord = (float) (yCord + (radius * sin(270*(Math.PI / 180))));
                        myBikeView.setX(perimiter_X_coord - ((float)(myBikeImage.getWidth()/2.5)));
                        myBikeView.setY(perimiter_Y_coord - ((myBikeView.getHeight()/2)+((int)(myBikeView.getHeight()/2.4))));
                    }
                }, 100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

    }


    public class PathAnimation extends Animation {
        private PathMeasure measure;
        private float[] pos = new float[2];

        public PathAnimation(Path path) {
            measure = new PathMeasure(path, false);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t){
            measure.getPosTan(measure.getLength() * interpolatedTime, pos,null);
            t.getMatrix().setTranslate(pos[0]+xCord, pos[1]-yCord);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public MediaPlayer audioPlayer(){
        //set up MediaPlayer
      mediaPlayer = MediaPlayer.create(HomeActivity.this, R.raw.sound);

        try {
            mediaPlayer.start();
            return mediaPlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
