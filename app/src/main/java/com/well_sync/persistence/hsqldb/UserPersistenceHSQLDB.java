package com.well_sync.persistence.hsqldb;

import com.well_sync.objects.Doctor;
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
    private final List<UserCredentials> userCredentialsList;
    private final List<Patient> patientsList;
    private final List<Doctor> doctorsList;

    public UserPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.userCredentialsList = new ArrayList<>();
        this.patientsList = new ArrayList<>();
        this.doctorsList = new ArrayList<>();
        loadUserCredentials();
        loadPatients();
        loadDoctors();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "MD", "");
    }

    private void loadUserCredentials() {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER_CREDENTIALS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userCredentialsList.add(new UserCredentials(resultSet.getString("email"), resultSet.getString("password"),resultSet.getString("role")));
            }
        } catch (final SQLException e) {
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
            e.printStackTrace();
        }
    }

    private void loadDoctors() {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOCTORS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = createDoctorFromResultSet(resultSet);
                doctorsList.add(doctor);
            }
        } catch (final SQLException e) {
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
                return new UserCredentials(resultSet.getString("email"), resultSet.getString("password"),resultSet.getString("role"));
            }
        } catch (final SQLException e) {
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDoctor(Doctor doctor) {
        try (Connection connection = connect()) {
            PreparedStatement doctorStatement = connection.prepareStatement("INSERT INTO DOCTORS VALUES (?, ?, ?)");
            doctorStatement.setString(1, doctor.getEmail());
            doctorStatement.setString(2, doctor.getFirstName());
            doctorStatement.setString(3, doctor.getLastName());
            doctorStatement.executeUpdate();

            List<Patient> doctorPatients = doctor.getPatients();
            if (doctorPatients != null && !doctorPatients.isEmpty()) {
                PreparedStatement assignStatement = connection.prepareStatement("INSERT INTO ASSIGNED_PATIENTS VALUES (?, ?)");
                for (Patient patient : doctorPatients) {
                    assignStatement.setString(1, doctor.getEmail());
                    assignStatement.setString(2, patient.getEmail());
                    assignStatement.executeUpdate();
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctor(UserCredentials userCredentials) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOCTORS WHERE email = ?");
            statement.setString(1, userCredentials.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createDoctorFromResultSet(resultSet);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Doctor getDoctor(String email) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOCTORS WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createDoctorFromResultSet(resultSet);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getPatientsList(){
        return this.patientsList;
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

    private Doctor createDoctorFromResultSet(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");

        Doctor doctor = new Doctor(email, firstName, lastName);
        loadDoctorPatients(doctor);

        return doctor;
    }

    private void loadDoctorPatients(Doctor doctor) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ASSIGNED_PATIENTS WHERE doctor_email = ?");
            statement.setString(1, doctor.getEmail());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String patientEmail = resultSet.getString("patient_email");
                Patient patient = getPatient(patientEmail);
                if (patient != null) {
                    doctor.addPatient(patient);
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
