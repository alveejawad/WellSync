package com.well_sync.objects;

import java.util.Date;
import java.util.Objects;

/**
 * Represents observations of patient mood for one specific date.
 */
public class MoodLog implements IDailyLog {

    private Date date;
    private int moodScore;
    private int sleepHours;
    private String notes;

    public MoodLog() {
        this.date = new Date(); // initialized to present
    }

    public MoodLog(Date date, int moodScore, int sleepHours, String notes) {
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

    @Override
    public int hashCode() {
        return Objects.hash(date, moodScore, sleepHours, notes);
    }
}
