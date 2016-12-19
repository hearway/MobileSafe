package com.example.mobilesafe.adapter;

import java.util.List;

import com.example.mobilesafe.HomeActivity;
import com.example.mobilesafe.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeAdapter extends BaseAdapter {
	
	private List<String> names;

	@Override
	public int getCount() {
		return names.size();
	}

	@Override
	public Object getItem(int position) {
		return names.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		View view = View.inflate(getC, resource, root)
//		View view = View.inflate(HomeActivity.class, R.layout.list_item, null);
		return null;
	}

}
