package com.example.nomnomgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Pause extends Activity {
	
		ImageButton resume;
		ImageButton quit;
		ImageButton menu;

		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pause);
			
			resume = (ImageButton) findViewById(R.id.resume);
			resume.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					getIntent();
					finish();
					
				}
			});
			
			quit = (ImageButton) findViewById(R.id.quit);
			quit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {	
				Intent intent = new Intent(Pause.this, Quit.class);
				startActivity(intent);
				}
			});
			
		
		}
	
		
}
