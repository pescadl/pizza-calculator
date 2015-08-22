package com.pescadl.pizza;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddPizzaDialogFragment.AddPizzaDialogListener{
    int slicesPerPizza;
    int slicesPerPerson;

    GridAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slicesPerPizza = Integer.parseInt(((EditText) findViewById(R.id.slices_per_pizza)).getText().toString());
        slicesPerPerson = Integer.parseInt( ((EditText) findViewById(R.id.slices_per_person)).getText().toString() );

        adapter = new GridAdapter(getBaseContext());
        adapter.addItem("cheese", 5, slicesPerPizza, slicesPerPerson);

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // edit when click on grid
                Toast.makeText(getBaseContext(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add_pizza).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddPizzaDialogFragment dialogFragment = new AddPizzaDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "addPizza");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(String name, int num){
        adapter.addItem(name, num, slicesPerPizza, slicesPerPerson);
    }
}
