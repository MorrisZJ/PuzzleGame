package NPuzzle.EightPuzzle.PuzzleStructure;
import NPuzzle.ModelAbstract.PriorityFrontier;
import java.util.ArrayList;
import java.util.List;


public class BeamFrontierEight extends PriorityFrontier<EightPuzzle, EightPuzzleNode, String> {

    private final List<EightPuzzleNode> list;

    public BeamFrontierEight() {
        this.list = new ArrayList<>();
    }

    /**
     *
     * @param node
     */
    @Override
    public void push(EightPuzzleNode node, String heuristic) {
        int nodeHeuristic = node.getHeuristic(heuristic);
        if (list.contains(node)) {
            int index = 0;
            for (EightPuzzleNode n : list) {
                if (n.equals(node)) break;
                index++;
            }
            int oldNodeH = list.get(index).getHeuristic(heuristic);
            if (oldNodeH > nodeHeuristic) {
                list.remove(index);
            } else return;
        }
        for(int i = 0; i < list.size(); i++) {
            int currentH = list.get(i).getHeuristic(heuristic);
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

    @Override
    public boolean contain(EightPuzzleNode node) {
        return list.contains(node);
    }

}
