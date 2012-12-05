package org.vanderwalker.sensortests;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AtmPressureFragment extends Fragment implements
        SensorEventListener {

    public static final String TAG = "AtmPressureFragment";

    SensorManager sensorMgr;
    Sensor atmPreSensor;
    TextView atmPreView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atm_pressure, container);

        // Get the text view for sensor output
        atmPreView = (TextView) view.findViewById(R.id.textViewAtmPre);

        // Get the sensor and add listener
        sensorMgr = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        atmPreSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PRESSURE);

        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorMgr.registerListener(this, atmPreSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);

        Log.d(TAG, "onPause()");
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    public void onSensorChanged(SensorEvent event) {
        atmPreView.setText(Float.toString(event.values[0]));
    }

}
