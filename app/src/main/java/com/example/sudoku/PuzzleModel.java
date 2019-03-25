package com.example.sudoku;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PuzzleModel implements Parcelable {
    public static final int SUDOKU_SIZE = 9;        // Dont mess with this
    public static final int SUDOKU_SQUARES = 3;     // Dont mess with this
    private int data[][];
    private boolean original[][];
    private List<SetValidator> validators;

    public boolean isOriginal(int x, int y) { return original[y][x]; }

    public int getCell(int x, int y) {
        return data[y][x];
    }

    public Set<Point> setCell(int x, int y, int value) {
        int previous = data[y][x];
        data[y][x] = value;
        Set<Point> errors = validate(new Point(x, y));
        if (errors.size() > 0) {
            data[y][x] = previous;
        }
        return errors;
    }

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
        this.validators = new ArrayList<>(SUDOKU_SIZE*3);

        // Rows
        for (int y = 0; y < SUDOKU_SIZE; y++) {
            Set<Point> row = new HashSet<>();
            for (int x = 0; x < SUDOKU_SIZE; x++) {
                row.add(new Point(x, y));
            }
            validators.add(new SetValidator(row));
        }

        // Columns
        for (int x = 0; x < SUDOKU_SIZE; x++) {
            Set<Point> col = new HashSet<>();
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                col.add(new Point(x, y));
            }
            validators.add(new SetValidator(col));
        }

        // Squares
        for (int ty = 0; ty < SUDOKU_SQUARES; ty++) {
            for (int tx = 0; tx < SUDOKU_SQUARES; tx++) {
                Set<Point> squares = new HashSet<>();
                for (int iy = 0; iy < SUDOKU_SQUARES; iy++) {
                    for (int ix = 0; ix < SUDOKU_SQUARES; ix++) {
                        squares.add(new Point(tx*SUDOKU_SQUARES+ix, ty*SUDOKU_SQUARES+iy));
                    }
                }
                validators.add(new SetValidator(squares));
            }
        }
    }

    public Set<Point> validate(Point modified) {
        Set<Point> errors = new HashSet<>();
        for (SetValidator validator : validators) {
            Point conflictPoint = validator.validate(modified);
            if (conflictPoint != null) {
                errors.add(conflictPoint);
            }
        }
        return errors;
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

    private class SetValidator {
        private final Set<Point> points;

        private SetValidator(Set<Point> points) {
            this.points = points;
        }

        private Point validate(Point modified) {
            Map<Integer, Point> seen = new HashMap<>();
            Point conflictPoint = null;
            for (Point point : points) {
                int value = data[point.y][point.x];
                if (value > 0) {
                    if (seen.containsKey(value)) {
                        if (point.equals(modified)) {
                            conflictPoint = seen.get(value);
                        } else {
                            conflictPoint = point;
                        }
                    } else {
                        seen.put(value, point);
                    }
                }
            }
            return conflictPoint;
        }
    }
}