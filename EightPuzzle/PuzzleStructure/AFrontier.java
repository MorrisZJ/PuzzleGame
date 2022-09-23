package EightPuzzle.PuzzleStructure;

import EightPuzzle.ModelAbstract.PriorityFrontier;
import EightPuzzle.Utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class AFrontier extends PriorityFrontier<EightPuzzle, EightPuzzleNode, String> {


    private final List<EightPuzzleNode> list;


    public AFrontier() {
        this.list = new ArrayList<EightPuzzleNode>();
    }


    @Override
    public void push(EightPuzzleNode node) {
        int nodeHeuristic = Util.heuristic2(node.getCurrentState()) + node.getDept();
        for(int i = 0; i < list.size(); i++) {
            int currentH = Util.heuristic2(list.get(i).getCurrentState()) + list.get(i).getDept();
            if(currentH > nodeHeuristic) {
                list.add(i, node);
                return;
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
