package fsu.csc3560.dkb.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity implements EditDialog.EditDialogListener, LunarDialog.LunarDialogListener {
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
    private Button lunarButton;

    private double lunarCycle = 4;
    private ImageView lunarCycleImage;
    private double currentLunarCycleCount = 1;
    private double lunarCyclePhaseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);

        editButton = findViewById(R.id.editButton);
        lunarButton = findViewById(R.id.lunarButton);
        lunarCycleImage = findViewById(R.id.lunarCycleImage);


        openDialog();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        lunarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLunarDialog();
            }
        });

    }

    public void openDialog(){
        EditDialog editDialog = new EditDialog();
        editDialog.show(getSupportFragmentManager(), "edit dialog");
    }

    public void openLunarDialog(){
        LunarDialog lunarDialog = new LunarDialog();
        lunarDialog.show(getSupportFragmentManager(), "lunar dialog");
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
        currentLunarCycleCount -= 1;

        if(dayNumber < 1){
            PreviousMonth(view);
            dayNumber = maximumNumDay;
        }

        UpdateLunarCycle();

        dayString = String.valueOf(dayNumber);
        day.setText(dayString);
    }

    public void NextDay(View view){
        dayNumber = Integer.valueOf(day.getText().toString());
        dayNumber += 1;
        currentLunarCycleCount += 1;

        if(dayNumber > maximumNumDay){
            NextMonth(view);
            dayNumber = 1;
        }

        UpdateLunarCycle();

        dayString = String.valueOf(dayNumber);
        day.setText(dayString);
    }

    @Override
    public void applyLunarValues(Double lunarCycleResult) {
        lunarCycle = lunarCycleResult;

        SetLunarCycles(lunarCycle);
    }

    public void SetLunarCycles(Double lunarCycle){
        lunarCyclePhaseCount = lunarCycle / 4;
    }

    public void UpdateLunarCycle(){
        if(currentLunarCycleCount < 1){
            currentLunarCycleCount = lunarCycle;
        }

        if(currentLunarCycleCount > lunarCycle){
            currentLunarCycleCount = 1;
        }

        if(currentLunarCycleCount <= lunarCyclePhaseCount){
            lunarCycleImage.setImageResource(R.drawable.new_moon);
        }
        else if (currentLunarCycleCount <= (lunarCyclePhaseCount * 2)){
            lunarCycleImage.setImageResource(R.drawable.first_quarter);
        }
        else if (currentLunarCycleCount <= (lunarCyclePhaseCount * 3)){
            lunarCycleImage.setImageResource(R.drawable.fullmoon);
        }
        else if(currentLunarCycleCount <= (lunarCyclePhaseCount * 4)){
            lunarCycleImage.setImageResource(R.drawable.third_quarter);
        }
    }
}