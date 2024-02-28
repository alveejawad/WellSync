package com.well_sync.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.well_sync.objects.Symptom;

/**
 * Represents observations of patient mood for one specific date.
 */
public class MoodLog implements IDailyLog {

    private Date date;
    private int moodScore;
    private int sleepHours;
    private String notes;

    private List<Symptom> symptomList;

    public MoodLog() {
        this.date = new Date(); // initialized to present
    }

    public MoodLog(Date date, int moodScore, int sleepHours, String notes) {
        this.symptomList = new ArrayList<>();
        this.date = date;
        this.setMoodScore(moodScore); // bounds checking
        this.setSleepHours(sleepHours); // " "
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public int getSleepHours() {
        return sleepHours;
    }

    public String getNotes() {
        return notes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public void setSleepHours(int sleepHours) {
        this.sleepHours = Math.min(sleepHours, 0);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoodLog moodLog = (MoodLog) o;
        return moodScore == moodLog.moodScore
                && sleepHours == moodLog.sleepHours
                && Objects.equals(date, moodLog.date)
                && Objects.equals(notes, moodLog.notes);
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

    @Override
    public int hashCode() {
        return Objects.hash(date, moodScore, sleepHours, notes);
    }
}
