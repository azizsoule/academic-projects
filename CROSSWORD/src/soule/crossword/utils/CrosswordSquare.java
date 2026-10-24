package soule.crossword.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CrosswordSquare extends TextField {

    private char solution;
    private final StringProperty proposition;
    private String horizontalClue, verticalClue;
    private boolean black;

    public CrosswordSquare() {
        black = true;
        proposition = new SimpleStringProperty();
        proposition.set("");
        this.setEditable(false);
        this.setAlignment(Pos.CENTER);
        this.setFont(Font.font("", FontWeight.BOLD, Font.getDefault().getSize()));
        this.setStyle("-fx-background-color: white; -fx-border-style: solid");
    }

    public char getSolution() {
        return this.solution;
    }

    public void setSolution(char solution) {
        this.solution = solution;
    }

    public String getProposition() {
        return this.proposition.get();
    }

    public StringProperty propositionProperty() {
        return this.proposition;
    }

    public void setProposition(String proposition) {
        this.proposition.set(proposition);
    }

    public String getHorizontalClue() {
        return this.horizontalClue;
    }

    public void setHorizontalClue(String horizontalClue) {
        this.horizontalClue = horizontalClue;
    }

    public String getVerticalClue() {
        return this.verticalClue;
    }

    public void setVerticalClue(String verticalClue) {
        this.verticalClue = verticalClue;
    }

    public boolean isBlack() {
        return this.black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

}
