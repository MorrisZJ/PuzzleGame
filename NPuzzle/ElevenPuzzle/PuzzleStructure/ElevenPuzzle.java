package NPuzzle.ElevenPuzzle.PuzzleStructure;

import NPuzzle.ModelAbstract.NPuzzle;

import java.util.Arrays;

public class ElevenPuzzle extends NPuzzle<Character> {


    /**
     * The constructor of the NPuzzle.
     *
     * @param puzzleStateArray The array representation of the state.
     */
    public ElevenPuzzle(Character[] puzzleStateArray) {
        super(puzzleStateArray, 11);
    }

    /**
     *
     * @param nPuzzle The puzzle state.
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
     * An override toString method that use super class's toString method.
     * @return Return string representation of the ElevenPuzzle.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * An overload toString method that print the state as the 3 x 4 grid format.
     * @param matrix Any string argument that used to call this method.
     */
    public void toString(String matrix) {
        Character[][] characters = new Character[4][3];
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
