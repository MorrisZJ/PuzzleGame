package NPuzzle.EightPuzzle.PuzzleStructure;

import NPuzzle.ModelAbstract.PriorityFrontier;
import NPuzzle.EightPuzzle.Utilities.EightHeuristic;

import java.util.ArrayList;
import java.util.List;

public class AFrontierEight extends PriorityFrontier<EightPuzzle, EightPuzzleNode, String> {

    private final List<EightPuzzleNode> list;

    public AFrontierEight() {
        this.list = new ArrayList<>();
    }

    /**
     *
     * @param node
     */
    @Override
    public void push(EightPuzzleNode node, String heuristic) {
        int nodeHeuristic = node.getHeuristic(heuristic) + node.getDept();
        if (list.contains(node)) {
            int index = 0;
            for (EightPuzzleNode n : list) {
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
                break;
            }
        }
        list.add(node);
    }

    @Override
    public EightPuzzleNode popPeek() {
        EightPuzzleNode state = list.get(0);
        list.remove(0);
        return state;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }


}
