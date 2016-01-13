package br.com.livroandroid.carros.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jordy on 12/14/15.
 */
public class PrefsApp {
    public static boolean isCheckPushOn(final Context context)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean("PREF_CHECK_PUSH",false);
    }
}
