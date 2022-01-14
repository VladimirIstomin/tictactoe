package tictactoe.Player;

import tictactoe.Board.*;

import java.util.ArrayList;
import java.util.List;

public class HardAi extends Ai {
    private static final String LEVEL_MOVE_MESSAGE = "Making move level \"hard\"";

    public HardAi(Board board, BoardStateChecker boardStateChecker, CellType playerCell) {
        super(board, boardStateChecker, playerCell);
    }

    @Override
    public void makeMove() {
        System.out.println(LEVEL_MOVE_MESSAGE);

        setOptimalMoveCell();
    }

    private void setOptimalMoveCell() {
        Move optimalMove = calculateOptimalMove(playerCell);
        Coordinate optimalMoveCoordinate = optimalMove.coordinate;

        board.setCell(optimalMoveCoordinate, playerCell);
    }

    // minimax algorithm
    private Move calculateOptimalMove(CellType playerCell) {
        if (boardStateChecker.checkWin(this.playerCell)) {
            return new Move(1, null);
        } else if (boardStateChecker.checkWin(this.playerCell == CellType.X ? CellType.O : CellType.X)) {
            return new Move(-1, null);
        } else if (boardStateChecker.checkAllCellsNotEmpty()) {
            return new Move(0, null);
        }

        List<Move> moves = new ArrayList<>();

        for (Cell emptyCell : board.getEmptyCells()) {
            board.setCell(emptyCell.getCoordinate(), playerCell);

            Move intermediateOptimalMove;

            if (playerCell == CellType.X) {
                intermediateOptimalMove = calculateOptimalMove(CellType.O);
            } else {
                intermediateOptimalMove = calculateOptimalMove(CellType.X);
            }

            moves.add(new Move(intermediateOptimalMove.score, emptyCell.getCoordinate()));

            board.setCell(emptyCell.getCoordinate(), CellType.EMPTY);
        }

        return selectOptimalMove(moves, playerCell);
    }

    private Move selectOptimalMove(List<Move> moves, CellType playerCell) {
        Move optimalMove = null;

        if (this.playerCell == playerCell) {
            for (Move move : moves) {
                if (optimalMove == null || move.score > optimalMove.score) {
                    optimalMove = move;
                }
            }
        } else {
            for (Move move : moves) {
                if (optimalMove == null || move.score < optimalMove.score) {
                    optimalMove = move;
                }
            }
        }

        return optimalMove;
    }

    private static class Move {
        final int score;
        final Coordinate coordinate;

        public Move(int score, Coordinate coordinate) {
            this.score = score;
            this.coordinate = coordinate;
        }
    }
}
