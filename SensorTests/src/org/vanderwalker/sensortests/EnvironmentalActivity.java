package org.vanderwalker.sensortests;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class EnvironmentalActivity extends Activity {

	public static final String TAG = "EnvironmentalActivity";
	SensorManager sensorMgr;
	Sensor ambTempSensor;
	Sensor relHumSensor;
	Sensor atmPreSensor;
	EnvironmentListener ambTempListener;
	EnvironmentListener relHumListener;
	EnvironmentListener atmPreListener;
	TextView ambTempView;
	TextView relHumView;
	TextView atmPreView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        
        // Get the text views for sensor output
        ambTempView = (TextView) findViewById(R.id.textViewAmbTemp);
        relHumView = (TextView) findViewById(R.id.textViewRelHum);
        atmPreView = (TextView) findViewById(R.id.textViewAtmPre);
        
        // Get the sensors
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ambTempSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        relHumSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        atmPreSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PRESSURE);
        
        // Create the listeners
    	ambTempListener = new EnvironmentListener(ambTempView);
    	relHumListener = new EnvironmentListener(relHumView);
    	atmPreListener = new EnvironmentListener(atmPreView);
    	
        Log.d(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sensors, menu);
        return true;
    }
   
    @Override
    public void onResume() {
    	super.onResume();
    	
    	// Set listener for the sensors
    	sensorMgr.registerListener(ambTempListener, ambTempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    	sensorMgr.registerListener(relHumListener, relHumSensor, SensorManager.SENSOR_DELAY_NORMAL);
    	sensorMgr.registerListener(atmPreListener, atmPreSensor, SensorManager.SENSOR_DELAY_NORMAL);
    	
    	Log.d(TAG, "onResume");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	
    	// Unregister the listeners
    	sensorMgr.unregisterListener(ambTempListener);
    	sensorMgr.unregisterListener(relHumListener);
    	sensorMgr.unregisterListener(atmPreListener);    	
    	
    	Log.d(TAG, "onPause()");
    }

	// Handle events for environment sensors
	public class EnvironmentListener implements SensorEventListener {

		private TextView view;
		
		// Constructor takes the view to update with the sensor output.
		EnvironmentListener(TextView v) {
			super();
			this.view = v;
		}
		public void onAccuracyChanged(Sensor arg0, int arg1) {
		}

		public void onSensorChanged(SensorEvent event) {
			view.setText(Float.toString(event.values[0]));
		}

	}
    
}
