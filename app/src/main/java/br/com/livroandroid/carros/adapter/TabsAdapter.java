package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragment.CarrosFragment;

/**
 * Created by jordy on 12/2/15.
 */
public class TabsAdapter extends FragmentPagerAdapter {
    private final Context context;

    public TabsAdapter(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public int getCount() {
//        Retorna o número de tabs
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 1)
            return context.getString(R.string.classicos);
        else if(position == 2)
            return context.getString(R.string.esportivos);
        return context.getString(R.string.luxo);


    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if(position == 0)
            args.putString("tipo", "classicos");
        else if (position == 1)
            args.putString("tipo","esportivos");
        else
            args.putString("tipo","luxo");
        Fragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

}
