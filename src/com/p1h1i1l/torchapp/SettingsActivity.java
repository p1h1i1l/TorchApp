package com.p1h1i1l.torchapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends Activity implements OnClickListener{
	CheckBox cb;
	Button saveSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		cb = (CheckBox)findViewById(R.id.startOnCB);
		saveSettings = (Button)findViewById(R.id.saveButton);
		
		saveSettings.setOnClickListener(this);
		loadPrefs();
	}
	
	private void loadPrefs() {
		SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
		boolean cbValue = sp.getBoolean("CHECKBOX", false);
		if(cbValue){
			cb.setChecked(true);
		}
		else
		{
			cb.setChecked(false);
		}
		
	}
	
	private void savePrefs(String key, boolean value)
	{
		SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		savePrefs("CHECKBOX", cb.isChecked());
		finish();
		}
}
