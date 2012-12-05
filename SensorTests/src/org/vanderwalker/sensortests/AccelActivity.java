package org.vanderwalker.sensortests;

import org.vanderwalker.sensortests.CalibrateDialogFragment.CalibrateDialogListener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AccelActivity extends FragmentActivity implements
        SensorEventListener, CalibrateDialogListener {

    private static final String TAG = "Gravity";

    SensorManager sensorMgr;
    Sensor gravitySensor;
    Sensor accelSensor;
    AccelListener accelListener;
    TextView xAxisText;
    TextView yAxisText;
    TextView zAxisText;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel);

        // TextViews for the gravity sensor results
        xAxisText = (TextView) findViewById(R.id.textViewGravityX);
        yAxisText = (TextView) findViewById(R.id.textViewGravityY);
        zAxisText = (TextView) findViewById(R.id.textViewGravityZ);

        // TextViews for the acceleration sensor.
        xAccelText = (TextView) findViewById(R.id.textViewAccelX);
        yAccelText = (TextView) findViewById(R.id.textViewAccelY);
        zAccelText = (TextView) findViewById(R.id.textViewAccelZ);

        accelListener = new AccelListener();

        // Get the sensors
        sensorMgr = ((SensorTestsApp) getApplication()).getSensorManager();
        gravitySensor = sensorMgr.getDefaultSensor(Sensor.TYPE_GRAVITY);
        accelSensor = sensorMgr
                .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        Log.d(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sensors, menu);
        return true;
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        Log.d(TAG, "onAccuracyChanged");
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
        sensorMgr.registerListener(accelListener, accelSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onResume");
    }

    // Unregister
    @Override
    public void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);
        sensorMgr.unregisterListener(accelListener);

        Log.d(TAG, "onPause");
    }

    /**
     * Display the calibration dialog.
     */
    public void showCalibrateDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        CalibrateDialogFragment dialogFragment = new CalibrateDialogFragment();
        dialogFragment.show(fm, "calibrate_dialog_title");
    }

    public void onFinishCalibrateDialog() {
        // Get the current values of the sensor, and use those as the offsets
        accelXOffset = accelX;
        accelYOffset = accelY;
        accelZOffset = accelZ;
        Log.d(TAG, "onFinishCalibrateDialog");
    }

    // Listener class to handle the acceleration sensor events.
    class AccelListener implements SensorEventListener {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
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

    }

}
