package org.vanderwalker.sensortests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class SensorsActivity extends Activity {

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
	
	// Start the AccelActivity - handler hooked to button in view
	public void startAccelActivity(View view) {
		Intent intent = new Intent(this, AccelActivity.class);
		startActivity(intent);
		Log.d(TAG, "startAccelActivity");
	}
	
	// Start the EnvironmentalActivity - handler hooked to button in view
	public void startEnvironmentalActivity(View view) {
		Intent intent = new Intent(this, EnvironmentalActivity.class);
		startActivity(intent);
		Log.d(TAG, "startEnvironmentalActivity");
	}
}
