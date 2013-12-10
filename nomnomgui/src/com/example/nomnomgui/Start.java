package com.example.nomnomgui;

import com.example.entity.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Start extends Activity {
	ImageButton boy;
	ImageButton girl;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
		boy = (ImageButton) findViewById(R.id.boy);
		girl = (ImageButton) findViewById(R.id.girl);
		
		boy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
			Intent intent = new Intent(Start.this, Level.class);
			//Person boyp = new Person("boy", 0, 0);
		//	intent.putExtra("player", boyp);
			startActivity(intent);
			//session the boyp
			}
		});
		
		girl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
				Intent intent = new Intent(Start.this, Level.class);
			//	Person girlp = new Person("girl",0,0);
				//intent.putExtra("player", girlp);
				startActivity(intent);
				//session the girlp
				}
			});
	}
			
}
