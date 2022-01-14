package tictactoe.Player;

import tictactoe.Board.*;

public class MediumAi extends Ai {
    private static final String LEVEL_MOVE_MESSAGE = "Making move level \"medium\"";

    public MediumAi(Board board, BoardStateChecker boardStateChecker, CellType playerCell) {
        super(board, boardStateChecker, playerCell);
    }

    @Override
    public void makeMove() {
        System.out.println(LEVEL_MOVE_MESSAGE);

        if (!setWinCell()) {
            if (!setDefenceCell()) {
                setRandomMoveCell();
            }
        }
    }

    private boolean setWinCell() {
        Cell[] cells = board.getCells();
        for (Cell cell : cells) {
            if (cell.getCellType() == CellType.EMPTY) {
                board.setCell(cell.getCoordinate(), playerCell);
                if (boardStateChecker.checkWin(playerCell)) {
                    return true;
                }
                board.setCell(cell.getCoordinate(), CellType.EMPTY);
            }
        }

        return false;
    }

    private boolean setDefenceCell() {
        CellType enemyCell = playerCell == CellType.X ? CellType.O : CellType.X;
        Cell[] cells = board.getCells();

        for (Cell cell : cells) {
            if (cell.getCellType() == CellType.EMPTY) {
                Coordinate currentCellCoordinate = cell.getCoordinate();
                board.setCell(currentCellCoordinate, enemyCell);
                if (boardStateChecker.checkWin(enemyCell)) {
                    board.setCell(currentCellCoordinate, playerCell);
                    return true;
                }
                board.setCell(currentCellCoordinate, CellType.EMPTY);
            }
        }

        return false;
    }
}
