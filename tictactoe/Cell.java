package tictactoe;

public enum Cell {
    EMPTY(" "),
    X("X"),
    O("O");

    private final String name;

    private Cell(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
