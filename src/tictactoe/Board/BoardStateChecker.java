package tictactoe.Board;

import java.util.Arrays;

public class BoardStateChecker {
    private final Board board;

    public BoardStateChecker(Board board) {
        this.board = board;
    }

    public boolean checkAllCellsNotEmpty() {
        return Arrays.stream(board.getCells())
                .noneMatch(cell -> cell.getCellType().equals(CellType.EMPTY));
    }

    public boolean checkWin(CellType playerCell) {
        Cell[] cells = board.getCells();
        CellType[] cellTypes = Arrays.stream(cells)
                .map(Cell::getCellType)
                .toArray(CellType[]::new);

        return (
            (cellTypes[0] == playerCell && cellTypes[1] == playerCell && cellTypes[2] == playerCell) ||
            (cellTypes[3] == playerCell && cellTypes[4] == playerCell && cellTypes[5] == playerCell) ||
            (cellTypes[6] == playerCell && cellTypes[7] == playerCell && cellTypes[8] == playerCell) ||
            (cellTypes[0] == playerCell && cellTypes[3] == playerCell && cellTypes[6] == playerCell) ||
            (cellTypes[1] == playerCell && cellTypes[4] == playerCell && cellTypes[7] == playerCell) ||
            (cellTypes[2] == playerCell && cellTypes[5] == playerCell && cellTypes[8] == playerCell) ||
            (cellTypes[0] == playerCell && cellTypes[4] == playerCell && cellTypes[8] == playerCell) ||
            (cellTypes[2] == playerCell && cellTypes[4] == playerCell && cellTypes[6] == playerCell)
        );
    }
}
