package NPuzzle.EightPuzzle.SlidingTile;


import NPuzzle.EightPuzzle.*;
import NPuzzle.EightPuzzle.PuzzleAction.MoveActionEightPuzzle;
import NPuzzle.EightPuzzle.Utilities.RandomGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class SlidingTiles extends Application {

    public final GameEightPuzzle puzzle = new GameEightPuzzle();
    private final GridPane gridPane = new GridPane();
    private Button[][] buttons = new Button[3][3];
    int counter = 0;

    @Override
    public void start(Stage primaryStage) {
        getGridPane().setOnKeyReleased(event -> {

            switch (event.getCode()) {
                case UP:
                    if (MoveActionEightPuzzle.checkMove("up", MoveActionEightPuzzle.findBlank(getPuzzle().getCurrentState()))) {
                        puzzle.move("up");
                        synchronize();
                        counter = counter + 1;
                        System.out.println(counter);
                    }
                    break;
                case DOWN:
                    if (MoveActionEightPuzzle.checkMove("down", MoveActionEightPuzzle.findBlank(getPuzzle().getCurrentState()))) {
                        puzzle.move("down");
                        synchronize();
                        counter = counter + 1;
                        System.out.println(counter);
                    }
                    break;
                case LEFT:
                    if (MoveActionEightPuzzle.checkMove("left", MoveActionEightPuzzle.findBlank(getPuzzle().getCurrentState()))) {
                        puzzle.move("left");
                        synchronize();
                        counter = counter + 1;
                        System.out.println(counter);
                    }
                    break;
                case RIGHT:
                    if (MoveActionEightPuzzle.checkMove("right", MoveActionEightPuzzle.findBlank(getPuzzle().getCurrentState()))) {
                        puzzle.move("right");
                        synchronize();
                        counter = counter + 1;
                        System.out.println(counter);
                    }
                    break;
                case A:
                    List<String> list;
                    list = puzzle.A_star();
                    System.out.println(list.toString());
                    for (String s : list) {
                        puzzle.move(s);
                        synchronize();
                    }
                    break;
                case R:
                    puzzle.setCurrentState(RandomGenerator.randomizeStateAction(puzzle.getCurrentState(), 100));
                    synchronize();
                    break;
                case B:
                    List<String> listB;
                    listB = puzzle.beam(35);
                    System.out.println(listB.toString());
                    for (String s : listB) {
                        puzzle.move(s);
                        synchronize();
                    }
                    break;
            }
        });
        puzzle.setState("53b 142 768");
        initializeButton();
        getGridPane().setStyle("-fx-border-width: 5 5 5 5;-fx-border-color: cyan; -fx-background-color: Black");  //cyan
        Scene scene = new Scene(this.getGridPane());//Create a scene for displaying the buttons
        primaryStage.setScene(scene);               //Add the "scene" to the main window
        primaryStage.setTitle("Eight Puzzle 3 x 3");  //Add the title of the game
        primaryStage.show();                        //Display the window
    }

    public void initializeButton() {
        int k = 0;
        for (int i = 0; i < getButtons().length; i++) {
            for (int j = 0; j < getButtons()[i].length; j++) {
                Button button = new Button();
                String temp = String.valueOf( getPuzzle().getCurrentState()[k]);
                if (!Objects.equals(temp, "b")) {
                    button.setText(temp);
                }
                button.setPrefSize(270, 270);
                button.setFont(Font.font("s", FontWeight.BOLD, 40));
                button.setStyle("-fx-border-width: 20 20 20 20;-fx-border-style: solid;-fx-border-color: grey; -fx-background-color: white");
                getButtons()[j][i] = button;
                gridPane.add(button, j, i);
                k++;
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }


    public void synchronize() {
        int k = 0;
        for (int i = 0; i < getButtons().length; i++) {
            for (int j = 0; j < getButtons()[i].length; j++) {
                String temp = String.valueOf(getPuzzle().getCurrentState()[k]);
                if (!Objects.equals(temp, "b")) {
                    getButtons()[j][i].setText(temp);
                } else {
                    getButtons()[j][i].setText(" ");
                }
                k++;
            }
        }
    }

    public GameEightPuzzle getPuzzle() {
        return puzzle;
    }
}


