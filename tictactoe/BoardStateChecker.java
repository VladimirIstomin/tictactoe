package tictactoe;

import java.util.Arrays;

class BoardStateChecker {
    private Cell playerCell;
    private final Board board;

    public BoardStateChecker(Board board) {
        this.board = board;
    }

    public boolean checkAllCellsNotEmpty() {
        return Arrays.stream(board.getBoardCells())
                .allMatch(cellRow -> Arrays.stream(cellRow)
                        .noneMatch(cell -> cell.equals(Cell.EMPTY)));
    }

    boolean checkWin(Cell playerCell) {
        this.playerCell = playerCell;
        return checkHorizontalWin()
                | checkVerticalWin()
                | checkUpwardDiagonalWin()
                | checkDownwardDiagonalWin();
    }

    boolean checkHorizontalWin() {
        return Arrays.stream(board.getBoardCells())
                .anyMatch(cellRow -> Arrays.stream(cellRow)
                        .allMatch(cell -> cell.equals(playerCell)));
    }

    boolean checkVerticalWin() {
        Cell[][] boardCells = board.getBoardCells();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            if (Arrays.stream(boardCells).allMatch(cellRow -> cellRow[finalI].equals(playerCell))) {
                return true;
            }
        }
        return false;
    }

    boolean checkUpwardDiagonalWin() {
        Cell[][] boardCells = board.getBoardCells();
        for (int i = 0; i < boardCells.length; i++) {
            if (boardCells[i][i] != playerCell) {
                return false;
            }
        }
        return true;
    }

    boolean checkDownwardDiagonalWin() {
        Cell[][] boardCells = board.getBoardCells();
        for (int i = 0; i < boardCells.length; i++) {
            if (boardCells[i][boardCells.length - 1 - i] != playerCell) {
                return false;
            }
        }
        return true;
    }
}
