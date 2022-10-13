package NPuzzle.Experiment;

import NPuzzle.EightPuzzle.GameEightPuzzle;


import java.util.*;

/**
 * The Experiment class is used for running experiment of searching algorithms
 * and collecting data of those.
 * @author Jiamu Zhang
 */
public class Experiment {


    /**
     * The main method that run the experiment.
     * @param args Argument for run the main method.
     * @throws Exception Exception for error.
     */
    public static void main(String[] args) throws Exception {


        // Initialize puzzle and list to store the result
        GameEightPuzzle puzzle = new GameEightPuzzle();

        puzzle.setState("b12 345 678");
        HashMap<Integer,  ArrayList<CostBranchTuple>> l1 = new HashMap<>();
        HashMap<Integer,  ArrayList<CostBranchTuple>> l2 = new HashMap<>();
        HashMap<Integer,  ArrayList<CostBranchTuple>> l3 = new HashMap<>();

        // Initialize max node limit
        puzzle.maxNodes(50000);

        // Conduct experiments
        doExperiment(200, puzzle, l1, l2, l3, 200, 35, 5000);

        // Processing and collecting the data of experiment
        HashMap<Integer, CostBranchTuple> data1 = dataCollecting(l1);
        HashMap<Integer, CostBranchTuple> data2 = dataCollecting(l2);
        HashMap<Integer, CostBranchTuple> data3 = dataCollecting(l3);

        // Print the experiment data and results
        System.out.println("l2 Data:");
        for (Integer ket : data2.keySet()) {
            System.out.println("d = " + ket.toString() + ", "  + data2.get(ket).toString());
        }
        System.out.println("");
        System.out.println("l1 Data:");
        for (Integer ket : data1.keySet()) {
            System.out.println("d = " + ket.toString() + ", " + data1.get(ket).toString());
        }
        System.out.println("");
        System.out.println("Beam Data:");
        for (Integer ket : data3.keySet()) {
            System.out.println("d = " + ket.toString() + ", "  + data3.get(ket).toString());
        }
        System.out.println("");
    }


    /**
     * This method is used for processing and collecting the data from experiment.
     * @param l The map from different depth to different list of CostBranchTuple.
     * @return Return an HashMap map from depth to CostBranchTuple with average cost and branching factor of
     *           a specific depth value.
     *
     */
    public static HashMap<Integer, CostBranchTuple> dataCollecting(HashMap<Integer, ArrayList<CostBranchTuple>> l)
    {

        // Map that stores the result
        HashMap<Integer, CostBranchTuple> avgData = new HashMap<>();

        // Loop that calculate the average data
        for (int i = 1; i < 50; i++) {
            int countCost = 0;
            int costSum = 0;
            int costAvg = 0;
            int countBf = 0;
            double bfSum = 0;
            double bfAvg = 0;
            if (l.get(i).size() != 0) {
                for (CostBranchTuple tuple: l.get(i)) {
                    countCost ++;
                    costSum += tuple.cost;
                    countBf ++;
                    bfSum += tuple.branchingFactor;
                }
                costAvg = costSum / countCost;
                bfAvg = bfSum / countBf;
                CostBranchTuple tuple = new CostBranchTuple(costAvg, bfAvg);
                avgData.put(i,tuple);
            } else {
                avgData.put(i, new CostBranchTuple(-1,-1));
            }

        }
        return avgData;
    }


    /**
     * This method conduct the experiment.
     * @param numbRandomProb Number of random puzzles.
     * @param puzzle The puzzle game instance.
     * @param l1 The map stores A* H1 search data.
     * @param l2 The map stores A* H2 search data.
     * @param l3 The map stores Beam search data.
     * @param beamK The k value for beam search
     * @param seed The seed of random puzzle generator.
     * @param randiRange The depth range of puzzles, which is the maximum number of randomizedMove from goal state
     *                   for generating a random initial state puzzle.
     * @throws Exception Throw exception when error.
     */
    public static void doExperiment(int numbRandomProb, GameEightPuzzle puzzle,  HashMap<Integer, ArrayList<CostBranchTuple>> l1,
                                    HashMap<Integer, ArrayList<CostBranchTuple>> l2,
                                    HashMap<Integer, ArrayList<CostBranchTuple>> l3,
                                    int beamK, int seed, int randiRange) throws Exception
    {

        int unSolveH1 = 0;
        int unSolveH2 = 0;
        int unSolveBeam = 0;

        double totalSolutionLH1 = 0.0;
        double totalSolutionLH2 = 0.0;
        double totalSolutionLBeam = 0.0;

        Random random = new Random();
        random.setSeed(seed);

        for (int i = -1; i < 50; i++) {
            l1.put(i, new ArrayList<>());
            l2.put(i, new ArrayList<>());
            l3.put(i, new ArrayList<>());
        }

        for (int i = 0; i < numbRandomProb; i++) {



            int d1, d2, d3 = 0;
            int n =  random.nextInt(randiRange) + 1;

            puzzle.randomizeState(n);

            List<String> list = puzzle.beam(beamK);

            if (list != null) {
                d3 = list.size();
                totalSolutionLBeam += d3;
                CostBranchTuple tuple3 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l3.get(d3).add(tuple3);
            } else {
                CostBranchTuple tuple3 = new CostBranchTuple(-1, -1);
                l3.get(-1).add(tuple3);
                unSolveBeam++;
            }

            list = puzzle.A_star("h1");

            if (list != null) {
                d1 = list.size();
                totalSolutionLH1 += d1;
                CostBranchTuple tuple1 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l1.get(d1).add(tuple1);
            } else {
                CostBranchTuple tuple1 = new CostBranchTuple(-1, -1);
                l1.get(-1).add(tuple1);
                unSolveH1++;
            }

            list = puzzle.A_star("h2");

            if (list != null) {
                d2 = list.size();
                totalSolutionLH2 += d2;
                CostBranchTuple tuple2 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l2.get(d2).add(tuple2);
            } else {
                CostBranchTuple tuple2 = new CostBranchTuple(-1, -1);
                l2.get(-1).add(tuple2);
                unSolveH2++;
            }

            System.out.println("The " + i + " puzzle set done!");

        }


        System.out.println("The following data record the solutions of puzzles solvable to both three algorithm." + ":");
        System.out.println("When max node limit is 50000, the number of solvable puzzle for h1 is: " + (200 - unSolveH1));
        System.out.println("The average solution length for h1 is: " + (totalSolutionLH1 / (200 - unSolveH1)));
        System.out.println("When max node limit is 50000, the number of solvable puzzle for h2 is: " + (200 - unSolveH2));
        System.out.println("The average solution length for h2 is: " + (totalSolutionLH2 / (200 - unSolveH2)));
        System.out.println("When max node limit is 50000, the number of solvable puzzle for beam is: " + (200 - unSolveBeam));
        System.out.println("The average solution length for beam is: " + (totalSolutionLBeam / (200 - unSolveBeam)));


    }

}
