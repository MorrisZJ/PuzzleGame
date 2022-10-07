package NPuzzle.EightPuzzle;
import NPuzzle.EightPuzzle.PuzzleStructure.AFrontierEight;
import NPuzzle.EightPuzzle.PuzzleStructure.BeamFrontierEight;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;

import java.util.*;

/**
 * This is the old version of search
 * @author Jiamu Zhang
 */
public class SearchEightPuzzle {


    /**
     *
     * @param startState
     * @param goalState
     * @return
     */
    public static List<EightPuzzleNode> aStarSearch(EightPuzzleNode startState, EightPuzzleNode goalState, String heuristic) {
        List<EightPuzzleNode> explored = new ArrayList<>();
        AFrontierEight frontier = new AFrontierEight();
        frontier.push(startState, heuristic);

        while (!frontier.isEmpty()) {

            EightPuzzleNode node = frontier.popPeek();

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
                        frontier.push(successor, heuristic);
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
    public static List<EightPuzzleNode> beamSearch(EightPuzzleNode startState, EightPuzzleNode goalState, int k, String heuristic) {

        List<EightPuzzleNode> explored = new ArrayList<>();
        BeamFrontierEight frontier = new BeamFrontierEight();
        frontier.push(startState, heuristic);
        int counter = 0;

        while (true) {
            ArrayList<EightPuzzleNode> listOfNodes = new ArrayList<>();

            while (frontier.size() > 0) {
                listOfNodes.add(frontier.popPeek());
                counter++;
            }

            for (EightPuzzleNode nodeTemp : listOfNodes) {

                if (nodeTemp == null) throw new RuntimeException();

                if (nodeTemp.equals(goalState)) {
                    ArrayList<EightPuzzleNode> stack = new ArrayList<>();
                    while (nodeTemp.getActFromParentToCurrent() != null) {
                        stack.add(nodeTemp);
                        nodeTemp = nodeTemp.getParentNode();
                    }
                    System.out.println(counter);
                    System.out.println(explored.size());
                    return stack;
                }

                if (!explored.contains(nodeTemp)) {
                    explored.add(nodeTemp);
                    for (EightPuzzleNode successor : nodeTemp.findReachableMove()) {
                        if (!explored.contains(successor)) {
                            successor.setDept(nodeTemp.getDept() + 1);
                            frontier.push(successor, heuristic);
                        }
                    }
                }
            }


        }

    }
}
