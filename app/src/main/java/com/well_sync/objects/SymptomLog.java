package com.well_sync.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents observations of patient's physical health symptoms for
 * one specific date.
 */
public class SymptomLog implements IDailyLog {

    public static class Symptom {
        String name;
        int intensity; // from 0 to 10

        public Symptom(String name, int intensity) {
            this.name = name;
            this.intensity = intensity;
        }
    }

    private Date date;

    private final List<Symptom> symptomList;

    public SymptomLog() {
        this.date = new Date();
        this.symptomList = new ArrayList<Symptom>();
    }

    public SymptomLog(Date date) {
        this.date = date;
        this.symptomList = new ArrayList<Symptom>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addSymptom(String name, int intensity) {
        Symptom symptom = new Symptom(name, intensity);
        // insert call to symptom validator here
        this.symptomList.add(symptom);
    }

    public void removeSymptom(Symptom symptom) {
        this.symptomList.remove(symptom);
    }

    public void removeSymptom(String name) {
        this.symptomList.forEach(symptom -> {
            if (symptom.name.equals(name)) this.symptomList.remove(symptom);
        });
    }

    public List<Symptom> getSymptoms() {
        return this.symptomList;
    }
}
