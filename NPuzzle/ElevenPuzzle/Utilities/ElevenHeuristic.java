package NPuzzle.ElevenPuzzle.Utilities;

import NPuzzle.ElevenPuzzle.PuzzleStructure.ElevenPuzzle;

public class ElevenHeuristic {

    public static int heuristic1(ElevenPuzzle puzzle) {
        int count = 0;
        String finalS = "b123456789xy";
        for (int i = 0; i < puzzle.getPuzzleStateArray().length; i++) {
            if (puzzle.getPuzzleStateArray()[i] != finalS.charAt(i)) count++;
        }
        return count;
    }

    public static int heuristic2(ElevenPuzzle puzzle) {
        return h2(puzzle.getPuzzleStateArray(), new Character[]{'b','1','2','3','4','5','6','7','8','9','x','y'});
    }


    public static Character[][] toTwoD(Character[] oneState) {
        int k = 0;
        Character[][] characters = new Character[3][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                characters[j][i] = oneState[k];
                k++;
            }
        }
        return characters;
    }


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
