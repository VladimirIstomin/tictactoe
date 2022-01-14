package tictactoe.Player;

import tictactoe.Board.Board;
import tictactoe.Board.CellType;

import java.util.Scanner;

public class User implements Player {
    private static final String ENTER_COORDINATES = "Enter the coordinates: ";
    private static final String CELL_COORDINATES_TYPE_ERROR = "You should enter numbers!";
    private static final String CELL_COORDINATES_BOUNDS_ERROR = "Coordinates should be from 1 to 3!";
    private static final String CELL_OCCUPIED_ERROR = "This cell is occupied! Choose another one!";

    private final CellType playerCell;
    private final Board board;
    private final Scanner scanner;

    public User(Scanner scanner, Board board, CellType playerCell) {
        this.scanner = scanner;
        this.board = board;
        this.playerCell = playerCell;
    }

    @Override
    public void makeMove() {
        while (true) {
            System.out.print(ENTER_COORDINATES);

            Integer y = null;
            Integer x = null;

            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
            }

            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
            }

            scanner.nextLine();

            if (y == null || x == null) {
                System.out.println(CELL_COORDINATES_TYPE_ERROR);
            } else if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println(CELL_COORDINATES_BOUNDS_ERROR);
            } else if (board.getCells()[(y - 1) * 3 + x - 1].getCellType() != CellType.EMPTY) {
                System.out.println(CELL_OCCUPIED_ERROR);
            } else {
                board.setCell(board.getCells()[(y - 1) * 3 + x - 1].getCoordinate(), playerCell);
                break;
            }
        }
    }
}
