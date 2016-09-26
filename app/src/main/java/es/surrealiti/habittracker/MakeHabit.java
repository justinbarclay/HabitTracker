package es.surrealiti.habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * Created by Justin on 2016-09-25.
 */

public class MakeHabit extends AppCompatActivity {
    private Button doneButton;
    private EditText name;
    private ToggleButton monday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_habit);
        doneButton = (Button) findViewById(R.id.done);
        monday = (ToggleButton) findViewById(R.id.Monday);
        name = (EditText) findViewById(R.id.habitName);

        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        doneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                done();

            }
        });
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
//        layout.addView(textView);
    }

    private void done() {
        Habit newHabit = new Habit(name.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("newHabit", newHabit);
        setResult(RESULT_OK, intent);
        finish();
    }
}
