package EightPuzzle.PuzzleStructure;
import EightPuzzle.ModelAbstract.PriorityFrontier;
import EightPuzzle.Utilities.Util;

/**
 *
 */
public class AStarFrontier extends PriorityFrontier<EightPuzzle, EightPuzzleNode, String> {

    private int size = 0;

    private AStarFrontierNode head;

    /**
     *
     */
    public static class AStarFrontierNode{

        private EightPuzzleNode node;

        private int priorityValue;

        private AStarFrontierNode next;

        public AStarFrontierNode(EightPuzzleNode node) {
            this.node = node;
            autoSetPriority();
        }

        /**
         *
         */
        public void autoSetPriority() {
            int p = Util.heuristic(getNode().getCurrentState()) + getNode().getDept();
            setPriorityValue(p);
        }

        public EightPuzzleNode getNode() {
            return node;
        }

        public void setNode(EightPuzzleNode node) {
            this.node = node;
        }

        public int getPriorityValue() {
            return priorityValue;
        }

        public void setPriorityValue(int priorityValue) {
            this.priorityValue = priorityValue;
        }

        public AStarFrontierNode getNext() {
            return next;
        }

        public void setNext(AStarFrontierNode next) {
            this.next = next;
        }
    }

    /**
     *
     * @param puzzleNode
     */
    public void push(EightPuzzleNode puzzleNode) {

        AStarFrontierNode aNode = new AStarFrontierNode(puzzleNode);

        setSize(size() + 1);

        if (getHead() == null) {
            setHead(aNode);
        } else {
            //If node should be set as a head with the lowest h(x) value
            if (getHead().getPriorityValue() > aNode.getPriorityValue()) {
                aNode.setNext(getHead());
                setHead(aNode);
            } else {
                AStarFrontierNode ptrPrev = getHead();
                AStarFrontierNode ptrAftr = getHead().getNext();
                while (ptrAftr != null && ptrAftr.getNext() != null) {
                    if (ptrAftr.getNext().getPriorityValue() > aNode.getPriorityValue()) {
                        ptrPrev.setNext(aNode);
                        aNode.setNext(ptrAftr);
                        return;
                    } else {
                        ptrPrev = ptrPrev.getNext();
                        ptrAftr = ptrAftr.getNext();
                    }
                }
                ptrPrev.setNext(aNode);
                aNode.setNext(ptrAftr);
            }
        }

    }

    /**
     *
     * @return
     */
    public EightPuzzleNode popPeek() {
        if (head == null) return null;
        EightPuzzleNode node = getHead().getNode();
        setHead(getHead().getNext());
        return node;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public AStarFrontierNode getHead() {
        return head;
    }

    public void setHead(AStarFrontierNode head) {
        this.head = head;
    }


}
