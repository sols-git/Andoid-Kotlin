package com.example.medapp_hm131;

import java.util.Date;

public class Healthy {
    public Double weight;
    public int numberStep;
    public Date timeRec;


    public Healthy(Double weight, int numberStep, Date timeRec) {
        this.weight = weight;
        this.numberStep = numberStep;
        this.timeRec = timeRec;
    }

    @Override
    public String toString() {
        return "Healthy{" +
                "weight=" + weight +
                ", numberStep=" + numberStep +
                ", timeRec=" + timeRec +
                '}';
    }
}
