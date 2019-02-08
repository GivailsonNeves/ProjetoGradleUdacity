package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoadJokeTest {

    @Rule
    public ActivityTestRule<MainTestActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainTestActivity.class);

    @Test
    public void clickLoadJoke() {

        MainTestActivity activity = mainActivityActivityTestRule.getActivity();
        activity.setLoadCallBack(new MainTestActivity.LoadTestCallBack() {
            @Override
            public void onLoadCallBack(String response) {

                assertTrue(response!= null && !response.isEmpty());

            }
        });

        onView(withId(R.id.btnTellJoke)).perform(click());

    }
}
