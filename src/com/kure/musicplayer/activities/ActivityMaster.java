package com.kure.musicplayer.activities;

import com.kure.musicplayer.R;
import com.kure.musicplayer.kMP;

import android.app.Activity;
import android.os.Bundle;

/**
 * Master Activity, from which every Activity other inherits.
 * This is needed so we can change the color theme at runtime.
 * 
 * What we do is make each Activity keep track of which
 * theme it currently has.
 * Whenever they have focus, we test to see if the global theme
 * was changed by the user.
 * If it was, it `recreate()`s itself.
 *  
 * @note We must call `Activity.setTheme()` BEFORE
 *       `Activity.setContentView()`!
 *        
 * Sources that made me apply this idea, thank you so much:
 * - http://stackoverflow.com/a/4673209
 * - http://stackoverflow.com/a/11875930
 * 
 */
public class ActivityMaster extends Activity {
	
	/**
	 * Keeping track of the current theme name.
	 * 
	 * @note It's name and valid values are defined on
	 *       `res/values/strings.xml`, at the fields
	 *       we can change on the Settings menu.
	 */
	protected String currentTheme = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		// Mandatory - when creating we don't have
		// a theme applied yet.
		refreshTheme();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// Every time the user focuses this Activity,
		// we need to check it.
		// It the theme changed, recreate ourselves.
		if (refreshTheme())
			recreate();
	}
	
	/**
	 * Tests if our current theme is the same as the one
	 * specified on `Settings`, reapplying the theme if
	 * not the case.
	 * 
	 * @return Flag that tells if we've changed the theme.
	 */
	public boolean refreshTheme() {
		
		// Getting global theme name from the Settings.
		// Second argument is the default value, in case
		// something went wrong.
		String theme = kMP.settings.get("themes", "light");
		
		if (currentTheme != theme)
		{
			// Testing each possible theme name.
			// I repeat - all valid theme names are specified
			// at `res/strings.xml`, right at the Settings sub menu.
			if (theme.equals("light"))
				this.setTheme(R.style.Theme_Light);

			else if (theme.equals("dark"))
				this.setTheme(R.style.Theme_Dark);
			
			else if (theme.equals("solariezd"))
				this.setTheme(R.style.Theme_Solarized);			
			
			currentTheme = theme;
			return true;
		}
		return false;
	}
}
