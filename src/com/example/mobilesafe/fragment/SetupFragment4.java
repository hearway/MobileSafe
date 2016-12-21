package com.example.mobilesafe.fragment;

import com.example.mobilesafe.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SetupFragment4 extends Fragment {
	
	private SharedPreferences sp;
	
	private CheckBox cb_step4_proteting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setup4, container, false);
		
		sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
		cb_step4_proteting = (CheckBox) view.findViewById(R.id.cb_step4_proteting);
		
		boolean  protecting = sp.getBoolean("protecting", false);
		if (protecting) {
			cb_step4_proteting.setText("手机防盗已经开启");
			cb_step4_proteting.setChecked(true);
		} else {
			cb_step4_proteting.setText("手机防盗没有开启");
			cb_step4_proteting.setChecked(false);
			
		}
		
		cb_step4_proteting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_step4_proteting.setText("手机防盗已经开启");
				} else {
					cb_step4_proteting.setText("手机防盗没有开启");
				}
				
				//保存选择的状态
				Editor editor = sp.edit();
				editor.putBoolean("protecting", isChecked);
				editor.commit();
			}

		});
		
		return view;
	}
	
}
