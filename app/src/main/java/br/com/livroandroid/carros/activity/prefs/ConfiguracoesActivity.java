package br.com.livroandroid.carros.activity.prefs;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import br.com.livroandroid.carros.R;

/**
 * Created by jordy on 12/14/15.
 * https://stackoverflow.com/questions/6822319/what-to-use-instead-of-addpreferencesfromresource-in-a-preferenceactivity
 */
public class ConfiguracoesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Carrega as configurações
        addPreferencesFromResource(R.xml.preferences);
    }
}
