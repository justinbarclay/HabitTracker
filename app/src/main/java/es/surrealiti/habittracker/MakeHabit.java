package es.surrealiti.habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import es.surrealiti.habittracker.R;

/**
 * Created by Justin on 2016-09-25.
 */

public class MakeHabit extends AppCompatActivity {
    Habit newHabit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_habit);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
//        layout.addView(textView);
    }
}
