package com.example.customadapter;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    public String name;
    public int age;
    public List <Pressure> prerssParam;
    public List <Healthy> healthParam;

    public User(String name, int age, List prerssParam, List healthParam) {
        this.name = name;
        this.age = age;
        this.prerssParam = prerssParam;
        this.healthParam = healthParam;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", prerssParam=" + prerssParam +
                ", healthParam=" + healthParam +
                '}';
    }
}
