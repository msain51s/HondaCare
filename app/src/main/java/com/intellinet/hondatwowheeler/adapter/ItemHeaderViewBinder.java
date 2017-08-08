package com.intellinet.hondatwowheeler.adapter;

import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.model.ItemHeader;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

/**
 * Created by Administrator on 8/2/2017.
 */

public class ItemHeaderViewBinder extends ViewBinder<ItemHeader, ItemHeaderViewBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }
    @Override
    public void bindView(StickyHeaderViewAdapter adapter, ViewHolder holder, int position, ItemHeader entity) {
        holder.headerText.setText(entity.getHeaderText());
        if(entity.getHeaderText().equalsIgnoreCase("Pending"))
            holder.headerText.setBackgroundColor(Color.parseColor("#f0ab4e"));
        else
            holder.headerText.setBackgroundColor(Color.parseColor("#80b840"));
    }
    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.steaky_header;
    }
    static class ViewHolder extends ViewBinder.ViewHolder {
        TextView headerText;
        public ViewHolder(View rootView) {
            super(rootView);
            this.headerText = (TextView) rootView.findViewById(R.id.header_text);

        }
    }
}
