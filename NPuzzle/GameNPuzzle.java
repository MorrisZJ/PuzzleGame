package NPuzzle;

import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;

import java.util.List;

public class GameNPuzzle {

    /**
     *
     */
    private Character[] currentState;

    /**
     *
     */
    private final Character[] goalState;


    private final int n;



    public GameNPuzzle(int n) {
        this.n = n;
        this.currentState = new Character[n];
        this.goalState = new Character[n];
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        for (int i = 0, j = 0; i < getCurrentState().length && j < state.length(); i++, j++) {
            if (state.charAt(j) != ' ') {
                getCurrentState()[i] = state.charAt(j);
            } else {
                i--;
            }
        }
    }




    /**
     *
     * @param direction
     */
    public void move(String direction) {
        setCurrentState(MoveActionEightPuzzle.moves(direction, getCurrentState()));
    }

    /**
     *
     */
    public void randomizeState() {

    }

    /**
     *
     * @return
     */
    public List<String> A_star() {
        return null;
    }


    public void maxNodes(int n) {

    }




    public Character[] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Character[] currentState) {
        this.currentState = currentState;
    }


    public Character[] getGoalState() {
        return goalState;
    }
}
