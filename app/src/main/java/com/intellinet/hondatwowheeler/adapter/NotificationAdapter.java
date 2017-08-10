package com.intellinet.hondatwowheeler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.NotificationModel;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

/**
 * Created by Chandan Dwivedi on 7/31/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<NotificationModel> notificationList;
    Context context;
    OnItemClickListener itemClickListener;

    public NotificationAdapter(ArrayList<NotificationModel> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notification, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationModel notificationModel = notificationList.get(position);

        holder.nTypeText.setVisibility(View.GONE);
        holder.nTitleIcon.setVisibility(View.GONE);
        holder.nImage.setVisibility(View.GONE);
        if(position==0){
            holder.nTypeText.setVisibility(View.VISIBLE);
            holder.nTypeText.setBackgroundColor(Color.parseColor("#1aba5d"));
            holder.nTypeText.setTextColor(Color.parseColor("#ffffff"));
            holder.nTypeText.setText("New Notification");
        }else if(position==4){
            holder.nTypeText.setVisibility(View.VISIBLE);
            holder.nTypeText.setBackgroundColor(Color.parseColor("#d8d8d8"));
            holder.nTypeText.setTextColor(Color.parseColor("#333333"));
            holder.nTypeText.setText("Old Notification");
        }

        holder.nTitleText.setText(notificationModel.getTitle());
        holder.nTimeText.setText(notificationModel.getTime());
        holder.nDescText.setText(notificationModel.getDesc());
        holder.nImage.setImageResource(notificationModel.getImg());

        if(position<4){
            holder.nTitleIcon.setVisibility(View.VISIBLE);
            holder.nImage.setVisibility(View.VISIBLE);
        }

        holder.itemView.setTag(notificationModel);
    }


    @Override
    public void onClick(final View v) {
        if (itemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemClickListener.onItemClick(v, (NotificationModel) v.getTag());
                }
            }, 200);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitleText, nTimeText, nDescText, nTypeText, nTitleIcon;
        ImageView nImage;

        public ViewHolder(View itemView) {
            super(itemView);
            nImage = (ImageView) itemView.findViewById(R.id.nImage);
            nTitleText = (TextView) itemView.findViewById(R.id.nTitle);
            nTimeText = (TextView) itemView.findViewById(R.id.nTime);
            nDescText = (TextView) itemView.findViewById(R.id.nDesc);
            nTypeText = (TextView) itemView.findViewById(R.id.nTypeTextView);
            nTitleIcon=(TextView)itemView.findViewById(R.id.nTitleIcon);

            nTitleText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
            nTimeText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
            nDescText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
            nTypeText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, NotificationModel notificationModel);
    }

}