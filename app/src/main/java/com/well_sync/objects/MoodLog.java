package com.well_sync.objects;

import java.util.Date;
import java.util.Objects;

/**
 * Represents observations of patient mood for one specific date.
 */
public class MoodLog {

    private Date date;
    private int moodScore;
    private int sleepMinutes;
    private String notes;

    public MoodLog() {
        this.date = new Date(); // initialized to present
    }

    public MoodLog(Date date, int moodScore, int sleepMinutes, String notes) {
        this.date = date;
        this.setMoodScore(moodScore); // bounds checking
        this.setSleepMinutes(sleepMinutes); // " "
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public int getSleepMinutes() {
        return sleepMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMoodScore(int moodScore) {
        if (moodScore < -4)
            this.moodScore = -4;
        else if (moodScore > 4)
            this.moodScore = 4;
        else
            this.moodScore = moodScore;
    }

    public void setSleepMinutes(int sleepMinutes) {
        this.sleepMinutes = Math.min(sleepMinutes, 0);
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
                && sleepMinutes == moodLog.sleepMinutes
                && Objects.equals(date, moodLog.date)
                && Objects.equals(notes, moodLog.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, moodScore, sleepMinutes, notes);
    }
}
