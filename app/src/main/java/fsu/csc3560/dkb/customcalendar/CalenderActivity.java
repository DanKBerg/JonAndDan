package fsu.csc3560.dkb.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity implements EditDialog.EditDialogListener {
    private TextView year;
    private TextView month;
    private TextView day;

    private Integer yearNumber;
    private Integer monthNumber;
    private Integer dayNumber;

    private String yearString;
    private String monthString;
    private String dayString;

    private int maximumNumMonth;
    private int maximumNumDay;

    private ImageButton editButton;

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

        maximumNumMonth = monthMaximumResult;
        maximumNumDay = dayMaximumResult;

        year.setText(yearString);
        month.setText(monthString);
        day.setText(dayString);
    }

    public void PreviousYear(View view){
        yearNumber = Integer.valueOf(year.getText().toString());
        yearNumber -= 1;
        yearString = String.valueOf(yearNumber);
        year.setText(yearString);
    }

    public void NextYear(View view){
        yearNumber = Integer.valueOf(year.getText().toString());
        yearNumber += 1;
        yearString = String.valueOf(yearNumber);
        year.setText(yearString);
    }

    public void PreviousMonth(View view){
        monthNumber = Integer.valueOf(month.getText().toString());
        monthNumber -= 1;

        if(monthNumber < 1){
            PreviousYear(view);
            monthNumber = maximumNumMonth;
        }

        monthString = String.valueOf(monthNumber);
        month.setText(monthString);
    }

    public void NextMonth(View view){
        monthNumber = Integer.valueOf(month.getText().toString());
        monthNumber += 1;

        if(monthNumber > maximumNumMonth){
            NextYear(view);
            monthNumber = 1;
        }

        monthString = String.valueOf(monthNumber);
        month.setText(monthString);
    }

    public void PreviousDay(View view){
        dayNumber = Integer.valueOf(day.getText().toString());
        dayNumber -= 1;

        if(dayNumber < 1){
            PreviousMonth(view);
            dayNumber = maximumNumDay;
        }

        dayString = String.valueOf(dayNumber);
        day.setText(dayString);
    }

    public void NextDay(View view){
        dayNumber = Integer.valueOf(day.getText().toString());
        dayNumber += 1;

        if(dayNumber > maximumNumDay){
            NextMonth(view);
            dayNumber = 1;
        }

        dayString = String.valueOf(dayNumber);
        day.setText(dayString);
    }
}