package com.example.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private final int ITEM1 = 1;
	private final int ITEM2 = 2;
	private final int ITEM3 = 3;
	private final int ITEM4 = 4;
	private final int ITEM5 = 5;
	private final int ITEM6 = 6;

	private GridView gv_home_list;
	private static String [] names = {
			"锁屏流量", "应用唤醒" , "联网控制",
			"锁屏联网", "待机联网" , "系统设置"
	};
	private static int[] ids = {
			R.drawable.callmsgsafe, R.drawable.app, R.drawable.netmanager,
			R.drawable.taskmanager, R.drawable.atools, R.drawable.settings
		};
	
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		gv_home_list = (GridView) findViewById(R.id.gv_home_list);
		adapter = new MyAdapter();
		gv_home_list.setAdapter(adapter);
		
		gv_home_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {				
				switch (position) {
				case ITEM1:
					
					break;
				case ITEM2:
					break;
				case ITEM3:
					break;
				case ITEM4:
					break;
				case ITEM5:
					break;
				case ITEM6:
					Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			return names[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(HomeActivity.this, R.layout.list_item_home, null);
			ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
			TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
			
			tv_item.setText(names[position]);
			iv_item.setImageResource(ids[position]);
			return view;
		}
		
	}
	

}
