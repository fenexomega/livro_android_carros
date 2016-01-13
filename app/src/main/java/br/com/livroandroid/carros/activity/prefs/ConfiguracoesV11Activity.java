package br.com.livroandroid.carros.activity.prefs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragment.PrefsFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ConfiguracoesV11Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_v11);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //ADiciona o fragment de configurações
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content,new PrefsFragment());
        ft.commit();
    }
}
