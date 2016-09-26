package es.surrealiti.habittracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Justin on 2016-09-23.
 */

public class Habit implements Parcelable {
    private String name;
    private int id;
    private Date created;
    private Map<String, Boolean> days;
    private ArrayList<Date> history;

    public Habit(String name){
        this.name = name;
        this.created = new Date();
        this.days = instantiateDays();
        this.history = new ArrayList<Date>();
    }

    public Habit(String name, Date day){
        this.name = name;
        this.created = day;
        this.days = this.instantiateDays();
        this.history = new ArrayList<Date>();
    }

    // Change to String
    @Override
    public String toString(){
        return this.created.toString() + " | " + this.name;
    }


    private Map<String, Boolean> instantiateDays(){
        Map<String, Boolean> map = new HashMap<String, Boolean>(7);
        map.put("Sunday", false);
        map.put("Monday", false);
        map.put("Tuesday", false);
        map.put("Wednesday", false);
        map.put("Thursday", false);
        map.put("Friday", false);
        map.put("Saturday", false);

        return map;
    }

    //Getters and Setters
    public void addDay(String day){
        this.days.put(day, true);
    }
    public void removeDate(String day){
        this.days.put(day, false);
    }

    public String getName(){
        return this.name;
    }
    public Map<String, Boolean> getDays(){
        return this.days;
    }
    public ArrayList<Date> getHistory(){
        return this.history;
    }
    //Implementation of Parcel
    protected Habit(Parcel in) {
        name = in.readString();
        created = new Date(in.readLong());
    }

    public static final Creator<Habit> CREATOR = new Creator<Habit>() {
        @Override
        public Habit createFromParcel(Parcel in) {
            return new Habit(in);
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeLong(created.getTime());
    }
    @Override
    public int describeContents(){
        return 0;
    }
}
