package com.well_sync.system_tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CheckDailyLogTest.class,
        DailyLogTest.class,
        LoginSignupTest.class,
        AddPatientForDoctorTest.class,
        EditPatientDetailTest.class,
        SeeDoctorNotesTest.class,
        TrackPatientDetailTest.class
})
public class AllAcceptanceTests {
}