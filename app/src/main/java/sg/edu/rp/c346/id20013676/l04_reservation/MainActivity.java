package sg.edu.rp.c346.id20013676.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    DatePickerDialog.OnDateSetListener dateSetListener;
//    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText name = findViewById(R.id.inputName);
        EditText number = findViewById(R.id.inputNumber);
        TextView dateText = findViewById(R.id.textDate);
        TextView dateOutput = findViewById(R.id.textDateOutput);
        Button reserveButton = findViewById(R.id.buttonReserve);
        Button resetButton = findViewById(R.id.buttonReset);
        CheckBox smoking = findViewById(R.id.checkBoxSmoking);
        NumberPicker pax = findViewById(R.id.inputPax);
        TimePicker timePicker = findViewById(R.id.timePicker);
        pax.setMaxValue(5);
        pax.setMinValue(1);

        dateOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateOutput.setText(String.format("%d/%d/%d", dayOfMonth, month+1, year));
            }
        };

//        timeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int hour = cal.get(Calendar.HOUR_OF_DAY);
//                int minute = cal.get(Calendar.MINUTE);
//                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, timeSetListener, hour, minute, true);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                timeOutput.setText(String.format("%d:%02d", hourOfDay, minute));
//               if (hourOfDay > 8) {
//                   hourOfDay = 8;
//               }
//            }
//        };



        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length()!=0 && number.getText().toString().trim().length()!=0) {

                    String time = String.format("%d:%02d", timePicker.getCurrentHour(),timePicker.getCurrentMinute());

                    if (!smoking.isChecked()) {
                        Toast.makeText((MainActivity.this), String.format("Reservation made for %s (%s) on %s at %s for %s",name.getText(),number.getText(),dateOutput.getText(),time, pax.getValue()), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText((MainActivity.this), String.format("Reservation made for %s (%s) on %s at %s for %s (Smoking Area)",name.getText(),number.getText(),dateOutput.getText(),time,pax.getValue()), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText((MainActivity.this),"Please fill in Name and Mobile No.",Toast.LENGTH_LONG).show();
                }

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay < 8) {
                    timePicker.setCurrentHour(8);
                }
                else if (hourOfDay > 20) {
                    timePicker.setCurrentHour(20);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                number.setText("");
                pax.setValue(1);
                timePicker.setCurrentMinute(30);
                timePicker.setCurrentHour(15);
                dateOutput.setText("1/6/2021");
                smoking.setChecked(false);
            }
        });
    }

}