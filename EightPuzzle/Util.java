package EightPuzzle;

public class Util {

    public static int heuristic(EightPuzzle puzzle) {
        int count = 0;
        String finalS = "b12345678";
        for (int i = 0; i < puzzle.getPuzzleStateArray().length; i++) {
            if (puzzle.getPuzzleStateArray()[i] != finalS.charAt(i)) count++;
        }
        return count;
    }

}
