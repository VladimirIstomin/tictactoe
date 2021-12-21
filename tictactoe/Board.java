package tictactoe;

import java.util.Arrays;

public class Board {
    private static Board board;
    private final Cell[][] cells;

    private Board() {
        cells = new Cell[3][3];
        Arrays.stream(cells)
                .forEach(cellRow -> Arrays.fill(cellRow, Cell.EMPTY));
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Cell[][] getBoardCells() {
        return cells;
    }

    public void setCell(Coordinate coordinate, Cell cell) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        cells[y - 1][x - 1] = cell;
    }

    @Override
    public String toString() {
        final String horizontalBorder = "-";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(horizontalBorder.repeat(9));
        stringBuilder.append("\n");

        for (Cell[] rowCells : cells) {
            String[] rowCellsString = Arrays.stream(rowCells)
                    .map(Cell::toString)
                    .toArray(String[]::new);

            stringBuilder.append(String.format(
                    "| %s |\n",
                    String.join(" ", rowCellsString)
            ));
        }

        stringBuilder.append(horizontalBorder.repeat(9));

        return stringBuilder.toString();
    }
}
