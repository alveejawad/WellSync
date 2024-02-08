package com.well_sync.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Import the individual test files
import com.well_sync.tests.business.MoodLogHandlerTest;
import com.well_sync.tests.business.PatientHandlerTest;
import com.well_sync.tests.business.UserAuthenticationHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserAuthenticationHandlerTest.class,
        MoodLogHandlerTest.class,
        PatientHandlerTest.class
})
public class AllUnitTests {}
