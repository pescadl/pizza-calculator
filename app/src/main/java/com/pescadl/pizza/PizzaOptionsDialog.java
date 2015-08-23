package com.pescadl.pizza;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by dayoung on 8/23/2015.
 */
public class PizzaOptionsDialog extends DialogFragment{

    public PizzaOptionsDialog(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.options_array, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch(which){
                    case 0:
                        EditPizzaDialog dialogFragment1 = new EditPizzaDialog();
                        dialogFragment1.show(getActivity().getSupportFragmentManager(), "EditPizzaDialog");
                        break;
                    case 1:
                        DeletePizzaDialog dialogFragment2 = new DeletePizzaDialog();
                        dialogFragment2.show(getActivity().getSupportFragmentManager(), "DeletePizzaDialog");
                        break;
                    default:
                        break;
                }
            }
        });

        return builder.create();
    }
}
