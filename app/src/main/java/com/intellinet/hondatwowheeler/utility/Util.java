package com.intellinet.hondatwowheeler.utility;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.activity.HomeActivity;
import com.intellinet.hondatwowheeler.activity.SBookingActivity;
import com.intellinet.hondatwowheeler.adapter.DialogListAdapter;
import com.intellinet.hondatwowheeler.callback.AnimationType;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.callback.MenuItem;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Chandan Dwivedi on 15.06.2017.
 */

public class Util {

    public static Animation expand(final View v) {
        v.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? RelativeLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density)*4);
        v.startAnimation(a);
        return  a;
    }


    public static Animation expandBottomLeft(final View v) {
        v.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? RelativeLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(1200);
        v.startAnimation(a);
        return  a;
    }


    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density)*4);
        v.startAnimation(a);
    }


    public static float calculateSize(float size, Context context){
        size=(size * context.getResources().getDisplayMetrics().density);
        return size;
    }


    public static Typeface getCustomFont(int type){
        Typeface typeface=null;
        if(type== FontType.ROBOTO_LIGHT)
            typeface=Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Light.ttf");
        else if(type==FontType.ROBOTO_THIN_ITALIC)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-ThinItalic.ttf");
        else if(type==FontType.ROBOTO_THIN)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Thin.ttf");
        else if(type==FontType.ROBOTO_MEDIUM)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Medium.ttf");
        else if(type==FontType.ROBOTO_CONDENSED)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Condensed.ttf");
        else if(type==FontType.ROBOTO_BOLD)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Bold.ttf");
        else if(type==FontType.ROBOTO_REGULAR)
            typeface= Typeface.createFromAsset(Application.mContext.getAssets(), "fonts/Roboto-Regular.ttf");

        return typeface;
    }


    public static AnimatorSet reduceSize(Context context, View view){
        AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.reduce_size);
        reducer.setTarget(view);
        reducer.start();
        return  reducer;
    }

    public static AnimatorSet regainSize(Context context, View view){
        AnimatorSet regainer = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.regain_size);
        regainer.setTarget(view);
        regainer.start();
        return  regainer;
    }


    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /*method to convert pixel into dip*/
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    /*method to convert sp into pixel*/
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Animator rippleEffect(View view){

        View myView = view;

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

// get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        //Interpolator for giving effect to animation
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        // Duration of the animation
        anim.setDuration(600);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();

        return anim;

    }


    /*view animation*/

    public static  void setAnimation(Context context, final View view, int type){
        Animation animation_type=null;

        if(type==AnimationType.SLIDE_UP) {
            animation_type = AnimationUtils.loadAnimation(context,
                    R.anim.slide_up);
            animation_type.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    return;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(animation_type);

        }
        else if(type== AnimationType.SLIDE_DOWN){
            animation_type = AnimationUtils.loadAnimation(context,
                    R.anim.slide_down);
        }
        view.startAnimation(animation_type);
        view.setVisibility(View.VISIBLE);
    }

    public static void showListDialog(Context context, final TextView selectedText){

        final ArrayList<String> list=new ArrayList<>();
        list.add("Free One");
        list.add("Free Two");
        list.add("Free Three");
        list.add("Free Four");
        list.add("Free Five");
        list.add("Paid");

        View title_view= LayoutInflater.from(context).inflate(R.layout.custom_title_view, null);
        TextView title_txt=(TextView) title_view.findViewById(R.id.custom_title_txt);
        title_txt.setText("Select Service Type");

        View custom_view= LayoutInflater.from(context).inflate(R.layout.dialog_list_prompt, null);
        ListView listView= (ListView) custom_view.findViewById(R.id.dialog_listview);
        DialogListAdapter adapter=new DialogListAdapter(context,list,selectedText);
        listView.setAdapter(adapter);



        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setCustomTitle(title_view);
        builder.setView(custom_view);

        final AlertDialog dialog=builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedText.setText(list.get(position));
                Application.selectedServiceTypeGlobal=list.get(position);
                dialog.dismiss();
            }
        });

    }

    public static void showSuccessDialog(final Activity context, String msg)
    {
        SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Success");
        dialog.setContentText(msg);
        dialog.setConfirmText("Home");
        dialog.setCancelText("Continue");
        dialog.setCancelable(false);
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();

                Application.problemDescriptionGlobal=null;
                Application.bikeSelectionFlag=false;
                Application.selectedServiceDateAndTimeGlobal=null;
                Application.dealerSelectionFlag=false;
                Application.dealerModelGlobal=null;
                Application.myBikeModelGlobal=null;
                Application.selectedServiceTypeGlobal=null;
context.finish();
                Intent intent=new Intent(context, SBookingActivity.class);
                intent.putExtra("ScreenName", "Service Booking");
                context.startActivity(intent);
            }
        });
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
                Intent intent=new Intent(context, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        })
                .show();

    }

}
