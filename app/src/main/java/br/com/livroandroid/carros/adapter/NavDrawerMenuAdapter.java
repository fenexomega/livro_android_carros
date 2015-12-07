package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.MainActivity;

/**
 * Created by jordy on 11/27/15.
 */
public class NavDrawerMenuAdapter extends BaseAdapter {
    private final List<NavDrawerMenuItem> list;
    private final Context context;
    private LayoutInflater inflater;

    public NavDrawerMenuAdapter(MainActivity context, List<NavDrawerMenuItem> list) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null)
        {
            //Cria o viewholder
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_nav_drawer,parent,false);
            convertView.setTag(holder);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
        }
        else
        {
            //Reaproveita o viewholder
            holder = (ViewHolder) convertView.getTag();
        }
        //Atualiza a view
        NavDrawerMenuItem item = list.get(position);
        holder.text.setText(item.title);
        holder.image.setImageResource(item.img);
        if(item.selected)
        {
            convertView.setBackgroundResource(R.drawable.selector_nav_drawer_selected);
            holder.text.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        }
        else
        {
            convertView.setBackgroundResource(R.drawable.selector_nav_drawer);
            holder.text.setTextColor(ContextCompat.getColor(context,R.color.black));
        }

        return convertView;
    }


    public void setSelected(int position, boolean selected)
    {
        clearSelected();
        list.get(position).selected = selected;
        notifyDataSetChanged();
    }
    public void clearSelected()
    {
        if(list != null)
        {
            for(NavDrawerMenuItem item : list)
            {
                item.selected = false;
            }

        }
        notifyDataSetChanged();
    }

    static class ViewHolder
    {
        public TextView text;
        public ImageView image;
    }

}
