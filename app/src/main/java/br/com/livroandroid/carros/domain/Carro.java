package br.com.livroandroid.carros.domain;

import java.io.Serializable;

/**
 * Created by jordy on 12/1/15.
 */
public class Carro implements Serializable {
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
