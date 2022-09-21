package EightPuzzle.PuzzleAction;

public class MoveActionEightPuzzle {

    /**
     * Moves function
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
     * Check whether the direction of move is valid
     * @param direction
     * @param blankIndex
     * @return
     */
    public static boolean checkMove(String direction,int blankIndex) {
        switch (direction) {
            case "up": return blankIndex != 6 && blankIndex != 7 && blankIndex != 8;
            case "down": return blankIndex != 0 && blankIndex != 1 && blankIndex != 2;
            case "left": return blankIndex != 2 && blankIndex != 5 && blankIndex != 8;
            case "right": return blankIndex != 0 && blankIndex != 3 && blankIndex != 6;
            default: return false;
        }
    }

    /**
     * Find the index of blank tile
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
