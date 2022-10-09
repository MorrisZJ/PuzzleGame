package NPuzzle.TestEIghtPuzzle;

import NPuzzle.EightPuzzle.GameEightPuzzle;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestEightPuzzle {

    public static void main(String[] args) throws Exception {

        GameEightPuzzle puzzle = new GameEightPuzzle();

        File fileSource = new File("/Users/morriszhang/Desktop/CWRU/Third-Year/First_Semester/CSDS391/Jiamu_Zhang_P1/Test_Case/Experiment.txt");


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
                        System.out.println("The number of tile moves is: " + list.size());
                        System.out.println("The solution is: " + list);
                        System.out.println("");
                    } else if (command1.equals("beam")) {
                        System.out.println("The initial state is: " + Arrays.toString(puzzle.getCurrentState()));
                        int k = sc.nextInt();
                        System.out.println("Local Beam search with k = " + k + " begin.....");
                        List<String> list = puzzle.beam(k);
                        System.out.println("The number of tile moves is: " + list.size());
                        System.out.println("The solution is: " + list);
                        System.out.println("");
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
