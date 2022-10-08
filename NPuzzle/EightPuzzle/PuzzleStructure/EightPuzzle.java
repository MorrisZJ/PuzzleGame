package NPuzzle.EightPuzzle.PuzzleStructure;
import NPuzzle.ModelAbstract.NPuzzle;

import java.util.Arrays;

/**
 * The EightPuzzle represents the state type that is eight-puzzle.
 * This class stores the array representation of the state.
 * @author Jiamu Zhang
 */
public class EightPuzzle extends NPuzzle<Character> {



    /**
     * This is the constructor of the class.
     * @param puzzleStateArray The array representation of the state.
     */
    public EightPuzzle(Character[] puzzleStateArray) {
        super(puzzleStateArray, 8);
    }

    /**
     * This is an override equals method that use the array representation of state as the comparing element.
     * @param nPuzzle Another comparing puzzle.
     * @return Return true if are same type of puzzle and state are equal, else return false.
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
     * @return Return string representation of the EightPuzzle.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * An overload toString method that print the state as the 3 x 3 grid format.
     * @param matrix Any string argument that used to call this method.
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
