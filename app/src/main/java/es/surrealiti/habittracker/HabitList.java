package es.surrealiti.habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Justin on 2016-10-02.
 */

public class HabitList {
    private ArrayList<Habit> weeklyHabits;

    public HabitList(ArrayList<Habit> habits){
        weeklyHabits = habits;
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

    // http://stackoverflow.com/a/21553431
    private String getDate(){
        Date date= new Date();
        SimpleDateFormat formatToday = new SimpleDateFormat("EEEE");
        String today = formatToday.format(date);
        return today;
    }

    public void update(Habit habit){
        for(int i=0; i<weeklyHabits.size(); ++i){
            if (habit.getID() == weeklyHabits.get(i).getID()){
                weeklyHabits.remove(i);
                weeklyHabits.add(i, habit);
            }
        }
    }
}
