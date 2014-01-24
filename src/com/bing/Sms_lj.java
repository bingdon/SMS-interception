package com.bing;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class Sms_lj extends BroadcastReceiver{
	private static final String TAG="Sms_lj";
	private static final String SMS_RECEIVED_ACTION="android.provider.Telephony.SMS_RECEIVED";
	private static final String SETTING_ACTION="com.bing.sms";
	private String settingString=null;
	private SmsApp app;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		app=(SmsApp)context.getApplicationContext();
		String action = intent.getAction();
		settingString=app.getSetting();
		
		
	    if (SMS_RECEIVED_ACTION.equals(action)) {
			Bundle bundle = intent.getExtras(); // 获取intent
			if (bundle!=null) {
				Object[] pdus=(Object[])bundle.get("pdus");
				SmsMessage[] messages=new SmsMessage[pdus.length];
				for (int i = 0; i < messages.length; i++) {
					byte[] pdu=(byte[])pdus[i];
					messages[i] = SmsMessage.createFromPdu(pdu); 
				}
				for (SmsMessage smsMessage : messages) {
					String cont= smsMessage.getMessageBody();
					String send_addrres = smsMessage.getOriginatingAddress();
					Date date=new Date(smsMessage.getTimestampMillis());
					SimpleDateFormat dateFormat = new SimpleDateFormat();
					String send_time= dateFormat.format(date);
					if ("+8615146690080".equals(send_addrres)) {
						
						 Toast.makeText(context, "发送人"+send_addrres+"信息:"+cont+settingString, 3000).show();
						abortBroadcast();
						NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
						Notification notification= new Notification(R.drawable.ll, "信息拦截", System.currentTimeMillis());
						Intent mIntent=new Intent(context, MainActivity.class);
						PendingIntent mpPendingIntent=PendingIntent.getActivity(context, 0, mIntent, android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
						notification.setLatestEventInfo(context, "短信拦截", "通知内容", mpPendingIntent);
						if (settingString.equals("open_sound")) {
							notification.defaults=Notification.DEFAULT_SOUND;
						} 
						else if(settingString.equals("open_vir")){
							notification.defaults=Notification.DEFAULT_VIBRATE;
						}
						
						//notification.defaults=Notification.DEFAULT_SOUND;
						//notification.defaults=Notification.DEFAULT_VIBRATE;
						notificationManager.notify(1, notification);
					} else {
                        Toast.makeText(context, "发送人"+send_addrres+"信息:"+cont+"时间："+send_time, 3000).show();
					}
				}
			}
		}

}
}
