package tictactoe.Player;

import tictactoe.Board.*;

import java.util.Random;

public abstract class Ai implements Player {
    protected final Board board;
    protected final CellType playerCell;
    protected BoardStateChecker boardStateChecker;

    public Ai(Board board, BoardStateChecker boardStateChecker, CellType playerCell) {
        this.board = board;
        this.playerCell = playerCell;
        this.boardStateChecker = boardStateChecker;
    }

    public abstract void makeMove();

    public void setRandomMoveCell() {
        Cell[] emptyCells = board.getEmptyCells();

        Random random = new Random();
        board.setCell(emptyCells[random.nextInt(emptyCells.length)].getCoordinate(), playerCell);
    }
}
