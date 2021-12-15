package tictactoe;

public enum Cell {
    EMPTY(" "),
    X("X"),
    O("O");

    private final String name;

    private Cell(String name) {
        this.name = name;
    }

    public static Cell getCellFromText(String text) {
        for (Cell value : Cell.values()) {
            if (value.name.equals(text))
                return value;
        }
        throw new IllegalArgumentException("No such cell text value");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
