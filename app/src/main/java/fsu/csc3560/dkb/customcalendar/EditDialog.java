package fsu.csc3560.dkb.customcalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EditDialog extends AppCompatDialogFragment {
    private EditText year;
    private EditText month;
    private EditText monthMaximum;
    private EditText day;
    private EditText dayMaximum;
    private EditDialogListener listener;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Edit Calendar")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer yearResult = Integer.valueOf(year.getText().toString());
                        Integer monthResult = Integer.valueOf(month.getText().toString());
                        Integer monthMaximumResult = Integer.valueOf(monthMaximum.getText().toString());
                        Integer dayResult = Integer.valueOf(day.getText().toString());
                        Integer dayMaximumResult = Integer.valueOf(dayMaximum.getText().toString());

                        listener.applyValues(yearResult, monthResult, monthMaximumResult, dayResult, dayMaximumResult);
                    }
                });

        year = view.findViewById(R.id.edit_year);
        month = view.findViewById(R.id.edit_month);
        monthMaximum = view.findViewById(R.id.maximum_month);
        day = view.findViewById(R.id.edit_day);
        dayMaximum = view.findViewById(R.id.maximum_day);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement EditDialogListener");
        }
    }

    public interface EditDialogListener{
        void applyValues(Integer year, Integer month, Integer monthMaximum, Integer day, Integer dayMaximum);
    }
}
