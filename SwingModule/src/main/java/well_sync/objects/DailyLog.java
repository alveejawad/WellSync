package well_sync.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents observations of patient health for one specific date.
 */
public class DailyLog {
    // Defined here in case future extensions want to change this
    public final int maxMoodScore = 4;
    public final int maxSleepHours = 16;

    private Date date;
    private int moodScore;
    private int sleepHours;
    private String notes;
    private List<Symptom> symptomList;
    private List<Medication> medicationList;
    private List<Substance> substanceList;

    public DailyLog() {
        this.date = new Date(); // initialized to present
    }

    public DailyLog(Date date, int moodScore, int sleepHours, String notes) {
        this.date = date;
        this.setMoodScore(moodScore); // bounds checking
        this.setSleepHours(sleepHours); // " "
        this.notes = notes;

        this.symptomList = new ArrayList<>();
        this.medicationList = new ArrayList<>();
        this.substanceList = new ArrayList<>();
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
        this.sleepHours = Math.max(sleepHours, 0);
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
            if (symptom.getName().equals(name)) this.symptomList.remove(symptom);
        });
    }

    public List<Symptom> getSymptoms() {
        return this.symptomList;
    }

    public void addMedication(String name, int quantity){
            MedicationValidator medicationValidator;
            Medication med = new Medication(name, quantity);
            //MedicationValidator.validateMedication(med);
            this.medicationList.add(med);
    }

    public void removeMedication(Medication medication) {
        this.medicationList.remove(medication);
    }

    public void removeMedication(String name) {
        this.medicationList.forEach(med -> {
            if (med.getName().equals(name)) this.medicationList.remove(med);
        });
    }

    public List<Medication> getMedications() {
        return this.medicationList;
    }

    public void addSubstance(String name, int quantity) {
        Substance sub = new Substance(name, quantity);
        // insert call to substance validator here
        this.substanceList.add(sub);
    }

    public void removeSubstance(Substance sub) {
        this.substanceList.remove(sub);
    }

    public void removeSubstance(String name) {
        this.substanceList.forEach(sub -> {
            if (sub.getName().equals(name)) this.substanceList.remove(sub);
        });
    }

    public List<Substance> getSubstances() {
        return this.substanceList;
    }

}
