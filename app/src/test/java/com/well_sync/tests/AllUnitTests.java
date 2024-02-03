package com.well_sync.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Import the individual test files
import com.well_sync.logic.RecipeTagValidator;
import com.well_sync.tests.business.ExceptionsTest;
import com.well_sync.tests.business.InitBusinessTest;
import com.well_sync.tests.business.RecipeHandlerTest;
import com.well_sync.tests.business.RecipeTagHandlerTest;
import com.well_sync.tests.business.RecipeValidatorTest;
import com.well_sync.tests.objects.InitObjectTest;
import com.well_sync.tests.objects.RecipeTagTest;
import com.well_sync.tests.objects.RecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitBusinessTest.class,
        InitObjectTest.class,
        RecipeTest.class,
        RecipeHandlerTest.class,
        RecipeTagTest.class,
        RecipeTagHandlerTest.class,
        RecipeValidatorTest.class,
        ExceptionsTest.class
})

public class AllUnitTests
{

}
