package com.example.jeonjin_il.mysecondapp;

/**
 * Created by jeonjin-il on 2016. 12. 28..
 */

public class WaterList {
    private int id;
    private int water_id;
    private String name;
    private int capacity;
    private int percentage;
    private String day;
    private String time;


    public int getWater_id() {
        return water_id;
    }

    public void setWater_id(int water_id) {
        this.water_id = water_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
