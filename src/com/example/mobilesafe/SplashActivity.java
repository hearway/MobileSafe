package com.example.mobilesafe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {

	private static final String TAG = "SplashActivity";
	
	private TextView tv_splash_version;
	private SharedPreferences sPreferences;
	
	private Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
        }  
    };  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		initViews();
		initDatas();
		initEvents();
	}
	
	private void initEvents() {
		boolean update = sPreferences.getBoolean("update", false);
		if (update) {
			checkUpdate();
		} else {
			mHandler.postDelayed(new Runnable() {  
	            @Override  
	            public void run() {  
	            	RelativeLayout rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
	            	rl_splash.setBackground(getResources().getDrawable(R.drawable.ad));
	                runAnimator(rl_splash);
	            }  
	        }, 200);
		}
	}

	private void checkUpdate() {		
	}

	private void initDatas() {
		sPreferences = getSharedPreferences("config", MODE_PRIVATE);
	}

	private void initViews() {
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本号 " + getVersionName());
	}

	private void runAnimator(RelativeLayout rl_splash) {  
        Log.e(TAG, "展示广告动画1s-----2");  
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rl_splash, "alpha", 0, 1).setDuration(1000);  
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());  
        objectAnimator.addListener(new AnimatorListenerAdapter() {  
            @Override  
            public void onAnimationEnd(Animator animation) {  
                enterMainPage();  
            }  
        });  
        objectAnimator.start();
    }

	protected void enterMainPage() {
		Log.e(TAG, "广告展示3s完毕进入主页-----3");  
        mHandler.postDelayed(new Runnable() {  
            @Override  
            public void run() {  
                Intent intent = new Intent();  
                intent.setClass(getApplicationContext(), HomeActivity.class);  
                startActivity(intent);  
                finish();  
            }  
        }, 3000);
		
	}
	
	private String getVersionName() {
		PackageManager pManager = getPackageManager();
		try {
			PackageInfo info = pManager.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, "can't find " + getPackageName());
			return "";
		}
	}
	
}
