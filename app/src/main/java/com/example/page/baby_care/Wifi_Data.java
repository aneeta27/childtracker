package com.example.page.baby_care;

/**
 * Created by PAGE on 12/9/2017.
 */

public class Wifi_Data {
    String SSID;

    String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    String BSSID;
    boolean isSelected=false;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSSID() {
        return SSID;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }


}
