package EightPuzzle.Utilities;


import EightPuzzle.PuzzleStructure.EightPuzzle;

import java.util.concurrent.Callable;

/**
 * The Util class stores some specific operation and function that are used
 * to support the main function of the program
 */
public class Util {

    /**
     * This is a heuristic function that return the numbers of the mismatch tiles.
     * @param puzzle Puzzle as an input stores the specific puzzle states that used to
     *               calculate the value of heuristic function.
     * @return Return h(x), the heuristic value of current state of the input puzzle
     */
    public static int heuristic1(EightPuzzle puzzle) {
        int count = 0;
        String finalS = "b12345678";
        for (int i = 0; i < puzzle.getPuzzleStateArray().length; i++) {
            if (puzzle.getPuzzleStateArray()[i] != finalS.charAt(i)) count++;
        }
        return count;
    }

    /**
     *
     * @param puzzle
     * @return
     */
    public static int heuristic2(EightPuzzle puzzle) {
        return h2(puzzle.getPuzzleStateArray(), new Character[]{'b','1','2','3','4','5','6','7','8'});
    }

    /**
     *
     * @param oneState
     * @return
     */
    public static Character[][] toTwoD(Character[] oneState) {
        int k = 0;
        int width = (int) Math.sqrt(oneState.length);
        Character[][] characters = new Character[width][width];
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                characters[j][i] = oneState[k];
                k++;
            }
        }
        return characters;
    }

    /**
     *
     * @param currentState
     * @param goalState
     * @return
     */
    public static int h2(Character[] currentState, Character[] goalState) {
        Character[][] cState = toTwoD(currentState);
        Character[][] gState = toTwoD(goalState);
        int totalDistance = 0;
        for (int i = 0; i < cState.length; i++) {
            for (int j = 0; j < cState[i].length; j++) {
                if (cState[i][j] != 'b') {
                    if (cState[i][j] != gState[i][j]) {
                        int[] position = findMisMatch(cState, gState, i, j);
                        totalDistance += Math.abs(position[0] - i) + Math.abs(position[1] - j);
                    }
                }
            }
        }
        return totalDistance;
    }

    private static int[] findMisMatch(Character[][] currentState, Character[][] goalState, int currentI, int currentJ) {
        int[] position = new int[2];
        for (int i = 0; i < goalState.length; i++) {
            for (int j = 0; j < goalState[i].length; j++) {
                if (goalState[i][j] == currentState[currentI][currentJ]) {
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }
        return position;
    }

}
