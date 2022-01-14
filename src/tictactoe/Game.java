package tictactoe;

import tictactoe.Board.Board;
import tictactoe.Board.BoardStateChecker;
import tictactoe.Board.CellType;
import tictactoe.Player.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private static final String NOT_FINISHED = "Game not finished";
    private static final String DRAW = "Draw";
    private static final String X_WINS = "X wins";
    private static final String O_WINS = "O wins";
    private static final String BAD_PARAMETERS = "Bad parameters!";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<PlayerType> COMMANDS = Arrays.asList(
            PlayerType.USER,
            PlayerType.EASY,
            PlayerType.MEDIUM,
            PlayerType.HARD);

    private final Board board;
    private final BoardStateChecker boardStateChecker;
    private Player player1;
    private Player player2;
    private String gameState;

    public Game() {
        board = Board.getInstance();
        boardStateChecker = new BoardStateChecker(board);
    }

    public void inputCommand() {
        String[] commandParts;
        List<PlayerType> playerTypes;

        while (true) {
            System.out.print("Input command: ");
            commandParts = SCANNER.nextLine().split(" ");

            playerTypes = Arrays.stream(commandParts)
                    .skip(1)
                    .map(PlayerType::getPlayerTypeByName)
                    .collect(Collectors.toList());

            if (commandParts[0].equals("exit")) {
                System.exit(0);
            } else if (commandParts[0].equals("start") && commandParts.length >= 3) {
                if (COMMANDS.containsAll(playerTypes)) {
                    break;
                }
            }
            System.out.println(BAD_PARAMETERS);
        }

        PlayerCreator playerCreator = new PlayerCreator();
        player1 = playerCreator.create(playerTypes.get(0), CellType.X);
        player2 = playerCreator.create(playerTypes.get(1), CellType.O);
    }

    public void start() {
        System.out.println(board);
        while (true) {
            player1.makeMove();
            System.out.println(board);
            updateGameState();
            if (!gameState.equals(NOT_FINISHED)) {
                break;
            }
            player2.makeMove();
            System.out.println(board);
            updateGameState();
            if (!gameState.equals(NOT_FINISHED)) {
                break;
            }
        }
        System.out.println(gameState);

        SCANNER.close();
    }

    private void updateGameState() {
        if (boardStateChecker.checkWin(CellType.X)) {
            gameState = X_WINS;
        } else if (boardStateChecker.checkWin(CellType.O)) {
            gameState = O_WINS;
        } else if (boardStateChecker.checkAllCellsNotEmpty()) {
            gameState = DRAW;
        } else {
            gameState = NOT_FINISHED;
        }
    }

    class PlayerCreator {
        public Player create(PlayerType playerType, CellType playerCell) {
            Player player;

            switch (playerType) {
                case EASY: player = new EasyAi(board, boardStateChecker, playerCell);
                    break;
                case MEDIUM: player = new MediumAi(board, boardStateChecker, playerCell);
                    break;
                case HARD: player = new HardAi(board, boardStateChecker, playerCell);
                    break;
                default: player = new User(SCANNER, board, playerCell);
            }

            return player;
        }
    }
}
