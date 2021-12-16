package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class Ai implements Player {
    private static final String EASY_LEVEL_MOVE_MESSAGE = "Making move level \"easy\"";

    private final Board board;
    private final Cell playerCell = Cell.O;


    public Ai(Board board) {
        this.board = board;
    }

    @Override
    public void makeMove() {
        System.out.println(EASY_LEVEL_MOVE_MESSAGE);

        Coordinate coordinate = getRandomEmptyCellCoordinate();

        board.setCell(coordinate, playerCell);
    }

    private Coordinate getRandomEmptyCellCoordinate() {
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
        return emptyCellsCoordinates.get(random.nextInt(emptyCellsCoordinates.size()));
    }
}
