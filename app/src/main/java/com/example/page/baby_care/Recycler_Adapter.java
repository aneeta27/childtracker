package com.example.page.baby_care;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PAGE on 12/9/2017.
 */

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.Viewhold> {

    private List<Wifi_Data> wifi_list;
    Context c=null;
    LayoutInflater li;
    static ArrayList<String> sel_list;


    public Recycler_Adapter(Context c,List<Wifi_Data> wlist)
    {
        sel_list=new ArrayList<>();
        this.c=c;
        wifi_list=wlist;
        li=LayoutInflater.from(c);
    }

    @Override
    public Viewhold onCreateViewHolder(ViewGroup parent, int viewType) {
        View li_v=li.inflate(R.layout.wifi_layout,parent,false);

        return new Viewhold(li_v);
    }

    @Override
    public void onBindViewHolder(final Viewhold holder, int position) {

        final Wifi_Data data=wifi_list.get(position);
        holder.tv.setText(data.getSSID());
        holder.tv2.setText(data.getBSSID());
        holder.v.setBackgroundColor(data.isSelected() ? Color.CYAN : Color.WHITE);
        if(data.isSelected()){
            holder.chk.setChecked(true);

        }
        else
        {holder.chk.setChecked(false);}

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setSelected(!data.isSelected());
                holder.v.setBackgroundColor(data.isSelected() ? Color.CYAN : Color.WHITE);
                if(data.isSelected()){
                    sel_list.add(data.getBSSID());
                    holder.chk.setChecked(true); }
                else
                {
                    sel_list.remove(data.getBSSID());
                    holder.chk.setChecked(false);}
            }
        });
    }

    @Override
    public int getItemCount() {
        return wifi_list.size();
    }

    public class Viewhold extends RecyclerView.ViewHolder
    {

        TextView tv;
        TextView tv2;
        CheckBox chk;
        View v;

        public Viewhold(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.Text);
            tv2=(TextView)itemView.findViewById(R.id.Text2);
            chk=(CheckBox)itemView.findViewById(R.id.chk);
            v=itemView;
        }
    }


}
