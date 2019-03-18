package com.example.sudoku;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PuzzleActivity extends AppCompatActivity {
    private static final Map<Point,Integer> pointToId;
    static {
        Map<Point,Integer> pointToIds = new HashMap<Point,Integer>();
        pointToIds.put(new Point(0, 0), R.id.textView00);
        pointToIds.put(new Point(1, 0), R.id.textView01);
        pointToIds.put(new Point(2, 0), R.id.textView02);
        pointToIds.put(new Point(3, 0), R.id.textView03);
        pointToIds.put(new Point(4, 0), R.id.textView04);
        pointToIds.put(new Point(5, 0), R.id.textView05);
        pointToIds.put(new Point(6, 0), R.id.textView06);
        pointToIds.put(new Point(7, 0), R.id.textView07);
        pointToIds.put(new Point(8, 0), R.id.textView08);

        pointToIds.put(new Point(0, 1), R.id.textView10);
        pointToIds.put(new Point(1, 1), R.id.textView11);
        pointToIds.put(new Point(2, 1), R.id.textView12);
        pointToIds.put(new Point(3, 1), R.id.textView13);
        pointToIds.put(new Point(4, 1), R.id.textView14);
        pointToIds.put(new Point(5, 1), R.id.textView15);
        pointToIds.put(new Point(6, 1), R.id.textView16);
        pointToIds.put(new Point(7, 1), R.id.textView17);
        pointToIds.put(new Point(8, 1), R.id.textView18);

        pointToIds.put(new Point(0, 2), R.id.textView20);
        pointToIds.put(new Point(1, 2), R.id.textView21);
        pointToIds.put(new Point(2, 2), R.id.textView22);
        pointToIds.put(new Point(3, 2), R.id.textView23);
        pointToIds.put(new Point(4, 2), R.id.textView24);
        pointToIds.put(new Point(5, 2), R.id.textView25);
        pointToIds.put(new Point(6, 2), R.id.textView26);
        pointToIds.put(new Point(7, 2), R.id.textView27);
        pointToIds.put(new Point(8, 2), R.id.textView28);

        pointToIds.put(new Point(0, 3), R.id.textView30);
        pointToIds.put(new Point(1, 3), R.id.textView31);
        pointToIds.put(new Point(2, 3), R.id.textView32);
        pointToIds.put(new Point(3, 3), R.id.textView33);
        pointToIds.put(new Point(4, 3), R.id.textView34);
        pointToIds.put(new Point(5, 3), R.id.textView35);
        pointToIds.put(new Point(6, 3), R.id.textView36);
        pointToIds.put(new Point(7, 3), R.id.textView37);
        pointToIds.put(new Point(8, 3), R.id.textView38);

        pointToIds.put(new Point(0, 4), R.id.textView40);
        pointToIds.put(new Point(1, 4), R.id.textView41);
        pointToIds.put(new Point(2, 4), R.id.textView42);
        pointToIds.put(new Point(3, 4), R.id.textView43);
        pointToIds.put(new Point(4, 4), R.id.textView44);
        pointToIds.put(new Point(5, 4), R.id.textView45);
        pointToIds.put(new Point(6, 4), R.id.textView46);
        pointToIds.put(new Point(7, 4), R.id.textView47);
        pointToIds.put(new Point(8, 4), R.id.textView48);

        pointToIds.put(new Point(0, 5), R.id.textView50);
        pointToIds.put(new Point(1, 5), R.id.textView51);
        pointToIds.put(new Point(2, 5), R.id.textView52);
        pointToIds.put(new Point(3, 5), R.id.textView53);
        pointToIds.put(new Point(4, 5), R.id.textView54);
        pointToIds.put(new Point(5, 5), R.id.textView55);
        pointToIds.put(new Point(6, 5), R.id.textView56);
        pointToIds.put(new Point(7, 5), R.id.textView57);
        pointToIds.put(new Point(8, 5), R.id.textView58);

        pointToIds.put(new Point(0, 6), R.id.textView60);
        pointToIds.put(new Point(1, 6), R.id.textView61);
        pointToIds.put(new Point(2, 6), R.id.textView62);
        pointToIds.put(new Point(3, 6), R.id.textView63);
        pointToIds.put(new Point(4, 6), R.id.textView64);
        pointToIds.put(new Point(5, 6), R.id.textView65);
        pointToIds.put(new Point(6, 6), R.id.textView66);
        pointToIds.put(new Point(7, 6), R.id.textView67);
        pointToIds.put(new Point(8, 6), R.id.textView68);

        pointToIds.put(new Point(0, 7), R.id.textView70);
        pointToIds.put(new Point(1, 7), R.id.textView71);
        pointToIds.put(new Point(2, 7), R.id.textView72);
        pointToIds.put(new Point(3, 7), R.id.textView73);
        pointToIds.put(new Point(4, 7), R.id.textView74);
        pointToIds.put(new Point(5, 7), R.id.textView75);
        pointToIds.put(new Point(6, 7), R.id.textView76);
        pointToIds.put(new Point(7, 7), R.id.textView77);
        pointToIds.put(new Point(8, 7), R.id.textView78);

        pointToIds.put(new Point(0, 8), R.id.textView80);
        pointToIds.put(new Point(1, 8), R.id.textView81);
        pointToIds.put(new Point(2, 8), R.id.textView82);
        pointToIds.put(new Point(3, 8), R.id.textView83);
        pointToIds.put(new Point(4, 8), R.id.textView84);
        pointToIds.put(new Point(5, 8), R.id.textView85);
        pointToIds.put(new Point(6, 8), R.id.textView86);
        pointToIds.put(new Point(7, 8), R.id.textView87);
        pointToIds.put(new Point(8, 8), R.id.textView88);

        pointToId = Collections.unmodifiableMap(pointToIds);
    }
    private PuzzleModel puzzleModel = null;
    private int currentX = -1;
    private int currentY = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        Intent intent = getIntent();
        puzzleModel = intent.getParcelableExtra(MainActivity.PUZZLE_KEY);

        refreshText();
    }

    private void refreshText() {
        for (int y = 0; y < PuzzleModel.SUDOKU_SIZE; y++) {
            for (int x = 0; x < PuzzleModel.SUDOKU_SIZE; x++) {
                findAndSetText(x, y, puzzleModel);
            }
        }
    }

    private void findAndSetText(int x, int y, PuzzleModel puzzleModel) {
        int id = pointToId.get(new Point(x, y));
        TextView textView = (TextView)findViewById(id);
        if (textView != null) {
            int value = puzzleModel.getCell(x, y);
                String text;
                if (value == 0) {
                    text = " ";
                } else {
                    text = Integer.toString(value);
                }

                textView.setText(text);
                if (puzzleModel.isOriginal(x, y)) {
                    textView.setOnClickListener(null);
                }
        } else {
            Log.d(MainActivity.LOG_MESSAGE, "Could not find View for ("+x+", "+y+")");
        }
    }

    public void selectCell(View view) {
        //TODO: find the colors from res/values/colors
        final int highlight = 0xFF00FF00;
        final int other = 0xFFFFFFFF;

        setContentView(R.layout.activity_puzzle);
        String tag = (String)view.getTag();
        currentY = Integer.valueOf(new String(new char[] {tag.charAt(0)}));
        currentX = Integer.valueOf(new String(new char[] {tag.charAt(1)}));

        for (int ty = 0; ty < PuzzleModel.SUDOKU_SIZE; ty++) {
            for (int tx = 0; tx < PuzzleModel.SUDOKU_SIZE; tx++) {
                int tid = pointToId.get(new Point(tx, ty));
                TextView text = (TextView)findViewById(tid);
                if (tx == currentX && ty == currentY) {
                    Log.d(MainActivity.LOG_MESSAGE, "Updated ("+tx+", "+ty+") highlighted");
                    text.setBackgroundColor(highlight);
                } else {
                    Log.d(MainActivity.LOG_MESSAGE, "Updated ("+tx+", "+ty+") bland");
                    text.setBackgroundColor(other);
                }
                text.invalidate();
            }
        }

        refreshText();

        TextView display = (TextView)findViewById(R.id.textView);
        display.setText(tag);

        ViewGroup group = (ViewGroup)findViewById(R.id.puzzleLayout);
        group.invalidate();
    }

    public void updateValue(View view) {
        setContentView(R.layout.activity_puzzle);

        int newValue = Integer.valueOf(new StringBuilder(((TextView)view).getText()).toString());
        puzzleModel.setCell(currentX, currentY, newValue);

        refreshText();
    }
}
