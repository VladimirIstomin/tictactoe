package tictactoe.Board;

public enum CellType {
    EMPTY(" "),
    X("X"),
    O("O");

    private final String name;

    private CellType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
