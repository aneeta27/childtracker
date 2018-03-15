package com.example.page.baby_care;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Recycler_Adapter ada;
    WifiManager mainWifi;
    //    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    String[] sb;
    RecyclerView recyclerView;
    Button btn;
    Button btn2;



    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // Initiate wifi service manager
        //mainWifi = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        // Check for wifi is disabled
        if (mainWifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();
            mainWifi.setWifiEnabled(true);
        }
        update_scan();


       /* //wifi scaned value broadcast receiver
        WifiReceiver receiverWifi = new WifiReceiver();

        // Register broadcast receiver
        // Broacast receiver will automatically call when number of wifi connections changed
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
*/

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Tracking.class);
                ArrayList<String> list = ada.sel_list;
                i.putExtra("BSSID", list);
                startActivity(i);
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_scan();
            }
        });

    }


    public void update_scan()
    {
        mainWifi.startScan();
        wifiList = mainWifi.getScanResults();
        int Number_Of_Wifi_connections = wifiList.size();
        Log.d("testrun", "" + Number_Of_Wifi_connections);
        sb = new String[Number_Of_Wifi_connections];
        List<Wifi_Data> li = new ArrayList<>();

        for (int i = 0; i < Number_Of_Wifi_connections; i++)
        {
            sb[i] = "" + (i + 1) + " " + wifiList.get(i).SSID + "\n" + wifiList.get(i).BSSID;
            Wifi_Data wd = new Wifi_Data();
            wd.setSSID(wifiList.get(i).SSID);
            wd.setBSSID(wifiList.get(i).BSSID);
            li.add(wd);
        }

        ada = new Recycler_Adapter(MainActivity.this, li);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(ada);
    }




    /*class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {

           StringBuilder sb = new StringBuilder();
            wifiList = mainWifi.getScanResults();
            sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");

            for(int i = 0; i < wifiList.size(); i++){

                sb.append(new Integer(i+1).toString() + ". ");
                sb.append((wifiList.get(i)).toString());
                sb.append("\n\n");
            }



            Log.d("testrun2",""+sb.toString());
       //     mainText.setText(sb);
        }

    }*/

}








