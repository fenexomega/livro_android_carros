package br.com.livroandroid.carros.other;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jordy on 12/14/15.
 */
public class Prefs  {
    // Identificador do banco de dados destas preferências
    public static final String PREF_ID = "livroandroid";
    public static boolean setBoolean(Context context, String chave,boolean valor)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(chave, valor);
        return editor.commit();
    }

    public static boolean getBoolean(Context context,String chave)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getBoolean(chave, true);
    }

    public static String getString(Context context,String chave)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID,0);
        return pref.getString(chave, "");
    }

    public static int getInt(Context context,String chave)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID,0);
        return pref.getInt(chave, 0);
    }

    public static boolean setString(Context context,String chave,String valor)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(chave,valor);
        return editor.commit();
    }

    public static boolean setInt(Context context,String chave, int valor)
    {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID,0);
        SharedPreferences.Editor  editor = pref.edit();
        editor.putInt(chave,valor);
        return editor.commit();
    }

}
