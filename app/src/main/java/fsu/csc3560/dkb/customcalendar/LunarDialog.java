package fsu.csc3560.dkb.customcalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class LunarDialog extends AppCompatDialogFragment {
    private EditText lunarCycle;
    private LunarDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lunar_dialog, null);

        builder.setView(view)
                .setTitle("The lunar cycle will start at the current day")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Double lunarCycleResult = Double.valueOf(lunarCycle.getText().toString());

                        listener.applyLunarValues(lunarCycleResult);

                    }
                });

        lunarCycle = view.findViewById(R.id.lunarCycle);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (LunarDialog.LunarDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement LunarDialogListener");
        }
    }

    public interface LunarDialogListener{
        void applyLunarValues(Double lunarCycle);
    }
}
