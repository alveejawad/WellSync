package com.well_sync.objects;

public class Symptom {
    String name;
    int intensity; // from 0 to 10

    public Symptom(String name, int intensity) {
        this.name = name;
        this.intensity = intensity;
    }
}
