package com.example.nomnomgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
	
	public class Quit extends Activity {
		ImageButton playagain;
		ImageButton menu2;
		ImageView character;
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.quit);
			
			
			playagain = (ImageButton) findViewById(R.id.playagain);
			menu2 = (ImageButton) findViewById(R.id.menu2);
			/*int goodQty = 0, badQty = 0, totalQty;
			totalQty = goodQty - badQty;
			if(totalQty > 20){
				//good
				//GET THE PERSON OBJECT HERE AND PLACE THE evaluationImageID inside the findViewById();
				//character = (ImageView) findViewById();	
			}if(totalQty < 10){
				//bad
			}*/
			
			
			
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

}
