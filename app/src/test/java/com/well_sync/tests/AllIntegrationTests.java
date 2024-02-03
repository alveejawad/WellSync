package com.well_sync.tests;

import com.well_sync.tests.business.RecipeHandlerIT;
import com.well_sync.tests.business.RecipeTagHandlerIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        RecipeHandlerIT.class,
        RecipeTagHandlerIT.class
})

public class AllIntegrationTests {
}
