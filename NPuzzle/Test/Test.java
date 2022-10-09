package NPuzzle.Test;
import NPuzzle.EightPuzzle.PuzzleStructure.AFrontierEight;
import NPuzzle.EightPuzzle.PuzzleStructure.BeamFrontierEight;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzle;
import NPuzzle.EightPuzzle.PuzzleStructure.EightPuzzleNode;
import NPuzzle.ElevenPuzzle.GameElevenPuzzle;
import NPuzzle.ModSearch.PuzzleSearch;

import java.util.List;


public class Test {



    public static void main(String[] args) throws Exception {
        testElevenAstar();
        testElevenBeam(); // Beam much faster
    }



    public static void testElevenAstar() throws Exception {

        GameElevenPuzzle puzzle = new GameElevenPuzzle();
        puzzle.maxNodes(50000);
        puzzle.setState("b12 345 678 9xy");
        puzzle.printState("matrix");
        puzzle.randomizeState(200);
        puzzle.printState("matrix");
        System.out.println(puzzle.A_star("h2"));



    }

    public static void testElevenBeam() throws Exception {

        GameElevenPuzzle puzzle = new GameElevenPuzzle();
        puzzle.maxNodes(50000);
        puzzle.setState("b12 345 678 9xy");
        puzzle.printState("matrix");
        puzzle.randomizeState(200);
        puzzle.printState("matrix");
        System.out.println(puzzle.beam(200));



    }




    public static void testAstar() throws Exception {
        Character[] state1 = new Character[]{'1','3','5','2','4','b','7','8','6'}; // Unsolvable
        Character[] stateGoal = new Character[]{'b','1','2','3','4','5','6','7','8'};
        Character[] state3 = new Character[]{'3','1','2','b','4','5','6','7','8'};
        Character[] state4 = new Character[]{'3','1','2','6','4','5','7','b','8'};
        Character[] state5 = new Character[]{'3','1','2','6','4','b','7','8','5'};
        Character[] state6 = new Character[]{'7','2','5','6','4','b','3','1','8'};
        Character[] state7 = new Character[]{'5','3','b','1','4','2','7','6','8'}; //Beam and A-star different, beam optimal!!
        Character[] state8 = new Character[]{'8','7','6','5','3','2','4','b','1'}; //Solved in 3 min for h1
        Character[] state9 = new Character[]{'7','8','5','3','b','4','2','6','1'}; //Solved in 4 min for h1
        Character[] state10 = new Character[]{'1','3','5','b','4','2','7','8','6'}; //

        EightPuzzle puzzleStart = new EightPuzzle(state8);
        EightPuzzleNode start = new EightPuzzleNode(puzzleStart);

        EightPuzzle puzzleGoal = new EightPuzzle(stateGoal);
        EightPuzzleNode goal = new EightPuzzleNode(puzzleGoal);

        PuzzleSearch<EightPuzzle, EightPuzzleNode, String, AFrontierEight> searchEight = new PuzzleSearch<>();
        List<EightPuzzleNode> list = searchEight.aStarSearch(start, goal, new AFrontierEight(), "h2", 50000);

        for (int i = 0; i < list.size() / 2; i++) {
            EightPuzzleNode temp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set(list.size() - 1 - i, temp);
        }
        System.out.println(list);
    }

    public static void testBeam(int k) throws Exception {
        Character[] state1 = new Character[]{'1', '3', '5', '2', '4', 'b', '7', '8', '6'}; // Unsolvable
        Character[] stateGoal = new Character[]{'b', '1', '2', '3', '4', '5', '6', '7', '8'};
        Character[] state3 = new Character[]{'3', '1', '2', 'b', '4', '5', '6', '7', '8'};
        Character[] state4 = new Character[]{'3', '1', '2', '6', '4', '5', '7', 'b', '8'};
        Character[] state5 = new Character[]{'3', '1', '2', '6', '4', 'b', '7', '8', '5'};
        Character[] state6 = new Character[]{'7', '2', '5', '6', '4', 'b', '3', '1', '8'};
        Character[] state7 = new Character[]{'5', '3', 'b', '1', '4', '2', '7', '6', '8'};
        Character[] state8 = new Character[]{'8', '7', '6', '5', '3', '2', '4', 'b', '1'}; //Solved in 3 min for h1
        Character[] state9 = new Character[]{'7', '8', '5', '3', 'b', '4', '2', '6', '1'}; //Solved in 4 min for h1
        Character[] state10 = new Character[]{'1','3','5','b','4','2','7','8','6'}; //

        EightPuzzle puzzleStart = new EightPuzzle(state8);
        EightPuzzleNode start = new EightPuzzleNode(puzzleStart);

        EightPuzzle puzzleGoal = new EightPuzzle(stateGoal);
        EightPuzzleNode goal = new EightPuzzleNode(puzzleGoal);
        PuzzleSearch<EightPuzzle, EightPuzzleNode, String, BeamFrontierEight> puzzleSearch = new PuzzleSearch<>();
        List<EightPuzzleNode> list = puzzleSearch.beamSearch(start, goal, new BeamFrontierEight(), "h2", k, 40000);

        for (int i = 0; i < list.size() / 2; i++) {
            EightPuzzleNode temp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set(list.size() - 1 - i, temp);
        }
        System.out.println(list);

    }
}
