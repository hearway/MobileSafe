package com.example.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class LostFindActivity extends Activity {
	private final String TAG = "LostFindActivity";
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);
		boolean configed = sp.getBoolean("configed", false);
		if (configed) {
			setContentView(R.layout.activity_lostfind);
		} else {
			Intent intent = new Intent(this, SetupActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	public void reEnterSetup(View view) {
		Intent intent = new Intent(this, SetupActivity.class);
		startActivity(intent);
		finish();
	}
	
}
