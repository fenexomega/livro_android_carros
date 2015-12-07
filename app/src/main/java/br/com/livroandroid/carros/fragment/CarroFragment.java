package br.com.livroandroid.carros.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

/**
 * A placeholder fragment containing a simple view.
 */
public class CarroFragment extends BaseFragment {

    private Carro carro;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carro, container, false);
    }

    public void setCarro(Carro c) {
        if(c == null)
            return;
        this.carro = c;
        setTextString(R.id.tDesc,carro.desc);
        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);

    }
}
