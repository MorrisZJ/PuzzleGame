package EightPuzzle;

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
        AStarFrontier frontier = new AStarFrontier();
        frontier.push(startState);

        while (!frontier.isEmpty()) {

            EightPuzzleNode node = frontier.popPeek();

            if (node == null) throw new RuntimeException();

            if (node.equals(goalState)) {
                ArrayList<EightPuzzleNode> stack = new ArrayList<>();
                while (node != null) {
                    stack.add(node);
                    node = node.getParentNode();
                }
                return stack;
            }

            if (!explored.contains(node)) {
                explored.add(node);
                for (EightPuzzleNode successor: node.findReachableMove()) {
                    if (!explored.contains(successor)) frontier.push(successor);
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

        return null;

    }
}
