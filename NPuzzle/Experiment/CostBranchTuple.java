package NPuzzle.Experiment;

/**
 * This class is a tuple storing the data of cost and branching factor of a search.
 * CostBranchTuple is used for experiment.
 * @author Jiamu Zhang
 */
public class CostBranchTuple {

    /**
     * The cost of a search
     */
    int cost;

    /**
     * The branching factor of a search
     */
    double branchingFactor;

    /**
     * The constructor of the class.
     * @param cost The cost of a search
     * @param branchingFactor The branching factor of a search
     */
    public CostBranchTuple(int cost, double branchingFactor) {
        this.cost = cost;
        this.branchingFactor = branchingFactor;
    }

    /**
     * The constructor of the class with no input.
     */
    public CostBranchTuple() {}

    /**
     * An override toString method that is used to print the cost
     * and branching factor for experiment result.
     * @return String of printed format
     */
    @Override
    public String toString() {
        return
                "cost =" + cost +
                ", branchingFactor = " + branchingFactor;
    }
}
