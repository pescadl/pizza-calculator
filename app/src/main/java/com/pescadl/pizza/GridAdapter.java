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

    public void update(int slicesPerPizza, int slicesPerPerson){
        int size = list.size();
        for(int i=0; i<size; i++){
            Node node = list.remove(0);
            addItem(node.name, node.numPeople, slicesPerPizza, slicesPerPerson);
        }
    }

    public void addItem(String name, int numPeople, int slicesPerPizza, int slicesPerPerson){
        int whole = slicesPerPerson * numPeople / slicesPerPizza;
        int fraction = slicesPerPerson * numPeople % slicesPerPizza;
        String numPizzas = "";
        if(whole == 0 && fraction == 0){
            numPizzas = "0";
        }
        else if(whole == 0){
            numPizzas = fraction + "/" + slicesPerPizza;
        }
        else if(fraction == 0){
            numPizzas = whole + "";
        }
        else{
            numPizzas = whole + " " + fraction+"/"+slicesPerPizza;
        }
        list.add(new Node(name, numPeople, numPizzas));
    }

    public void editItem(String name, int numPeople, int slicesPerPizza, int slicesPerPerson, int position){
        int whole = slicesPerPerson * numPeople / slicesPerPizza;
        int fraction = slicesPerPerson * numPeople % slicesPerPizza;
        String numPizzas = "";
        if(whole == 0 && fraction == 0){
            numPizzas = "0";
        }
        else if(whole == 0){
            numPizzas = fraction + "/" + slicesPerPizza;
        }
        else if(fraction == 0){
            numPizzas = whole + "";
        }
        else{
            numPizzas = whole + " " + fraction+"/"+slicesPerPizza;
        }
        list.set(position, new Node(name, numPeople, numPizzas));
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

            ((TextView) convertView.findViewById(R.id.name)).setText(list.get(position).name);
            ((TextView) convertView.findViewById(R.id.num_people)).setText(String.valueOf(list.get(position).numPeople));
            ((TextView) convertView.findViewById(R.id.num_pizzas)).setText(list.get(position).numPizzas);
        }
        else{
            ((TextView) convertView.findViewById(R.id.name)).setText(list.get(position).name);
            ((TextView) convertView.findViewById(R.id.num_people)).setText(String.valueOf(list.get(position).numPeople));
            ((TextView) convertView.findViewById(R.id.num_pizzas)).setText(list.get(position).numPizzas);
        }

        return convertView;
    }
}
