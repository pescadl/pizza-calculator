package com.pescadl.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by dayoung on 8/21/2015.
 */
public class AddPizzaDialog extends DialogFragment{

    EditText addPizzaName;
    EditText addPeopleNum;

    AddPizzaDialogListener listener;
    public interface AddPizzaDialogListener {
        public void onAddPizzaDialogPositiveClick(String name, int num);
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_pizza, null);
        addPizzaName = (EditText) view.findViewById(R.id.add_pizza_name);
        addPeopleNum = (EditText) view.findViewById(R.id.add_num_people);

        builder.setView(view);
        builder.setTitle("Add Pizza");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                //do nothing
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

    @Override
    public void onStart(){
        super.onStart();

        ((AlertDialog)getDialog()).getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if( !(addPizzaName.getText().toString().equals("") || addPeopleNum.getText().toString().equals("")) ){
                    listener.onAddPizzaDialogPositiveClick(addPizzaName.getText().toString(), Integer.parseInt(addPeopleNum.getText().toString()));
                    dismiss();
                }
            }
        });
    }
}
