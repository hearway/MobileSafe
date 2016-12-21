package com.example.mobilesafe;

import java.util.ArrayList;
import java.util.List;

import com.example.mobilesafe.fragment.SetupFragment1;
import com.example.mobilesafe.fragment.SetupFragment2;
import com.example.mobilesafe.fragment.SetupFragment3;
import com.example.mobilesafe.fragment.SetupFragment4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetupActivity extends FragmentActivity implements OnClickListener {
	
	private static final String TAG = "SetupActivity";
	private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    
    private Button btn_next;
    private Button btn_pre;
    
    private SharedPreferences sPreferences;
    
    private int currentPosition = 0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		
		mViewPager = (ViewPager) findViewById(R.id.vp_setup);
		
		btn_pre = (Button) findViewById(R.id.btn_pre);
		btn_next = (Button) findViewById(R.id.btn_next);
		
		btn_pre.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		
		sPreferences = getSharedPreferences("config", MODE_PRIVATE);
		
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new SetupFragment1());
		mFragments.add(new SetupFragment2());
		mFragments.add(new SetupFragment3());
		mFragments.add(new SetupFragment4());
		
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return mFragments.size();
			}
			
			@Override
			public Fragment getItem(int position) {
				return mFragments.get(position);
			}
		};
		
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if (0 == position) {
					btn_pre.setVisibility(View.INVISIBLE);
				} else if (3 == position) {
					btn_next.setText("�������");
				} else {
					btn_next.setText("��һ��");
					btn_pre.setVisibility(View.VISIBLE);
				}
				mViewPager.setCurrentItem(position);
				currentPosition = position;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_next:
			if (3 == currentPosition) {
				Editor editor = sPreferences.edit();
				editor.putBoolean("configed", true);
				editor.commit();
				Intent intent = new Intent(this, LostFindActivity.class);
				startActivity(intent);
				finish();
			} else if (2 == currentPosition) {
				EditText et_setup3_phone = (EditText) findViewById(R.id.et_setup3_phone);
				String phone = et_setup3_phone.getText().toString().trim();
				if (TextUtils.isEmpty(phone)) {
					Toast.makeText(this, "��ȫ���뻹û������", Toast.LENGTH_SHORT).show();
				} else {				
					Editor editor = sPreferences.edit();
					editor.putString("safenumber", phone);
					editor.commit();
				
					mViewPager.setCurrentItem(++currentPosition);
				}
			} else {
				mViewPager.setCurrentItem(++currentPosition);
			}
			break;
		case R.id.btn_pre:
			Log.e(TAG, "btn_pre onClicked");
			mViewPager.setCurrentItem(--currentPosition);
			break;
		default:
			break;
		}
		
	}
	

}
