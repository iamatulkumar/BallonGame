package com.ockypocky.demogame.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String PREFS_GLOBAL = "prefs_global";
    private static final String PREF_CURRENT_SCORE = "pref_current_score";
    private static final String PREF_CURRENT_LEVEL = "pref_current_level";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(
                PREFS_GLOBAL, Context.MODE_PRIVATE);
    }

//  Setters and getters for global preferences
    public static void setCurrentScore(Context context, int score) {
        SharedPreferences.Editor editor =
                getPreferences(context).edit();
        editor.putInt(PREF_CURRENT_SCORE, score);
        editor.apply();
    }


    public static void setCurrentLevel(Context context, int level) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(PREF_CURRENT_LEVEL, level);
        editor.apply();
    }



}