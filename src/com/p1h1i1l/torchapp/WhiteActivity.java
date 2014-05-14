package com.p1h1i1l.torchapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class WhiteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_white);
		
		setBrightness(255);
		
		Button onOffButton = (Button)findViewById(R.id.on_off_button);
		onOffButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.appear, R.anim.appear);
			}
		});
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		 SharedPreferences sp = PreferenceManager
				 .getDefaultSharedPreferences(this);
				 boolean cbValue = sp.getBoolean("CHECKBOX", false);
				
				 if (cbValue != true) {
				 finish();
				 }
		
		
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
