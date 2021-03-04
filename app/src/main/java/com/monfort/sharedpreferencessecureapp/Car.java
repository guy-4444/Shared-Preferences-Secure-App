package com.monfort.sharedpreferencessecureapp;

public class Car {

    private String model = "";
    private int engine = 0;

    public Car() { }

    public Car(String model, int engine) {
        this.model = model;
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getEngine() {
        return engine;
    }

    public Car setEngine(int engine) {
        this.engine = engine;
        return this;
    }
}
