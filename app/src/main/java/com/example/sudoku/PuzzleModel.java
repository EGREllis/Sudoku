package com.example.sudoku;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<ValidationError> setCell(int x, int y, int value) {
        int previous = data[y][x];
        data[y][x] = value;
        List<ValidationError> errors = validate();
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
            validators.add(new SetValidator(row, y, ValidationErrorType.ROW));
        }

        // Columns
        for (int x = 0; x < SUDOKU_SIZE; x++) {
            Set<Point> col = new HashSet<>();
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                col.add(new Point(x, y));
            }
            validators.add(new SetValidator(col, x, ValidationErrorType.COLUMN));
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
                validators.add(new SetValidator(squares,ty*SUDOKU_SQUARES+tx, ValidationErrorType.SQUARE));
            }
        }
    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        for (SetValidator validator : validators) {
            if (!validator.validate()) {
                errors.add(new ValidationError(validator.type, validator.index));
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

    public static enum ValidationErrorType {
        ROW,
        COLUMN,
        SQUARE
    }

    public static class ValidationError {
        public final ValidationErrorType type;
        public final int index;

        public ValidationError(ValidationErrorType type, int index) {
            this.type = type;
            this.index = index;
        }

        public ValidationErrorType getType() {
            return type;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return String.format("%1$s %2$d", type, index);
        }
    }

    private class SetValidator {
        private final Set<Point> points;
        private final int index;
        private final ValidationErrorType type;

        private SetValidator(Set<Point> points, int index, ValidationErrorType type) {
            this.points = points;
            this.index = index;
            this.type = type;
        }

        private boolean validate() {
            Set<Integer> seen = new HashSet<Integer>();
            boolean isValid = true;
            for (Point point : points) {
                int value = data[point.y][point.x];
                if (value > 0) {
                    if (seen.contains(value)) {
                        isValid = false;
                        break;
                    } else {
                        seen.add(value);
                    }
                }
            }
            return isValid;
        }
    }
}