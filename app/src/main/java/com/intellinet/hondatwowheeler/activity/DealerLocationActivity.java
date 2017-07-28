package com.intellinet.hondatwowheeler.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.DealerListAdapter;
import com.intellinet.hondatwowheeler.adapter.MyBikeAdapter;
import com.intellinet.hondatwowheeler.callback.AnimationType;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.DealerModel;
import com.intellinet.hondatwowheeler.model.MyBike;
import com.intellinet.hondatwowheeler.utility.Util;

import java.io.Serializable;
import java.util.ArrayList;

public class DealerLocationActivity extends BaseActivity implements DealerListAdapter.OnItemClickListener{

    String activityName;
    TextView select_dealer_text,select_state_text,select_city_text;
    ImageView arrow_icon;
    View stateAndCityLayout;
    RecyclerView dealer_recyclerview;
    ArrayList<DealerModel> dealerList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DealerListAdapter dealerListAdapter;

    ListView alert_listview;
    ArrayList<String> sorted_list=new ArrayList<String>();
    ArrayList<String> state_list=new ArrayList<String>();
    ArrayList<String> city_list=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    AlertDialog myalertDialog;
    int textlength=0;

    boolean isStateAndCityLayoutShowing=true;
    Typeface roboto_regular,roboto_light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dealer_location, containerLayout);
        roboto_regular= Util.getCustomFont(FontType.ROBOTO_REGULAR);
        roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);

  /*VIEW INTITIALISATION*/
        select_dealer_text=(TextView)findViewById(R.id.select_dealer_textview);
        select_state_text=(TextView)findViewById(R.id.select_state_textview);
        select_city_text=(TextView)findViewById(R.id.select_city_textview);
        arrow_icon= (ImageView) findViewById(R.id.arrow_icon);
        stateAndCityLayout=findViewById(R.id.state_and_city_layout);
        dealer_recyclerview= (RecyclerView) findViewById(R.id.dealer_recyclerview);

        select_dealer_text.setTypeface(roboto_regular);
        select_state_text.setTypeface(roboto_light);
        select_city_text.setTypeface(roboto_light);

        linearLayoutManager=new LinearLayoutManager(DealerLocationActivity.this);
        dealer_recyclerview.setLayoutManager(linearLayoutManager);
        dealerListAdapter=new DealerListAdapter(DealerLocationActivity.this, dealerList);
        dealerListAdapter.setOnItemClickListener(DealerLocationActivity.this);
        dealer_recyclerview.setAdapter(dealerListAdapter);
        initRecycler();


        getIntentData();
        getSupportActionBar().setTitle(activityName);
        titleText.setVisibility(View.GONE);
    }



    public void  getIntentData(){
        Intent intent=getIntent();
        activityName=intent.getStringExtra("ScreenName");
  //      checkText.setText(activityName);
    }

    /*Add Data to List*/
    public void initRecycler() {
        DealerModel model1 = new DealerModel();
        model1.setName("Shri Khatri Honda Motors");
        model1.setAddress("Block A 10 Near Railway Road");
        model1.setDistance("02");
        model1.setDistanceUnit("KM");
        model1.setContactNo("+91 (0)124-7712800");
        model1.setEmailAddress("hondadealer@gmail.com");
        dealerList.add(model1);

        DealerModel model2 = new DealerModel();
        model2.setName("Raja Ram Honda");
        model2.setAddress("Shop No 101,102 Sohna Road");
        model2.setDistance("100");
        model2.setDistanceUnit("MTR");
        model2.setContactNo("+91 (0)124-7712800");
        model2.setEmailAddress("hondadealer@gmail.com");
        dealerList.add(model2);

        DealerModel model3 = new DealerModel();
        model3.setName("Karni Autoparts");
        model3.setAddress("Block C 33 Near Railway Road");
        model3.setDistance("05");
        model3.setDistanceUnit("KM");
        model3.setContactNo("+91 (0)124-7712800");
        model3.setEmailAddress("hondadealer@gmail.com");
        dealerList.add(model3);

        DealerModel model4 = new DealerModel();
        model4.setName("Shri Khatri Honda Motors");
        model4.setAddress("Block A 10 Near Railway Road");
        model4.setDistance("600");
        model4.setDistanceUnit("MTR");
        model4.setContactNo("+91 (0)124-7712800");
        model4.setEmailAddress("hondadealer@gmail.com");
        dealerList.add(model4);

        dealerListAdapter.notifyDataSetChanged();
    }

 /*HIDE AND SHOW SELECT DEALER LAYOUT FUNCTIONALITY
 * */
    public void performHideAndShowSelectDealerLayout(View view){

        GradientDrawable drawable= (GradientDrawable) view.getBackground();

        if(isStateAndCityLayoutShowing) {
      //      stateAndCityLayout.setVisibility(View.GONE);
     //       Util.setAnimation(this,stateAndCityLayout, AnimationType.SLIDE_UP);
            Util.collapse(stateAndCityLayout);
            isStateAndCityLayoutShowing=false;
     //       bottom_line_text.setVisibility(View.GONE);
        }
        else {
        //    stateAndCityLayout.setVisibility(View.VISIBLE);
        //    Util.setAnimation(this,stateAndCityLayout, AnimationType.SLIDE_DOWN);
            Util.expand(stateAndCityLayout);
            isStateAndCityLayoutShowing=true;
        }
    }

   /* public void setBottomRoundCorner(GradientDrawable drawable,int i){
        if(i==0) {
            drawable.setCornerRadi(new float[]{5, 5, 5, 5});
            drawable.
            bottom_line_text.setVisibility(View.GONE);
        }
        else {
            drawable.setCornerRadii(new float[]{5, 5, 0, 0});
            bottom_line_text.setVisibility(View.VISIBLE);
        }

    }*/

  /*STATE SELECTION
  * */

  public void performStateClick(View view){
      state_list.clear();
      state_list.add("Haryana");

      createDialog(state_list,1,select_state_text);
  }

