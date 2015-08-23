package com.pescadl.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

/**
 * Created by dayoung on 8/22/2015.
 */
public class EditPizzaDialog extends DialogFragment{

    EditText editPizzaName;
    EditText editPeopleNum;

    EditPizzaDialogListener listener;
    public interface EditPizzaDialogListener {
        public void onEditPizzaDialogPositiveClick(String name, int num);
    }

    public EditPizzaDialog(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            listener = (EditPizzaDialogListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement AddPizzaDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_pizza, null);
        editPizzaName = (EditText) view.findViewById(R.id.edit_pizza_name);
        editPeopleNum = (EditText) view.findViewById(R.id.edit_num_people);

        editPizzaName.setText(((Node)(((MainActivity) getActivity()).adapter.getItem( ((MainActivity) getActivity()).clickPosition ))).name);
        editPeopleNum.setText(String.valueOf(((Node) (((MainActivity) getActivity()).adapter.getItem(((MainActivity) getActivity()).clickPosition))).numPeople));

        builder.setView(view);
        builder.setTitle("Edit Pizza");
        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener(){
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
                if(!(editPizzaName.getText().toString().equals("") || editPeopleNum.getText().toString().equals(""))){
                    listener.onEditPizzaDialogPositiveClick(editPizzaName.getText().toString(), Integer.parseInt(editPeopleNum.getText().toString()));
                    dismiss();
                }
            }
        });
    }
}
