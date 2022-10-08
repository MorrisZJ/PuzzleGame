package NPuzzle.EightPuzzle.Utilities;


import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzle;


/**
 * The Util class stores some specific operation and function that are used
 * to support the main function of the program
 * @author Jiamu Zhang
 */
public class EightHeuristic {

    /**
     * This is a heuristic function that return the numbers of the mismatch tiles.
     * @param puzzle Puzzle as an input stores the specific puzzle states that used to
     *               calculate the value of heuristic function.
     * @return Return h1(x), the heuristic value of current state of the input puzzle.
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
     * This is a heuristic function that return the sum of the distance of tiles from their goal positions.
     * @param puzzle Puzzle as an input stores the specific puzzle states that used to
     *               calculate the value of heuristic function.
     * @return Return h2(x), the heuristic value of current state of the input puzzle.
     */
    public static int heuristic2(EightPuzzle puzzle) {
        return h2(puzzle.getPuzzleStateArray(), new Character[]{'b','1','2','3','4','5','6','7','8'});
    }

    /**
     * Convert the 1-D array representation of the state into 2-D array representation.
     * @param oneState The 1-D array representation of a state.
     * @return Return 2-D array representation.
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
     * The helper method that used to calculate the heuristic value based on h2(x)
     * @param currentState Current state of puzzle.
     * @param goalState Goal state of puzzle.
     * @return Return h2(x), the heuristic value of current state of the input puzzle.
     */
    private static int h2(Character[] currentState, Character[] goalState) {
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

    /**
     * Find the index of the mismatch tile.
     * @param currentState Current state of puzzle.
     * @param goalState Goal state of puzzle.
     * @param currentI The horizontal index of the 2-D array.
     * @param currentJ The vertical index of the 2-D array.
     * @return Return the index of misMatch tail in the goal state.
     */
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
