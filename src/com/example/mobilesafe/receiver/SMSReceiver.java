package com.example.mobilesafe.receiver;

import com.example.mobilesafe.R;
import com.example.mobilesafe.service.GPSService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	private static final String TAG = "SMSReceiver";
	private SharedPreferences sp;

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		
		for(Object b:objs){
			//�����ĳһ������
			SmsMessage sms =SmsMessage.createFromPdu((byte[]) b);
			//������
			String sender = sms.getOriginatingAddress();//15555555556
			String safenumber = sp.getString("safenumber", "");//5556
			//5556
			///1559999995556
			Log.e(TAG, "====sender=="+sender);
			String body = sms.getMessageBody();
			
			if (sender.contains(safenumber)) {
				
				if ("#*location*#".equals(body)) {
					//�õ��ֻ���GPS
					Log.i(TAG, "�õ��ֻ���GPS");
					//��������
					Intent i = new Intent(context, GPSService.class);
					context.startService(i);
					SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
					String lastlocation = sp.getString("lastlocation", null);
					if (TextUtils.isEmpty(lastlocation)) {
						SmsManager.getDefault().sendTextMessage(sender, null, "geting loaction.....", null, null);
					} else {
						SmsManager.getDefault().sendTextMessage(sender, null, lastlocation, null, null);
					}
					
					abortBroadcast();
				} else if ("#*alarm*#".equals(body)) {
					Log.i(TAG, "���ű���Ӱ��");
					MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
					player.setLooping(false); //ʵ��true
					player.setVolume(1.0f, 1.0f);
					player.start();
					
					abortBroadcast();
				}
				else if("#*wipedata*#".equals(body)){
					Log.e(TAG, "Զ���������");
					abortBroadcast();
				}
				else if("#*lockscreen*#".equals(body)){
					Log.e(TAG, "Զ������");
					abortBroadcast();
				}
			}
		}
	}

}
