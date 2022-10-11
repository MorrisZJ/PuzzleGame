package NPuzzle.ElevenPuzzle;

import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;
import NPuzzle.ElevenPuzzle.PuzzleStructure.AFrontierEle;
import NPuzzle.ElevenPuzzle.PuzzleStructure.BeamFrontierEle;
import NPuzzle.ElevenPuzzle.PuzzleStructure.ElevenPuzzle;
import NPuzzle.ElevenPuzzle.PuzzleStructure.ElevenPuzzleNode;
import NPuzzle.ElevenPuzzle.Utilities.RandomGenEle;
import NPuzzle.ModSearch.PuzzleSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jiamu Zhang
 */
public class GameElevenPuzzle {


    /**
     * The current state of the puzzle.
     */
    private Character[] currentState;

    /**
     * The goal state of the puzzle.
     * Use x and y represent 10 and 11
     */
    private final Character[] goalState = new Character[]{'b','1','2','3','4','5','6','7','8','9','x','y'};

    /**
     * The maximum number of nodes to consider during the local beam search.
     */
    private int maxNodes;


    /**
     * The cost of the most recent run of search.
     */
    private int costCurrentRun = 0;

    /**
     * The branching factor of the most recent run of search.
     */
    private double bfCurrentRun = 0;


    /**
     * The setState method set the puzzle state based on input string.
     * @param state The state to be set.
     */
    public void setState(String state) {
        if (getCurrentState() == null) setCurrentState(new Character[getGoalState().length]);
        for (int i = 0, j = 0; i < getGoalState().length && j < state.length(); i++, j++) {
            if (state.charAt(j) != ' ') {
                getCurrentState()[i] = state.charAt(j);
            } else {
                i--;
            }
        }
    }


    /**
     * Print the current state of the puzzle.
     * Based on input format, the state can be either printed in line format or matrix format.
     * @param format The format of the printed state.
     */
    public void printState(String format) {
        if (format.equals("string")) {
            System.out.println(Arrays.toString(getCurrentState()));
        } else if (format.equals("matrix")) {
            toString(format);
        }
    }

    /**
     * The move method move the adjacent tile to the blank tile based on direction.
     * @param direction The direction of the move.
     */
    public void move(String direction) {
        Character[] state = MoveActionEightPuzzle.moves(direction, getCurrentState());
        if (state != null) setCurrentState(state);
        else System.out.println("Invalid Moves" + "\n");

    }

    /**
     * Make n random moves from the goal state.
     * @param n The number of random moves.
     */
    public void randomizeState(int n) {
        setCurrentState(RandomGenEle.randomizeStateAction(getGoalState(), n, 123));
    }

    /**
     * Solve ghe puzzle from its current state using A-star search using input heuristic function.
     * The heuristic can either be h1 or h2.
     * @return Return a list of moves from current state to goal state.
     */
    public List<String> A_star(String heuristic) throws Exception {
        ElevenPuzzleNode stateNode = new ElevenPuzzleNode(new ElevenPuzzle(getCurrentState()));
        ElevenPuzzleNode goalNode = new ElevenPuzzleNode(new ElevenPuzzle(getGoalState()));
        PuzzleSearch<ElevenPuzzle, ElevenPuzzleNode, String, AFrontierEle> search = new PuzzleSearch<>();
        List<ElevenPuzzleNode> listOfNodes = search.aStarSearch(stateNode, goalNode, new AFrontierEle(), heuristic, getMaxNodes());
        if (listOfNodes != null) {
            LinkedList<String> listMove = new LinkedList<>();
            for (ElevenPuzzleNode listOfNode : listOfNodes) {
                if (listOfNode.getActFromParentToCurrent() != null) {
                    listMove.addFirst(listOfNode.getActFromParentToCurrent());
                }
            }
            setCostCurrentRun(search.costCurrentRun);
            setBfCurrentRun(search.bfCurrentRun);
            return listMove;
        } else return null;
    }

    /**
     * Solve the puzzle from its current state using Local Beam search with k preserved states at each iteration.
     * The usage of evaluation is h2, the sum of the distance of tiles from their goal positions.
     * @param k The number of preserved states at each iteration.
     * @return Return a list of moves from current state to goal state.
     */
    public List<String> beam(int k) throws Exception {
        ElevenPuzzleNode stateNode= new ElevenPuzzleNode(new ElevenPuzzle(getCurrentState()));
        ElevenPuzzleNode goalNode= new ElevenPuzzleNode(new ElevenPuzzle(getGoalState()));
        PuzzleSearch<ElevenPuzzle, ElevenPuzzleNode, String, BeamFrontierEle> search = new PuzzleSearch<>();
        List<ElevenPuzzleNode> listOfNodes = search.beamSearch(stateNode, goalNode, new BeamFrontierEle(), "h2", k, getMaxNodes());
        if (listOfNodes != null) {
            LinkedList<String> listMove = new LinkedList<>();
            for (ElevenPuzzleNode listOfNode : listOfNodes) {
                if (listOfNode.getActFromParentToCurrent() != null) {
                    listMove.addFirst(listOfNode.getActFromParentToCurrent());
                }
            }
            setCostCurrentRun(search.costCurrentRun);
            setBfCurrentRun(search.bfCurrentRun);
            return listMove;
        } else return null;
    }

    /**
     * The maxNodes method specifies the maximum number of nodes to be considered during search.
     * @param n The maximum number of nodes to be considered during search.
     */
    public void maxNodes(int n) {
        this.maxNodes = n;
    }


    /**
     * Print the state in matrix format.
     * @param matrix The string argument that invoke this overload toString method.
     */
    private void toString(String matrix) {
        Character[][] characters = new Character[4][3];
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

    /**
     * Getter method that return the current state.
     * @return Return the current state.
     */
    public Character[] getCurrentState() {
        return currentState;
    }

    /**
     * Setter method that set the current state.
     * @param currentState The current state of eight puzzle.
     */
    public void setCurrentState(Character[] currentState) {
        this.currentState = currentState;
    }

    /**
     * Getter method that return the goal state.
     * @return Return the goal state.
     */
    public Character[] getGoalState() {
        return goalState;
    }

    /**
     * Getter method that return the number of maximum nodes to be considered during a search.
     * @return The maximum nodes to be considered during a search.
     */
    public int getMaxNodes() {
        return this.maxNodes;
    }

    /**
     * Getter method for cost.
     * @return Cost
     */
    public int getCostCurrentRun() {
        return costCurrentRun;
    }

    /**
     * Set cost for current run.
     * @param costCurrentRun Cost of search.
     */
    public void setCostCurrentRun(int costCurrentRun) {
        this.costCurrentRun = costCurrentRun;
    }

    /**
     * Getter method for Branching factor.
     * @return Branching factor
     */
    public double getBfCurrentRun() {
        return bfCurrentRun;
    }

    /**
     * Set Branching factor for current run.
     * @param bfCurrentRun Branching factor.
     */
    public void setBfCurrentRun(double bfCurrentRun) {
        this.bfCurrentRun = bfCurrentRun;
    }
}
