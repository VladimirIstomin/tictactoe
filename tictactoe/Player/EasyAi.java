package tictactoe.Player;

import tictactoe.Board.Board;
import tictactoe.Board.BoardStateChecker;
import tictactoe.Board.CellType;

public class EasyAi extends Ai {
    private static final String LEVEL_MOVE_MESSAGE = "Making move level \"easy\"";

    public EasyAi(Board board, BoardStateChecker boardStateChecker, CellType playerCell) {
        super(board, boardStateChecker, playerCell);
    }

    @Override
    public void makeMove() {
        System.out.println(LEVEL_MOVE_MESSAGE);

        setRandomMoveCell();
    }
}
