package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by baguzzzaji on 30/09/2016.
 */

public class JokesTest extends AndroidTestCase {
    public void testAsyncTask() {
        final CountDownLatch signal = new CountDownLatch(1);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(getContext(), new EndpointsAsyncTask.OnCallback() {
            @Override
            public void onFinished(String result) {
                assertNotNull(result);
                signal.countDown();
            }
        });
        asyncTask.execute();

        try {
            signal.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
