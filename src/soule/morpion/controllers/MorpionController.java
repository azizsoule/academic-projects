package soule.morpion.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import soule.morpion.utils.Owner;
import soule.morpion.utils.TicTacToeModel;
import soule.morpion.utils.TicTacToeSquare;
import java.net.URL;
import java.util.ResourceBundle;

public class MorpionController implements Initializable {

    @FXML
    private GridPane board;

    @FXML
    private Button restartButton;

    @FXML
    private Label xCountLabel;

    @FXML
    private Label oCountLabel;

    @FXML
    private Label freeCasesLabel;

    @FXML
    private Label winnerTextLabel;

    TicTacToeModel model = TicTacToeModel.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 0; i < TicTacToeModel.BOARD_HEIGHT; i++) {
            for (int j = 0; j < TicTacToeModel.BOARD_WIDTH; j++) {
                if (model.validSquare(i,j)) {
                    TicTacToeSquare square = new TicTacToeSquare(i, j);
                    double height = board.getPrefHeight() / TicTacToeModel.BOARD_HEIGHT;
                    double width = board.getPrefWidth() / TicTacToeModel.BOARD_WIDTH;
                    square.setPrefHeight(height);
                    square.setPrefWidth(width);
                    square.ownerProperty().bind(model.getSquare(i, j));
                    square.textProperty().bind(Bindings.when(square.ownerProperty().isEqualTo(Owner.FIRST)).then("x").otherwise(Bindings.when(square.ownerProperty().isEqualTo(Owner.SECOND)).then("o").otherwise("")));
                    square.winnerProperty().bind(model.getWinningSquare(i,j));
                    square.fontProperty().bind(Bindings.when(square.winnerProperty().isEqualTo(new SimpleBooleanProperty(true))).then(Font.font("", FontWeight.BOLD, 40)).otherwise(Font.font("", FontWeight.BOLD, 20)));
                    board.add(square, j, i);
                }
            }
        }

        xCountLabel.textProperty().bind(Bindings.concat(model.getScore(Owner.FIRST), Bindings.when(model.getScore(Owner.FIRST).greaterThanOrEqualTo(2)).then(" cases ").otherwise(" case "),"pour X"));
        oCountLabel.textProperty().bind(Bindings.concat(model.getScore(Owner.SECOND), Bindings.when(model.getScore(Owner.SECOND).greaterThanOrEqualTo(2)).then(" cases ").otherwise(" case "), "pour O"));

        xCountLabel.styleProperty().bind(Bindings.when(model.turnProperty().isEqualTo(Owner.FIRST)).then("-fx-background-color: cyan").otherwise("-fx-background-color: red"));
        oCountLabel.styleProperty().bind(Bindings.when(model.turnProperty().isEqualTo(Owner.SECOND)).then("-fx-background-color: cyan").otherwise("-fx-background-color: red"));

        freeCasesLabel.textProperty().bind(Bindings.when(model.gameOver().not()).then(Bindings.concat(model.getScore(Owner.NONE).asString(), Bindings.when(model.getScore(Owner.NONE).greaterThanOrEqualTo(2)).then(" cases libres").otherwise(" case libre"))).otherwise(""));

        winnerTextLabel.textProperty().bind(model.getEndOfGameMessage());

        restartButton.setOnAction(actionEvent -> {
            model.restart();
        });

    }

}
