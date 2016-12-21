package com.example.mobilesafe.fragment;

import com.example.mobilesafe.R;
import com.example.mobilesafe.SelectContactActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SetupFragment3 extends Fragment {
	
	private Button btn_select_contact;
	private EditText et_setup3_phone;
	private SharedPreferences sp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setup3, container, false);
		
		btn_select_contact = (Button) view.findViewById(R.id.btn_select_contact);
		btn_select_contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SelectContactActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		
		sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
		et_setup3_phone = (EditText) view.findViewById(R.id.et_setup3_phone);
		et_setup3_phone.setText(sp.getString("safenumber", ""));
		
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			return;
		}
		String phone = data.getStringExtra("phone").replace("-", "");
		et_setup3_phone.setText(phone);
	}
}
