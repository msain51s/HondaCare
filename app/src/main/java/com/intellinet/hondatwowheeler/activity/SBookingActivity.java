package com.intellinet.hondatwowheeler.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.callback.MenuItem;
import com.intellinet.hondatwowheeler.model.DealerModel;
import com.intellinet.hondatwowheeler.model.MyBike;
import com.intellinet.hondatwowheeler.utility.Application;
import com.intellinet.hondatwowheeler.utility.Util;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SBookingActivity extends BaseActivity implements View.OnClickListener,TextWatcher{

    String activityName;
    View select_bike_layout,bike_selected_layout,
            select_dealer_layout,dealer_selected_layout,
            select_date_and_time_layout,
            select_service_type_layout,
            buttonLayout,rotateLayout;
    TextView select_bike_text,bikeModelName,bikeRegistrationNo,
            select_dealer_text,dealerNameText,dealerAddressText,dealerContactNoText,dealerEmailAddressText,
            select_booking_date_and_time_text,
            select_service_type_text;
    ImageView bikeImageView,editBikeSelection,editDealerSelection;
    EditText problem_description_editText;
    MyBike dataModel;
    DealerModel dealerDataModel;
    String navigationFrom;

    Typeface roboto_regular,roboto_light;
    Animation rotateAnimation;
    AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_sbooking, containerLayout);
        getIntentData();
        getSupportActionBar().setTitle(activityName);
        serviceHistoryIcon.setVisibility(View.VISIBLE);
        titleText.setVisibility(View.GONE);
        roboto_regular= Util.getCustomFont(FontType.ROBOTO_REGULAR);
        roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);
        rotateAnimation= AnimationUtils.loadAnimation(this, R.anim.rotate_anim);

        /*view initialization*/
        select_bike_layout=findViewById(R.id.select_bike_layout);
        bike_selected_layout=findViewById(R.id.bike_selected_layout);
        select_bike_text= (TextView) findViewById(R.id.select_bike_text);
        bikeImageView= (ImageView) findViewById(R.id.bikeImage);
        bikeModelName= (TextView) findViewById(R.id.bikeModelName);
        bikeRegistrationNo= (TextView) findViewById(R.id.bikeRegNumber);
        select_dealer_layout=findViewById(R.id.select_dealer_layout);
        dealer_selected_layout=findViewById(R.id.dealer_selected_layout);
        select_dealer_text= (TextView) findViewById(R.id.select_dealer_text);
        dealerNameText= (TextView) findViewById(R.id.dealerNameTextview);
        dealerAddressText= (TextView) findViewById(R.id.dealerAddressTextview);
        dealerContactNoText= (TextView) findViewById(R.id.dealerContactNoTextview);
        dealerEmailAddressText= (TextView) findViewById(R.id.dealerEmailAddressTextview);
        select_date_and_time_layout=findViewById(R.id.select_date_and_time_layout);
        select_booking_date_and_time_text= (TextView) findViewById(R.id.select_booking_date_and_time_text);
        select_service_type_layout=findViewById(R.id.select_service_type_layout);
        select_service_type_text= (TextView) findViewById(R.id.select_service_type_text);
        problem_description_editText= (EditText) findViewById(R.id.problem_description_editText);
        editBikeSelection= (ImageView) findViewById(R.id.editBikeSelection);
        editDealerSelection= (ImageView) findViewById(R.id.editDealerSelection);
        buttonLayout= findViewById(R.id.buttonLayout);
        rotateLayout= findViewById(R.id.rotateLayout);

        select_bike_text.setTypeface(roboto_regular);
        select_dealer_text.setTypeface(roboto_regular);
        bikeModelName.setTypeface(roboto_regular);
        bikeRegistrationNo.setTypeface(roboto_light);
        dealerNameText.setTypeface(roboto_regular);
        dealerAddressText.setTypeface(roboto_light);
        dealerContactNoText.setTypeface(roboto_light);
        dealerEmailAddressText.setTypeface(roboto_light);
        select_booking_date_and_time_text.setTypeface(roboto_regular);
        select_service_type_text.setTypeface(roboto_regular);
        problem_description_editText.setTypeface(roboto_regular);

   /*SETTING EVENT LISTENER */
        select_bike_layout.setOnClickListener(this);
        select_dealer_layout.setOnClickListener(this);
        editBikeSelection.setOnClickListener(this);
        editDealerSelection.setOnClickListener(this);
        select_date_and_time_layout.setOnClickListener(this);
        select_service_type_layout.setOnClickListener(this);
        problem_description_editText.addTextChangedListener(this);
        buttonLayout.setOnClickListener(this);
        serviceHistoryIcon.setOnClickListener(this);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            navigationFrom=bundle.getString("from");
            if(navigationFrom!=null)
                if(navigationFrom.equalsIgnoreCase("MyBikeScreen")) {
                    dataModel = (MyBike) bundle.get("model");
                    if (dataModel != null) {
                        Application.bikeSelectionFlag=true;
                        bikeSelectionDataFilling();
                    }
            }else if(navigationFrom.equalsIgnoreCase("DealerLocationScreen")){
                    dealerDataModel= (DealerModel) bundle.get("model");
                    if(dealerDataModel!=null){
                        Application.dealerSelectionFlag=true;
                        dealerSelectionDataFilling();
                    }
                }
        }

  /*SAVING THE STATE OF THE ACTIVITY AND SETTING THE SELECTED DATA*/

     if(Application.bikeSelectionFlag){
         bikeSelectionDataFilling();
     }
     if(Application.dealerSelectionFlag){
         dealerSelectionDataFilling();
         select_booking_date_and_time_text.setText(Application.selectedServiceDateAndTimeGlobal);
         select_service_type_text.setText(Application.selectedServiceTypeGlobal);
         problem_description_editText.setText(Application.problemDescriptionGlobal);
     }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent!=null){
            navigationFrom=intent.getStringExtra("from");
            if(navigationFrom!=null)
                if(navigationFrom.equalsIgnoreCase("MyBikeScreen")) {
                    dataModel = (MyBike) intent.getSerializableExtra("model");
                    if (dataModel != null) {
                        Application.bikeSelectionFlag=true;
                        bikeSelectionDataFilling();
                    }
                }else if(navigationFrom.equalsIgnoreCase("DealerLocationScreen")){
                    dealerDataModel= (DealerModel) intent.getSerializableExtra("model");
                    if(dealerDataModel!=null){
                        Application.dealerSelectionFlag=true;
                        dealerSelectionDataFilling();
                    }
                }
        }
    }

    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");
    }

  /*BIKE SELECTION DATA FILLING*/

  public void bikeSelectionDataFilling(){
      select_bike_layout.setVisibility(View.GONE);
      bike_selected_layout.setVisibility(View.VISIBLE);
      bikeModelName.setText(Application.myBikeModelGlobal.getBikeModelName());
      bikeRegistrationNo.setText(Application.myBikeModelGlobal.getBikeRegNumber());
      bikeImageView.setImageResource(Application.myBikeModelGlobal.getBikeImageUrl());
  }

  /*DEALER SELECTION DATA FILLING*/

  public void dealerSelectionDataFilling(){
      select_dealer_layout.setVisibility(View.GONE);
      dealer_selected_layout.setVisibility(View.VISIBLE);
      dealerNameText.setText(Application.dealerModelGlobal.getName());
      dealerAddressText.setText(Application.dealerModelGlobal.getAddress());
      dealerContactNoText.setText(Application.dealerModelGlobal.getContactNo());
      dealerEmailAddressText.setText(Application.dealerModelGlobal.getEmailAddress());
  }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SBookingActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        SBookingActivity.this.finish();
    }

    public void refreshScreen(){

    }
    @Override
    public void onClick(View view) {

        if(view==select_bike_layout){
            Intent intent=new Intent(SBookingActivity.this,MyBikeActivity.class);
            intent.putExtra("from","ServiceBookingScreen");
            intent.putExtra("ScreenName", "My Bikes");
            startActivity(intent);
        }else if(view==select_dealer_layout){
            Intent intent=new Intent(SBookingActivity.this,DealerLocationActivity.class);
            intent.putExtra("from","ServiceBookingScreen");
            intent.putExtra("ScreenName", "Dealer Location");
            startActivity(intent);
        }else if(view==editBikeSelection){
            Intent intent=new Intent(SBookingActivity.this,MyBikeActivity.class);
            intent.putExtra("from","ServiceBookingScreen");
            intent.putExtra("ScreenName", "My Bikes");
            startActivity(intent);
        }else if(view==editDealerSelection){
            Intent intent=new Intent(SBookingActivity.this,DealerLocationActivity.class);
            intent.putExtra("from","ServiceBookingScreen");
            intent.putExtra("ScreenName", "Dealer Location");
            startActivity(intent);
        }else if(view==select_date_and_time_layout){

            dateAndTimePickerShow();
        }else if(view==select_service_type_layout){
            Util.showListDialog(this,select_service_type_text);
        }else if(view==buttonLayout){
            performSubmitRequest();
        }else if(view==serviceHistoryIcon){
            Intent intent=new Intent(SBookingActivity.this,ServiceHistoryActivity.class);
            startActivity(intent);
        }
    }

    public void dateAndTimePickerShow(){
        // Initialize
        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                "Date And Time",
                "OK",
                "Cancel"
        );
  //      dateTimeDialogFragment.setAlertStyle(R.style.SwitchDateTime.HeaderBackground);
  //      dateTimeDialogFragment.setAlertStyle(R.style.DateLabelYear);


