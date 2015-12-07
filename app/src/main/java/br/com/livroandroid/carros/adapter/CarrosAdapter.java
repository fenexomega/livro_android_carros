package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

/**
 * Created by jordy on 12/1/15.
 */
public class CarrosAdapter extends RecyclerView.Adapter<CarrosAdapter.ViewHolder> {

    private final Context context;
    private final List<Carro> carros;
    private CarroOnClickListener carroOnClickListener;

    public CarrosAdapter(Context context,List<Carro> carros,CarroOnClickListener carroOnClickListener) {
        this.context = context;
        this.carros = carros;
        this.carroOnClickListener = carroOnClickListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Carro c = carros.get(position);
        holder.textView.setText(c.nome);
        holder.progressBar.setVisibility(ProgressBar.VISIBLE);
        Picasso.with(context).load(c.urlFoto).fit().into(holder.imageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                   //DEU CERTO
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        // Click
        if(carroOnClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    //A variavel position é final
                    carroOnClickListener.onClickCarro(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.carros != null ? carros.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public ProgressBar progressBar;
        public TextView textView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public interface CarroOnClickListener {
        public void onClickCarro(View view,int idx);
    }

}
