package com.well_sync.system_tests;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.well_sync.R;
import com.well_sync.presentation.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditPatientDetailTest {

    @Before
    public void loginTest() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.editEmail)).perform(typeText("patient1@example.com"));
        onView(withId(R.id.editPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Loginbutton)).perform(click());
    }

    @Test
    public void editDetail() {
        onView(withId(R.id.user)).perform(click());
        onView(withId(R.id.Editbutton)).perform(click());

        // perform edit details
        onView(withId(R.id.editFirstName)).perform(typeText("Lucifer"));
        onView(withId(R.id.editLastName)).perform(typeText("Nguyen"));
        onView(withId(R.id.datePicker)).perform(click());
        onView(Matchers.allOf(ViewMatchers.withClassName(Matchers.equalTo("android.widget.DatePicker")), ViewMatchers.isDisplayed()))
                .perform(PickerActions.setDate(2001, 7, 7));
        onView(withId(R.id.Male)).perform(click());
        onView(withId(R.id.BloodTypes)).perform(click());
        onView(ViewMatchers.withText("A+")).perform(click());
        onView(withId(R.id.savebutton)).perform(click());

    }
}
