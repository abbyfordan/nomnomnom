package com.example.nomnomgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	ImageButton start;
	ImageButton ins;
	ImageButton aboutus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		start = (ImageButton) findViewById(R.id.start);
		ins = (ImageButton) findViewById(R.id.ins);
		aboutus = (ImageButton) findViewById (R.id.aboutus);
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(MainActivity.this, Start.class);
			startActivity(intent);
			}
		});
		
		

		ins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(MainActivity.this, Ins.class);
			startActivity(intent);
			}
		});

	
		aboutus.setOnClickListener(new OnClickListener() {	

		@Override
		public void onClick(View arg0) {	
		Intent intent = new Intent(MainActivity.this, AboutUs.class);
		startActivity(intent);
		}
	});
}
	
	
@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
