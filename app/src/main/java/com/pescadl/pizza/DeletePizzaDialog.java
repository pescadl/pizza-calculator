package com.pescadl.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by dayoung on 8/23/2015.
 */
public class DeletePizzaDialog extends DialogFragment{

    DeletePizzaDialogListener listener;
    public interface DeletePizzaDialogListener {
        public void onDeletePizzaDialogPositiveClick();
    }

    public DeletePizzaDialog(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            listener = (DeletePizzaDialogListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement DeletePizzaDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_delete_pizza, null));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                listener.onDeletePizzaDialogPositiveClick();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
