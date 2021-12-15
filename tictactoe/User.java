package tictactoe;

import java.util.Scanner;

public class User implements Player {
    private static final String ENTER_COORDINATES = "Enter the coordinates: > ";
    private static final String CELL_COORDINATES_TYPE_ERROR = "You should enter numbers!";

    private final Cell playerCell;
    private final Board board;
    private final Scanner scanner;

    public User(Scanner scanner, Board board, Cell playerCell) {
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

            if (y == null || x == null) {
                scanner.nextLine();
                System.out.println(CELL_COORDINATES_TYPE_ERROR);
            } else if (board.setCell(y, x, playerCell)) {
                break;
            }
        }
    }
}
