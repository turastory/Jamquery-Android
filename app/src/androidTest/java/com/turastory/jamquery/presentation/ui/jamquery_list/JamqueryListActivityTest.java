package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.turastory.jamquery.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by tura on 2018-04-11.
 */
@RunWith(AndroidJUnit4.class)
public class JamqueryListActivityTest {
    
    @Rule
    public ActivityTestRule<JamqueryListActivity> activityTestRule = new ActivityTestRule<>(JamqueryListActivity.class);
    
    @Test
    public void test_showEmptyViewWhenNotEditQuery() {
        onView(withId(R.id.jamquery_list_empty_view))
            .check(matches(isDisplayed()));
    }
}