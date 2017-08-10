package com.intellinet.hondatwowheeler.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.activity.ContactUsActivity;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.model.ContactUsModel;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class StickyContactUsAdapter extends RecyclerView.Adapter<StickyContactUsAdapter.ViewHolder> implements
        StickyHeaderAdapter<StickyContactUsAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    ArrayList<ContactUsModel> conArrayList;
    ContactUsActivity context;

    public StickyContactUsAdapter(ContactUsActivity context,  ArrayList<ContactUsModel> conArrayList) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.conArrayList=conArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.adapter_contactus, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        ContactUsModel contactUsModel = conArrayList.get(position);
        viewHolder.itemTitle.setText(contactUsModel.getConTitle());
        viewHolder.itemAddress.setText(contactUsModel.getConAddress());
        viewHolder.phNumberText.setText(contactUsModel.getConPhone());
        viewHolder.mailText.setText(contactUsModel.getConMail());

        viewHolder.phLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.callFeature(viewHolder.phNumberText.getText().toString().trim());
            }
        });

        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               context.mailFeature(viewHolder.mailText.getText().toString().trim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return conArrayList.size();
    }

    @Override
    public long getHeaderId(int position) {
        return (long) position / 1;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_layout, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        ContactUsModel contactUsModel = conArrayList.get(position);
        viewholder.headerText.setText(contactUsModel.getConHeader());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle, itemAddress, phNumberText, mailText;
        RelativeLayout phLayout, mLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemAddress = (TextView) itemView.findViewById(R.id.itemAddress);
            phNumberText = (TextView) itemView.findViewById(R.id.phNumberText);
            mailText=(TextView)itemView.findViewById(R.id.mText);
            phLayout = (RelativeLayout)itemView.findViewById(R.id.phLayout);
            mLayout=(RelativeLayout)itemView.findViewById(R.id.mLayout);

            itemTitle.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
            itemAddress.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
            phNumberText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));
            mailText.setTypeface(Util.getCustomFont(FontType.ROBOTO_LIGHT));

        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView headerText;

        public HeaderHolder(View itemView) {
            super(itemView);
            headerText = (TextView) itemView.findViewById(R.id.headerText);
            headerText.setTypeface(Util.getCustomFont(FontType.ROBOTO_REGULAR));
        }
    }

}
