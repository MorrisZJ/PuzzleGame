package EightPuzzle;

import EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import EightPuzzle.PuzzleAction.Search;
import EightPuzzle.PuzzleStructure.EightPuzzle;
import EightPuzzle.PuzzleStructure.EightPuzzleNode;

import java.util.*;

/**
 *
 */
public class GameEightPuzzle {

    public static void main(String[] args) {

    }

    /**
     *
     */
    private Character[] currentState;

    /**
     *
     */
    private final Character[] goalState = new Character[]{'b','1','2','3','4','5','6','7','8'};

    /**
     *
     * @param state
     */
    public void setState(String state) {
        if (getCurrentState() == null) setCurrentState(new Character[9]);
        for (int i = 0, j = 0; i < goalState.length && j < state.length(); i++, j++) {
            if (state.charAt(j) != ' ') {
                getCurrentState()[i] = state.charAt(j);
            } else {
                i--;
            }
        }
    }




    /**
     *
     * @param format
     */
    public void printState(String format) {
        if (format.equals("string")) {
            System.out.println(Arrays.toString(getCurrentState()));
        } else if (format.equals("matrix")) {
            toString(format);
        }
    }

    /**
     *
     * @param direction
     */
    public void move(String direction) {
        setCurrentState(MoveActionEightPuzzle.moves(direction, getCurrentState()));
    }

    /**
     *
     */
    public void randomizeState() {

    }

    /**
     *
     * @return
     */
    public List<String> A_star() {
        EightPuzzleNode stateNode= new EightPuzzleNode(new EightPuzzle(getCurrentState()));
        EightPuzzleNode goalNode= new EightPuzzleNode(new EightPuzzle(getGoalState()));
        List<EightPuzzleNode> listOfNodes = Search.aStarSearch(stateNode, goalNode);
        LinkedList<String> listMove = new LinkedList<>();
        for (EightPuzzleNode listOfNode : listOfNodes) {
            if (listOfNode.getActFromParentToCurrent() != null) {
                listMove.addFirst(listOfNode.getActFromParentToCurrent());
            }
        }
        return listMove;
    }


    public void maxNodes(int n) {

    }


    /**
     *
     * @param matrix
     */
    private void toString(String matrix) {
        Character[][] characters = new Character[3][3];
        int k = 0;
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                characters[i][j] = getCurrentState()[k];
                k++;
            }
        }
        for (Character[] character : characters) {
            System.out.println(Arrays.toString(character));
        }
        System.out.println("  ");
    }

    public Character[] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Character[] currentState) {
        this.currentState = currentState;
    }

    public GameEightPuzzle() {}

    public Character[] getGoalState() {
        return goalState;
    }
}
