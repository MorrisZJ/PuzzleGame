package NPuzzle.ModSearch;
import NPuzzle.ModelAbstract.Node;
import NPuzzle.ModelAbstract.PriorityFrontier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The PuzzleSearch class is a searching engine that contains A-Star search and Local Beam search functions.
 * This class is implemented as a generic searching engine, so it can be utilized for any types of state-based
 * tree-search problems by defining the specific state type, node type, action type, and frontier type.
 *
 * @author Jiamu Zhang
 *
 * @param <StateType> The StateType is the specific representation of the puzzle state of searching problems.
 * @param <NodeType> The NodeType is the node that compose the search tree during the search.
 *                  The NodeType is the subclass of the Node, which stores the current state, parent node, and the action from parent state
 *                  to current state.
 * @param <ActionType> The representation of action from parent node to current node in NodeType class.
 * @param <FrontierType> The type of frontier that is used for searching algorithm.
 *                      The FrontierType is the subclass of PriorityFrontier.
 */
public class PuzzleSearch<StateType, NodeType extends Node<StateType, NodeType, ActionType>,
        ActionType, FrontierType extends PriorityFrontier<StateType, NodeType, ActionType>> {


    /**
     * The aStarSearch method find the solution to solve the puzzle based on input starting state and goal state using
     * Local Beam Search searching algorithm.
     *
     * @param startState The initial state of the searching problem.
     * @param goalState The goal state of the searching problem.
     * @param frontier The type of frontier that is utilized for searching process, which is also known as the opened
     *                 list of the A-Star searching algorithm.
     * @param heuristic The heuristic function that is utilized for searching process.
     * @param k The number of nodes preserved during each iteration of the search.
     * @param maxNode The maximum number of nodes to consider during the whole searching process.
     * @return The method will return a list of string of moves from initial state to goal state.
     */
    public List<NodeType> beamSearch(NodeType startState, NodeType goalState, FrontierType frontier, String heuristic, int k, int maxNode) {

         // push the node of the initial state into the frontier of the beam search
         frontier.push(startState, heuristic);

         // A counter that used to keep track of the total number of nodes consider during the searching process
         int count = 0;

         // A list that stores the kth best successors
         List<NodeType> kBestList = new LinkedList<>();

         // The main loop of local beam search
         while (!frontier.isEmpty() && count < maxNode) {

             // Select k the best successors to the list
             int size = frontier.size();
             for (int i = 0; i < Math.min(size, k); i++) {
                 kBestList.add(frontier.popPeek());
             }

             // Pop the remaining node, or say prune the extra path
             while (!frontier.isEmpty()) {
                 frontier.popPeek();
             }

             for(NodeType node: kBestList) {

                 // Check if the successor is the goal
                 if (node.equals(goalState)) {
                     ArrayList<NodeType> stack = new ArrayList<>();
                     while (node != null && node.getActFromParentToCurrent() != null) {
                         stack.add(node);
                         node = node.getParentNode();
                     }
                     System.out.println(count);
                     return stack;

                 } else {

                     // Add the successors of each node in kBestList into the frontier
                     for (NodeType successor: node.findReachableMove()) {
                         successor.setDept(node.getDept() + 1);
                         frontier.push(successor, heuristic);
                         count++;
                     }
                 }
             }

             // Clear the kBestList
             kBestList.clear();
         }

        // The frontier is empty or number of total considered nodes exceeds the limit,
        // so the searching is fail, and the exception is thrown.
         throw new RuntimeException("The number of nodes exceeded the limit during the search");
    }


    /**
     * The aStarSearch method find the solution to solve the puzzle based on input starting state and goal state using
     * A* searching algorithm.
     *
     * @param startState The initial state of the searching problem.
     * @param goalState The goal state of the searching problem.
     * @param frontier The type of frontier that is utilized for searching process, which is also known as the opened
     *                 list of the A-Star searching algorithm.
     * @param heuristic The heuristic function that is utilized for searching process.
     * @return The method will return a list of string of moves from initial state to goal state.
     */
    public List<NodeType> aStarSearch(NodeType startState, NodeType goalState, FrontierType frontier, String heuristic, int maxNode) {

        List<NodeType> explored = new ArrayList<>(); // explored: Closed List
        frontier.push(startState, heuristic);  // frontier: Opened list

        // A counter that used to keep track of the total number of nodes consider during the searching process
        int count = 0;

        // Main loop of the searching process
        while (!frontier.isEmpty() && count < maxNode) {

            // Pop the node with the lowest f(n)
            NodeType node = frontier.popPeek();

            // Assert node is not null, or if it is null, the while loop will end and method throws the exception
            assert node != null;

            // The goal state is found, return the solution of searching problem
            if (node.equals(goalState)) {
                ArrayList<NodeType> stack = new ArrayList<>();
                while (node != null && node.getActFromParentToCurrent() != null) {
                    stack.add(node);
                    node = node.getParentNode();
                }
                System.out.println(count);
                return stack;
            }

            /*
             * For each successor of current node, check if the successor should be added or if the priority
             * value of node inside the frontier should be updated
             */
            for (NodeType successor: node.findReachableMove()) {

                successor.setDept(node.getDept() + 1);

                // If successor inside the opened list, check and update if successor has lower f(n)
                if (frontier.contain(successor)) {

                    /* The push method inside the frontier will check whether the node is already inside the frontier
                     * If already in the frontier, the frontier will automatically update the lowest f(n) if successor
                     * has a smaller f(n).
                     */
                    frontier.push(successor, heuristic);

                /*
                 * If successor inside the closed list, check whether the node should be moved from closed list
                 * to the open list.
                 */
                } else if (explored.contains(successor)) {
                    int index = 0;

                    //Get the index of that node in closed list
                    for (NodeType n : explored) {
                        if (n.equals(successor)) break;
                        index++;
                    }

                    // The node in the closed list is found
                    NodeType target = explored.get(index);

                    //Calculate the priority value of that node
                    int f = target.getDept();
                    if (f > successor.getDept()) {

                        //move the node from closed list to open list
                        frontier.push(successor, heuristic);
                        explored.remove(index);
                    }

                    // If the successor is not visited, just add it to the frontier
                } else {
                    frontier.push(successor, heuristic);
                }

                count++;

            }

            // Add the current node to the closed list
            explored.add(node);
        }

        // The frontier is empty, so the searching is fail, and the exception is thrown
        throw new RuntimeException("Searching fail.");
    }

}
