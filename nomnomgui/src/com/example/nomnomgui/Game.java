package com.example.nomnomgui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.entity.Person;
import com.example.nomnomgui.R;

@SuppressLint("NewApi")
public class Game extends Activity implements OnTouchListener {
	String gender = "", stage = "";
	int score = 0,goodIntake = 0,badIntake = 0;
	
	private Food[] cloud1 = { new Food("glow", "apple", R.drawable.apple),
			new Food("glow", "good", R.drawable.banana),
			new Food("bad", "bad", R.drawable.burger),
			new Food("go", "good", R.drawable.bread),
			new Food("bad", "bad", R.drawable.cake),
			new Food("bad", "bad", R.drawable.candy),
			new Food("go", "good", R.drawable.cereals),
			new Food("grow", "good", R.drawable.cheese),
			new Food("grow", "good", R.drawable.chicken), 
			new Food("bad", "bad", R.drawable.donut),
			new Food("grow", "good", R.drawable.egg),
			new Food("grow", "good", R.drawable.fish),
			new Food("bad", "bad", R.drawable.fries),
			new Food("bad", "bad", R.drawable.hotdog),
			new Food("glow", "good", R.drawable.pear),
			new Food("glow", "good", R.drawable.pineapple),
			new Food("bad", "bad", R.drawable.longanisa),
			new Food("grow", "good", R.drawable.milk),
			new Food("bad", "bad", R.drawable.pizza),
			new Food("bad", "bad", R.drawable.icecream),
			new Food("bad", "bad", R.drawable.lollipop),
			new Food("go", "good", R.drawable.potato),
			new Food("go", "good", R.drawable.rice),
			new Food("bad", "bad", R.drawable.soda),
			new Food("glow", "good", R.drawable.strawberry),
			new Food("glow", "good", R.drawable.watermelon)};

	private Rect mDisplaySize = new Rect();
	private RelativeLayout mRootLayout;
	private ArrayList<View> mAllImageViews = new ArrayList<View>();

	private float mScale;

	ImageView cloud;
	ImageView cribeopen;
	ImageView charOpen;
	View viewLoad;
	ImageButton pause;
	TextView time;
	TextView scoreview;
	Dialog dialog;
	ImageButton resume;
	ImageButton quit;
	ImageButton menu;
	RelativeLayout game_layout;
	int timer;
	ImageView imageView;
	public boolean isPaused;
	float elapsedTime;

	Timer animTimer;
	Timer countdownTimer;
	Timer scoreTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		readSomeValues();
		super.onCreate(savedInstanceState);
		viewLoad = LayoutInflater.from(Game.this).inflate(R.layout.game, null);
		setContentView(viewLoad);
		timer = 60;
		score = 0;
		elapsedTime = 0;
		isPaused = false;
		cloud = (ImageView) findViewById(R.drawable.cloud);
		pause = (ImageButton) findViewById(R.id.pause);
		time = (TextView) findViewById(R.id.time);
		scoreview = (TextView) findViewById(R.id.score);
		charOpen = (ImageView) findViewById(R.id.laraopen);
		
		if(gender.equals("boy")){
			charOpen.setImageResource(R.drawable.cribeopen);
			
		}if(gender.equals("girl")){
			charOpen.setImageResource(R.drawable.laraopen);
		}
		
		charOpen.setOnTouchListener(this);
		

		animTimer = new Timer();
		countdownTimer = new Timer();
		scoreTimer = new Timer();

		// game_layout = (RelativeLayout) findViewById(R.id.game_layout);

		/*
		 * pause.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { //animator.pause();
		 * //isPaused = true; Intent intent = new Intent(Game.this,
		 * Pause.class); startActivity(intent); } });
		 */

		pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new Dialog(viewLoad.getContext(),
						android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
				View vLoad = LayoutInflater.from(Game.this).inflate(
						R.layout.pause, null);
				dialog.setContentView(vLoad);
				

				isPaused = true;
				elapsedTime = 0;

				resume = (ImageButton) dialog.findViewById(R.id.resume);
				quit = (ImageButton) dialog.findViewById(R.id.quit);

				resume.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// View vLoad = LayoutInflater.from(Game.this).inflate(
						// R.layout.game, null);
						// setContentView(R.layout.game);
						dialog.dismiss();
						isPaused = false;

					}
				});

				quit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						dialog.dismiss();
						Intent intent = new Intent(Game.this, Quit.class);
						intent.putExtra("scoreview", score);
						intent.putExtra("good", goodIntake);
						intent.putExtra("bad", badIntake);
						
						startActivity(intent);
						saveSomeValues();
					}
				});

				dialog.show();
			}
		});

		Display display = getWindowManager().getDefaultDisplay();
		display.getRectSize(mDisplaySize);

		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mScale = metrics.density;

		mRootLayout = (RelativeLayout) findViewById(R.id.game_layout);

		animTimer.schedule(new ExeTimerTask(), 0, 4000);
		countdownTimer.schedule(new TimerTimerTask(), 0, 1000);
		scoreTimer.schedule(new ScoreTimerTask(), 0, 10);
		
	}

	public void startAnimation(final ImageView aniView) {

		aniView.setPivotX(aniView.getWidth() / 2);
		aniView.setPivotY(aniView.getHeight() / 2);

		long delay = new Random().nextInt(Constants.MAX_DELAY);

		final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
		animator.setDuration(Constants.ANIM_DURATION);
		animator.setInterpolator(new AccelerateInterpolator());
		animator.setStartDelay(delay);

		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			int angle = 50 + (int) (Math.random() * 101);
			int movex = new Random().nextInt(mDisplaySize.right);

			float startTime = 0;

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = ((Float) (animation.getAnimatedValue()))
						.floatValue();
				/*
				 * if (isPaused && elapsedTime == 0) { startTime = ((Float)
				 * (animation.getAnimatedValue())) .floatValue(); } if
				 * (isPaused) { elapsedTime = ((Float)
				 * animation.getAnimatedValue()) .floatValue() - startTime; //
				 * animator.pause(); }
				 */

				aniView.setTranslationY((mDisplaySize.bottom + (150 * mScale))
						* value - elapsedTime);

				float r1 = aniView.getWidth() / 2;
				float r2 = 5;

				float x1 = aniView.getX();
				float y1 = aniView.getY();

				float x2 = charOpen.getX();
				float y2 = 640;

				float dist = (float) Math.sqrt((x1 - x2) * (x1 - x2)
						+ (y1 - y2) * (y1 - y2));

				if (r1 + r2 > dist) {
					mRootLayout.removeView(aniView);
					mAllImageViews.remove(aniView);
					animator.cancel();

					if (((String) aniView.getTag()).equals("good")) {
						score += 100; 
						goodIntake ++;}
					else if (((String) aniView.getTag()).equals("bad") && score > 0) {
						score -= 50;
						
					}
					
					if (((String) aniView.getTag()).equals("bad")) {
						badIntake ++;
					}

				}

			}
		});

		animator.start();

	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			int foodViewID = new Random().nextInt(cloud1.length);
			Drawable f1 = getResources().getDrawable(cloud1[foodViewID].getId());

			int foodViewID2 = new Random().nextInt(cloud1.length);
			Drawable f2 = getResources().getDrawable(cloud1[foodViewID2].getId());
			
			int foodViewID3 = new Random().nextInt(cloud1.length);
			Drawable f3 = getResources().getDrawable(cloud1[foodViewID3].getId()); 

			LayoutInflater inflate = LayoutInflater.from(Game.this);
			imageView = (ImageView) inflate.inflate(R.layout.ani_image_view,
					null);
			ImageView imageView1 = (ImageView) inflate.inflate(
					R.layout.ani_image_view, null);
			ImageView imageView2 = (ImageView) inflate.inflate(
					R.layout.ani_image_view, null);

			imageView.setImageDrawable(f1);
			imageView.setTag(cloud1[foodViewID].getCategory());

			imageView1.setImageDrawable(f2);
			imageView1.setTag(cloud1[foodViewID2].getCategory());
			
			imageView2.setImageDrawable(f3);
			imageView2.setTag(cloud1[foodViewID3].getCategory());

			mRootLayout.addView(imageView);
			mRootLayout.addView(imageView1);
			mRootLayout.addView(imageView2);

			mAllImageViews.add(imageView);
			mAllImageViews.add(imageView1);
			mAllImageViews.add(imageView2);

			LayoutParams animationLayout = (LayoutParams) imageView
					.getLayoutParams();
			animationLayout.setMargins(30, 90, 0, 0);
			animationLayout.width = (int) (60 * mScale);
			animationLayout.height = (int) (60 * mScale);
			startAnimation(imageView);

			LayoutParams animationLayout1 = (LayoutParams) imageView1
					.getLayoutParams();
			animationLayout1.setMargins(190, 90, 0, 0);
			animationLayout1.width = (int) (60 * mScale);
			animationLayout1.height = (int) (60 * mScale);
			startAnimation(imageView1);

			LayoutParams animationLayout2 = (LayoutParams) imageView2
					.getLayoutParams();
			animationLayout2.setMargins(350, 90, 0, 0);
			animationLayout2.width = (int) (60 * mScale);
			animationLayout2.height = (int) (60 * mScale);
			startAnimation(imageView2);

			View cloudsLayout = LayoutInflater.from(Game.this).inflate(
					R.layout.cloud, null);
			mRootLayout.addView(cloudsLayout);
			mAllImageViews.add(cloudsLayout);

		}
	};

	private Handler heheHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (timer > 0) {
				time.setText(Integer.toString(timer--));
			}
			
			/*
			  else { Intent intent = new Intent(Game.this, Quit.class);
			  startActivity(intent);
			  
			  } */
			 

		}
	};

	private Handler scoreHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			scoreview.setText(Integer.toString(score));

		}
	};
	

	

	private class ExeTimerTask extends TimerTask {
		@Override
		public void run() {
			// we don't really use the message 'what' but we have to specify
			// something.
			if (!isPaused) {
				mHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
			}
		}
	}

	public class TimerTimerTask extends TimerTask {
		public void run() {
			if (!isPaused) {
				heheHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
			}
		}
	}

	public class ScoreTimerTask extends TimerTask {
		public void run() {
			if (!isPaused) {
				scoreHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
			}
		}
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int eid = event.getAction();
		switch (eid) {
		case MotionEvent.ACTION_MOVE:

			LayoutParams mParams = (LayoutParams) charOpen.getLayoutParams();

			float x = event.getRawX();
			float offset = charOpen.getWidth() / 2;
			Point size = new Point();
			getWindowManager().getDefaultDisplay().getSize(size);
			int loc = 0;
			if (x >= 0 && x <= size.x) {
				loc = (int) (x - offset);
				if (loc < 0) {
					loc = 0;
				}
				if (loc + (offset * 2) > size.x) {
					loc = (int) (size.x - (offset * 2));
				}
			}

			mParams.leftMargin = loc;
			charOpen.setLayoutParams(mParams);
			break;
		default:
			break;
		}
		return true;
	}
	public void readSomeValues()
	{
		BufferedReader input = null;
    	try {
    		input = new BufferedReader(new InputStreamReader(this.openFileInput("nomnomnomfile")));
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