package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragment.CarroFragment;

public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);
        setUpToolBar();


        CarroFragment cf = (CarroFragment) getSupportFragmentManager()
                .findFragmentById(R.id.CarroFragment);
        Carro c = (Carro) getIntent().getSerializableExtra("carro");
        cf.setCarro(c);
        // Título da toolbar e botão up navigation
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
