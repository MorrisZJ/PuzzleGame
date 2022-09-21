package EightPuzzle.ModelAbstract;

/**
 *
 * @param <StateType>
 * @param <NodeType>
 * @param <ActionType>
 */
public abstract class PriorityFrontier<StateType, NodeType extends Node<StateType, NodeType, ActionType>, ActionType> {

    public abstract void push(NodeType node);

    public abstract NodeType popPeek();

    public abstract boolean isEmpty();

    public abstract int size();


}
