package com.pescadl.pizza;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dayoung on 8/21/2015.
 */
public class GridAdapter extends BaseAdapter{
    Context context;
    private ArrayList<Node> list = new ArrayList<>();

    public GridAdapter(Context c){
        context = c;
    }

    public void addItem(String name, int people, int pizza, int person){
        list.add(new Node(name, people, (people * person / pizza) + ((people * person % pizza !=0) ? " "+(people * person % pizza)+"/"+pizza : "")));
    }

    public void removeItem(int position){
        list.remove(position);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_grid, null);
        }
        else{
            ((TextView) convertView.findViewById(R.id.name)).setText(list.get(position).name);
            ((TextView) convertView.findViewById(R.id.people)).setText(String.valueOf(list.get(position).people));
            ((TextView) convertView.findViewById(R.id.fraction)).setText(list.get(position).fraction);
        }

        return convertView;
    }
}
