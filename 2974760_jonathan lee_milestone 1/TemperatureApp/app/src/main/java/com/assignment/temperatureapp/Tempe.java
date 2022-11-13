package com.assignment.temperatureapp;

public class Tempe {

    int id;
    String degree;
    String date;

    public Tempe(int id, String degree, String date) {
        this.id = id;
        this.degree = degree;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
