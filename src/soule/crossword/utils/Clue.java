package soule.crossword.utils;

public class Clue {

    private final String clue;
    private final int row;
    private final int column;
    private final boolean horizontal;

    public Clue(String clue, int row, int column, boolean horizontal) {
        this.clue = clue;
        this.row = row;
        this.column = column;
        this.horizontal = horizontal;
    }

    public String getClue() {
        return clue;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    @Override
    public String toString() {
        return this.clue + " (" + (row+1) + "," + (column+1) + ")";
    }

}
