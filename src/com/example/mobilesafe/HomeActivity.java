package com.example.mobilesafe;

import com.example.mobilesafe.ui.MD5Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	protected static final String TAG = "HomeActivity";
	private final int ITEM0 = 0;
	private final int ITEM1 = 1;
	private final int ITEM2 = 2;
	private final int ITEM3 = 3;
	private final int ITEM4 = 4;
	private final int ITEM5 = 5;
	private SharedPreferences sPreferences;
	
	private EditText et_setup_pwd;
	private EditText et_setup_confirm;
	
	private Button btn_ok;
	private Button btn_cancel;
	private AlertDialog dialog;

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
		sPreferences = getSharedPreferences("config", MODE_PRIVATE);
		
		gv_home_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {				
				switch (position) {
				case ITEM0:
					break;
				case ITEM1:
					Log.e(TAG, "ITEM1 clicked");
					break;
				case ITEM2:
					break;
				case ITEM3:
					break;
				case ITEM4:
					showLostFindDialog();
					break;
				case ITEM5:
					Log.e(TAG, "ITEM6 clicked");
					Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}
	
	protected void showLostFindDialog() {
		if (isSetupPwd()) {
			showEnterDialog();
		} else {
			showSetupPwdDialog();
		}
	}

	private void showEnterDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		View view = View.inflate(HomeActivity.this, R.layout.dialog_enter_password, null);
		et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String pwd = et_setup_pwd.getText().toString().trim();
				String savedPwd = sPreferences.getString("password", null);
				if (TextUtils.isEmpty(pwd)) {
					Toast.makeText(HomeActivity.this, "输入密码为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (MD5Utils.md5Password(pwd).equals(savedPwd)) {
					dialog.dismiss();
					Intent intent = new Intent(HomeActivity.this, LostFindActivity.class);
					startActivity(intent);
					Log.e(TAG, "密码一致");
				} else {
					et_setup_pwd.setText("");
					Toast.makeText(HomeActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setView(view);
		dialog = builder.show();
		
	}
	
	private void showSetupPwdDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		View view = View.inflate(HomeActivity.this, R.layout.dialog_setup_password, null);
		et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
		et_setup_confirm = (EditText) view.findViewById(R.id.et_setup_confirm);
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String pwd = et_setup_pwd.getText().toString().trim();
				String pwdConfirm = et_setup_confirm.getText().toString().trim();
				if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdConfirm)) {
					Toast.makeText(HomeActivity.this, "密码为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (pwd.equals(pwdConfirm)) {
					Editor editor = sPreferences.edit();
					editor.putString("password", MD5Utils.md5Password(pwd));
					editor.commit();
					dialog.dismiss();
					Log.e(TAG, "密码保存成功");
					Intent intent = new Intent(HomeActivity.this, LostFindActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(HomeActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setView(view);
		dialog = builder.show();
	}

	private boolean isSetupPwd() {
		String password = sPreferences.getString("password", null);
		
		if (TextUtils.isEmpty(password)) {
			return false;
		}
		
		return true;
		
//		return !TextUtils.isEmpty(password);
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
