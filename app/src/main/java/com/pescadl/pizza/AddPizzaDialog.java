package com.pescadl.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dayoung on 8/21/2015.
 */
public class AddPizzaDialog extends DialogFragment{

    EditText addPizzaName;
    EditText addPeopleNum;

    AddPizzaDialogListener listener;
    public interface AddPizzaDialogListener {
        public void onDialogPositiveClick(String name, int num);
    }

    public AddPizzaDialog(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            listener = (AddPizzaDialogListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement AddPizzaDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_pizza, null);
        addPizzaName = (EditText) view.findViewById(R.id.add_pizza_name);
        addPeopleNum = (EditText) view.findViewById(R.id.add_num_people);

        builder.setView(view);
        builder.setTitle("Add Pizza");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                listener.onDialogPositiveClick(addPizzaName.getText().toString(), Integer.parseInt(addPeopleNum.getText().toString()));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
