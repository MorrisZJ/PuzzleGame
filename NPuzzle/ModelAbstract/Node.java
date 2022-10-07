package NPuzzle.ModelAbstract;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <StateType>
 * @param <NodeType>
 * @param <ActionType>
 */
public abstract class Node<StateType, NodeType, ActionType> {
    private final StateType currentState;
    private final NodeType parentNode;
    private final ActionType actFromParentToCurrent;


    /**
     *
     * @param currentState
     * @param parentNode
     * @param actFromParentToCurrent
     */
    protected Node(StateType currentState, NodeType parentNode, ActionType actFromParentToCurrent) {
        this.currentState = currentState;
        this.parentNode = parentNode;
        this.actFromParentToCurrent = actFromParentToCurrent;
    }

    /**
     *
     * @param currentState
     */
    protected Node(StateType currentState) {
        this(currentState, null, null);
    }

    /**
     *
     * @return
     * @throws InterruptedException
     */
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

    /**
     *
     * @return
     */
    public StateType getCurrentState() {
        return currentState;
    }

    /**
     *
     * @return
     */
    public NodeType getParentNode() {
        return parentNode;
    }

    /**
     *
     * @return
     */
    public ActionType getActFromParentToCurrent() {
        return actFromParentToCurrent;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return currentState.toString();
    }

    /**
     *
     * @return
     */
    public abstract ActionType[] allActionsOfGame();

    /**
     *
     * @param o
     * @return
     */
    public abstract boolean equals(Object o);

    /**
     *
     * @param actionDone
     * @return
     * @throws InterruptedException
     */
    public abstract NodeType doMove(ActionType actionDone) throws InterruptedException;

    public abstract int getHeuristic(String heuristic);

    public abstract void setDept(int dept);

    public abstract int getDept();




}
