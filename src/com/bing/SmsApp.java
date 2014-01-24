package com.bing;

import android.app.Application;

public class SmsApp extends Application{
	private String Setting;
	private static final String NEWSET="open_sound";
	public void onCreate(){
		super.onCreate();
		set(NEWSET);
	}
	 
	public String getSetting(){
		return Setting;
	}
	
	public void set(String setting){
		this.Setting=setting;
	}
	

}
