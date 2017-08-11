package com.intellinet.hondatwowheeler.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.FeedbackModel;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

/**
 * Created by Chandan Dwivedi on 7/24/2017.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<FeedbackModel> fTypeList;
    Context context;
    OnItemClickListener itemClickListener;

    public FeedbackAdapter(ArrayList<FeedbackModel> fTypeList, Context context) {
        this.fTypeList = fTypeList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feedback, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public int getItemCount() {
        return fTypeList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FeedbackModel feedbackModel=fTypeList.get(position);
        holder.fTypeText.setText(feedbackModel.getfTypeTitle());
        holder.fTypeImage.setImageResource(feedbackModel.getfTypeImage());

        holder.itemView.setTag(feedbackModel);
    }


    @Override
    public void onClick(final View v) {
        if(itemClickListener!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemClickListener.onItemClick(v, (FeedbackModel) v.getTag());
                }
            }, 200);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView fTypeText;
        ImageView fTypeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            fTypeImage=(ImageView)itemView.findViewById(R.id.fItemImage);
            fTypeText=(TextView)itemView.findViewById(R.id.fItemName);
            fTypeText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view, FeedbackModel feedbackModel);
    }

}
