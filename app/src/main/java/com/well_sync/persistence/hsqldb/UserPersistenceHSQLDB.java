package com.well_sync.persistence.hsqldb;

import android.util.Log;

import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.IUserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceHSQLDB implements IUserPersistence {

    private final String dbPath;
    private IUserPersistence userPersistence;
    private List<UserCredentials> userCredentialsList;
    private List<Patient> patientsList;

    public UserPersistenceHSQLDB(IUserPersistence userPersistence, String dbPath) {
        this.dbPath = dbPath;
        this.userPersistence = userPersistence;
        this.userCredentialsList = new ArrayList<>();
        this.patientsList = new ArrayList<>();
        loadUserCredentials();
        loadPatients();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "MD", "");
    }

    private void loadUserCredentials() {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER_CREDENTIALS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userCredentialsList.add(new UserCredentials(resultSet.getString("email"), resultSet.getString("password")));
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private void loadPatients() {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PATIENTS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Patient patient = createPatientFromResultSet(resultSet);
                patientsList.add(patient);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public UserCredentials getUserCredentials(UserCredentials userCredentials) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER_CREDENTIALS WHERE email = ?");
            statement.setString(1, userCredentials.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new UserCredentials(resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setUserCredentials(UserCredentials user) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USER_CREDENTIALS VALUES (?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatient(UserCredentials userCredentials) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PATIENTS WHERE email = ?");
            statement.setString(1, userCredentials.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createPatientFromResultSet(resultSet);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setPatient(Patient patient) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PATIENTS VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, patient.getEmail());
            statement.setString(2, patient.getFirstName());
            statement.setString(3, patient.getLastName());
            statement.setString(4, patient.getBloodType().toString());
            statement.setString(5, patient.getSex().toString());
            statement.setInt(6, patient.getAge());
            statement.executeUpdate();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatient(String email) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PATIENTS WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createPatientFromResultSet(resultSet);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getPatientsList(){
        return this.patientsList;
    }

    public List<UserCredentials> getUserCredentialsList() {
        return this.userCredentialsList;
    }

    private Patient createPatientFromResultSet(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String bloodType = resultSet.getString("bloodType");
        String sex = resultSet.getString("sex");
        int age = resultSet.getInt("age");

        return new Patient(email, firstName, lastName, bloodType, sex, age);
    }
}
