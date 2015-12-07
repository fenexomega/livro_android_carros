package br.com.livroandroid.carros;

import android.app.Application;
import android.util.Log;

/**
 * Created by jordy on 11/23/15.
 */
public class CarrosApplication extends Application {
    public static String TAG = "CarrosApplication";
    private static CarrosApplication instance = null;
    public static CarrosApplication getInstance()
    {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CarrosApplication.onCreate()");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }
}
