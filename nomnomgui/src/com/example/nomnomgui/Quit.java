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
import android.widget.ImageView;
import android.widget.TextView;

public class Quit extends Activity {
	ImageButton playagain;
	ImageButton menu2;
	ImageView character;
	TextView finalScore;
	TextView good;
	TextView bad;
	String gender = "", stage = "";
	int score = 0, goodIntake = 0, badIntake = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quit);
		readSomeValues();
		finalScore = (TextView) findViewById(R.id.score2);
		good = (TextView) findViewById(R.id.good);
		bad = (TextView) findViewById(R.id.bad);
		
		int score = getIntent().getIntExtra("scoreview", 0);
		int goodIntake = getIntent().getIntExtra("good", 0);
		int badIntake = getIntent().getIntExtra("bad", 0);
		
		finalScore.setText(""+score);
		good.setText("= "+goodIntake);
		bad.setText("= "+badIntake);
		

		playagain = (ImageButton) findViewById(R.id.playagain);
		menu2 = (ImageButton) findViewById(R.id.menu2);
		character = (ImageView) findViewById(R.id.girlquit);
		// ---------------CONDITION LINE STARTS HERE-----------------------
		if (gender.equals("boy")) {
			if (stage.equals("go")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.boy);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.cribeokgo);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.cribebadgo);
				}
			}if (stage.equals("glow")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.cribegoodglow);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.boy);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.cribebadglowpimple);
				}
			}if (stage.equals("grow")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.cribegoodgrow);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.boy);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.cribefat);
				}
			}
		}
		if (gender.equals("girl")) {
			if (stage.equals("go")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.girl);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.laraokgo);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.larabadgo);
				}
			}if (stage.equals("glow")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.laragoodglow);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.girl);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.larabadglowpimple);
				}
			}if (stage.equals("grow")) {
				if (goodIntake > badIntake) {
					character.setImageResource(R.drawable.laragoodgrow);
				}
				if (goodIntake == badIntake) {
					character.setImageResource(R.drawable.girl);
				}
				if (goodIntake < badIntake) {
					character.setImageResource(R.drawable.larabadgrow);
				}
			}
		}
		// ---------------CONDITION LINE ENDS HERE-----------------------
		
		
		playagain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(Quit.this, Game.class);
				
				startActivity(intent);
				
				
			}
		});

		menu2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Quit.this, MainActivity.class);
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
			//score = Integer.parseInt(input.readLine());
			//goodIntake = Integer.parseInt(input.readLine());
			//badIntake = Integer.parseInt(input.readLine());
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
			writer.write(gender + eol);
			writer.write(stage + eol);
			//writer.write(score + eol);
			//writer.write(goodIntake + eol);
			//writer.write(badIntake + eol);
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
