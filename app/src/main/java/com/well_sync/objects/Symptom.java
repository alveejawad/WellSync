package com.well_sync.objects;

public class Symptom {
    public final int maxIntensity = 10;
    private String name;
    private int intensity; // from 0 to 10

    public Symptom(String name, int intensity) {
        this.name = name;
        this.intensity = intensity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntensity() {
        return this.intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

}
