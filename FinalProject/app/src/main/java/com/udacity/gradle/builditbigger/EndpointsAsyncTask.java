package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.baguzzzaji.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by baguzzzaji on 30/09/2016.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi myApi = null;
    private Context context;

    public interface OnCallback {
        void onFinished(String result);
    }

    OnCallback callback;

    public EndpointsAsyncTask(Context context, OnCallback onCallback) {
        this.context = context;
        callback = onCallback;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.87.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApi = builder.build();
        }

        try {
            return myApi.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        callback.onFinished(s);
    }
}