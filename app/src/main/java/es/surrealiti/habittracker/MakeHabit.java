package es.surrealiti.habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ToggleButton tuesday;
    private ToggleButton wednesday;
    private ToggleButton thursday;
    private ToggleButton friday;
    private ToggleButton saturday;
    private ToggleButton sunday;

    Habit habit = new Habit("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_habit);
        doneButton = (Button) findViewById(R.id.done);
        monday = (ToggleButton) findViewById(R.id.Monday);
        tuesday = (ToggleButton) findViewById(R.id.Tuesday);
        wednesday = (ToggleButton) findViewById(R.id.Wednesday);
        thursday = (ToggleButton) findViewById(R.id.Thursday);
        friday = (ToggleButton) findViewById(R.id.Friday);

        name = (EditText) findViewById(R.id.habitName);

        monday.setOnCheckedChangeListener(trackDay);
        tuesday.setOnCheckedChangeListener(trackDay);
        wednesday.setOnCheckedChangeListener(trackDay);
        thursday.setOnCheckedChangeListener(trackDay);
        friday.setOnCheckedChangeListener(trackDay);


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                habit.setName(s.toString());
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                done();

            }
        });
    }

    private CompoundButton.OnCheckedChangeListener trackDay = new CompoundButton.OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            habit.trackDay(buttonView.getText().toString(), isChecked);
        }
    };

    private void done() {
        if (name.getText().toString() == ""){
            // Eventually add a pop up prompting user for name
        }
        Intent intent = new Intent();
        intent.putExtra("newHabit", habit);
        setResult(RESULT_OK, intent);
        finish();
    }
}
