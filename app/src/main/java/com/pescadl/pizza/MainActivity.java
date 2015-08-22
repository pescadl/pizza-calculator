package com.pescadl.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AddPizzaDialog.AddPizzaDialogListener{
    int slicesPerPizza;
    int slicesPerPerson;

    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slicesPerPizza = Integer.parseInt(((EditText) findViewById(R.id.text_slices_per_pizza)).getText().toString() );
        slicesPerPerson = Integer.parseInt( ((EditText) findViewById(R.id.text_slices_per_person)).getText().toString() );

        adapter = new GridAdapter(getBaseContext());
        //TODO remove later
        adapter.addItem("cheese", 5, slicesPerPizza, slicesPerPerson);

        GridView gridView = (GridView) findViewById(R.id.view_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // TODO edit when click on grid
                Toast.makeText(getBaseContext(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //set listener for "Add Pizza" button
        findViewById(R.id.button_add_pizza).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddPizzaDialog dialogFragment = new AddPizzaDialog();
                dialogFragment.show(getSupportFragmentManager(), "AddPizzaDialog");
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
