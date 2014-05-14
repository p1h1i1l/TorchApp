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

public class BlackActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black);

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
				lightsOn();
			}
		});
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settingButton:
			Intent intent = new Intent(BlackActivity.this, SettingsActivity.class);
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
		float curBrightnessValue = 0;
		try {
			curBrightnessValue = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Intent intent = new Intent(BlackActivity.this, WhiteActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.appear, R.anim.appear);
		// finish();

		setBrightness(curBrightnessValue);
	}

	private void setBrightness(float brightness) {
		WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
		layoutParams.screenBrightness = brightness / 255;
		getWindow().setAttributes(layoutParams);
	}

	public void setBrightnessNEW(float brightness) {
		try {
			int brightnessMode = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE);

			if (brightnessMode == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
				android.provider.Settings.System
						.putInt(getContentResolver(),
								android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
								android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			}

			WindowManager.LayoutParams layoutParams = getWindow()
					.getAttributes();
			layoutParams.screenBrightness = brightness;
			getWindow().setAttributes(layoutParams);

		} catch (Exception e) {
			// do something useful
		}
	}

}
