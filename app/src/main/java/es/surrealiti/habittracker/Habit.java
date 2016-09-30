package es.surrealiti.habittracker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Justin on 2016-09-23.
 */

public class Habit implements Parcelable {
    private String name;
    private UUID id;
    private Date created;
    private HashMap<String, Boolean> days;
    private ArrayList<Date> history;

    public Habit(String name){
        this.name = name;
        this.id = UUID.randomUUID();
        this.created = new Date();
        this.days = instantiateDays();
        this.history = new ArrayList<Date>();
    }

    public Habit(String name, Date day){
        this.name = name;
        this.id = UUID.randomUUID();
        this.created = day;
        this.days = this.instantiateDays();
        this.history = new ArrayList<Date>();
    }

    // Change to String
    @Override
    public String toString(){
        return this.created.toString() + " | " + this.name + "\n" + this.days.toString();
    }

    public UUID getID(){
        return this.id;
    }

    public boolean equals(Habit otherHabit){
        return this.id == otherHabit.getID();
    }

    private HashMap<String, Boolean> instantiateDays(){
        HashMap<String, Boolean> map = new HashMap<String, Boolean>(7);
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
    public void trackDay(String day, Boolean track){
        this.days.put(day, track);
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Map<String, Boolean> getDays(){
        return this.days;
    }
    public ArrayList<Date> getHistory(){
        return this.history;
    }
    //Implementation of Parcel
    protected Habit(Parcel in) {
        this.name = in.readString();
        this.id = UUID.fromString(in.readString());
        this.created = new Date(in.readLong());
        days = (HashMap<String, Boolean>) in.readBundle().getSerializable("days");
        this.history = new ArrayList<Date>();
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
        out.writeString(id.toString());
        out.writeLong(created.getTime());

        Bundle daysBundle = new Bundle();
        daysBundle.putSerializable("days",days);
        out.writeBundle(daysBundle);
    }
    @Override
    public int describeContents(){
        return 0;
    }

    public void addHistory(){
        history.add(new Date());
    }

    public void deleteHistory(Date date){
        history.remove(date);
    }
}
