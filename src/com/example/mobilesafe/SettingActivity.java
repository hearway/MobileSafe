package com.example.mobilesafe;

import com.example.mobilesafe.ui.SettingItemView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {
	private SettingItemView siv_update;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		siv_update = (SettingItemView) findViewById(R.id.siv_update);
		
		boolean update = sp.getBoolean("update", false);
		if (update) {
			siv_update.setChecked(true);
			siv_update.setDesc("�Զ������Ѿ�����");
		} else {
			siv_update.setChecked(false);
			siv_update.setDesc("�Զ������Ѿ��ر�");
		}
		
		siv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editor editor = sp.edit();
				if ( siv_update.isChecked()) {
					siv_update.setChecked(false);
					siv_update.setDesc("�Զ������Ѿ��ر�");
					editor.putBoolean("update", false);
					
				} else {
					siv_update.setChecked(true);
					siv_update.setDesc("�Զ������Ѿ�����");
					editor.putBoolean("update", true);
				}
				editor.apply();
			}
		});
	}

}
