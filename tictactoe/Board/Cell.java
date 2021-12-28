package tictactoe.Board;

public class Cell {
    private final Coordinate coordinate;
    private CellType cellType;

    public Cell(Coordinate coordinate, CellType cellType) {
        this.coordinate = coordinate;
        this.cellType = cellType;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
