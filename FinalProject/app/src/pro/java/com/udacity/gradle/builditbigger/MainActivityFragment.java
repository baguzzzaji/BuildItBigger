package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showthejoke.ShowTheJoke;

public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.OnCallback {

    public MainActivityFragment() {

    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(getActivity(), this).execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onFinished(String result) {
        Intent intent = new Intent(getActivity(), ShowTheJoke.class);
        intent.putExtra(ShowTheJoke.JOKE_KEY, result);

        startActivity(intent);
    }
}
