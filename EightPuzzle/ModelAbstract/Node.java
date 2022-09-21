package EightPuzzle.ModelAbstract;

import java.util.ArrayList;
import java.util.List;

public abstract class Node<StateType, NodeType, ActionType> {
    private final StateType currentState;
    private final NodeType parentNode;
    private final ActionType actFromParentToCurrent;

    protected Node(StateType currentState, NodeType parentNode, ActionType actFromParentToCurrent) {
        this.currentState = currentState;
        this.parentNode = parentNode;
        this.actFromParentToCurrent = actFromParentToCurrent;
    }

    protected Node(StateType currentState) {
        this(currentState, null, null);
    }

    public List<NodeType> findReachableMove() throws InterruptedException {
        List<NodeType> moves = new ArrayList<>();
        for (ActionType action: allActionsOfGame()) {
            NodeType newNodeAfterMove = doMove(action);
            if (newNodeAfterMove == null && !newNodeAfterMove.equals(this)) {
                moves.add(newNodeAfterMove);
            }
        }
        return moves;
    }

    public StateType getCurrentState() {
        return currentState;
    }

    public NodeType getParentNode() {
        return parentNode;
    }

    public ActionType getActFromParentToCurrent() {
        return actFromParentToCurrent;
    }

    public String toString() {
        return currentState.toString();
    }

    public abstract ActionType[] allActionsOfGame();

    public abstract boolean equals(Object o);

    public abstract NodeType doMove(ActionType actionDone) throws InterruptedException;

}
