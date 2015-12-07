package br.com.livroandroid.carros.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.livroandroid.carros.R;

/**
 * Created by jordy on 11/23/15.
 */
public class BaseActivity extends livroandroid.lib.activity.BaseActivity {
    protected void setUpToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);
    }
}