/*CITY SELECTION
* */

    public void performCityClick(View view){
        city_list.clear();
        city_list.add("Gurgaon");
        city_list.add("Rewari");

        createDialog(city_list,2,select_city_text);
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(DealerLocationActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        DealerLocationActivity.this.finish();
    }


    public void createDialog(final ArrayList<String> data_list, final int code, final TextView textView )
    {

        AlertDialog.Builder myDialog = new AlertDialog.Builder(DealerLocationActivity.this);

        final EditText editText = new EditText(DealerLocationActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 20, 15, 0);
        editText.setLayoutParams(params);
        editText.setPadding(20, 20, 20, 20);
        editText.setHint("Search ");
        editText.setTextSize(16);
        editText.setBackground(getResources().getDrawable(R.drawable.search_edit_text_bg));
        alert_listview= new ListView(DealerLocationActivity.this);
        alert_listview.setLayoutParams(params);
        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        sorted_list.clear();
        sorted_list.addAll(data_list);
        adapter = new ArrayAdapter<String>(
                DealerLocationActivity.this, android.R.layout.simple_list_item_1, sorted_list);
        LinearLayout layout = new LinearLayout(DealerLocationActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(getResources().getColor(R.color.white));
        layout.addView(editText);
        layout.addView(alert_listview);
        LayoutInflater ll=getLayoutInflater();
        View title_view=ll.inflate(R.layout.custom_title_view, null);
        TextView title_txt=(TextView) title_view.findViewById(R.id.custom_title_txt);
        if(code==1)
            title_txt.setText("Select State");
        else if(code==2)
            title_txt.setText("Select City");

        myDialog.setCustomTitle(title_view);

        myDialog.setView(layout);


        alert_listview.setAdapter(adapter);
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                textlength = editText.getText().length();
                sorted_list.clear();

                for (int i = 0; i < data_list.size(); i++) {
                    if (textlength <= data_list.get(i).length()) {
                        if (data_list.get(i).toLowerCase().startsWith(editText.getText().toString().toLowerCase().trim())) {
                            sorted_list.add(data_list.get(i));
                        }
                    }
                }


                adapter = new ArrayAdapter<String>(
                        DealerLocationActivity.this, android.R.layout.simple_list_item_1, sorted_list);
                adapter.notifyDataSetChanged();
                alert_listview.setAdapter(adapter);

            }
        });

        myalertDialog=myDialog.show();


        alert_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myalertDialog.dismiss();
                String selectedValue = sorted_list.get(position);
                textView.setText(selectedValue);

            }
        });


    }


    @Override
    public void onItemClick(View view, DealerModel model) {
         Intent intent=new Intent(DealerLocationActivity.this,DealerInfoActivity.class);
         intent.putExtra("dealerModel", model);
        startActivity(intent);
    }
}
