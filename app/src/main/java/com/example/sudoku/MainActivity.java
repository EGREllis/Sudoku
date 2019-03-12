package com.example.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.sudoku.MESSAGE";
    public static final String LOG_MESSAGE = "SEND_MESSAGE:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Log.d(LOG_MESSAGE,"Created intent");
        EditText editText = (EditText) findViewById(R.id.editText);
        Log.d(LOG_MESSAGE, "Found editText");
        final String message = editText.getText().toString();
        if (message != null && message.length() > 0) {
            Log.d(LOG_MESSAGE, "Retrieved non-null, non-empty text from editText");
        } else {
            Log.d(LOG_MESSAGE, "Retrieved null or empty text from editText");
        }
        intent.putExtra(EXTRA_MESSAGE, message);
        Log.d(LOG_MESSAGE, "Added text to intent ("+message+")");
        startActivity(intent);
    }
}
