package com.example.nomnomgui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread logoTimer = new Thread() {
			public void run(){
				try{
					sleep(3000);
					Intent splashIntent = new Intent(Splash.this, MainActivity.class);
					startActivity(splashIntent);
				} 
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		logoTimer.start();
		
	}
	
	
}