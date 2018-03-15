package com.example.page.baby_care;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
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

public class Recycler_Adapter_tracker extends RecyclerView.Adapter<Recycler_Adapter_tracker.Viewhold> {

    private List<Wifi_Data> wifi_list;
    Context c=null;
    LayoutInflater li;
    static ArrayList<String> sel_list;


    public Recycler_Adapter_tracker(Context c, List<Wifi_Data> wlist)
    {
        sel_list=new ArrayList<>();
        this.c=c;
        wifi_list=wlist;
        li=LayoutInflater.from(c);
    }

    @Override
    public Viewhold onCreateViewHolder(ViewGroup parent, int viewType) {
        View li_v=li.inflate(R.layout.wifi_strength_layout,parent,false);

        return new Viewhold(li_v);
    }

    @Override
    public void onBindViewHolder(final Viewhold holder, int position) {

        final Wifi_Data data=wifi_list.get(position);
        holder.tv.setText(data.getSSID());
        holder.tv2.setText(data.getBSSID());
        holder.tv3.setText(data.getLevel());

        if(Integer.parseInt(data.getLevel())<=99&&Integer.parseInt(data.getLevel())>95)
        {
            holder.cv.setBackgroundColor(Color.GREEN);
        }

        if(Integer.parseInt(data.getLevel())<=95&&Integer.parseInt(data.getLevel())>90)
        {
            holder.cv.setBackgroundColor(Color.YELLOW);
        }

        if(Integer.parseInt(data.getLevel())<=90)
        {
            holder.cv.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return wifi_list.size();
    }

    public class Viewhold extends RecyclerView.ViewHolder
    {

        TextView tv;
        TextView tv2;
        TextView tv3;
        CardView cv;

        View v;

        public Viewhold(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.Text);
            tv2=(TextView)itemView.findViewById(R.id.Text2);
            tv3=(TextView)itemView.findViewById(R.id.txtl);
            cv=(CardView)itemView.findViewById(R.id.cardview);
            v=itemView;
        }
    }


}
