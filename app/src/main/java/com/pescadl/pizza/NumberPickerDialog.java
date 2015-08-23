package com.pescadl.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by dayoung on 8/22/2015.
 */
public class NumberPickerDialog extends DialogFragment{

    private NumberPicker numberPicker;
    private NumberPickerDialogListener listener;

    public NumberPickerDialog(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_number_picker, null);

        numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(12);

        builder.setView(view);
        builder.setTitle("Slices Per Pizza");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                listener.onNumberPickerDialogPositiveClick(getTag(), numberPicker.getValue());
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

    public interface NumberPickerDialogListener{
        void onNumberPickerDialogPositiveClick(String tag, int num);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            listener = (NumberPickerDialogListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement NumberPickerDialogListener");
        }
    }
}
