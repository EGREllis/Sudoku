package com.example.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Log.d(MainActivity.LOG_MESSAGE, "Fetching Intent");
        Intent intent = getIntent();
        Log.d(MainActivity.LOG_MESSAGE, "Fetching message from Intent");
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message != null && message.length() > 0) {
            Log.d(MainActivity.LOG_MESSAGE, "Fetched non-null non-empty message from Intent ("+message+")");
        } else {
            Log.d(MainActivity.LOG_MESSAGE, "Fetched null or empty message from Intent");
        }
        Log.d(MainActivity.LOG_MESSAGE, "Fetching TextView");
        TextView view = (TextView)findViewById(R.id.textView1);
        if (view != null) {
            Log.d(MainActivity.LOG_MESSAGE, "Fetched non-null TextView");
            view.setText(message);
        } else {
            Log.d(MainActivity.LOG_MESSAGE, "Fetched a null TextView!");
        }
    }
}
