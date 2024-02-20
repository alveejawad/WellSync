package com.well_sync.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.IUserPersistence;
public class UserPersistenceHSQLDB implements IUserPersistence{
    @Override
    public UserCredentials getUserCredentials(UserCredentials userCredentials) {
        return null;
    }

    @Override
    public void setUserCredentials(UserCredentials user) {

    }

    @Override
    public Patient getPatient(UserCredentials userCredentials) {
        return null;
    }

    @Override
    public void setPatient(Patient patient) {

    }

    @Override
    public Patient getPatient(String email) {
        return null;
    }
}
