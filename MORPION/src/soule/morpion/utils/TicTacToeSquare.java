package soule.morpion.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TicTacToeSquare extends Label {

    private final int row;

    private final int column;

    private static TicTacToeModel model = TicTacToeModel.getInstance();

    private ObjectProperty<Owner> ownerProperty = new SimpleObjectProperty<>(Owner.NONE);

    private BooleanProperty winnerProperty = new SimpleBooleanProperty(false);

    public ObjectProperty<Owner> ownerProperty() {
        return ownerProperty;
    }

    public BooleanProperty winnerProperty() {
        return winnerProperty;
    }

    public TicTacToeSquare(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.setAlignment(Pos.CENTER);
        this.setFont(Font.font("", FontWeight.BOLD, 20));
        this.setOnMouseClicked(this::onMouseClicked);
        this.setOnMouseEntered(this::onMouseEntered);
        this.setOnMouseExited(this::onMouseExited);
    }

    private void onMouseClicked (MouseEvent mouseEvent) {
        model.play(row, column);
    }

    private void onMouseEntered (MouseEvent mouseEvent) {
        if (model.legalMove(row, column).getValue()) {
            this.setStyle("-fx-background-color: #549c54");
        } else {
            this.setStyle("-fx-background-color: #e34c4c");
        }
    }

    private void onMouseExited (MouseEvent mouseEvent) {
        this.setStyle("-fx-background-color: transparent");
    }

}