package com.example.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LostFindActivity extends Activity {
	private final String TAG = "LostFindActivity";
	private SharedPreferences sp;
	private TextView tv_lostfind_safenum;
	private ImageView iv_lostfind_protecting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);
		boolean configed = sp.getBoolean("configed", false);
		if (configed) {
			setContentView(R.layout.activity_lostfind);
			tv_lostfind_safenum = (TextView) findViewById(R.id.tv_lostfind_safenum);
			tv_lostfind_safenum.setText(sp.getString("safenumber", ""));
			iv_lostfind_protecting = (ImageView) findViewById(R.id.iv_lostfind_protecting);
			boolean protecting = sp.getBoolean("protecting", false);
			if (protecting) {
				iv_lostfind_protecting.setImageResource(R.drawable.lock);
			} else {
				iv_lostfind_protecting.setImageResource(R.drawable.unlock);
			}
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
