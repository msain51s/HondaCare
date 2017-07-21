package com.intellinet.hondatwowheeler.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.activity.DealerLocationActivity;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.DealerModel;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/20/2017.
 */

public class DealerListAdapter extends RecyclerView.Adapter<DealerListAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<DealerModel> dealerList;
    Context context;
    OnItemClickListener itemClickListener;

    static Typeface roboto_regular,roboto_light;
    public DealerListAdapter(Context context, ArrayList<DealerModel> list) {
        this.dealerList = list;
        this.context = context;
        roboto_regular=Util.getCustomFont(FontType.ROBOTO_REGULAR);
        roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public DealerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dealer_list, parent, false);
        DealerListAdapter.ViewHolder vh = new DealerListAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public int getItemCount() {
        return dealerList.size();
    }

    @Override
    public void onBindViewHolder(DealerListAdapter.ViewHolder holder, int position) {
        DealerModel model = dealerList.get(position);

        holder.dealerName.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.distance.setText(model.getDistance());
        holder.distance_unit.setText(model.getDistanceUnit());

        holder.itemView.setTag(model);
    }

    @Override
    public void onClick(final View v) {

        if(itemClickListener!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemClickListener.onItemClick(v, (DealerModel) v.getTag());
                }
            }, 200);
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView dealerName,address,distance,distance_unit;

        public ViewHolder(View itemView) {
            super(itemView);
            dealerName=(TextView)itemView.findViewById(R.id.dealerNameTextView);
            address=(TextView)itemView.findViewById(R.id.dealerAddressTextView);
            distance=(TextView)itemView.findViewById(R.id.dealerDistanceTextView);
            distance_unit= (TextView) itemView.findViewById(R.id.dealerDistanceUnitTextView);

            dealerName.setTypeface(roboto_regular);
            address.setTypeface(roboto_light);
            distance.setTypeface(roboto_regular);
            distance_unit.setTypeface(roboto_regular);

        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view, DealerModel model);
    }

}

