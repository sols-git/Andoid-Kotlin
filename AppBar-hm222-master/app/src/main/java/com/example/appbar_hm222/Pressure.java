package com.example.appbar_hm222;

import java.util.Date;

public class Pressure {

    public int hiPressVal;
    public int lowPressVal;
    public int pulseVal;
    public Boolean tachyCheck;
    public Date timeRec;

    public Pressure(int hiPressVal, int lowPressVal, int pulseVal, Boolean tachyCheck, Date timeRec) {
        this.hiPressVal = hiPressVal;
        this.lowPressVal = lowPressVal;
        this.pulseVal = pulseVal;
        this.tachyCheck = tachyCheck;
        this.timeRec = timeRec;
    }

    @Override
    public String toString() {
        return "Pressure{" +
                "hiPressVal=" + hiPressVal +
                ", lowPressVal=" + lowPressVal +
                ", pulseVal=" + pulseVal +
                ", tachyCheck=" + tachyCheck +
                ", timeRec=" + timeRec +
                '}';
    }
}

