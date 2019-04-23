package com.example.sudoku.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.sudoku.Util.debug;
import static com.example.sudoku.Util.requiredButExcessiveExceptionHandling;

public class ClasspathFilePuzzleFactory implements PuzzleFactory {
    // There ought to be a nicer way to do this...
    private static final String CLASSPATH_FILENAME = "sudoku_library.dat";
    private static Pattern HEADER_PATTERN = Pattern.compile("Puzzles:(\\d+)");
    private static Pattern PUZZLE_PATTERN = Pattern.compile("(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)");

    protected BufferedReader getReader() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CLASSPATH_FILENAME);
        if (inputStream == null) {
            debug(String.format("Could not load sudoku classloader file %1$s", CLASSPATH_FILENAME));
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 10);
        return reader;
    }

    @Override
    public Puzzle createPuzzle() {
        Puzzle result = null;
        BufferedReader reader = getReader();
        StringBuilder sudoku = new StringBuilder();
        int nLines = 0;
        int digit = 0;
        try {
            String header = reader.readLine();
            Matcher headerMatcher = HEADER_PATTERN.matcher(header);
            if (headerMatcher.matches()) {
                nLines = Integer.valueOf(headerMatcher.group(1));
                int randomPuzzle = (int)(Math.random()*1.0*nLines);
                debug(String.format("Loading puzzle %1$d of %2$d", randomPuzzle,  nLines));
                for (int currentPuzzle = 0; currentPuzzle < randomPuzzle; currentPuzzle++) {
                    reader.readLine();  //Skip the lines we don't care about
                }
                String puzzleText = reader.readLine();
                if (puzzleText.length() != Puzzle.SUDOKU_SIZE * Puzzle.SUDOKU_SIZE) {
                    debug(String.format("Puzzle on line %1$d is unexpected length %2%d", nLines, puzzleText.length()));
                    return null;
                }
                Matcher puzzleMatcher = PUZZLE_PATTERN.matcher(puzzleText);
                if (puzzleMatcher.matches()) {
                    int[][] data = new int[Puzzle.SUDOKU_SIZE][];
                    for (int i = 0; i < Puzzle.SUDOKU_SIZE; i++) {
                        data[i] = new int[Puzzle.SUDOKU_SIZE];
                    }

                    for (digit = 0; digit < Puzzle.SUDOKU_SIZE * Puzzle.SUDOKU_SIZE; digit++) {
                        int y = digit / Puzzle.SUDOKU_SIZE;
                        int x = digit - (y * Puzzle.SUDOKU_SIZE);
                        int value = Integer.valueOf(puzzleMatcher.group(digit+1)); // Group 0 is the whole match - hence the +1
                        sudoku.append(value).append(x == 8 ? "\n" : "");
                        data[y][x] = value;
                    }
                    result = Puzzle.createPuzzleModelFromOriginalData(data);
                    debug(String.format("Loaded puzzle %1$d of %2$d", randomPuzzle, nLines));
                } else {
                    debug(String.format("Failed to parse puzzle data (puzzle %1$d): %2$s", nLines, puzzleText));
                }
            } else {
                debug(String.format("Failed to parse header from classpath file: %1$s", header));
            }
        } catch (Exception e) {
            debug(String.format("Problem parsing classpath (puzzle %1$d, digit %2$d) file...\n", nLines, digit) +sudoku.toString());
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                requiredButExcessiveExceptionHandling(e);
            }
        }
        return result;
    }
}
