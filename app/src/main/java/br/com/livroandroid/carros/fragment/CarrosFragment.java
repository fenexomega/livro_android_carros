package br.com.livroandroid.carros.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.CarroActivity;
import br.com.livroandroid.carros.adapter.CarrosAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;
import br.com.livroandroid.carros.other.Utils;


public class CarrosFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Carro> carros;
    private String tipo;
    private ProgressBar progress;
    private SwipeRefreshLayout swipe;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            this.tipo = getArguments().getString("tipo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);


        progress = (ProgressBar) view.findViewById(R.id.progressBar);
        progress.setVisibility(View.VISIBLE);

        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {

        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(Utils.isNetworkAvailable(getContext()) == true)
                    taskCarros();
                else
                {
                    Snackbar.make(getView(),"There is no internet connection",Snackbar.LENGTH_SHORT);
                    swipe.setRefreshing(false);
                }

            }
        };
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipe.setOnRefreshListener(OnRefreshListener());
        swipe.setColorSchemeResources(R.color.refresh_progress1,
                R.color.refresh_progress2, R.color.refresh_progress3);
        taskCarros();
    }

    private void taskCarros()
    {
        //Para ver a progressbar
        if(Utils.isNetworkAvailable(getContext()) == true)
            new GetCarrosTask().execute();
    }

    private CarrosAdapter.CarroOnClickListener getCarroClickListener() {
        return new CarrosAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                Intent intent = new  Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro",c);
                startActivity(intent);

            }
        };
    }

    private class GetCarrosTask extends AsyncTask<Void,Void,List<Carro>>
    {
        @Override
        protected List<Carro> doInBackground(Void... params) {
            try {
                Thread.sleep(500);
                return CarroService.getCarros(getContext(), tipo);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            progress.setVisibility(View.GONE);
            if(carros != null)
            {
                CarrosFragment.this.carros = carros;
                //Atualiza a view na UI Thread
                recyclerView.setAdapter(new CarrosAdapter(getContext(),carros,getCarroClickListener()));
                swipe.setRefreshing(false);
            }
        }
    }
}
