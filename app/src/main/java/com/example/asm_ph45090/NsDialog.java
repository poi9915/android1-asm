package com.example.asm_ph45090;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

public class NsDialog extends AppCompatDialogFragment {
    private TextInputLayout edNewId, edNewName;
    private Spinner spinNewPB;
    private NsDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ns_acitvity, null);
        edNewId = view.findViewById(R.id.edNewNsID);
        edNewName = view.findViewById(R.id.edNewNsName);
        spinNewPB = view.findViewById(R.id.spinNewPB);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                new String[]{"Hành chính", "Nhân Sự", "Đào tạo"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNewPB.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Thêm nhân sự")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newId = edNewId.getEditText().getText().toString();
                        String newName = edNewName.getEditText().getText().toString();
                        String newPB = spinNewPB.getSelectedItem().toString();
                        listener.applyText(newId, newName, newPB);
                    }
                });
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (NsDialogListener) context;

        }catch (ClassCastException e){
            Log.d("NSD", "onAttach: " + e.toString());
        }
    }

    public interface NsDialogListener{
        void applyText(String newId , String newName , String newPB);
    }
}
