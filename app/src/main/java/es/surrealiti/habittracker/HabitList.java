package es.surrealiti.habittracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Created by Justin on 2016-10-02.
 */

public class HabitList {
    private ArrayList<Habit> weeklyHabits;

    public HabitList(){
        weeklyHabits = new ArrayList<Habit>();
    }
    public HabitList(ArrayList<Habit> habits){
        weeklyHabits = habits;
    }

    public ArrayList<Habit> getAllHabits(){
        return weeklyHabits;
    }
    public ArrayList<Habit> getTodaysHabits() {
        ArrayList<Habit> todaysHabits = new ArrayList<Habit>();
        String today = getDate();
        for(int i =0; i<weeklyHabits.size(); ++i){
            if(weeklyHabits.get(i).getDays().get(today)){
                todaysHabits.add(weeklyHabits.get(i));
            }
        }
        return todaysHabits;
    }

    public void removeHabit(Habit habit){
        System.out.println("Made it into HabitList");
        System.out.println(weeklyHabits);
        System.out.println(habit.getID());
        for(int i =0; i< weeklyHabits.size(); ++i){
            System.out.println(weeklyHabits.get(i).getID());
            if(habit.isEqual(weeklyHabits.get(i))){
                System.out.println("Habit removed");
                weeklyHabits.remove(i);
            }

        }
    }

    public void addHabit(Habit habit){
        weeklyHabits.add(habit);
    }

    // http://stackoverflow.com/a/21553431
    private String getDate(){
        Date date= new Date();
        SimpleDateFormat formatToday = new SimpleDateFormat("EEEE");
        String today = formatToday.format(date);
        return today;
    }

    public void update(Habit habit){
        for(int i=0; i<weeklyHabits.size(); ++i){
            if (habit.isEqual(weeklyHabits.get(i))){
                weeklyHabits.remove(i);
                weeklyHabits.add(i, habit);
            }
        }
    }
}
