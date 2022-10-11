package NPuzzle.ElevenPuzzle.PuzzleAction;

/**
 * The MoveActionElevenPuzzle class stores the function of moves actions of Eleven-Puzzle.
 * @author Jiamu Zhang
 */
public class MoveActionElevenPuzzle {

    /**
     * Moves function that do moves action based on input direction and array representation of puzzle.
     * @param direction The direction of move.
     * @param beforeMove The array representation of puzzle before the move is done.
     * @return Return an array representation of puzzle after the move is done.
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
     * Check whether the direction of move is valid.
     * @param direction The direction of move.
     * @param blankIndex The index of blank tile.
     * @return Return true if the move is valid, else return false.
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
     * Find the index of blank tile.
     * @param beforeMove The array representation of puzzle before the move is done.
     * @return Return the index of blank tile.
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
     * Swap two tiles.
     * @param characters The array representation of puzzle.
     * @param i The first tile's index.
     * @param j The second tile's index.
     */
    private static void swap(Character[] characters, int i, int j) {
        Character temp = characters[i];
        characters[i] = characters[j];
        characters[j] = temp;
    }

}
