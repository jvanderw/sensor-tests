package org.vanderwalker.sensortests;

import java.util.List;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorTestsApp extends Application {

	private static final String TAG = SensorTestsApp.class.getSimpleName();
	public SensorManager sensorMgr;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		this.sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		// TODO: Get a list of the sensors on the device, so we know what
		// features can be enabled.
		List<Sensor> allSensors = this.sensorMgr.getSensorList(Sensor.TYPE_ALL);
				
		Log.d(TAG, "onCreate");
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.d(TAG, "onTerminate");
	}
	
	public SensorManager getSensorManager() {
		return this.sensorMgr;
	}
}
