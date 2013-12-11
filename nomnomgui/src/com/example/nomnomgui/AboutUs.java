package com.example.nomnomgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AboutUs extends Activity {
	
	ImageButton backbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
		
		backbtn = (ImageButton) findViewById(R.id.backbtn);
		
		backbtn.setOnClickListener(new OnClickListener() {	

			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(AboutUs.this, MainActivity.class);
			startActivity(intent);
			}
		});
	}
		


}
