package vsp.shop.com.bakeit;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vsp.shop.com.bakeit.ui.MainActivity;
import vsp.shop.com.bakeit.util.Constant;
import vsp.shop.com.bakeit.util.ExpressoIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void init(){
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }

    @Test
    public void test_to_load_detail_activity() {
        onView(withId(R.id.recipe_recycler_view)).check(matches(isDisplayed()));

        // Type text and then press the button.
        onView(withId(R.id.recipe_recycler_view))
                .perform(actionOnItemAtPosition(0, click()));

        // Check that the text was changed.
        onView(withText(Constant.INGREDIENTS)).check(matches(isDisplayed()));
    }

    @Test
    public void test_to_check_orientation_change() {


        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.recipe_recycler_view)).check(matches(isDisplayed()));

        // Type text and then press the button.
        onView(withId(R.id.recipe_recycler_view))
                .perform(actionOnItemAtPosition(0, click()));

        // Check that the text was changed.
        onView(withId(R.id.two_pane)).check(matches(isDisplayed()));


    }
}
