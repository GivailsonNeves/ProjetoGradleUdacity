package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LoadJokeUnitTest {

    final CompletableFuture<String> future = new CompletableFuture<>();

    @Test
    public void testAsyncLoad() throws ExecutionException, InterruptedException {



        final EndpointsAsyncTask endPoint = new EndpointsAsyncTask();

        endPoint.execute(
                new EndpointsAsyncTask.OnEndPointCallBack() {
                    @Override
                    public void onEndPointBack(String response) {

                        future.complete(response);

                    }
                });

      assertNotNull(future.get());

    }
}
