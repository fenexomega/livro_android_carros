package br.com.livroandroid.carros.other;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jordy on 12/12/15.
 */
public class Utils {

    public static void alertDialog(Context context,String title,String message)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle(title);
        b.setMessage(message);
        b.create().show();
    }

    public static boolean isNetworkAvailable(Context context)
    {
        try
        {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivity == null)
            {
                return false;
            }
            else
            {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if(info != null)
                {
                    for (int i = 0; i < info.length; i++) {
                        if(info[i].getState() == NetworkInfo.State.CONNECTED)
                            return true;
                    }
                }
            }
        }
        catch (SecurityException e)
        {

            alertDialog(context, e.getClass().getSimpleName(), e.getMessage());
        }


        return true;
    }
}
