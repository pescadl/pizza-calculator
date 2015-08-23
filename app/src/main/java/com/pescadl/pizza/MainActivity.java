package com.pescadl.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements NumberPickerDialog.NumberPickerDialogListener, AddPizzaDialog.AddPizzaDialogListener, EditPizzaDialog.EditPizzaDialogListener, DeletePizzaDialog.DeletePizzaDialogListener{

    private final String TAG_PIZZA_NUMBER_PICKER_DIALOG = "PizzaNumberPickerDialog";
    private final String TAG_PERSON_NUMBER_PICKER_DIALOG = "PersonNumberPickerDialog";

    private GridAdapter adapter;
    private int gridPositionClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create NumberPickerDialog when clicks text_slices_per_pizza
        findViewById(R.id.text_slices_per_pizza).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.show(getSupportFragmentManager(), TAG_PIZZA_NUMBER_PICKER_DIALOG);
            }
        });

        //create NumberPickerDialog when clicks text_slices_per_person
        findViewById(R.id.text_slices_per_person).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.show(getSupportFragmentManager(), TAG_PERSON_NUMBER_PICKER_DIALOG);
            }
        });


        //create GridAdapter
        adapter = new GridAdapter(this);

        //initialize the grid view
        GridView gridView = (GridView) findViewById(R.id.view_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                gridPositionClicked = position;
                PizzaOptionsDialog dialogFragment = new PizzaOptionsDialog();
                dialogFragment.show(getSupportFragmentManager(), "PizzaOptionsDialog");
            }
        });


        //create AddPizzaDialog when clicks button_add_pizza
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
        adapter.addItem(name, num, getSlicesPerPizza(), getSlicesPerPerson(), -1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditPizzaDialogPositiveClick(String name, int num){
        adapter.editItem(name, num, getSlicesPerPizza(), getSlicesPerPerson(), gridPositionClicked);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeletePizzaDialogPositiveClick(){
        adapter.removeItem(gridPositionClicked);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNumberPickerDialogPositiveClick(String tag, int num){
        if(tag.equals(TAG_PIZZA_NUMBER_PICKER_DIALOG)){
            ((EditText) findViewById(R.id.text_slices_per_pizza)).setText(String.valueOf(num));
        }
        else if(tag.equals(TAG_PERSON_NUMBER_PICKER_DIALOG)){
            ((EditText) findViewById(R.id.text_slices_per_person)).setText(String.valueOf(num));
        }

        adapter.update(getSlicesPerPizza(), getSlicesPerPerson());
        adapter.notifyDataSetChanged();
    }

    public GridAdapter getAdapter(){
        return adapter;
    }

    public int getGridPositionClicked(){
        return gridPositionClicked;
    }

    private int getSlicesPerPizza(){
        return Integer.parseInt(((EditText) findViewById(R.id.text_slices_per_pizza)).getText().toString() );
    }

    private int getSlicesPerPerson(){
        return Integer.parseInt(((EditText) findViewById(R.id.text_slices_per_person)).getText().toString() );
    }

}
