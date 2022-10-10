package NPuzzle.Experiment;

public class CostBranchTuple {
    int cost;
    double branchingFactor;

    public CostBranchTuple(int cost, double branchingFactor) {
        this.cost = cost;
        this.branchingFactor = branchingFactor;
    }

    public CostBranchTuple() {}

    @Override
    public String toString() {
        return
                "cost =" + cost +
                ", branchingFactor = " + branchingFactor;
    }
}
