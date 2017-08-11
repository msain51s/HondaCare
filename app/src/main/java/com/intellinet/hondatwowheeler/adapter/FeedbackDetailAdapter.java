package com.intellinet.hondatwowheeler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.FeedbackDetailModel;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

/**
 * Created by Chandan Dwivedi on 7/25/2017.
 */

public class FeedbackDetailAdapter extends RecyclerView.Adapter<FeedbackDetailAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<FeedbackDetailModel> fDetailList;
    Context context;
    OnItemClickListener itemClickListener;

    public FeedbackDetailAdapter(ArrayList<FeedbackDetailModel> fDetailList, Context context) {
        this.fDetailList = fDetailList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feedback_detail, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public int getItemCount() {
        return fDetailList.size();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FeedbackDetailModel fbDetailModel=fDetailList.get(position);

        holder.catTitleText.setText(fbDetailModel.getCatTitle());
        holder.catImage.setImageResource(fbDetailModel.getCatImage());

        holder.fndCatLayout.setBackgroundColor(Color.parseColor("#e3e3e3"));
        if(position % 2 ==0){
           holder.fndCatLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        GradientDrawable catShape = (GradientDrawable)holder.catImage.getBackground();
        catShape.setColor(Color.parseColor("#999999"));

        holder.sfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sfImage.setImageResource(R.drawable.selected_thumb_down_icon);
                holder.ofImage.setImageResource(R.drawable.unselected_ok_text_icon);
                holder.hfImage.setImageResource(R.drawable.unselected_thumb_up_icon);
                GradientDrawable catShape = (GradientDrawable)holder.catImage.getBackground();
                catShape.setColor(Color.parseColor("#Fe0000"));
                fbDetailModel.setCatStatus("Sad");
            }
        });


        holder.ofImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sfImage.setImageResource(R.drawable.unselected_thumb_icon);
                holder.ofImage.setImageResource(R.drawable.selected_ok_text_yellow_icon);
                holder.hfImage.setImageResource(R.drawable.unselected_thumb_up_icon);
                GradientDrawable catShape = (GradientDrawable)holder.catImage.getBackground();
                catShape.setColor(Color.parseColor("#fdba13"));
                fbDetailModel.setCatStatus("Ok");
            }
        });


        holder.hfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sfImage.setImageResource(R.drawable.unselected_thumb_icon);
                holder.ofImage.setImageResource(R.drawable.unselected_ok_text_icon);
                holder.hfImage.setImageResource(R.drawable.selected_thumb_green_icon);
                GradientDrawable catShape = (GradientDrawable)holder.catImage.getBackground();
                catShape.setColor(Color.parseColor("#63b931"));
                fbDetailModel.setCatStatus("Happy");
            }
        });


        holder.itemView.setTag(fbDetailModel);
    }


    @Override
    public void onClick(final View v) {
        if(itemClickListener!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemClickListener.onItemClick(v, (FeedbackDetailModel) v.getTag());
                }
            }, 200);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView catTitleText;
        ImageView catImage, sfImage, ofImage, hfImage;
        RelativeLayout fndCatLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            catTitleText=(TextView)itemView.findViewById(R.id.catTitle);
            catTitleText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
            catImage=(ImageView)itemView.findViewById(R.id.catImage);
            sfImage=(ImageView)itemView.findViewById(R.id.sfImage);
            ofImage=(ImageView)itemView.findViewById(R.id.ofImage);
            hfImage=(ImageView)itemView.findViewById(R.id.hfImage);
            fndCatLayout=(RelativeLayout)itemView.findViewById(R.id.fndCatLayout);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view, FeedbackDetailModel fbDetailModel);
    }

}

