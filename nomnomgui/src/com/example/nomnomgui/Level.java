package com.example.nomnomgui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Level extends Activity {
	ImageButton go;
	ImageButton grow;
	ImageButton glow;
	String gender = "", stage = "";
	int score = 0, goodIntake = 0, badIntake = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		go = (ImageButton) findViewById(R.id.go);
		grow = (ImageButton) findViewById(R.id.grow);
		glow = (ImageButton) findViewById(R.id.glow);
		readSomeValues();

		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				stage = "go";
				saveSomeValues();
				Intent intent = new Intent(Level.this, Ins.class);
				startActivity(intent);

			}
		});

		grow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				stage = "grow";
				saveSomeValues();
				Intent intent = new Intent(Level.this, Ins.class);
				startActivity(intent);
				
			}
		});

		glow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				stage = "glow";
				saveSomeValues();
				Intent intent = new Intent(Level.this, Ins.class);
				startActivity(intent);
				
			}
		});
	}

	public void readSomeValues() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(
					this.openFileInput("nomnomnomfile")));
			gender = input.readLine();
			stage = input.readLine();
			score = Integer.parseInt(input.readLine());
			goodIntake = Integer.parseInt(input.readLine());
			badIntake = Integer.parseInt(input.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveSomeValues() {
		String eol = System.getProperty("line.separator");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					this.openFileOutput("nomnomnomfile", Context.MODE_PRIVATE)));
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
