package NPuzzle.TestPuzzle;

import NPuzzle.EightPuzzle.GameEightPuzzle;
import NPuzzle.ElevenPuzzle.GameElevenPuzzle;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The TestPuzzle class is used to test the basic functionality of the Eight Puzzle and
 * Eleven Puzzle by reading a command file.
 * @author Jiamu Zhang
 */
public class TestPuzzle {

    /**
     * The main method that run the test.
     * @param args Argument of main method.
     * @throws Exception Throw exception when error.
     */
    public static void main(String[] args) throws Exception {

        // Initialize EightPuzzle
         GameEightPuzzle puzzle = new GameEightPuzzle();

        /*
            To test the eleven puzzle, please just comment
            the eight puzzle and make things under not commented
         */

        // Initialize ElevenPuzzle
        // GameElevenPuzzle puzzle = new GameElevenPuzzle();

        // Please update the path of command file!
        File fileSource = new File("/Users/morriszhang/Desktop/CWRU/Third-Year/First_Semester/CSDS391/Jiamu_Zhang_P1/Test_Case/Experiment.txt");
        //File fileSource = new File("/Users/morriszhang/Desktop/CWRU/Third-Year/First_Semester/CSDS391/Jiamu_Zhang_P1/Test_Case/Experiment_11Puzzle.txt");

        Scanner sc = new Scanner(fileSource);
        boolean flag = true;
        while (sc.hasNext() && flag) {

            String command = sc.next();

            switch (command) {
                case "setState" -> {
                    String command1 = sc.nextLine();
                    puzzle.setState(command1);
                }
                case "move" -> {
                    String command1 = sc.next();
                    puzzle.move(command1);
                    puzzle.printState("matrix");
                }
                case "randomizeState" -> {
                    int n = sc.nextInt();
                    puzzle.randomizeState(n);
                }
                case "solve" -> {
                    String command1 = sc.next();
                    if (command1.equals("A-star")) {
                        System.out.println("The initial state is: " + Arrays.toString(puzzle.getCurrentState()));
                        String command2 = sc.next();
                        System.out.println("A-star search with " + command2 + " heuristic begin.....");
                        List<String> list = puzzle.A_star(command2);
                        if (list == null) {
                            System.out.println("Searching fail.");
                            System.out.println("");
                        } else {
                            System.out.println("The search cost is: " + puzzle.getCostCurrentRun());
                            System.out.println("The effective branching factor is: " + puzzle.getBfCurrentRun());
                            System.out.println("The number of tile moves is: " + list.size());
                            System.out.println("The solution is: " + list);
                            System.out.println("");
                        }

                    } else if (command1.equals("beam")) {
                        System.out.println("The initial state is: " + Arrays.toString(puzzle.getCurrentState()));
                        int k = sc.nextInt();
                        System.out.println("Local Beam search with k = " + k + " begin.....");
                        List<String> list = puzzle.beam(k);
                        if (list == null) {
                            System.out.println("Searching fail.");
                            System.out.println("");
                        } else {
                            System.out.println("The search cost is: " + puzzle.getCostCurrentRun());
                            System.out.println("The effective branching factor is: " + puzzle.getBfCurrentRun());
                            System.out.println("The number of tile moves is: " + list.size());
                            System.out.println("The solution is: " + list);
                            System.out.println("");
                        }
                    }
                }
                case "maxNode" -> {
                    int command1 = sc.nextInt();
                    puzzle.maxNodes(command1);
                    System.out.println("The max node limit is now " + command1 + ".");
                    System.out.println("");
                }
                case "printState" -> {
                    puzzle.printState("matrix");
                }
                case "end" -> {
                    flag = false;
                }
                default -> System.out.println("Please enter a valid command.");
            }

        }


    }


}
