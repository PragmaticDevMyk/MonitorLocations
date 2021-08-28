package com.example;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyActivity extends Activity
{
    final String _logTag = "Monitor Location";
    LocationListener _networkListener;
    LocationListener _gpsListener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.main_menu, menu) ;
        return true;
    }

    public void onStartListening(MenuItem item) {
        Log.d(_logTag, "Monitor Location - Start Listening");

        try {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

            _networkListener = new MyLocationListener();
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, _networkListener);

            _gpsListener = new MyLocationListener();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, _gpsListener);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    public void onStopListening(MenuItem item) {
        Log.d(_logTag, "Monitor Location - Stop Listening");

        doStopListening();
    }

    public void onRecentLocation(MenuItem item) {
        Log.d(_logTag, "Monitor - Recent Location");

        Location networkLocation;
        Location gpsLocation;

        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);

        networkLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        String networkLogMessage = LogHelper.FormatLocationInfo(networkLocation);
        String gpsLogMessage = LogHelper.FormatLocationInfo(gpsLocation);

        Log.d(_logTag, "Monitor Location" + networkLogMessage);
        Log.d(_logTag, "Monitor Location" + gpsLogMessage);

    }

    public void onSingleLocation(MenuItem item) {
        Log.d(_logTag, "Monitor - Single Location");

        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);

        _networkListener = new MyLocationListener();
        lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, _networkListener, null);

        _gpsListener = new MyLocationListener();
        lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, _gpsListener, null);
    }

    public void onExit(MenuItem item) {
        Log.d(_logTag, "Monitor Location Exit");

        doStopListening();

        finish();
    }

    void doStopListening() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (_networkListener != null) {
            lm.removeUpdates(_networkListener);
            _networkListener = null;
        }
        if (_gpsListener != null) {
            lm.removeUpdates(_gpsListener);
            _gpsListener = null;
        }
    }

}
