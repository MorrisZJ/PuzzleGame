package EightPuzzle.PuzzleAction;
import EightPuzzle.PuzzleStructure.AFrontier;
import EightPuzzle.PuzzleStructure.BeamFrontier;
import EightPuzzle.PuzzleStructure.EightPuzzleNode;

import java.util.*;

/**
 *
 */
public class Search {


    /**
     *
     * @param startState
     * @param goalState
     * @return
     */
    public static List<EightPuzzleNode> aStarSearch(EightPuzzleNode startState, EightPuzzleNode goalState) {
        List<EightPuzzleNode> explored = new ArrayList<>();
        AFrontier frontier = new AFrontier();
        frontier.push(startState);

        while (!frontier.isEmpty()) {

            EightPuzzleNode node = frontier.popPeek();
            //node.getCurrentState().toString("");
            if (node == null) throw new RuntimeException();

            if (node.equals(goalState)) {
                ArrayList<EightPuzzleNode> stack = new ArrayList<>();
                while (node != null && node.getActFromParentToCurrent() != null) {
                    stack.add(node);
                    node = node.getParentNode();
                }
                System.out.println(explored.size());
                return stack;
            }

            if (!explored.contains(node)) {
                explored.add(node);
                for (EightPuzzleNode successor: node.findReachableMove()) {
                    if (!explored.contains(successor)) {
                        successor.setDept(node.getDept() + 1);
                        frontier.push(successor);
                    }
                }
            }

        }
        throw new RuntimeException();
    }


    /**
     *
     * @param startState
     * @param goalState
     * @param k
     * @return
     */
    public static List<EightPuzzleNode> beamSearch(EightPuzzleNode startState, EightPuzzleNode goalState, int k) {

        List<EightPuzzleNode> explored = new ArrayList<>();
        BeamFrontier frontier = new BeamFrontier(k);
        frontier.push(startState);

        while (true) {
            ArrayList<EightPuzzleNode> listOfNodes = new ArrayList<>();

            while (frontier.size() > 0) {
                listOfNodes.add(frontier.popPeek());
            }

            for (EightPuzzleNode nodeTemp : listOfNodes) {

                if (nodeTemp == null) throw new RuntimeException();

                if (nodeTemp.equals(goalState)) {
                    ArrayList<EightPuzzleNode> stack = new ArrayList<>();
                    while (nodeTemp.getActFromParentToCurrent() != null) {
                        stack.add(nodeTemp);
                        nodeTemp = nodeTemp.getParentNode();
                    }
                    System.out.println(explored.size());
                    return stack;
                }

                if (!explored.contains(nodeTemp)) {
                    explored.add(nodeTemp);
                    for (EightPuzzleNode successor : nodeTemp.findReachableMove()) {
                        if (!explored.contains(successor)) {
                            successor.setDept(nodeTemp.getDept() + 1);
                            frontier.push(successor);
                        }
                    }
                }
            }


        }

    }
}
