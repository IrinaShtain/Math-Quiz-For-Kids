package com.shtainyky.mathquizforkids;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {


    public static String getStoredPosition(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.PREF_LAST_POSITION, Constants.NOTHING);
    }

    public static void setStoredPosition(Context context, String position) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(Constants.PREF_LAST_POSITION, position)
                .apply();
    }
    public static int getStoredPositionSwitchNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(Constants.PREF_LAST_SWITCH_NUMBER, 0);
    }

    public static void setStoredPositionSwitchNumber(Context context, int position) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(Constants.PREF_LAST_SWITCH_NUMBER, position)
                .apply();
    }




}
