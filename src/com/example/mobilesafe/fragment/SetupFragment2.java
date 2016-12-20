package com.example.mobilesafe.fragment;

import com.example.mobilesafe.R;
import com.example.mobilesafe.ui.SettingItemView;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SetupFragment2 extends Fragment {
	
	protected static final String TAG = "SetupFragment2";
	private SharedPreferences sp;
	private SettingItemView siv_setup2_sim;
	private TelephonyManager tm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setup2, container, false);
		
		sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
		tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		siv_setup2_sim = (SettingItemView) view.findViewById(R.id.siv_setup2_sim);
		
		String sim = sp.getString("sim", null);
		if (TextUtils.isEmpty(sim)) {
			siv_setup2_sim.setChecked(false);
		} else {
			siv_setup2_sim.setChecked(true);
		}
		
		siv_setup2_sim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e(TAG, "siv_setup2_sim onClicked");
				Editor editor = sp.edit();
				
				if (siv_setup2_sim.isChecked()) {
					siv_setup2_sim.setChecked(false);
					editor.putString("sim", null);
				} else {
					siv_setup2_sim.setChecked(true);
					String sim = tm.getSimSerialNumber();
					editor.putString("sim", sim);
				}
				editor.commit();
			}
		});
		
		
		return view;
	}
	
}
