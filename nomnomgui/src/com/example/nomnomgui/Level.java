package com.example.nomnomgui;

import com.example.entity.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Level extends Activity {
	ImageButton go;
	ImageButton grow;
	ImageButton glow;
	//Person p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		go = (ImageButton) findViewById(R.id.go);
		grow = (ImageButton) findViewById(R.id.grow);
		glow = (ImageButton) findViewById(R.id.glow);
	//	p = getIntent().getParcelableExtra("player");
		
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(Level.this, Game.class);
		//	intent.putExtra("player", p);
			startActivity(intent);
			
			}
		});
		
		grow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
				Intent intent = new Intent(Level.this, Game.class);
			startActivity(intent);
			}
		});
		
		glow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
				Intent intent = new Intent(Level.this, Game.class);
			startActivity(intent);
			}
		});
	}
}
