package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final String ENTER_CELLS = "Enter the cells: > ";
    private static final String STATE_SIZE_ERROR = "The input should represent 9 cells!";
    private static final String STATE_CHARS_ERROR = "The input contains wrong characters: "
            + "only '_', 'X' and 'O' are allowed!";
    private static final String STATE_RATIO_ERROR = "The number of X's of O's is incorrect";
    private static final String NOT_FINISHED = "Game not finished";
    private static final String DRAW = "Draw";
    private static final String X_WINS = "X wins";
    private static final String O_WINS = "O wins";

    static final Scanner scanner = new Scanner(System.in);
    private Board board;
    private Player user;
    private Player ai; // will operate in start() function in the future release

    public Game() {
        initializeGameState();
    }

    public void start() {
        System.out.println(board);
        while (true) {
            user.makeMove();
            System.out.println(board);
            String gameState = getGameState();
            System.out.println(gameState);
            if (!gameState.equals(NOT_FINISHED)) {
                break;
            }
        }

        scanner.close();
    }

    private void initializeGameState() {
        while (true) {
            System.out.print(ENTER_CELLS);

            String initialBoardState = scanner.nextLine();
            String[] cellsContent = initialBoardState.split("");
            long numberOfX = Arrays.stream(cellsContent).filter(s -> s.equals("X")).count();
            long numberOfO = Arrays.stream(cellsContent).filter(s -> s.equals("O")).count();

            if (initialBoardState.length() != 9) {
                System.out.println(STATE_SIZE_ERROR);
            } else if (!Arrays.stream(cellsContent).allMatch("XO_"::contains)) {
                System.out.println(STATE_CHARS_ERROR);
            } else if (Math.abs(numberOfX - numberOfO) > 1) {
                System.out.println(STATE_RATIO_ERROR);
            } else {
                board = Board.getInstance();
                user = new User(scanner, board, numberOfX > numberOfO ? Cell.O : Cell.X);
                ai = new Ai(scanner, board, numberOfX > numberOfO ? Cell.X : Cell.O);
                board.setBoardCells(initialBoardState);
                break;
            }
        }
    }

    private String getGameState() {
         class BoardCells {
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

        BoardCells boardCells = new BoardCells();

        if (boardCells.checkWin(Cell.X)) {
            return X_WINS;
        } else if (boardCells.checkWin(Cell.O)) {
            return O_WINS;
        } else if (boardCells.checkAllCellsNotEmpty()) {
            return DRAW;
        }
        return NOT_FINISHED;
    }
}
