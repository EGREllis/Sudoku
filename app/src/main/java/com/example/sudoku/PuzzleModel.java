package com.example.sudoku;

import android.os.Parcel;
import android.os.Parcelable;

public class PuzzleModel implements Parcelable {
    public static final int SUDOKU_SIZE = 9;
    private int data[][];
    private boolean original[][];

    public boolean isOriginal(int x, int y) { return original[y][x]; }

    public int getCell(int x, int y) {
        return data[y][x];
    }

    public void setCell(int x, int y, int value) { data[y][x] = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[0].length; x++) {
                dest.writeInt(data[y][x]);
            }
        }
        for (int y = 0; y < original.length; y++) {
            dest.writeBooleanArray(original[y]);
        }
    }

    public static Parcelable.Creator<PuzzleModel> CREATOR = new Parcelable.Creator<PuzzleModel>() {
        public PuzzleModel createFromParcel(Parcel in) {
            int data[][] = new int[SUDOKU_SIZE][];
            boolean original[][] = new boolean[SUDOKU_SIZE][];
            for (int y = 0; y < data.length; y++) {
                data[y] = new int[SUDOKU_SIZE];
                original[y] = new boolean[SUDOKU_SIZE];
            }
            for (int y = 0; y < data.length; y++) {
                for (int x = 0; x < data.length; x++) {
                    int cell = in.readInt();
                    data[y][x] = cell;
                }
            }
            for (int y = 0; y < original.length; y++) {
                original[y] = new boolean[SUDOKU_SIZE];
                in.readBooleanArray(original[y]);
            }
            return new PuzzleModel(data, original);
        }

        public PuzzleModel[] newArray(int size) {
            return new PuzzleModel[size];
        }
    };

    public PuzzleModel(int data[][], boolean original[][]) {
        this.data = data;
        this.original = original;
    }

    public static PuzzleModel createPuzzleModelFromOriginalData(int data[][]) {
        boolean[][] original = new boolean[SUDOKU_SIZE][];
        for (int y = 0; y < SUDOKU_SIZE; y++) {
            original[y] = new boolean[SUDOKU_SIZE];
        }
        for (int y = 0; y < SUDOKU_SIZE; y++) {
            for (int x = 0; x < SUDOKU_SIZE; x++) {
                original[y][x] = data[y][x] != 0;
            }
        }
        return new PuzzleModel(data, original);
    }
}