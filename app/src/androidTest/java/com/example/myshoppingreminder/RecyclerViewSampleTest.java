package com.example.myshoppingreminder;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



public class RecyclerViewSampleTest {

    private static final int ITEM_BELOW_THE_FOLD = 1;


    @Rule
    public ActivityScenarioRule<MainProductActivity> activityScenarioRule =
            new ActivityScenarioRule<MainProductActivity>(MainProductActivity.class);

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        onView(ViewMatchers.withId(R.id.my_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));

        String itemElementText = getApplicationContext().getResources().getString(
                R.string.priority) + String.valueOf(ITEM_BELOW_THE_FOLD);
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

}
