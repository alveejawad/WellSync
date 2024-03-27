package com.well_sync.system_tests;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
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
public class LoginSignupTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void loginAsPatientTest() {
        onView(withId(R.id.editEmail)).perform(typeText("patient1@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Loginbutton)).perform(click());

        // perform log out
        onView(withId(R.id.logout)).perform(click());
    }



    @Test
    public void signUpAsPatientTest() {
        onView(withId(R.id.SignUp)).perform(click());
        onView(withId(R.id.editEmail)).perform(typeText("hungludao@gmail.com"));
        onView(withId(R.id.editPassword)).perform(typeText("password1"));
        onView(withId(R.id.editConfirmPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Roles)).perform(click());
        onView(ViewMatchers.withText("PATIENT")).perform(click());
        onView(withId(R.id.Nextbutton)).perform(click());

        // perform add details to new user
        onView(withId(R.id.editFirstName)).perform(typeText("Luke"));
        onView(withId(R.id.editLastName)).perform(typeText("Dao"));
        onView(withId(R.id.datePicker)).perform(click());
        onView(Matchers.allOf(ViewMatchers.withClassName(Matchers.equalTo("android.widget.DatePicker")), ViewMatchers.isDisplayed()))
                .perform(PickerActions.setDate(2001, 1, 14));
        onView(withId(R.id.Male)).perform(click());
        onView(withId(R.id.BloodTypes)).perform(click());
        onView(ViewMatchers.withText("A+")).perform(click());
        onView(withId(R.id.savebutton)).perform(click());
    }

    @Test
    public void signUpAsDoctorTest() {
        onView(withId(R.id.SignUp)).perform(click());
        onView(withId(R.id.editEmail)).perform(typeText("hungludao@gmail.com"));
        onView(withId(R.id.editPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editConfirmPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Roles)).perform(click());
        onView(ViewMatchers.withText("DOCTOR")).perform(click());
        onView(withId(R.id.Nextbutton)).perform(click());

        // perform log out
        onView(withId(R.id.logout)).perform(click());
    }

    @Test
    public void loginAsDoctorTest() {
        onView(withId(R.id.editEmail)).perform(typeText("doctor1@example.com"));
        onView(withId(R.id.editPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Loginbutton)).perform(click());

        // perform log out
        onView(withId(R.id.logout)).perform(click());
    }
}
