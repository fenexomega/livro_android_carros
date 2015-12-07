package br.com.livroandroid.carros.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

import br.com.livroandroid.carros.fragment.CarrosTabFragment;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.NavDrawerMenuAdapter;
import br.com.livroandroid.carros.adapter.NavDrawerMenuItem;
import br.com.livroandroid.carros.fragment.AboutDialog;
import br.com.livroandroid.carros.fragment.SiteLivroFragment;
import livroandroid.lib.fragment.NavigationDrawerFragment;

public class MainActivity extends BaseActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_about)
        {
            toast("Clicou no Sobre");
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolBar();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_drawer_fragment);
        //Configura o drawer layout
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Pintar a barra de fundo
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        mNavigationDrawerFragment.setUp(drawerLayout);

    }

    @Override
    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(
            NavigationDrawerFragment navDrawerFrag, LayoutInflater inflater, ViewGroup container) {
        //Retorna a view e o identificador do ListView
        View view = LayoutInflater.from(this).inflate(R.layout.nav_drawer_listview,container,false);

        //Preenche o cabeçalho com foto, nome e email
        navDrawerFrag.setHeaderValues(view,R.id.listViewContainer,R.drawable.nav_drawer_header,R.drawable.ic_logo_user
        ,R.string.nav_drawer_username,R.string.nav_drawer_user_email);

        return new NavigationDrawerFragment.NavDrawerListView(view,R.id.listView);
    }

    @Override
    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navDrawerFrag) {
        //Retorna Adapter que preenchera listview
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        //Deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this,list);
        return listAdapter;
    }

    private void changeFragmentOnPage(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_drawer_container, fragment, "TAG").commit();
    }

    @Override
    public void onNavDrawerItemSelected(NavigationDrawerFragment navDrawerFrag, int position) {
        //Metódo chamado quando item é selecionado
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);
        this.listAdapter.setSelected(position, true);
        switch (position)
        {
            case 0:
                changeFragmentOnPage(new CarrosTabFragment());
                break;
            case 1:
                changeFragmentOnPage(new SiteLivroFragment());
                break;
            case 2:
                AboutDialog.showAbout(getSupportFragmentManager());
                break;
            default:
                toast("Erro!");


        }
    }
}