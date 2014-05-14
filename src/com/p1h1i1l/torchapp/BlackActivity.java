package com.p1h1i1l.torchapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.SettingNotFoundException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class BlackActivity extends Activity {

	 float curBrightnessValue = 0;
	 boolean lightsOn = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black);

		 try {
		 curBrightnessValue = android.provider.Settings.System.getInt(
		 getContentResolver(),
		 android.provider.Settings.System.SCREEN_BRIGHTNESS);
		 } catch (SettingNotFoundException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean cbValue = sp.getBoolean("CHECKBOX", false);

		if (cbValue == true) {
			lightsOn();
		}

		Button onOffButton = (Button) findViewById(R.id.on_off_button);
		onOffButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(lightsOn == true)
				{
					lightsOff();
				}
				else if(lightsOn == false)
				{
					lightsOn();
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settingButton:
			Intent intent = new Intent(BlackActivity.this,
					SettingsActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_black, menu);
		// return true;

		MenuInflater mif = getMenuInflater();
		mif.inflate(R.menu.activity_black, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void lightsOn() {
		LinearLayout someView = (LinearLayout) findViewById(R.id.BlackActivity);
		someView.setBackgroundColor(getResources().getColor(
				android.R.color.white));

		lightsOn = true;
		
		setBrightness(255);
	}
	
	private void lightsOff() {
		LinearLayout someView = (LinearLayout) findViewById(R.id.BlackActivity);
		someView.setBackgroundColor(getResources().getColor(
				android.R.color.black));

		lightsOn = false;
		
		setBrightness(curBrightnessValue);
	}

	private void setBrightness(float brightness) {
		WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
		layoutParams.screenBrightness = brightness / 255;
		getWindow().setAttributes(layoutParams);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		setBrightness(curBrightnessValue);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean cbValue = sp.getBoolean("CHECKBOX", false);

		if (cbValue == true) {
			lightsOn();
		}
		else
		{
			lightsOff();
		}
	}
	
	

}
