package NPuzzle.ElevenPuzzle.PuzzleAction;

/**
 *
 * @author Jiamu Zhang
 */
public class MoveActionElevenPuzzle {

    /**
     *
     * @param direction
     * @param beforeMove
     * @return
     */
    public static Character[] moves(String direction, Character[] beforeMove) {
        Character[] afterMove = new Character[beforeMove.length];
        System.arraycopy(beforeMove, 0, afterMove, 0, afterMove.length);
        int blankIndex = findBlank(beforeMove);
        //Call different move function based on different input direction
        switch (direction) {
            case "up":
                if (checkMove(direction, blankIndex)) {
                    int targetIndex = blankIndex + 3;
                    swap(afterMove, blankIndex, targetIndex);
                    return afterMove;
                }
                break;
            case "down":
                if (checkMove(direction, blankIndex)) {
                    int targetIndex = blankIndex - 3;
                    swap(afterMove, blankIndex, targetIndex);
                    return afterMove;
                }
                break;
            case "left":
                if (checkMove(direction, blankIndex)) {
                    int targetIndex = blankIndex + 1;
                    swap(afterMove, blankIndex, targetIndex);
                    return afterMove;
                }
                break;
            case "right":
                if (checkMove(direction, blankIndex)) {
                    int targetIndex = blankIndex - 1;
                    swap(afterMove, blankIndex, targetIndex);
                    return afterMove;
                }
                break;
            default:
                throw new RuntimeException("Invalid direction exception.");
        }

        return null;
    }

    /**
     *
     * @param direction
     * @param blankIndex
     * @return
     */
    public static boolean checkMove(String direction,int blankIndex) {
        return switch (direction) {
            case "up" -> blankIndex != 9 && blankIndex != 10 && blankIndex != 11;
            case "down" -> blankIndex != 0 && blankIndex != 1 && blankIndex != 2;
            case "left" -> blankIndex != 2 && blankIndex != 5 && blankIndex != 8 && blankIndex != 11;
            case "right" -> blankIndex != 0 && blankIndex != 3 && blankIndex != 6 && blankIndex != 9;
            default -> false;
        };
    }

    /**
     *
     * @param beforeMove
     * @return
     */
    public static int findBlank(Character[] beforeMove) {
        int index = 0;
        while (index < beforeMove.length) {
            if (beforeMove[index] == 'b' ) break;
            index++;
        }
        return index;
    }

    /**
     *
     * @param characters
     * @param i
     * @param j
     */
    private static void swap(Character[] characters, int i, int j) {
        Character temp = characters[i];
        characters[i] = characters[j];
        characters[j] = temp;
    }

}
