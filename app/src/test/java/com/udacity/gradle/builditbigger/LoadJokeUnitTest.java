package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;

@RunWith(RobolectricTestRunner.class)
public class LoadJokeUnitTest {

    @Before
    public void setUp() {
        Robolectric.getBackgroundThreadScheduler().pause();
        Robolectric.getForegroundThreadScheduler().pause();
    }

    @Test
    public void testAsyncLoad() {


        //Robolectric.flushBackgroundThreadScheduler();
        new EndpointsAsyncTask()
                .execute(new EndpointsAsyncTask.OnEndPointCallBack() {
            @Override
            public void onEndPointBack(String response) {
                assertNotNull(response);
            }
        });
        Robolectric.flushBackgroundThreadScheduler();

        //ShadowLooper.runUiThreadTasks();
    }
}


