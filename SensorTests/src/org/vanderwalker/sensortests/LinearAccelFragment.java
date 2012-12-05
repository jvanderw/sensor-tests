package org.vanderwalker.sensortests;

import org.vanderwalker.sensortests.CalibrateDialogFragment.CalibrateDialogListener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LinearAccelFragment extends Fragment implements
        SensorEventListener, CalibrateDialogListener, OnClickListener {

    private static final String TAG = "LinearAccelFragment";

    SensorManager sensorMgr;
    Sensor accelSensor;
    TextView xAccelText;
    TextView yAccelText;
    TextView zAccelText;

    private float accelXOffset = 0;
    private float accelYOffset = 0;
    private float accelZOffset = 0;

    private float accelX = 0;
    private float accelY = 0;
    private float accelZ = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_linear_accel, container);

        // TextViews for the acceleration sensor.
        xAccelText = (TextView) view.findViewById(R.id.textViewAccelX);
        yAccelText = (TextView) view.findViewById(R.id.textViewAccelY);
        zAccelText = (TextView) view.findViewById(R.id.textViewAccelZ);

        // Attach handler for button
        Button cb = (Button) view.findViewById(R.id.buttonCalibrateAccel);
        cb.setOnClickListener(this);

        // Get the sensors
        sensorMgr = ((SensorTestsApp) getActivity().getApplication())
                .getSensorManager();
        accelSensor = sensorMgr
                .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        Log.d(TAG, "onCreate");

        return view;
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        Log.d(TAG, "onAccuracyChanged");
    }

    public void onSensorChanged(SensorEvent event) {
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];

        // Remove offsets
        String x = String.format("%.02f", accelX - accelXOffset);
        String y = String.format("%.02f", accelY - accelYOffset);
        String z = String.format("%.02f", accelZ - accelZOffset);

        xAccelText.setText(x);
        yAccelText.setText(y);
        zAccelText.setText(z);
    }

    // Register to listen
    @Override
    public void onResume() {
        super.onResume();
        sensorMgr.registerListener(this, accelSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onResume");
    }

    // Unregister
    @Override
    public void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);

        Log.d(TAG, "onPause");
    }

    // Display the calibrate dialog
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        CalibrateDialogFragment dialogFragment = new CalibrateDialogFragment(getId());
        dialogFragment.show(fm, "calibrate_dialog_title");
        Log.d(TAG, "onClick");
    }

    public void onFinishCalibrateDialog() {
        // Get the current values of the sensor, and use those as the offsets
        accelXOffset = accelX;
        accelYOffset = accelY;
        accelZOffset = accelZ;
        Log.d(TAG, "onFinishCalibrateDialog");
    }

}
