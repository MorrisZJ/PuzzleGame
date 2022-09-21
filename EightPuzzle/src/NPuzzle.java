package EightPuzzle.src;

import java.util.Arrays;

/**
 * Class stores the array representation of the states.
 * @param <representationType> Type of array.
 */
public abstract class NPuzzle<representationType> {

    private final int puzzleSize;
    private final representationType[] puzzleStateArray;

    protected NPuzzle(representationType[] puzzleStateArray, int puzzleSize) {
        this.puzzleStateArray = puzzleStateArray;
        this.puzzleSize = puzzleSize;
    }

    public representationType[] getPuzzleStateArray() {
        return puzzleStateArray;
    }

    public int getPuzzleSize() {
        return puzzleSize;
    }

    public abstract boolean equals(NPuzzle<representationType> nPuzzle);

    @Override
    public String toString() {
        return "NPuzzle{" +
                "puzzleStateArray=" + Arrays.toString(puzzleStateArray) +
                '}';
    }
}
