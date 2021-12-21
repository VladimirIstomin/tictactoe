package tictactoe;

public class MediumAi extends Ai {
    private static final String LEVEL_MOVE_MESSAGE = "Making move level \"medium\"";

    public MediumAi(Board board, Cell playerCell) {
        super(board, playerCell);
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoardCells()[i][j] == Cell.EMPTY) {
                    board.setCell(new Coordinate(j + 1, i + 1), playerCell);
                    if (boardStateChecker.checkWin(playerCell)) {
                        return true;
                    }
                    board.setCell(new Coordinate(j + 1, i + 1), Cell.EMPTY);
                }
            }
        }

        return false;
    }

    private boolean setDefenceCell() {
        Cell enemyCell = playerCell == Cell.X ? Cell.O : Cell.X;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoardCells()[i][j] == Cell.EMPTY) {
                    Coordinate currentCellCoordinate = new Coordinate(j + 1, i + 1);
                    board.setCell(currentCellCoordinate, enemyCell);
                    if (boardStateChecker.checkWin(enemyCell)) {
                        board.setCell(currentCellCoordinate, playerCell);
                        return true;
                    }
                    board.setCell(currentCellCoordinate, Cell.EMPTY);
                }
            }
        }

        return false;
    }
}
