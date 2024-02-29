package com.well_sync.persistence.hsqldb;

import android.util.Log;
import com.well_sync.objects.Patient;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.Symptom;
import com.well_sync.objects.Medication;
import com.well_sync.objects.Substance;
import com.well_sync.persistence.IMoodLogPersistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoodLogPersistenceHSQLDB implements IMoodLogPersistence {

    private final String dbPath;
    private IMoodLogPersistence moodLogPersistence;

    public MoodLogPersistenceHSQLDB(IMoodLogPersistence moodLogPersistence, String dbPath) {
        this.dbPath = dbPath;
        this.moodLogPersistence = moodLogPersistence;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "MD", "");
    }

    @Override
    public void setMoodLog(Patient patient, MoodLog moodLog) {
        try (Connection connection = connect()) {
            // Insert MoodLog into DAILY_LOG table
            PreparedStatement moodLogStatement = connection.prepareStatement(
                    "INSERT INTO DAILY_LOG (patient_email, log_date, mood_score, sleep_hours, notes) VALUES (?, ?, ?, ?, ?)"
            );
            moodLogStatement.setString(1, patient.getEmail());
            moodLogStatement.setDate(2, new java.sql.Date(moodLog.getDate().getTime()));
            moodLogStatement.setInt(3, moodLog.getMoodScore());
            moodLogStatement.setInt(4, moodLog.getSleepHours());
            moodLogStatement.setString(5, moodLog.getNotes());
            moodLogStatement.executeUpdate();

            // Insert Symptoms into SYMPTOMS table
            for (Symptom symptom : moodLog.getSymptoms()) {
                PreparedStatement symptomStatement = connection.prepareStatement(
                        "INSERT INTO SYMPTOMS (patient_email, log_date, symptom, intensity) VALUES (?, ?, ?, ?)"
                );
                symptomStatement.setString(1, patient.getEmail());
                symptomStatement.setDate(2, new java.sql.Date(moodLog.getDate().getTime()));
                symptomStatement.setString(3, symptom.getName());
                symptomStatement.setInt(4, symptom.getIntensity());
                symptomStatement.executeUpdate();
            }

            // Insert Medications into MEDICATIONS table
            for (Medication medication : moodLog.getMedications()) {
                PreparedStatement medicationStatement = connection.prepareStatement(
                        "INSERT INTO MEDICATIONS (patient_email, log_date, medication, quantity) VALUES (?, ?, ?, ?)"
                );
                medicationStatement.setString(1, patient.getEmail());
                medicationStatement.setDate(2, new java.sql.Date(moodLog.getDate().getTime()));
                medicationStatement.setString(3, medication.getName());
                medicationStatement.setInt(4, medication.getQuantity());
                medicationStatement.executeUpdate();
            }

            // Insert Substances into SUBSTANCES table
            for (Substance substance : moodLog.getSubstances()) {
                PreparedStatement substanceStatement = connection.prepareStatement(
                        "INSERT INTO SUBSTANCES (patient_email, log_date, substance, quantity) VALUES (?, ?, ?, ?)"
                );
                substanceStatement.setString(1, patient.getEmail());
                substanceStatement.setDate(2, new java.sql.Date(moodLog.getDate().getTime()));
                substanceStatement.setString(3, substance.getName());
                substanceStatement.setInt(4, substance.getQuantity());
                substanceStatement.executeUpdate();
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public MoodLog getMoodLog(Patient patient, Date date) {
        MoodLog moodLog = null;
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM DAILY_LOG WHERE patient_email = ? AND log_date = ?"
            );
            statement.setString(1, patient.getEmail());
            statement.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                moodLog = getMoodLogFromResultSet(connection, resultSet, patient.getEmail());
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return moodLog;
    }

    @Override
    public List<MoodLog> getAllMoodLogs(Patient patient) {
        List<MoodLog> moodLogs = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT DISTINCT log_date FROM DAILY_LOG WHERE patient_email = ?"
            );
            statement.setString(1, patient.getEmail());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("log_date");
                MoodLog moodLog = getMoodLog(patient, date);
                if (moodLog != null) {
                    moodLogs.add(moodLog);
                }
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return moodLogs;
    }

    private MoodLog getMoodLogFromResultSet(Connection connection, ResultSet resultSet, String email) throws SQLException {
        int moodScore = resultSet.getInt("mood_score");
        int sleepHours = resultSet.getInt("sleep_hours");
        String notes = resultSet.getString("notes");
        Date date = resultSet.getDate("log_date");
        MoodLog moodLog = new MoodLog(date, moodScore, sleepHours, notes);

        // Fetch Symptoms
        PreparedStatement symptomStatement = connection.prepareStatement(
                "SELECT * FROM SYMPTOMS WHERE patient_email = ? AND log_date = ?"
        );
        symptomStatement.setString(1, email);
        symptomStatement.setDate(2, new java.sql.Date(date.getTime()));
        ResultSet symptomResultSet = symptomStatement.executeQuery();
        while (symptomResultSet.next()) {
            String symptomName = symptomResultSet.getString("symptom");
            int symptomIntensity = symptomResultSet.getInt("intensity");
            moodLog.addSubstance(symptomName, symptomIntensity);
        }

        // Fetch Medications
        PreparedStatement medicationStatement = connection.prepareStatement(
                "SELECT * FROM MEDICATIONS WHERE patient_email = ? AND log_date = ?"
        );
        medicationStatement.setString(1, email);
        medicationStatement.setDate(2, new java.sql.Date(date.getTime()));
        ResultSet medicationResultSet = medicationStatement.executeQuery();
        while (medicationResultSet.next()) {
            String medicationName = medicationResultSet.getString("medication");
            int medicationQuantity = medicationResultSet.getInt("quantity");
            moodLog.addMedication(medicationName, medicationQuantity);
        }

        // Fetch Substances
        PreparedStatement substanceStatement = connection.prepareStatement(
                "SELECT * FROM SUBSTANCES WHERE patient_email = ? AND log_date = ?"
        );
        substanceStatement.setString(1, email);
        substanceStatement.setDate(2, new java.sql.Date(date.getTime()));
        ResultSet substanceResultSet = substanceStatement.executeQuery();
        while (substanceResultSet.next()) {
            String substanceName = substanceResultSet.getString("substance");
            int substanceQuantity = substanceResultSet.getInt("quantity");
            moodLog.addSubstance(substanceName, substanceQuantity);
        }

        return moodLog;
    }
}
