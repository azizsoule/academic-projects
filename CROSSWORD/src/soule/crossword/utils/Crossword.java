package soule.crossword.utils;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Crossword extends Grid<CrosswordSquare> {

    private final ObservableList<Clue> verticalClues;
    private final ObservableList<Clue> horizontalClues;

    private boolean horizontal;

    private Crossword(int height, int width) {
        super(height, width);
        horizontal = true;
        verticalClues = FXCollections.observableArrayList();
        horizontalClues = FXCollections.observableArrayList();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.setCell(i, j, new CrosswordSquare());
            }
        }
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public StringProperty propositionProperty(int row, int column) {
        return this.getCell(row, column).propositionProperty();
    }

    public boolean isBlackSquare(int row, int column) {
        return this.getCell(row, column).isBlack();
    }

    public void setBlackSquare(int row, int column, boolean black) {
        this.getCell(row, column).setBlack(black);
    }

    public char getSolution(int row, int column) {
        return this.getCell(row, column).getSolution();
    }

    public void setSolution(int row, int column, char solution) {
        this.getCell(row, column).setSolution(solution);
    }

    public char getProposition(int row, int column) {
        return this.getCell(row, column).getProposition().length()==0 ? '0' : this.getCell(row, column).getProposition().charAt(0);
    }

    public void setProposition(int row, int column, char proposition) {
        this.getCell(row, column).setProposition(Character.toString(proposition));
    }

    public String getClue(int row, int column, boolean horizontal) {
        Predicate<Clue> predicate = clue -> clue.getRow()==row && clue.getColumn()==column;
        FilteredList<Clue> filteredList;
        filteredList = horizontal ? this.horizontalClues.filtered(predicate) : this.verticalClues.filtered(predicate);
        return filteredList.size() == 0 ? null : filteredList.get(0).getClue();
    }

    public void setClue(int row, int column, boolean horizontal, String definition) {
        Clue clue = new Clue(definition, row, column, horizontal);
        if (horizontal) {
            this.horizontalClues.add(clue);
        } else {
            this.verticalClues.add(clue);
        }
    }

    public ObservableList<Clue> getVerticalClues() {
        return this.verticalClues;
    }

    public ObservableList<Clue> getHorizontalClues() {
        return this.horizontalClues;
    }

    public static Crossword createPuzzle(Database database, int puzzleNumber) {
        HashMap<String, Integer> dimensions = database.getDimensions(puzzleNumber);
        Crossword crossword = new Crossword(dimensions.get(Database.HEIGTH), dimensions.get(Database.WIDTH));
        ArrayList<HashMap<String,Object>> clues = database.getClues(puzzleNumber);

        clues.forEach(clue -> {

            int row = ((int) clue.get(Database.ROW))-1;
            int column = ((int) clue.get(Database.COLUMN))-1;
            boolean horizontal = (boolean) clue.get(Database.HORIZONTAL);
            String definition = (String) clue.get(Database.CLUE);
            String solution = (String) clue.get(Database.SOLUTION);

            solution = solution.toUpperCase();

            crossword.setClue(
                row,
                column,
                horizontal,
                definition
            );

            if (horizontal) {
                crossword.getCell(row, column).setHorizontalClue(crossword.getClue(row, column, true));
                for (int i = column; i < (column + solution.length()); i++) {
                    crossword.getCell(row, i).setSolution(solution.charAt(i-column));
                    crossword.getCell(row, i).setBlack(false);
                    crossword.getCell(row, i).setProposition("");
                    crossword.getCell(row, i).setHorizontalClue(crossword.getClue(row, column, true));
                }
            } else {
                crossword.getCell(row, column).setVerticalClue(crossword.getClue(row, column, false));
                for (int i = row; i < (row + solution.length()); i++) {
                    crossword.getCell(i, column).setSolution(solution.charAt(i-row));
                    crossword.getCell(i, column).setBlack(false);
                    crossword.getCell(i, column).setProposition("");
                    crossword.getCell(i, column).setVerticalClue(crossword.getClue(row, column, false));
                }
            }

        });

        return crossword;
    }

    private void loop(int start, int end) {

    }

    public void printProposition(int row, int column) {
        System.out.println("[row = "+ row +" | column = "+ column + "] PROPOSITION = " + this.getCell(row, column).getProposition());
    }

    public void printSolution(int row, int column){
        System.out.println("[row = "+ row +" | column = "+ column + "] SOLUTION = " + String.valueOf(this.getCell(row, column).getSolution()));
    }

}
