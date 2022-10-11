package NPuzzle.ElevenPuzzle.PuzzleStructure;

import NPuzzle.ModelAbstract.PriorityFrontier;

import java.util.ArrayList;
import java.util.List;

/**
 * The AFrontierEle is a priority frontier specialized for A-Star search in Eleven-Puzzle.
 * This class is the subclass of PriorityFrontier.
 * Every node in the frontier is sorted according to the priority function h(n) + g(n).
 * @author Jiamu Zhang
 */
public class AFrontierEle extends PriorityFrontier<ElevenPuzzle, ElevenPuzzleNode, String> {

    /**
     * The list that stores the node of state.
     */
    private final List<ElevenPuzzleNode> list;

    /**
     * The constructor of the class.
     */
    public AFrontierEle() {
        this.list = new ArrayList<>();
    }


    /**
     * The push method add the node with the type of heuristic function into the list.
     * The node will be added to the position according to input type of heuristic function
     * and the state and depth inside node.
     * @param node The node that is to be pushed into the frontier.
     * @param heuristic The type of heuristic function, either h1 or h2.
     */
    @Override
    public void push(ElevenPuzzleNode node, String heuristic) {
        int nodeHeuristic = node.getHeuristic(heuristic) + node.getDept();
        if (list.contains(node)) {
            int index = 0;
            for (ElevenPuzzleNode n : list) {
                if (n.equals(node)) break;
                index++;
            }
            int oldNodeH = list.get(index).getHeuristic(heuristic) + list.get(index).getDept();
            if (oldNodeH > nodeHeuristic) {
                list.remove(index);
            } else return;
        }
        for(int i = 0; i < list.size(); i++) {
            int currentH = list.get(i).getHeuristic(heuristic) + list.get(i).getDept();
            if(currentH > nodeHeuristic) {
                list.add(i, node);
                return;
            }
        }
        list.add(node);
    }

    /**
     * The method that pop the first element out.
     * @return Return the first element of the priority frontier.
     */
    @Override
    public ElevenPuzzleNode popPeek() {
        ElevenPuzzleNode state = list.get(0);
        list.remove(0);
        return state;
    }

    /**
     * The method check if the frontier is empty.
     * @return Return true if the frontier is empty, else return false.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * Return the current size of the frontier.
     * @return current size.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Check if the frontier contain input element.
     * @param node The node that is to be checked whether it is in the frontier.
     * @return Return true if in the frontier, else return false.
     */
    @Override
    public boolean contain(ElevenPuzzleNode node) {
        return list.contains(node);
    }
}
