package tictactoe;

public class EasyAi extends Ai {
    private static final String LEVEL_MOVE_MESSAGE = "Making move level \"easy\"";

    public EasyAi(Board board, Cell playerCell) {
        super(board, playerCell);
    }

    @Override
    public void makeMove() {
        System.out.println(LEVEL_MOVE_MESSAGE);

        setRandomMoveCell();
    }
}
