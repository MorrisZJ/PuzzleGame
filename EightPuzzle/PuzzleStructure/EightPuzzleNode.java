package EightPuzzle.PuzzleStructure;


import EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import EightPuzzle.ModelAbstract.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiamu Zhang
 */
public class EightPuzzleNode extends Node<EightPuzzle, EightPuzzleNode, String> {

    private int dept = 0;

    /**
     *
     * @param currentState
     * @param parentNode
     * @param actFromParentToCurrent
     */
    public EightPuzzleNode(EightPuzzle currentState, EightPuzzleNode parentNode, String actFromParentToCurrent) {
        super(currentState, parentNode, actFromParentToCurrent);
    }

    /**
     *
     * @param currentState
     */
    public EightPuzzleNode(EightPuzzle currentState) {
        super(currentState);
    }

    /**
     *
     * @return
     */
    @Override
    public String[] allActionsOfGame() {
        return new String[]{"up", "down", "left", "right"};
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof EightPuzzleNode
                && getCurrentState().equals(((EightPuzzleNode) o).getCurrentState());
    }

    /**
     *
     * @param actionDone
     * @return
     */
    @Override
    public EightPuzzleNode doMove(String actionDone){
        Character[] currentState = this.getCurrentState().getPuzzleStateArray();
        Character[] afterState = MoveActionEightPuzzle.moves(actionDone,currentState);
        EightPuzzle newPuzzle = new EightPuzzle(afterState);
        return new EightPuzzleNode(newPuzzle, this, actionDone);
    }


    @Override
    public String toString() {
        return getActFromParentToCurrent();
    }

    public List<EightPuzzleNode> findReachableMove() {
        List<EightPuzzleNode> moves = new ArrayList<>();
        for (String action: allActionsOfGame()) {
            EightPuzzleNode newNodeAfterMove = doMove(action);
            if (newNodeAfterMove.getCurrentState().getPuzzleStateArray() != null && !newNodeAfterMove.equals(this)) {
                moves.add(newNodeAfterMove);
            }
        }
        return moves;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }
}
