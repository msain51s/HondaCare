package com.intellinet.hondatwowheeler.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.ItemHeader;
import com.intellinet.hondatwowheeler.model.ServiceHistoryModel;
import com.intellinet.hondatwowheeler.utility.Application;
import com.intellinet.hondatwowheeler.utility.Util;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

/**
 * Created by Administrator on 8/2/2017.
 */

public class ItemViewBinder extends ViewBinder<ServiceHistoryModel, ItemViewBinder.ViewHolder> {
    Context context;
    public ItemViewBinder(Context context){
        this.context=context;
    }
    @Override
    public ItemViewBinder.ViewHolder provideViewHolder(View itemView) {
        return new ItemViewBinder.ViewHolder(itemView);
    }
    @Override
    public void bindView(StickyHeaderViewAdapter adapter, ItemViewBinder.ViewHolder holder, int position, final ServiceHistoryModel entity) {
        holder.bikeModelName.setText(entity.getBikeModelName());
        holder.dealerName.setText(entity.getDealerName());
        holder.dealerAddress.setText(entity.getDealerAddress());
        holder.dealerContactNo.setText(entity.getDealerContactNo());
        holder.dealerEmailId.setText(entity.getEmailAddress());
        holder.problemDescription.setText(entity.getProblemDescription());

        holder.dealerContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+entity.getDealerContactNo()));

                if (ActivityCompat.checkSelfPermission(Application.mContext,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
        });

        holder.dealerEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(new String[]{entity.getEmailAddress()});
            }
        });
    }

    protected void sendEmail(String[] to) {
        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
           context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Application.mContext, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.adapter_service_history_list_item;
    }
    static class ViewHolder extends ViewBinder.ViewHolder {
        Typeface roboto_regular,roboto_light;
        TextView bikeModelName,bikeRegistrationNo,
                dealerName,dealerAddress,dealerContactNo,dealerEmailId,bookingDate,serviceType,problemDescription;
        ImageView bikeImage;
        public ViewHolder(View rootView) {
            super(rootView);
            roboto_regular= Util.getCustomFont(FontType.ROBOTO_REGULAR);
            roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);

            this.bikeModelName = (TextView) rootView.findViewById(R.id.bikeModelName);
            this.bikeRegistrationNo= (TextView) rootView.findViewById(R.id.bikeRegNumber);
            this.bikeImage= (ImageView) rootView.findViewById(R.id.bikeImage);
            this.dealerName= (TextView) rootView.findViewById(R.id.dealerNameTextview);
            this.dealerAddress= (TextView) rootView.findViewById(R.id.dealerAddressTextview);
            this.dealerContactNo= (TextView) rootView.findViewById(R.id.dealerContactNoTextview);
            this.dealerEmailId= (TextView) rootView.findViewById(R.id.dealerEmailAddressTextview);
            this.bookingDate= (TextView) rootView.findViewById(R.id.dateTextValue);
            this.serviceType= (TextView) rootView.findViewById(R.id.serviceTypeTextValue);
            this.problemDescription= (TextView) rootView.findViewById(R.id.problemDescriptionText);

            this.bikeModelName.setTypeface(roboto_regular);
            this.bikeRegistrationNo.setTypeface(roboto_light);
            this.dealerName.setTypeface(roboto_regular);
            this.dealerAddress.setTypeface(roboto_light);
            this.dealerContactNo.setTypeface(roboto_light);
            this.dealerEmailId.setTypeface(roboto_light);
            this.bookingDate.setTypeface(roboto_light);
            this.serviceType.setTypeface(roboto_light);
            this.problemDescription.setTypeface(roboto_light);


        }
    }
}
