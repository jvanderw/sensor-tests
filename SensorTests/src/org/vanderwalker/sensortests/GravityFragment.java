package org.vanderwalker.sensortests;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GravityFragment extends Fragment implements SensorEventListener {

    private static final String TAG = "GravityFragment";

    SensorManager sensorMgr;
    Sensor gravitySensor;
    TextView xAxisText;
    TextView yAxisText;
    TextView zAxisText;

    public GravityFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gravity, container);
        xAxisText = (TextView) view.findViewById(R.id.textViewGravityX);
        yAxisText = (TextView) view.findViewById(R.id.textViewGravityY);
        zAxisText = (TextView) view.findViewById(R.id.textViewGravityZ);
        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the sensors
        sensorMgr = ((SensorTestsApp) getActivity().getApplication()).getSensorManager();
        gravitySensor = sensorMgr.getDefaultSensor(Sensor.TYPE_GRAVITY);

        Log.d(TAG, "onCreate");
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    public void onSensorChanged(SensorEvent event) {

        String x = String.format("%.02f", event.values[0]);
        String y = String.format("%.02f", event.values[1]);
        String z = String.format("%.02f", event.values[2]);

        xAxisText.setText(x);
        yAxisText.setText(y);
        zAxisText.setText(z);
    }

    // Register to listen
    @Override
    public void onResume() {
        super.onResume();
        sensorMgr.registerListener(this, gravitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        ;

        Log.d(TAG, "onResume");
    }

    // Unregister
    @Override
    public void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);

        Log.d(TAG, "onPause");
    }

}
