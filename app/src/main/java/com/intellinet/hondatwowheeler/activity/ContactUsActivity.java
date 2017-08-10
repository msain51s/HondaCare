package com.intellinet.hondatwowheeler.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.adapter.StickyContactUsAdapter;
import com.intellinet.hondatwowheeler.model.ContactUsModel;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

public class ContactUsActivity extends AppCompatActivity {

     Toolbar toolbar;
     RelativeLayout callLayout, mailLayout;
     TextView numberTextView, cfMailId;
     String  cfNumber;

    private RecyclerView mList;
    private StickyHeaderDecoration decor;
    ArrayList<ContactUsModel> conArrayList = new ArrayList<>();
    StickyContactUsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initToolBar();
        callLayout=(RelativeLayout)findViewById(R.id.callLayout);
        mailLayout=(RelativeLayout)findViewById(R.id.mailLayout);
        numberTextView=(TextView)findViewById(R.id.cfNumberText);
        cfMailId=(TextView)findViewById(R.id.cfMailId);
        cfCallAndMail();
        mList = (RecyclerView)findViewById(R.id.contactList);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(ContactUsActivity.this));
        adapter = new StickyContactUsAdapter(ContactUsActivity.this, conArrayList);
        decor = new StickyHeaderDecoration(adapter);
        mList.setAdapter(adapter);
        mList.addItemDecoration(decor, 0);
        initRecyclerList();
    }


    private void initToolBar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ContactUsActivity.this, SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                ContactUsActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void cfCallAndMail(){
        String text = numberTextView.getText().toString().trim();
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 14, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        numberTextView.setText(spannable);
        cfNumber = text.substring(0, 14);
        cfNumber = cfNumber.replace(" ", "");


        callLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cfNumber!=null && !cfNumber.equalsIgnoreCase("")){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + cfNumber));
                    startActivity(callIntent);
                }
            }
        });

        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cfEmail=cfMailId.getText().toString().trim();
                if(cfEmail!=null && !cfEmail.equalsIgnoreCase("")){
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{cfEmail});
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email Client :"));
                }
            }
        });

    }


    private  void initRecyclerList(){

        ContactUsModel contactUsModel = new ContactUsModel("Corporate Office North", "Honda Motorcycle & Scooter India Pvt. Ltd.",
                "Commercial Complex II, Sector 49-50 Golf Course Extension Road, Gurgaon, Haryana-122018, India", "+91-124-6712800", "customercare@hondaindia.co.in");

        ContactUsModel contactUsModel1 = new ContactUsModel("Corporate Office South", "Honda Motorcycle & Scooter India Pvt. Ltd.",
                "Commercial Complex II, Sector 49-50 Golf Course Extension Road, Chennai, Tamilnadu-122018, India", "+91-44-67128000", "customercare@hondaindia.co.in");

        ContactUsModel contactUsModel2 = new ContactUsModel("Corporate & Registered Office", "Honda Motorcycle & Scooter India Pvt. Ltd.",
                "Commercial Complex II, Sector 49-50 Golf Course Extension Road, New Delhi, New Delhi-110018, India", "+91-11-67128001", "customercare@hondaindia.co.in");

        ContactUsModel contactUsModel3 = new ContactUsModel("Zonal Office", "Honda Motorcycle & Scooter India Pvt. Ltd.",
                "Commercial Complex II, Sector 49-50 Golf Course Extension Road, Lucknow, UP-277502, India", "+91-5498-671280", "customercare@hondaindia.co.in");

        ContactUsModel contactUsMode4 = new ContactUsModel("Corporate & Registered Office", "Honda Motorcycle & Scooter India Pvt. Ltd.",
                "Commercial Complex II, Sector 49-50 Golf Course Extension Road, Gurgaon, Haryana-122018, India", "+91-124-6712800", "customercare@hondaindia.co.in");

        conArrayList.add(contactUsModel);
        conArrayList.add(contactUsModel1);
        conArrayList.add(contactUsModel2);
        conArrayList.add(contactUsModel3);
        conArrayList.add(contactUsMode4);

        adapter.notifyDataSetChanged();

    }


    public void callFeature(String cNumber){
        if(cNumber!=null && !cNumber.equalsIgnoreCase("")){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + cNumber));
            startActivity(callIntent);
        }
    }

    public void mailFeature(String cEmail){
        if(cEmail!=null && !cEmail.equalsIgnoreCase("")){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{cEmail});
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email Client :"));
        }
    }


}
