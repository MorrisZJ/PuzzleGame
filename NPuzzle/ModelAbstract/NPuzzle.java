package NPuzzle.ModelAbstract;

import java.util.Arrays;

/**
 * Class stores the array representation of the states.
 * @param <representationType> Type of array.
 */
public abstract class NPuzzle<representationType> {

    /**
     *
     */
    private final int puzzleSize;

    /**
     *
     */
    private final representationType[] puzzleStateArray;

    /**
     *
     * @param puzzleStateArray
     * @param puzzleSize
     */
    protected NPuzzle(representationType[] puzzleStateArray, int puzzleSize) {
        this.puzzleStateArray = puzzleStateArray;
        this.puzzleSize = puzzleSize;
    }

    /**
     *
     * @return
     */
    public representationType[] getPuzzleStateArray() {
        return puzzleStateArray;
    }

    /**
     *
     * @return
     */
    public int getPuzzleSize() {
        return puzzleSize;
    }

    /**
     *
     * @param nPuzzle
     * @return
     */
    public abstract boolean equals(NPuzzle<representationType> nPuzzle);

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "NPuzzle{" +
                "puzzleStateArray=" + Arrays.toString(puzzleStateArray) +
                '}';
    }
}
