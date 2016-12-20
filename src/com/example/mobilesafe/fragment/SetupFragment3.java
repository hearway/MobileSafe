package com.example.mobilesafe.fragment;

import com.example.mobilesafe.R;
import com.example.mobilesafe.SelectContactActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SetupFragment3 extends Fragment {
	
	private Button btn_select_contact;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setup3, container, false);
		
		btn_select_contact = (Button) view.findViewById(R.id.btn_select_contact);
		btn_select_contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SelectContactActivity.class);
				startActivity(intent);
			}
		});
		
		return view;
	}
	
}
