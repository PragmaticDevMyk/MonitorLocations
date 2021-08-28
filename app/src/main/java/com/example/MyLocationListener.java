package com.example;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {
    final String _logTag = "Monitor Location";

    public void onLocationChanged(Location location) {
        String provider = location.getProvider();
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        float accuracy = location.getAccuracy();
        long time = location.getTime();

        String logMessage = LogHelper.FormatLocationInfo(provider, lat, lng, accuracy, time);

        Log.d(_logTag, "Monitor Location:" + logMessage);
    }

    public void onStatusChanged(String s, int i, Bundle bundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onProviderEnabled(String s) {
        Log.d(_logTag, "Monitor Location - Provider Enabled:" + s);
    }

    public void onProviderDisabled(String s) {
        Log.d(_logTag, "Monitor Location - Provider DISabled:" + s);
    }
}
