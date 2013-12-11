package com.example.nomnomgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class Ins extends Activity {
	ImageButton ins;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ins);
		
		ins = (ImageButton) findViewById(R.id.inspic);
		
		ins.setOnClickListener(new OnClickListener() {	

			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(Ins.this, Game.class);
			startActivity(intent);
			}
		});
	}
		
	

}
