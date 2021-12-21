package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public abstract class Ai implements Player {
    protected final Board board;
    protected final Cell playerCell;
    protected BoardStateChecker boardStateChecker;

    public Ai(Board board, Cell playerCell) {
        this.board = board;
        this.playerCell = playerCell;
        boardStateChecker = new BoardStateChecker(board);
    }

    public abstract void makeMove();

    public void setRandomMoveCell() {
        ArrayList<Coordinate> emptyCellsCoordinates = new ArrayList<>();
        Cell[][] boardCells = board.getBoardCells();

        for (int i = 0; i < boardCells.length; i++) {
            for (int j = 0; j < boardCells[i].length; j++) {
                if (boardCells[i][j] == Cell.EMPTY) {
                    emptyCellsCoordinates.add(new Coordinate(j + 1, i + 1));
                }
            }
        }

        Random random = new Random();
        board.setCell(emptyCellsCoordinates.get(random.nextInt(emptyCellsCoordinates.size())), playerCell);
    }
}
