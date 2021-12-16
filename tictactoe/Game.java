package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final String NOT_FINISHED = "Game not finished";
    private static final String DRAW = "Draw";
    private static final String X_WINS = "X wins";
    private static final String O_WINS = "O wins";

    static final Scanner scanner = new Scanner(System.in);
    private final Board board;
    private final Player user;
    private final Player ai;
    private volatile String gameState;

    public Game() {
        board = Board.getInstance();
        user = new User(scanner, board);
        ai = new Ai(board);
    }

    public void start() {
        System.out.println(board);
        while (true) {
            user.makeMove();
            System.out.println(board);
            updateGameState();
            if (!gameState.equals(NOT_FINISHED)) {
                break;
            }
            ai.makeMove();
            System.out.println(board);
            updateGameState();
            if (!gameState.equals(NOT_FINISHED)) {
                break;
            }
        }
        System.out.println(gameState);

        scanner.close();
    }

    private void updateGameState() {
        BoardStateChecker boardStateChecker = new BoardStateChecker();

        if (boardStateChecker.checkWin(Cell.X)) {
            gameState = X_WINS;
        } else if (boardStateChecker.checkWin(Cell.O)) {
            gameState = O_WINS;
        } else if (boardStateChecker.checkAllCellsNotEmpty()) {
            gameState = DRAW;
        } else {
            gameState = NOT_FINISHED;
        }
    }

    class BoardStateChecker {
        private Cell playerCell;

        public boolean checkAllCellsNotEmpty() {
            return Arrays.stream(board.getBoardCells())
                    .allMatch(cellRow -> Arrays.stream(cellRow)
                            .noneMatch(cell -> cell.equals(Cell.EMPTY)));
        }

        boolean checkWin(Cell playerCell) {
            this.playerCell = playerCell;
            return checkHorizontalWin()
                    | checkVerticalWin()
                    | checkUpwardDiagonalWin()
                    | checkDownwardDiagonalWin();
        }

        boolean checkHorizontalWin() {
            return Arrays.stream(board.getBoardCells())
                    .anyMatch(cellRow -> Arrays.stream(cellRow)
                            .allMatch(cell -> cell.equals(playerCell)));
        }

        boolean checkVerticalWin() {
            Cell[][] boardCells = board.getBoardCells();
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                if (Arrays.stream(boardCells).allMatch(cellRow -> cellRow[finalI].equals(playerCell))) {
                    return true;
                }
            }
            return false;
        }

        boolean checkUpwardDiagonalWin() {
            Cell[][] boardCells = board.getBoardCells();
            for (int i = 0; i < boardCells.length; i++) {
                if (boardCells[i][i] != playerCell) {
                    return false;
                }
            }
            return true;
        }

        boolean checkDownwardDiagonalWin() {
            Cell[][] boardCells = board.getBoardCells();
            for (int i = 0; i < boardCells.length; i++) {
                if (boardCells[i][boardCells.length - 1 - i] != playerCell) {
                    return false;
                }
            }
            return true;
        }
    }
}
