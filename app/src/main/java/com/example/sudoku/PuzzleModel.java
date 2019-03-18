package com.example.sudoku;

import android.os.Parcel;
import android.os.Parcelable;

public class PuzzleModel implements Parcelable {
    public static final int SUDOKU_SIZE = 9;
    private int data[][];

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
    }

    public static Parcelable.Creator<PuzzleModel> CREATOR = new Parcelable.Creator<PuzzleModel>() {
        public PuzzleModel createFromParcel(Parcel in) {
            int data[][] = new int[SUDOKU_SIZE][];
            for (int y = 0; y < data.length; y++) {
                data[y] = new int[SUDOKU_SIZE];
            }
            for (int y = 0; y < data.length; y++) {
                for (int x = 0; x < data.length; x++) {
                    data[y][x] = in.readInt();
                }
            }
            return new PuzzleModel(data);
        }

        public PuzzleModel[] newArray(int size) {
            return new PuzzleModel[size];
        }
    };

    public PuzzleModel(int data[][]) {
        this.data = data;
    }
}