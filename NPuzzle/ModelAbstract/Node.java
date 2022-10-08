package NPuzzle.ModelAbstract;

import java.util.ArrayList;
import java.util.List;

/**
 * The Node is an abstract class that defines the basic element of the puzzle's node.
 *
 * @author Jiamu Zhang
 *
 * @param <StateType> The StateType is the specific representation of the puzzle current state of searching problems.
 * @param <Node> The NodeType is the type of parent node of Node.
 * @param <ActionType> The representation of action from parent node to current node in NodeType class.
 */
public abstract class Node<StateType, Node, ActionType> {
    private final StateType currentState;
    private final Node parentNode;
    private final ActionType actFromParentToCurrent;

    /**
     * The constructor of node.
     * @param currentState The node's current state of searching problems.
     * @param parentNode The node's parent node.
     * @param actFromParentToCurrent The action from parent node to current node.
     */
    protected Node(StateType currentState, Node parentNode, ActionType actFromParentToCurrent) {
        this.currentState = currentState;
        this.parentNode = parentNode;
        this.actFromParentToCurrent = actFromParentToCurrent;
    }

    /**
     * The constructor of node, with only current state as argument.
     * @param currentState The node's current state of searching problems.
     */
    protected Node(StateType currentState) {
        this(currentState, null, null);
    }


    /**
     * The method will return a list of successors or current node
     * @return Return a list of successors of current node.
     */
    public abstract List<Node> findReachableMove();

    /**
     * The getter method returning the current state of node.
     * @return Return the current state of node.
     */
    public StateType getCurrentState() {
        return currentState;
    }

    /**
     * The getter method returning the parent node.
     * @return Return the parent node of current node.
     */
    public Node getParentNode() {
        return parentNode;
    }

    /**
     * The method will return the action from parent node to current node.
     * @return Return the action from parent node to current node.
     */
    public ActionType getActFromParentToCurrent() {
        return actFromParentToCurrent;
    }

    /**
     * An override toString method that uses the state's toString method.
     * @return String representation of the class.
     */
    public String toString() {
        return currentState.toString();
    }

    /**
     * Obtain the action list of game.
     * Different game or puzzle will have different list of action type.
     * @return Return all actions of such game, without considering the validity of such move of current state.
     */
    public abstract ActionType[] allActionsOfGame();

    /**
     * The equal method of class.
     * @param o The comparing object.
     * @return Return true if equal, false if not.
     */
    public abstract boolean equals(Object o);

    /**
     * The method that return a new node after a move from current state.
     * @param actionDone The direction/type of move.
     * @return Return a new node after a move from current state.
     * @throws InterruptedException Throw exception if such move is invalid.
     */
    public abstract Node doMove(ActionType actionDone) throws InterruptedException;

    /**
     * The method will calculate the heuristic value of current state.
     * @param heuristic The type of heuristic function, either h1 or h2.
     * @return Return the heuristic value of current state,
     */
    public abstract int getHeuristic(String heuristic);

    /**
     * The setter method that set the depth of node in a search tree.
     * @param dept The depth of node.
     */
    public abstract void setDept(int dept);

    /**
     * The getter method that return the current depth of current node.
     * @return Return the depth of node.
     */
    public abstract int getDept();




}
