package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.arch.persistence.room.Room;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.turastory.jamquery.R;
import com.turastory.jamquery.data.datasource.local.JamqueryLocalDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * TODO: Test clicking on the list item.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class JamqueryListActivityTest {
    
    @Rule
    public ActivityTestRule<JamqueryListActivity> activityTestRule = new ActivityTestRule<JamqueryListActivity>(JamqueryListActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            JamqueryLocalDatabase.setDatabaseProvider(context ->
                Room.inMemoryDatabaseBuilder(context, JamqueryLocalDatabase.class)
                    .build());
        }
    };
    
    @Test
    public void test_showEmptyViewWhenNotEditQuery() {
        onView(withId(R.id.jamquery_list_empty_view))
            .check(matches(isDisplayed()));
    }
    
    @Test
    public void test_hideEmptyViewWhenEditQuery() {
        onView(withId(R.id.edit_text_query))
            .perform(typeText("asdf"));
        
        onView(withId(R.id.jamquery_list_empty_view))
            .check(matches(not(isDisplayed())));
    }
    
    @Test
    public void test_showEmptyViewAgainAfterClearEditQuery() {
        onView(withId(R.id.edit_text_query))
            .perform(typeText("asdf"));
    
        onView(withId(R.id.jamquery_list_empty_view))
            .check(matches(not(isDisplayed())));
    
        onView(withId(R.id.edit_text_query))
            .perform(clearText());
    
        onView(withId(R.id.jamquery_list_empty_view))
            .check(matches(isDisplayed()));
    }
    
    @Test
    public void test_showAddDialogOnClickFabAndClose() {
        onView(withId(R.id.fab_add))
            .perform(click());
        
        onView(withId(R.id.edit_text_title))
            .check(matches(isDisplayed()));
        
        onView(withId(R.id.button_add))
            .perform(click());
        
        onView(withId(R.id.edit_text_title))
            .check(doesNotExist());
    }
    
    @Test
    public void test_addJamqueryValidateUrl() {
        onView(withId(R.id.fab_add))
            .perform(click());
        
        onView(withId(R.id.edit_text_url))
            .check(matches(isDisplayed()));
        
        validUrl("https://www.google.com/");
        validUrl("http://realm.io/kr/");
        invalidUrl("http://www");
        invalidUrl("https:int.com/");
    }
    
    private void invalidUrl(String stringToBeTyped) {
        onView(withId(R.id.edit_text_url))
            .perform(clearText())
            .perform(typeText(stringToBeTyped));
        
        onView(withId(R.id.text_message_url))
            .check(matches(withText("Invalid Url")));
    }
    
    private void validUrl(String stringToBeTyped) {
        onView(withId(R.id.edit_text_url))
            .perform(clearText())
            .perform(typeText(stringToBeTyped));
        
        onView(withId(R.id.text_message_url))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
    
    @Test
    public void test_addJamqueryAndSearch() {
        String title = "Google";
        String url = "https://www.google.com/";
        
        performAddJamquery(title, url);
        
        onView(withId(R.id.edit_text_query))
            .perform(clearText())
            .perform(typeText("Goo"));
        
        onView(withText("Google"))
            .check(matches(isDisplayed()));
        
        onView(withId(R.id.edit_text_query))
            .perform(clearText())
            .perform(typeText("ogl"));
        
        onView(withText("Google"))
            .check(matches(isDisplayed()));
    }
    
    private void performAddJamquery(String title, String url) {
        onView(withId(R.id.fab_add))
            .perform(click());
        
        onView(withId(R.id.edit_text_title))
            .perform(typeText(title));
        
        onView(withId(R.id.edit_text_url))
            .perform(typeText(url));
        
        onView(withId(R.id.button_add))
            .perform(click());
    }
}