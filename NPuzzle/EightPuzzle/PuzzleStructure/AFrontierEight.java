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


    @Override
    public void push(EightPuzzleNode node) {
        int nodeHeuristic = EightHeuristic.heuristic2(node.getCurrentState()) + node.getDept();
        for(int i = 0; i < list.size(); i++) {
            int currentH = EightHeuristic.heuristic2(list.get(i).getCurrentState()) + list.get(i).getDept();
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
