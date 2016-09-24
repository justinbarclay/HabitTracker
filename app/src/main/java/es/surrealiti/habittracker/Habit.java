package es.surrealiti.habittracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Justin on 2016-09-23.
 */

public class Habit {
    private String name;
    private int id;
    private Date created;
    private ArrayList<Date> weekdays;
    private ArrayList<Date> completed;

    public Habit(String name, Date day){
        this.name = name;
        this.created = new Date();
        this.weekdays = new ArrayList<Date>();
        this.weekdays.add(day);
        this.completed = new ArrayList<Date>();
    }

    @Override
    public String toString(){
        return this.created.toString() + " | " + this.name;
    }
}
