package com.example.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

	private static final String TAG = "BootCompleteReceiver";
	private SharedPreferences sp;
	private TelephonyManager tm;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		String saveSim = sp.getString("sim", null) + "afu";
		String currentSim = tm.getSimSerialNumber();
		
		if (!saveSim.equals(currentSim)) {
			Toast.makeText(context, "SimCard changed", Toast.LENGTH_LONG).show();
			Log.e(TAG, "SimCard changed");
		} else {
			Toast.makeText(context, "SimCard not changed", Toast.LENGTH_LONG).show();
			Log.e(TAG, "SimCard not changed");
		}
	}

}
