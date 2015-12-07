package br.com.livroandroid.carros.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordy on 12/1/15.
 */
public class CarroService {
    public static List<Carro> getCarros(Context context)
    {
        List<Carro> carros = new ArrayList<Carro>();
        for (int i = 0; i < 20; i++) {
            Carro c = new Carro();
            c.nome = "Carro + " + i;
            c.desc = "Desc + " + i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }

        return carros;
    }

    public static List<Carro> getCarros(Context context, String tipo) {
        List<Carro> carros = new ArrayList<Carro>();
        for (int i = 0; i < 20; i++) {
            Carro c = new Carro();
            c.nome = "Carro " + tipo + i;
            c.desc = "Desc + " + i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }

        return carros;

    }
}