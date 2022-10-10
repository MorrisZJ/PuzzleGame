package NPuzzle.Experiment;

import NPuzzle.EightPuzzle.GameEightPuzzle;


import java.util.*;


public class Experiment {

    public static void main(String[] args) throws Exception {

        GameEightPuzzle puzzle = new GameEightPuzzle();
        puzzle.setState("b12 345 678");
        puzzle.maxNodes(50000);
        HashMap<Integer,  ArrayList<CostBranchTuple>> l1 = new HashMap<>();
        HashMap<Integer,  ArrayList<CostBranchTuple>> l2 = new HashMap<>();
        HashMap<Integer,  ArrayList<CostBranchTuple>> l3 = new HashMap<>();
        doExperiment(3000, puzzle, l1, l2, l3);

        HashMap<Integer, CostBranchTuple> data1 = dataCollectingH2(l1);
        HashMap<Integer, CostBranchTuple> data2 = dataCollectingH2(l2);
        HashMap<Integer, CostBranchTuple> data3 = dataCollectingH2(l3);

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



    public static HashMap<Integer, CostBranchTuple> dataCollectingH2(HashMap<Integer, ArrayList<CostBranchTuple>> l)
    {

        HashMap<Integer, CostBranchTuple> avgData = new HashMap<>();

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





    public static void doExperiment(int numbRandomProb, GameEightPuzzle puzzle,  HashMap<Integer, ArrayList<CostBranchTuple>> l1,
                                    HashMap<Integer, ArrayList<CostBranchTuple>> l2,
                                    HashMap<Integer, ArrayList<CostBranchTuple>> l3) throws Exception
    {

        Random random = new Random();
        random.setSeed(120);

        for (int i = -1; i < 50; i++) {
            l1.put(i, new ArrayList<>());
            l2.put(i, new ArrayList<>());
            l3.put(i, new ArrayList<>());
        }

        for (int i = 0; i < numbRandomProb; i++) {

            System.out.println("The " + i + " puzzle set done!");

            int d1, d2, d3 = 0;
            int n =  random.nextInt(100) + 1;

            puzzle.randomizeState(n);

            List<String> list = puzzle.A_star("h1");

            if (list != null) {
                d1 = list.size();
                CostBranchTuple tuple1 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l1.get(d1).add(tuple1);
            } else {
                CostBranchTuple tuple1 = new CostBranchTuple(-1, -1);
                l1.get(-1).add(tuple1);
            }

            list = puzzle.A_star("h2");

            if (list != null) {
                d2 = list.size();
                CostBranchTuple tuple2 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l2.get(d2).add(tuple2);
            } else {
                CostBranchTuple tuple2 = new CostBranchTuple(-1, -1);
                l2.get(-1).add(tuple2);
            }

            list = puzzle.beam(100);

            if (list != null) {
                d3 = list.size();
                CostBranchTuple tuple3 = new CostBranchTuple(puzzle.getCostCurrentRun(), puzzle.getBfCurrentRun());
                l3.get(d3).add(tuple3);
            } else {
                CostBranchTuple tuple3 = new CostBranchTuple(-1, -1);
                l3.get(-1).add(tuple3);
            }


        }
    }

}
