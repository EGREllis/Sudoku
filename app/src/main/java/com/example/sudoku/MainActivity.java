package com.example.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_MESSAGE = "SUDOKU";
    public static final String PUZZLE_KEY = "com.example.sudoku.PUZZLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newPuzzle(View view) {
        Intent intent = new Intent(this, PuzzleActivity.class);

        PuzzleModel puzzle = new PuzzleModelFactory().createPuzzle();
        intent.putExtra(PUZZLE_KEY, puzzle);
        startActivity(intent);
    }
}
