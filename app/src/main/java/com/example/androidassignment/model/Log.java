package com.example.androidassignment.model;


import java.util.Date;

public class Log {
    private int id;
    private int day;
    private String dayName;
    private String pilot;
    private int  serial;
    private String contract;
    private String category;
    private Date date;

    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public String getLongString(){
        return Double.toString(longitude);
    }
    public String getLatString(){
        return Double.toString(latitude);
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getSerial() {
        return serial;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }
    public Log(){

    }
    public Log(int day, String dayName, String pilot, int serial, String contract, String category ) {
        this.day = day;
        this.dayName = dayName;
        this.pilot = pilot;
        this.serial = serial;
        this.contract = contract;
        this.category = category;
        this.longitude= 0.0;
        this.latitude = 0.0;
    }

    public Log(int day, String dayName, String pilot, int serial, String contract, String category , double longitude , double latitude ) {
        this.day = day;
        this.dayName = dayName;
        this.pilot = pilot;
        this.serial = serial;
        this.contract = contract;
        this.category = category;
        this.longitude= longitude;
        this.latitude = latitude;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
