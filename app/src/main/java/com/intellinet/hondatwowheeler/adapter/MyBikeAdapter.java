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
import com.intellinet.hondatwowheeler.model.MyBike;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

/**
 * Created by Chandan Dwivedi on 7/17/2017.
 */

public class MyBikeAdapter extends RecyclerView.Adapter<MyBikeAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<MyBike> myBikeArrayList;
    Context context;
    OnItemClickListener itemClickListener;

    public MyBikeAdapter(Context context, ArrayList<MyBike> myBikeArrayList) {
        this.myBikeArrayList = myBikeArrayList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mybike, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public int getItemCount() {
        return myBikeArrayList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            MyBike myBike = myBikeArrayList.get(position);

            holder.bikeModelName.setText(myBike.getBikeModelName());
            holder.bikeRegNumber.setText(myBike.getBikeRegNumber());
            holder.bikeImage.setImageResource(myBike.getBikeImageUrl());

            holder.itemView.setTag(myBike);
    }

    @Override
    public void onClick(final View v) {

        if(itemClickListener!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemClickListener.onItemClick(v, (MyBike)v.getTag());
                }
            }, 200);
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bikeImage;
        TextView bikeModelName;
        TextView bikeRegNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            bikeImage=(ImageView)itemView.findViewById(R.id.bikeImage);
            bikeModelName=(TextView)itemView.findViewById(R.id.bikeModelName);
            bikeRegNumber=(TextView)itemView.findViewById(R.id.bikeRegNumber);
            bikeModelName.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
            bikeRegNumber.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
        }
    }


    public interface OnItemClickListener{
       void onItemClick(View view, MyBike myBike);
    }

}
