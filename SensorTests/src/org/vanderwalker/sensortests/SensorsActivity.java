package org.vanderwalker.sensortests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class SensorsActivity extends FragmentActivity {

	private static final String TAG = "SensorsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitvity_sensors);
		Log.d(TAG, "onCreate");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sensors, menu);
        return true;
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	// Start the LinearAccelFragment - handler hooked to button in view
	public void startAccelActivity(View view) {
		Intent intent = new Intent(this, LinearAccelFragment.class);
		startActivity(intent);
		Log.d(TAG, "startAccelActivity");
	}
	
	// Start the AtmPressureFragment - handler hooked to button in view
	public void startEnvironmentalActivity(View view) {
		Intent intent = new Intent(this, AtmPressureFragment.class);
		startActivity(intent);
		Log.d(TAG, "startEnvironmentalActivity");
	}
}
