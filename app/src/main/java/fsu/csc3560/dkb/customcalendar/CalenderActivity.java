package fsu.csc3560.dkb.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity implements EditDialog.EditDialogListener {
    private TextView year;
    private TextView month;
    private TextView day;

    private String yearString;
    private String monthString;
    private String dayString;

    private int maximumNumMonth;
    private int maximumNumDay;

    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);

        editButton = findViewById(R.id.editButton);

        openDialog();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog(){
        EditDialog editDialog = new EditDialog();
        editDialog.show(getSupportFragmentManager(), "edit dialog");
    }

    @Override
    public void applyValues(Integer yearResult, Integer monthResult, Integer monthMaximumResult, Integer dayResult, Integer dayMaximumResult) {
        yearString = String.valueOf(yearResult);
        monthString = String.valueOf(monthResult);
        dayString = String.valueOf(dayResult);

        year.setText(yearString);
        month.setText(monthString);
        day.setText(dayString);
    }
}