package tictactoe;

import java.util.Scanner;

public class Ai implements Player {
    private final Scanner scanner;
    private final Board board;
    private final Cell playerCell;


    public Ai(Scanner scanner, Board board, Cell playerCell) {
        this.scanner = scanner;
        this.board = board;
        this.playerCell = playerCell;
    }

    @Override
    public void makeMove() {

    }
}
