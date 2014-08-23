package com.hamzah.autoinstall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction()
		.replace(android.R.id.content, new PrefsFragment()).commit();
	}
	
	public static class PrefsFragment extends PreferenceFragment {
		@SuppressWarnings("deprecation")
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref);
			this.getPreferenceManager().setSharedPreferencesName("pref");
			this.getPreferenceManager().setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
			
		}
	}
}
