package com.example.nomnomgui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.example.entity.Person;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Start extends Activity {
	ImageButton boy;
	ImageButton girl;
	
	String gender = "", stage = "";
	int score = 0,goodIntake = 0,badIntake = 0;
	
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
			Intent intent = new Intent(Start.this, Level.class);;
			startActivity(intent);
			gender = "boy";
			saveSomeValues();
			}
		});
		
		girl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
				Intent intent = new Intent(Start.this, Level.class);
				startActivity(intent);
				gender = "girl";
				saveSomeValues();
				}
			});
	}
	public void saveSomeValues()
	{
		String eol = System.getProperty("line.separator");
    	BufferedWriter writer = null;
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(this.openFileOutput("nomnomnomfile", Context.MODE_PRIVATE)));
    		writer.write(gender+eol);
    		writer.write(stage+eol);
    		writer.write(score+eol);
    		writer.write(goodIntake+eol);
    		writer.write(badIntake+eol);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (writer != null) {
    			try {
    				writer.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    		}
    	}
	}		
}
