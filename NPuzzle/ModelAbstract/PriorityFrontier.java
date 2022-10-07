package NPuzzle.ModelAbstract;

/**
 *
 * @param <StateType>
 * @param <NodeType>
 * @param <ActionType>
 */
public abstract class PriorityFrontier<StateType, NodeType extends Node<StateType, NodeType, ActionType>, ActionType> {


    /**
     *
     * @param node
     */
    public abstract void push(NodeType node, String heuristic);

    /**
     *
     * @return
     */
    public abstract NodeType popPeek();

    /**
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     *
     * @return
     */
    public abstract int size();

    public abstract boolean contain(NodeType node);
}
