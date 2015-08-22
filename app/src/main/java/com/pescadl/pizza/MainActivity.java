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

public class MainActivity extends AppCompatActivity implements AddPizzaDialog.AddPizzaDialogListener, NumberPickerDialog.NumberPickerDialogListener{
    GridAdapter adapter;
    final String PIZZA_NUMBER_PICKER_DIALOG_TAG = "PizzaNumberPickerDialog";
    final String PERSON_NUMBER_PICKER_DIALOG_TAG = "PersonNumberPickerDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text_slices_per_pizza).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.show(getSupportFragmentManager(), PIZZA_NUMBER_PICKER_DIALOG_TAG);
            }
        });

        findViewById(R.id.text_slices_per_person).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.show(getSupportFragmentManager(), PERSON_NUMBER_PICKER_DIALOG_TAG);
            }
        });

        adapter = new GridAdapter(getBaseContext());

        GridView gridView = (GridView) findViewById(R.id.view_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // TODO edit when click on grid
                Toast.makeText(getBaseContext(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
    public void onAddPizzaDialogPositiveClick(String name, int num){
        adapter.addItem(name, num, getSlicesPerPizza(), getSlicesPerPerson());
    }

    @Override
    public void onNumberPickerDialogPositiveClick(String tag, int num){
        if(tag.equals(PIZZA_NUMBER_PICKER_DIALOG_TAG)){
            ((EditText) findViewById(R.id.text_slices_per_pizza)).setText(String.valueOf(num));
        }
        else if(tag.equals(PERSON_NUMBER_PICKER_DIALOG_TAG)){
            ((EditText) findViewById(R.id.text_slices_per_person)).setText(String.valueOf(num));
        }

        adapter.update(getSlicesPerPizza(), getSlicesPerPerson());
        ((GridView) findViewById(R.id.view_grid)).setAdapter(adapter);
    }

    private int getSlicesPerPizza(){
        return Integer.parseInt(((EditText) findViewById(R.id.text_slices_per_pizza)).getText().toString() );
    }

    private int getSlicesPerPerson(){
        return Integer.parseInt(((EditText) findViewById(R.id.text_slices_per_person)).getText().toString() );
    }
}
