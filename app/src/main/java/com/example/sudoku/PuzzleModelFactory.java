package com.example.sudoku;

public class PuzzleModelFactory {
    public PuzzleModel createPuzzle() {
        /**
         * This is a cheat - need more than one puzzle!
         *
         * Upgrades:
         * 1) Randomly get a puzzle from a set of hardcoded puzzles
         * 2) Randomly get a puzzle from a file
         * 3) Randomly generate a solveable sudoku (not sure how feasible this is on an old phone
         *                                          or even a new one, but be able to honestly market
         *                                          "every available sudoku puzzle" has an appeal)
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
