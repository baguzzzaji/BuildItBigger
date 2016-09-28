package com.example.showthejoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowTheJoke extends AppCompatActivity {

    public static String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_the_joke);

        String joke = getIntent().getStringExtra(JOKE_KEY);
        TextView jokeTextView = (TextView) findViewById(R.id.joke_content);

        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }
    }
}
