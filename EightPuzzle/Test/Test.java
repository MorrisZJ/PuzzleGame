package EightPuzzle.Test;
import EightPuzzle.PuzzleStructure.EightPuzzle;
import EightPuzzle.PuzzleAction.Search;
import EightPuzzle.PuzzleStructure.EightPuzzleNode;

import java.util.List;


public class Test {



    public static void main(String[] args)  {
        testAstar();
    }



    public static void testAstar() {
        Character[] state1 = new Character[]{'1','3','5','2','4','b','7','8','6'}; // Unsolvable
        Character[] stateGoal = new Character[]{'b','1','2','3','4','5','6','7','8'};
        Character[] state3 = new Character[]{'3','1','2','b','4','5','6','7','8'};
        Character[] state4 = new Character[]{'3','1','2','6','4','5','7','b','8'};
        Character[] state5 = new Character[]{'3','1','2','6','4','b','7','8','5'};
        Character[] state6 = new Character[]{'7','2','5','6','4','b','3','1','8'};
        Character[] state7 = new Character[]{'5','3','b','1','4','2','7','6','8'};
        Character[] state8 = new Character[]{'8','7','6','5','3','2','4','b','1'}; //Solved in 3 min for h1
        Character[] state9 = new Character[]{'7','8','5','3','b','4','2','6','1'}; //Solved in 4 min for h1

        EightPuzzle puzzleStart = new EightPuzzle(state8);
        EightPuzzleNode start = new EightPuzzleNode(puzzleStart);

        EightPuzzle puzzleGoal = new EightPuzzle(stateGoal);
        EightPuzzleNode goal = new EightPuzzleNode(puzzleGoal);

        List<EightPuzzleNode> list = Search.aStarSearch(start, goal);
        System.out.println(list);
        System.out.println(list.size());
    }



    public static void testMove()  {
        Character[] characters = new Character[]{'1','3','5','2','4','b','7','8','6'};
        EightPuzzle puzzle = new EightPuzzle(characters);
        EightPuzzleNode node = new EightPuzzleNode(puzzle);
        System.out.println(node);
        node.getCurrentState().toString("");
        node = node.doMove("left");
        node.getCurrentState().toString("");
    }

    public static void testBeam(int k) {
        Character[] state1 = new Character[]{'1','3','5','2','4','b','7','8','6'}; // Unsolvable
        Character[] stateGoal = new Character[]{'b','1','2','3','4','5','6','7','8'};
        Character[] state3 = new Character[]{'3','1','2','b','4','5','6','7','8'};
        Character[] state4 = new Character[]{'3','1','2','6','4','5','7','b','8'};
        Character[] state5 = new Character[]{'3','1','2','6','4','b','7','8','5'};
        Character[] state6 = new Character[]{'7','2','5','6','4','b','3','1','8'};
        Character[] state7 = new Character[]{'5','3','b','1','4','2','7','6','8'};

        EightPuzzle puzzleStart = new EightPuzzle(state7);
        EightPuzzleNode start = new EightPuzzleNode(puzzleStart);

        EightPuzzle puzzleGoal = new EightPuzzle(stateGoal);
        EightPuzzleNode goal = new EightPuzzleNode(puzzleGoal);

        List<EightPuzzleNode> list2 = Search.beamSearch(start,goal,k);
        for (int i = list2.size() - 2; i >= 0 ; i--) {
            System.out.println(list2.get(i).getActFromParentToCurrent());
        }
        System.out.println(list2.size());

    }
}
