package NPuzzle.ModelAbstract;

/**
 * The PriorityFrontier is an abstract class defining the frontier used in searching process.
 * @param <StateType> The StateType is the specific representation of the puzzle current state of searching problems.
 * @param <NodeType> The NodeType is the type of parent node of Node, usually the same as current node.
 * @param <ActionType> The representation of action from parent node to current node in NodeType class.
 * @author Jiamu Zhang
 */
public abstract class PriorityFrontier<StateType, NodeType
        extends Node<StateType, NodeType, ActionType>, ActionType> {


    /**
     * The method push the input node into the priority frontier with specific heuristic function type.
     * @param node The node that is to be pushed into the frontier.
     * @param heuristic The type of heuristic function.
     */
    public abstract void push(NodeType node, String heuristic);

    /**
     * The method that pop the first element out.
     * @return Return the first element of the priority frontier.
     */
    public abstract NodeType popPeek();

    /**
     * The method check if the frontier is empty.
     * @return Return true if the frontier is empty, else return false.
     */
    public abstract boolean isEmpty();

    /**
     * Return the current size of the frontier.
     * @return current size.
     */
    public abstract int size();

    /**
     * Check if the frontier contain input element.
     * @param node The node that is to be checked whether it is in the frontier.
     * @return Return true if in the frontier, else return false.
     */
    public abstract boolean contain(NodeType node);
}
