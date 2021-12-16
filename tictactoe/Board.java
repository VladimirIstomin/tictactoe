package tictactoe;

import java.util.Arrays;

public class Board {
    private static final String CELL_COORDINATES_BOUNDS_ERROR = "Coordinates should be from 1 to 3!";
    private static final String CELL_OCCUPIED_ERROR = "This cell is occupied! Choose another one!";

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

    public boolean setCell(Coordinate coordinate, Cell cell) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println(CELL_COORDINATES_BOUNDS_ERROR);
        } else if (!cells[y - 1][x - 1].toString().equals(" ")) {
            System.out.println(CELL_OCCUPIED_ERROR);
        } else {
            cells[y - 1][x - 1] = cell;
            return true;
        }
        return false;
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
