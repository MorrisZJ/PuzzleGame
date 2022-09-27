package NPuzzle.EightPuzzle.PuzzleStructure;

import NPuzzle.ModelAbstract.PriorityFrontier;
import NPuzzle.EightPuzzle.Utilities.EightHeuristic;

import java.util.ArrayList;
import java.util.List;


public class BeamFrontierEight extends PriorityFrontier<EightPuzzle, EightPuzzleNode, String> {

    private final List<EightPuzzleNode> list;

    private final int k;


    public BeamFrontierEight(int k) {
        this.list = new ArrayList<>();
        this.k = k;
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
        if (list.size() > k) {
            list.remove(list.size() - 1);
        }
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