// Assign values
       /* dateTimeFragment.startAtCalendarView();
        dateTimeFragment.set24HoursMode(true);
        dateTimeFragment.setMinimumDateTime(new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
        dateTimeFragment.setDefaultDateTime(new GregorianCalendar(2017, Calendar.MARCH, 4, 15, 20).getTime());
// Or assign each element, default element is the current moment
// dateTimeFragment.setDefaultHourOfDay(15);
// dateTimeFragment.setDefaultMinute(20);
// dateTimeFragment.setDefaultDay(4);
// dateTimeFragment.setDefaultMonth(Calendar.MARCH);
// dateTimeFragment.setDefaultYear(2017);

// Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e(TAG, e.getMessage());
        }
*/
// Set listener
        dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                // Date is get on positive button click
                // Do something
                select_booking_date_and_time_text.setText(new SimpleDateFormat("MMMM dd : HH:mm:a", Locale.getDefault()).format(date));
                Application.selectedServiceDateAndTimeGlobal=new SimpleDateFormat("MMMM dd : HH:mm:a", Locale.getDefault()).format(date);
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Date is get on negative button click
            }
        });

// Show
        dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Application.problemDescriptionGlobal=charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    /*Initialize network operation*/
    private void performSubmitRequest(){
        animatorSet=Util.reduceSize(SBookingActivity.this, buttonLayout);
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
                Util.regainSize(SBookingActivity.this, buttonLayout);
                buttonLayout.setVisibility(View.VISIBLE);
               /* Intent intent = new Intent(RegisterActivity.this, OTPVerifyActivity.class);
                intent.putExtra("mobileNumber", mobileNumber);
                startActivity(intent);*/
               Util.showSuccessDialog(SBookingActivity.this,"Your Service Booked successfully,Dealer will contact you soon !!!");
            }

            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}

        });
    }
}
