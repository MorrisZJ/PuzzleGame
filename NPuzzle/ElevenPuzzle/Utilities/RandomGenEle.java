package NPuzzle.ElevenPuzzle.Utilities;

import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import NPuzzle.ElevenPuzzle.PuzzleAction.MoveActionElevenPuzzle;

import java.util.Random;

public class RandomGenEle {

    public static Character[] randomizeStateAction(Character[] currentState, int nStep, int seedBase) {
        Character[] stateTemp = new Character[currentState.length];
        System.arraycopy(currentState, 0, stateTemp, 0, stateTemp.length);
        Random random = new Random();
        random.setSeed(seedBase);
        int count = 0;
        while (count < nStep) {
            int seed = random.nextInt(4) + 1;
            String direction = randomMoveGenerator(seed);
            assert stateTemp != null;
            if (MoveActionElevenPuzzle.checkMove(direction, MoveActionElevenPuzzle.findBlank(stateTemp))) {
                stateTemp = MoveActionElevenPuzzle.moves(direction, stateTemp);
                count++;
            }
        }
        return stateTemp;
    }

    public static String randomMoveGenerator(int seed) {
        if (seed == 1) return "up";
        else if (seed == 2) return "down";
        else if (seed == 3) return "left";
        else return "right";

    }
}
