package NPuzzle.ElevenPuzzle.PuzzleStructure;


import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;
import NPuzzle.ModelAbstract.PriorityFrontier;

import java.util.ArrayList;
import java.util.List;

public class AFrontierEle extends PriorityFrontier<ElevenPuzzle, ElevenPuzzleNode, String> {

    /**
     * The list that stores the node of state.
     */
    private final List<ElevenPuzzleNode> list;

    public AFrontierEle() {
        this.list = new ArrayList<>();
    }

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
                break;
            }
        }
        list.add(node);
    }

    @Override
    public ElevenPuzzleNode popPeek() {
        ElevenPuzzleNode state = list.get(0);
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

    @Override
    public boolean contain(ElevenPuzzleNode node) {
        return list.contains(node);
    }
}
