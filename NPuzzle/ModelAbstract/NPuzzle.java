package NPuzzle.ModelAbstract;

import java.util.Arrays;

/**
 * Class stores the array representation of the states of N-Puzzle.
 * @param <representationType> Type of array.
 * @author Jiamu Zhang
 */
public abstract class NPuzzle<representationType> {

    /**
     * The size of the puzzle.
     */
    private final int puzzleSize;

    /**
     * The array representation of the state.
     */
    private final representationType[] puzzleStateArray;

    /**
     * The constructor of the NPuzzle.
     * @param puzzleStateArray The array representation of the state.
     * @param puzzleSize The size of the puzzle.
     */
    protected NPuzzle(representationType[] puzzleStateArray, int puzzleSize) {
        this.puzzleStateArray = puzzleStateArray;
        this.puzzleSize = puzzleSize;
    }

    /**
     * A getter method that return the state of puzzle.
     * @return Return the array representation of state.
     */
    public representationType[] getPuzzleStateArray() {
        return puzzleStateArray;
    }

    /**
     * A getter method that return the size of the puzzle.
     * @return Return the size of the puzzle.
     */
    public int getPuzzleSize() {
        return puzzleSize;
    }

    /**
     * An equal method tha used to compare to another state whether two states are the same.
     * @param nPuzzle The puzzle state.
     * @return Return true if equal, else return false.
     */
    public abstract boolean equals(NPuzzle<representationType> nPuzzle);

    /**
     * An override toString method that used to print the state representation to the terminal.
     * @return Return string representation of puzzle.
     */
    @Override
    public String toString() {
        return "NPuzzle{" +
                "puzzleStateArray=" + Arrays.toString(puzzleStateArray) +
                '}';
    }
}
