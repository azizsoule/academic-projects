package soule.crossword.utils;

import java.util.Arrays;

public class Grid<T> {

    private final int height;
    private final int width;
    private final Object[][] cells;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Object[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean correctCoords(int row, int column) {
        return row>=0 && row<height && column>=0 && column<width;
    }

    public T getCell(int row, int column) {
        return (T) cells[row][column];
    }

    public void setCell(int row, int column, T cellValue) {
        cells[row][column] = cellValue;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "height=" + height +
                ", width=" + width +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }
}
