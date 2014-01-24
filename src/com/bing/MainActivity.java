package com.bing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private String sv=null;
	 private Button soundButton,virButton;
	 private SmsApp app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		app=(SmsApp)getApplication();
		sv=app.getSetting();
		soundButton=(Button)findViewById(R.id.button1);
		soundButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (app.getSetting()=="close_sound") {
					app.set("open_sound");
					soundButton.setText("关闭声音");
					
				}
				else {
					app.set("close_sound");
					soundButton.setText("开启声音");
				}
				//Toast.makeText(MainActivity.this, "数值："+app.getSetting(), 5000).show();
			}
		});
		virButton=(Button)findViewById(R.id.button3);
		virButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (app.getSetting().equals("close_vir")) {
					app.set("open_vir");
					virButton.setText("关闭震动");
				}else {
					app.set("close_vir");
					virButton.setText("打开震动");
				}
				//Toast.makeText(MainActivity.this, "数值："+app.getSetting(), 5000).show();
				
			}
		});
		
		intliza();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void intliza(){
		if (app.getSetting().equals("open_sound")) {
			soundButton.setText("关闭声音");
		}
		if (app.getSetting().equals("open_vir")) {
			virButton.setText("关闭震动");
		}
		else {
			soundButton.setText("打开声音");
			virButton.setText("打开震动");
		}
	}
}
