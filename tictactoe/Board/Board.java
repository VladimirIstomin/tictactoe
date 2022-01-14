package tictactoe.Board;

import java.util.Arrays;

public class Board {
    private static Board board;
    private final Cell[] cells;

    private Board() {
        cells = new Cell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = new Cell(new Coordinate(i % 3 + 1, i / 3 + 1), CellType.EMPTY);
        }
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCell(Coordinate coordinate, CellType cellType) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        cells[(y - 1) * 3 + x - 1].setCellType(cellType);
    }

    public Cell[] getEmptyCells() {
        return Arrays.stream(cells)
                .filter(cell -> cell.getCellType() == CellType.EMPTY)
                .toArray(Cell[]::new);
    }

    @Override
    public String toString() {
        return String.format(
            "---------\n" +
            "| %s %s %s |\n" +
            "| %s %s %s |\n" +
            "| %s %s %s |\n" +
            "---------",
            Arrays.stream(cells).map(cell -> cell.getCellType().toString()).toArray()
        );
    }
}
