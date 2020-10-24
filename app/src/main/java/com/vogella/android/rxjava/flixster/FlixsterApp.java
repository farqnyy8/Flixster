package com.vogella.android.rxjava.flixster;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vogella.android.rxjava.flixster.theme.ThemeChangeHelper;
import com.vogella.android.rxjava.flixster.theme.ThemeType;

public class FlixsterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initAppTheme();
    }

    private void initAppTheme() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        ThemeType appTheme = ThemeType.getThemeType(
                sharedPreferences.getString(
                        ThemeChangeHelper.SAVEDTHEME,
                        ThemeType.DefaultMode.toString()
                )
        );
        ThemeChangeHelper.setAppTheme(appTheme);
    }
}