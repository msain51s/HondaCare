package com.intellinet.hondatwowheeler.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intellinet.hondatwowheeler.R;
import com.intellinet.hondatwowheeler.callback.FontType;
import com.intellinet.hondatwowheeler.utility.Util;

import java.util.List;

/**
 * Created by Administrator on 8/1/2017.
 */

public class DialogListAdapter extends BaseAdapter{
    List<String> list;
    Context context;
    LayoutInflater inflater;
    TextView selectedText;

    Typeface roboto_light,roboto_regular;
    public DialogListAdapter(Context context,List<String> list,TextView selectedText){
        this.context=context;
        this.list=list;
        this.selectedText=selectedText;
        inflater=LayoutInflater.from(context);
        roboto_regular= Util.getCustomFont(FontType.ROBOTO_REGULAR);
        roboto_light=Util.getCustomFont(FontType.ROBOTO_LIGHT);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.dialog_list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.itemText = (TextView) vi.findViewById(R.id.item_text);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        holder.itemText.setText(list.get(position));

        return vi;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView itemText;
    }
}
