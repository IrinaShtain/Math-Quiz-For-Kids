package com.shtainyky.mathquizforkids.utils;

import android.content.Context;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.shtainyky.mathquizforkids.R;

public class SettingsPreferences {
    public static boolean OK;


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
    private static boolean getStoredOperation(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Constants.OPERATION, true);
    }

    public static void setStoredOperation(Context context, boolean firstOperation) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(Constants.OPERATION, firstOperation)
                .apply();
    }

    public static void textForLetsPlayButton(Context context) {
        String operation = SettingsPreferences.getStoredPosition(context);
        int limit = SettingsPreferences.getStoredPositionSwitchNumber(context);
        Resources resources = context.getResources();
        if (operation.equals(Constants.NOTHING)) {
            OK = false;
            Toast.makeText(context,
                    resources.getString(R.string.no_operation),
                    Toast.LENGTH_LONG).show();
        }
        else if (limit == 0) {
            OK = false;
            Toast.makeText(context,
                    resources.getString(R.string.no_limits),
                    Toast.LENGTH_LONG).show();
        }
        else {
            OK = true;
            String msg = resources.getString(R.string.choosen_setting) +
                    " " + operation;
            if (getStoredOperation(context))
                msg = msg + " " + resources.getString(R.string.till) + " " + limit;
            else
            if (limit != 11)
                msg = msg + " " + resources.getString(R.string.by) + " " + limit;
            else
            msg = msg + " " + resources.getString(R.string.random_table);
            Toast.makeText(context,
                    msg, Toast.LENGTH_LONG).show();
        }
    }
}
