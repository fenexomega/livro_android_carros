package br.com.livroandroid.carros.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.TabsAdapter;
import br.com.livroandroid.carros.other.Prefs;

public class CarrosTabFragment extends Fragment implements
        TabLayout.OnTabSelectedListener {
    private ViewPager viewpager;
    private TabLayout tablayout;

    public CarrosTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros_tab,container,false);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        tablayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //View pager
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));
        //TABS
        tablayout.addTab(tablayout.newTab().setText(R.string.classicos));
        tablayout.addTab(tablayout.newTab().setText(R.string.esportivos));
        tablayout.addTab(tablayout.newTab().setText(R.string.luxo));
        //Listener para tratar eventos de click na tab
        tablayout.setOnTabSelectedListener(this);
        //Se mudar o viewpager atualiza a tab selecionada
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        //AO criar a view, mostra a última tab selecionada
        int tabIdx = Prefs.getInt(getContext(),"tabIdx");
        viewpager.setCurrentItem(tabIdx);


        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewpager.setCurrentItem(tab.getPosition());
        Prefs.setInt(getContext(),"tabIdx",tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}


