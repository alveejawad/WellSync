package com.well_sync.tests;

import com.well_sync.tests.business.DailyLogHandlerIT;
import com.well_sync.tests.business.PatientHandlerIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        DailyLogHandlerIT.class,
        PatientHandlerIT.class
})

public class AllIntegrationTests {
}
