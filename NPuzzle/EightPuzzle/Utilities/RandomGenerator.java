package NPuzzle.EightPuzzle.Utilities;

import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;


import java.util.Random;

/**
 *
 * @author Jiamu Zhang
 */
public class RandomGenerator {

    public static Character[] randomizeStateAction(Character[] currentState, int nStep) {
        Character[] stateTemp = new Character[currentState.length];
        System.arraycopy(currentState, 0, stateTemp, 0, stateTemp.length);
        int count = 0;
        while (count < nStep) {
            String direction = randomMoveGenerator();
            assert stateTemp != null;
            if (MoveActionEightPuzzle.checkMove(direction, MoveActionEightPuzzle.findBlank(stateTemp))) {
                stateTemp = MoveActionEightPuzzle.moves(direction, stateTemp);
                count++;
            }
        }

        return stateTemp;
    }

    public static String randomMoveGenerator() {
        Random random = new Random();
        int seed = random.nextInt(4) + 1;
        if (seed == 1) return "up";
        else if (seed == 2) return "down";
        else if (seed == 3) return "left";
        else return "right";

    }
}