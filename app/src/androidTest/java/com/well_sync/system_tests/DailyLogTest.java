package com.well_sync.system_tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.well_sync.R;
import com.well_sync.presentation.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DailyLogTest {

    @Before
    public void loginTest() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.editEmail)).perform(typeText("patient1@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editPassword)).perform(typeText("password1"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Loginbutton)).perform(click());
    }
    @Test
    public void dailyLogTest() {
        // mood log
        onView(withId(R.id.moodID)).perform(click());
        onView(withId(R.id.smile)).perform(longClick());
        onView(withId(R.id.sleep_hours)).perform(typeText("8"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.user_notes)).perform(typeText("I feel ok today"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.emotions_result)).check(matches(withText("Emotion: Happy Mood")));
        onView(withId(R.id.sleep_hours_result)).check(matches(withText("Sleep Hours: 8")));
        onView(withId(R.id.note_result)).check(matches(withText("User Notes: I feel ok today")));
        onView(withId(R.id.button_continue)).perform(click());

        // symptom log
        onView(withId(R.id.symptomID)).perform(click());
        onView(withId(R.id.ratingSadness)).perform(longClick());
        onView(withId(R.id.ratingHelplessness)).perform(longClick());
        onView(withId(R.id.ratingSelfEsteem)).perform(longClick());
        onView(withId(R.id.ratingIsolation)).perform(longClick());
        onView(withId(R.id.ratingLowMotivation)).perform(longClick());
        onView(withId(R.id.ratingImpulsivity)).perform(longClick());
        onView(withId(R.id.ratingConcentration)).perform(longClick());
        onView(withId(R.id.ratingAggressiveness)).perform(longClick());
        onView(withId(R.id.ratingGrandioseIdeas)).perform(longClick());
        onView(withId(R.id.ratingRacingThoughts)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingAnxiety)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingSleep)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingHeadache)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingPain)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingAppetite)).perform(scrollTo(), scrollTo(), longClick());
        onView(withId(R.id.ratingGuilt)).perform(scrollTo(), longClick());
        onView(withId(R.id.ratingSuicide)).perform(scrollTo(), longClick());
        onView(withId(R.id.savebutton)).perform(scrollTo(), click());

        // medication log
        onView(withId(R.id.medicationID)).perform(click());
        onView(withId(R.id.name_of_pills)).perform(typeText("Melatonin"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.num_of_pills)).perform(typeText("5"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.num_of_dosage)).perform(typeText("3"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save)).perform(click());
        onView(withId(R.id.button_continue_1)).perform(click());

        // substance log
        onView(withId(R.id.substanceUseID)).perform(click());
        onView(withId(R.id.name_of_subs)).perform(typeText("Alcohol"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.num_of_subs)).perform(typeText("6"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_1)).perform(click());
        onView(withId(R.id.button_continue_2)).perform(click());

        // perform log out
        onView(withId(R.id.logout)).perform(click());
    }
}
