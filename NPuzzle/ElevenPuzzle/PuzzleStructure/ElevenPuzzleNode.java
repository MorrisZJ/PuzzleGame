package NPuzzle.ElevenPuzzle.PuzzleStructure;

import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;
import NPuzzle.EightPuzzle.Utilities.EightHeuristic;
import NPuzzle.ElevenPuzzle.PuzzleAction.MoveActionElevenPuzzle;
import NPuzzle.ElevenPuzzle.Utilities.ElevenHeuristic;
import NPuzzle.ModelAbstract.Node;

import java.util.ArrayList;
import java.util.List;

public class ElevenPuzzleNode extends Node<ElevenPuzzle, ElevenPuzzleNode, String> {

    /**
     * The dept of the current node, representing the total cost from initial state to current state.
     */
    private int dept = 0;

    public ElevenPuzzleNode(ElevenPuzzle currentState, ElevenPuzzleNode parentNode, String actFromParentToCurrent) {
        super(currentState, parentNode, actFromParentToCurrent);
    }

    public ElevenPuzzleNode(ElevenPuzzle currentState) {
        super(currentState);
    }

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

    @Override
    public String[] allActionsOfGame() {
        return new String[]{"up", "down", "left", "right"};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ElevenPuzzleNode
                && getCurrentState().equals(((ElevenPuzzleNode) o).getCurrentState());
    }

    @Override
    public ElevenPuzzleNode doMove(String actionDone) {
        Character[] currentState = this.getCurrentState().getPuzzleStateArray();
        Character[] afterState = MoveActionElevenPuzzle.moves(actionDone,currentState);
        ElevenPuzzle newPuzzle = new ElevenPuzzle(afterState);
        return new ElevenPuzzleNode(newPuzzle, this, actionDone);
    }

    @Override
    public int getHeuristic(String heuristic) {
        if (heuristic.equals("h1")) {
            return ElevenHeuristic.heuristic1(getCurrentState());
        } else if (heuristic.equals("h2")) {
            return ElevenHeuristic.heuristic2(getCurrentState());
        } else return ElevenHeuristic.heuristic2(getCurrentState());
    }

    @Override
    public void setDept(int dept) {
        this.dept = dept;
    }

    @Override
    public int getDept() {
        return this.dept;
    }

    @Override
    public String toString() {
        return getActFromParentToCurrent();
    }
}
