package br.com.livroandroid.carros.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;

/**
 * Created by jordy on 11/27/15.
 */
public class NavDrawerMenuItem {
    public int title;
    public int img;
    public boolean selected;

    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }

    public static List<NavDrawerMenuItem> getList()
    {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.carros, R.drawable.ic_drawer_carro));
        list.add(new NavDrawerMenuItem(R.string.site_livro, R.drawable.ic_drawer_site_livro));
        list.add(new NavDrawerMenuItem(R.string.configuracoes, R.drawable.ic_drawer_settings));
        return list;
    }
}
