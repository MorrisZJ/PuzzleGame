package NPuzzle.ElevenPuzzle.PuzzleStructure;

import NPuzzle.ElevenPuzzle.PuzzleAction.MoveActionElevenPuzzle;
import NPuzzle.ElevenPuzzle.Utilities.ElevenHeuristic;
import NPuzzle.ModelAbstract.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The ElevenPuzzleNode class is used during the searching process.
 * The class stores the depth, the current state, the parent node, and the action from parent node to current node.
 * @author Jiamu Zhang
 */
public class ElevenPuzzleNode extends Node<ElevenPuzzle, ElevenPuzzleNode, String> {

    /**
     * The dept of the current node, representing the total cost from initial state to current state.
     */
    private int dept = 0;

    /**
     * The constructor of this class.
     * @param currentState The node's current state of searching problems.
     * @param parentNode The node's parent node.
     * @param actFromParentToCurrent The action from parent node to current node.
     */
    public ElevenPuzzleNode(ElevenPuzzle currentState, ElevenPuzzleNode parentNode, String actFromParentToCurrent) {
        super(currentState, parentNode, actFromParentToCurrent);
    }

    /**
     * The constructor of node, with only current state as argument.
     * @param currentState The node's current state of searching problems.
     */
    public ElevenPuzzleNode(ElevenPuzzle currentState) {
        super(currentState);
    }

    /**
     * The method will return a list of successors or current node
     * @return Return a list of successors of current node.
     */
    @Override
    public List<ElevenPuzzleNode> findReachableMove() {
        List<ElevenPuzzleNode> moves = new ArrayList<>();
        for (String action: allActionsOfGame()) {
            ElevenPuzzleNode newNodeAfterMove = doMove(action);
            if (newNodeAfterMove.getCurrentState().getPuzzleStateArray() != null && !newNodeAfterMove.equals(this)) {
                moves.add(newNodeAfterMove);
            }
        }
        return moves;
    }

    /**
     * Obtain the action list of Eleven-Puzzle.
     * @return Return all actions of such game, without considering the validity of such move of current state.
     */
    @Override
    public String[] allActionsOfGame() {
        return new String[]{"up", "down", "left", "right"};
    }

    /**
     * The equal method of class.
     * @param o The comparing object.
     * @return Return true if equal, false if not.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ElevenPuzzleNode
                && getCurrentState().equals(((ElevenPuzzleNode) o).getCurrentState());
    }

    /**
     * The method that return a new node after a move from current state.
     * @param actionDone The direction of move.
     * @return Return a new node after a move from current state.
     */
    @Override
    public ElevenPuzzleNode doMove(String actionDone) {
        Character[] currentState = this.getCurrentState().getPuzzleStateArray();
        Character[] afterState = MoveActionElevenPuzzle.moves(actionDone,currentState);
        ElevenPuzzle newPuzzle = new ElevenPuzzle(afterState);
        return new ElevenPuzzleNode(newPuzzle, this, actionDone);
    }

    /**
     * Return the heuristic value based on input heuristic function type.
     * @return Return the heuristic value.
     */
    @Override
    public int getHeuristic(String heuristic) {
        if (heuristic.equals("h1")) {
            return ElevenHeuristic.heuristic1(getCurrentState());
        } else if (heuristic.equals("h2")) {
            return ElevenHeuristic.heuristic2(getCurrentState());
        } else return ElevenHeuristic.heuristic2(getCurrentState());
    }

    /**
     * Setter method that set the depth of the node.
     * @param dept The depth of the node
     */
    @Override
    public void setDept(int dept) {
        this.dept = dept;
    }

    /**
     * Return the depth of the current node.
     * @return The depth of the current node.
     */
    @Override
    public int getDept() {
        return this.dept;
    }

    /**
     * An override toString method that return the action from parent state to goal state.
     * @return String representation of the class.
     */
    @Override
    public String toString() {
        return getActFromParentToCurrent();
    }
}
