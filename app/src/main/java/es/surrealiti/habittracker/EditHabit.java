package es.surrealiti.habittracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Justin on 2016-09-30.
 */

public class EditHabit extends AppCompatActivity {
    private Habit habit;
    private EditText name;

//    private ArrayList<Date> history;
//    private ListView historyContainer;
//    private ArrayAdapter<Date> historyAdapter;

    private Calendar myCalendar;
    private TextView createdOn;
    private TextView completions;
    //Weekdays!
    private int index;
    private Switch monday;
    private Switch tuesday;
    private Switch wednesday;
    private Switch thursday;
    private Switch friday;
    private Switch saturday;
    private Switch sunday;


    private Button doneButton;

    DatePickerDialog.OnDateSetListener date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habit);

//        historyContainer = (ListView) findViewById(R.id.History);
        doneButton = (Button) findViewById(R.id.done);
        monday = (Switch) findViewById(R.id.Monday);
        tuesday = (Switch) findViewById(R.id.Tuesday);
        wednesday = (Switch) findViewById(R.id.Wednesday);
        thursday = (Switch) findViewById(R.id.Thursday);
        friday = (Switch) findViewById(R.id.Friday);
        saturday = (Switch) findViewById(R.id.Saturday);
        sunday = (Switch) findViewById(R.id.Sunday);

        completions = (TextView) findViewById(R.id.completions);


        index = getIntent().getIntExtra("index", 2);


        name = (EditText) findViewById(R.id.habitName);
        habit =(Habit) getIntent().getParcelableExtra("Habit");


        createdOn = (TextView) findViewById(R.id.createdOn);
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        createdOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditHabit.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        }

    private CompoundButton.OnCheckedChangeListener trackDay = new CompoundButton.OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            habit.trackDay(buttonView.getText().toString(), isChecked);
        }
    };

    public void onResume(){
        super.onResume();
        myCalendar.setTime(habit.createdOn());

        sunday.setChecked(habit.getDays().get("Sunday"));
        monday.setChecked(habit.getDays().get("Monday"));
        tuesday.setChecked(habit.getDays().get("Tuesday"));
        wednesday.setChecked(habit.getDays().get("Wednesday"));
        thursday.setChecked(habit.getDays().get("Thursday"));
        friday.setChecked(habit.getDays().get("Friday"));
        saturday.setChecked(habit.getDays().get("Saturday"));

        createdOn.setText(formatDate(habit.createdOn()));

        completions.setText(setCompletionText());

    }
    public void onStart(){
        super.onStart();
        System.out.println(habit.toString());
        name.setText(habit.getName());

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        monday.setOnCheckedChangeListener(trackDay);
        tuesday.setOnCheckedChangeListener(trackDay);
        wednesday.setOnCheckedChangeListener(trackDay);
        thursday.setOnCheckedChangeListener(trackDay);
        friday.setOnCheckedChangeListener(trackDay);
        sunday.setOnCheckedChangeListener(trackDay);
        saturday.setOnCheckedChangeListener(trackDay);
//        historyContainer.setScrollContainer(false);
//        history = habit.getHistory();
//        historyAdapter = new ArrayAdapter<Date>(this, R.layout.history_item, history);
//        historyContainer.setAdapter(historyAdapter);

    }

    public void done(){
        if (name.getText().toString() == ""){
            // Eventually add a pop up prompting user for name
        }
        Intent intent = new Intent();
        intent.putExtra("index", index);
        intent.putExtra("Habit", habit);
        setResult(RESULT_OK, intent);
        finish();
    }
    private String formatDate(Date date){
        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat simpleFormat = new SimpleDateFormat(myFormat, Locale.US);
        return "Created on: " + simpleFormat.format(date);
    }
    private void updateLabel() {

        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date newDate = myCalendar.getTime();
        habit.setCreatedionDate(newDate);
        createdOn.setText(formatDate(newDate));
    }

    private String setCompletionText(){
        int totalCompletions = habit.getHistory().size();
        if(totalCompletions<1){
            return "You have not completed this habit yet.";
        } else if(totalCompletions == 1) {
            return "You have completed this habit once";
        } else if(totalCompletions > 1){
            return "You have completed this habit " + totalCompletions + " times";
        }
        return "Error retrieving completions";
    }
};

