package EightPuzzle.PuzzleStructure;
import EightPuzzle.ModelAbstract.NPuzzle;

import java.util.Arrays;

/**
 *
 */
public class EightPuzzle extends NPuzzle<Character> {



    /**
     *
     * @param puzzleStateArray
     */
    public EightPuzzle(Character[] puzzleStateArray) {
        super(puzzleStateArray, 8);
    }

    /**
     *
     * @param nPuzzle
     * @return
     */
    @Override
    public boolean equals(NPuzzle<Character> nPuzzle) {
        //Check the size
        if (nPuzzle.getPuzzleStateArray() == null) return false;
        else if (nPuzzle.getPuzzleStateArray().length != this.getPuzzleStateArray().length) return false;
        //Check the element
        else {
            for (int i = 0; i < nPuzzle.getPuzzleSize() + 1; i++) {
                if (nPuzzle.getPuzzleStateArray()[i] != this.getPuzzleStateArray()[i]) return false;
            }
        }
        //The same size and element inside, checked!
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *
     * @param matrix
     * @return
     */
    public void toString(String matrix) {
        Character[][] characters = new Character[3][3];
        int k = 0;
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                characters[i][j] = getPuzzleStateArray()[k];
                k++;
            }
        }
        for (Character[] character : characters) {
            System.out.println(Arrays.toString(character));
        }
        System.out.println("  ");
    }

}
