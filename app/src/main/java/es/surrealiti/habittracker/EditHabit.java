package es.surrealiti.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Justin on 2016-09-30.
 */

public class EditHabit extends AppCompatActivity {
    private Habit habit;
    private EditText name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habit);

        name = (EditText) findViewById(R.id.habitName);
        habit =(Habit) getIntent().getParcelableExtra("Habit");
    }
    public void onStart(){
        super.onStart();
        System.out.println(habit.toString());
        name.setText(habit.getName());
    }
};
