package com.example.sudoku;

public class PuzzleModelFactory {
    public PuzzleModel createPuzzle() {
        /**
         * This is a cheat - need more than one puzzle!
         */
        int data[][] = new int[][] {
                {8, 7, 6, 9, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 0, 3, 0, 5, 8, 0, 0},
                {4, 0, 0, 0, 0, 0, 2, 1, 0},
                {0, 9, 0, 5, 0, 0, 0, 0, 0},
                {0, 5, 0, 0, 4, 0, 3, 0, 6},
                {0, 2, 9, 0, 0, 0, 0, 0, 8},
                {0, 0, 4, 6, 9, 0, 1, 7, 3},
                {0, 0, 0, 0, 0, 1, 0, 0, 4}
        };
        return PuzzleModel.createPuzzleModelFromOriginalData(data);
    }
}
