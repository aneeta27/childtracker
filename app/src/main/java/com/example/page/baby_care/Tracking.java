package com.example.page.baby_care;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tracking extends AppCompatActivity {

    ArrayList<String> arr;
    WifiManager mainWifi;
    List<ScanResult> wifiList;
    Recycler_Adapter_tracker ada;
    RecyclerView recyclerView;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

       mp=MediaPlayer.create(Tracking.this,R.raw.beep);

        // Initiate wifi service manager
        mainWifi = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        Bundle b = getIntent().getExtras();
        arr = b.getStringArrayList("BSSID");

        for(int ii=0;ii<arr.size();ii++){

            Toast.makeText(Tracking.this, arr.get(ii).toString(), Toast.LENGTH_SHORT).show();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(Tracking.this));

        mHandler.postDelayed(mRunnable,1000);




    }




    private final Handler mHandler=new Handler();

Runnable mRunnable=new Runnable() {
    @Override
    public void run() {

        mainWifi.startScan();
        wifiList = mainWifi.getScanResults();

        int  Number_Of_Wifi_connections=wifiList.size();

        List<Wifi_Data> li=new ArrayList<>();


        for(int i = 0; i < Number_Of_Wifi_connections; i++){

            if(arr.contains(wifiList.get(i).BSSID)) {
                Wifi_Data wd = new Wifi_Data();
                wd.setSSID(wifiList.get(i).SSID);
                wd.setBSSID(wifiList.get(i).BSSID);

                int level=WifiManager.calculateSignalLevel(wifiList.get(i).level,100);

                if(level<=90)
                {
                         mp.start();
                }


                wd.setLevel(String.valueOf(level));


               // Toast.makeText(Tracking.this,wifiList.get(i).level,Toast.LENGTH_SHORT).show();

                li.add(wd);
            }
        }

        ada=null;

        ada=new Recycler_Adapter_tracker(Tracking.this,li);

        ada.notifyDataSetChanged();

        recyclerView.setAdapter(ada);


        mHandler.postDelayed(mRunnable,100);
    }
};









}
