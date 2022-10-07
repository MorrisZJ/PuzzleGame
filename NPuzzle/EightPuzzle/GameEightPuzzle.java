package NPuzzle.EightPuzzle;

import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import NPuzzle.EightPuzzle.PuzzleStructure.AFrontierEight;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzle;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;
import NPuzzle.EightPuzzle.Utilities.RandomGenerator;
import NPuzzle.ModSearch.PuzzleSearch;

import java.util.*;

/**
 *
 */
public class GameEightPuzzle {


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
     * @param n
     */
    public void randomizeState(int n) {
        setCurrentState(RandomGenerator.randomizeStateAction(getCurrentState(), n));
    }

    /**
     *
     * @return
     */
    public List<String> A_star(String heuristic) throws Exception {
        EightPuzzleNode stateNode= new EightPuzzleNode(new EightPuzzle(getCurrentState()));
        EightPuzzleNode goalNode= new EightPuzzleNode(new EightPuzzle(getGoalState()));
        PuzzleSearch<EightPuzzle, EightPuzzleNode, String, AFrontierEight> search = new PuzzleSearch<>();
        List<EightPuzzleNode> listOfNodes = search.aStarSearch(stateNode, goalNode, new AFrontierEight(), heuristic);
        LinkedList<String> listMove = new LinkedList<>();
        for (EightPuzzleNode listOfNode : listOfNodes) {
            if (listOfNode.getActFromParentToCurrent() != null) {
                listMove.addFirst(listOfNode.getActFromParentToCurrent());
            }
        }
        return listMove;
    }

    /**
     * No need heuristic input
     * define own eval fun
     * @param k
     * @return
     */
    public List<String> beam(int k, String heuristic) {
        EightPuzzleNode stateNode= new EightPuzzleNode(new EightPuzzle(getCurrentState()));
        EightPuzzleNode goalNode= new EightPuzzleNode(new EightPuzzle(getGoalState()));

        List<EightPuzzleNode> listOfNodes = SearchEightPuzzle.beamSearch(stateNode, goalNode, k, heuristic);
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
