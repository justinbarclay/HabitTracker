package es.surrealiti.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class View_Today_Habits extends AppCompatActivity {
    private ArrayList<Habit> allHabits = new ArrayList<Habit>();
    ListView habitsContainer;
    Button newHabit;
    ArrayAdapter<Habit> habitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__today__habits);
        allHabits.add(new Habit("Make a habit tracker", new Date()));
        habitsContainer = (ListView) findViewById(R.id.list_habits);
        newHabit = (Button) findViewById(R.id.add_habit_button);

        newHabit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_OK);
                addNewHabit();

            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    protected void onStart() {
        super.onStart();
        this.habitAdapter = new ArrayAdapter<Habit>(this, R.layout.single_habit, allHabits);
        habitsContainer.setAdapter(habitAdapter);
    }
    public void addNewHabit() {
//        this.allHabits.add(new Habit("This is a new habit", new Date()));
//        this.habitAdapter.notifyDataSetChanged();
        Intent intent = new Intent(this, MakeHabit.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Habit newHabit= (Habit) data.getParcelableExtra("newHabit");
                Log.i("info", String.valueOf(newHabit));
                allHabits.add(newHabit);
                habitAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                throw new RuntimeException();
            }
        }
    }
}
