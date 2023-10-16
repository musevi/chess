package chess;

import java.util.Objects;

public class ChessPositionImpl implements ChessPosition {

    private int row;
    private int column;

    public ChessPositionImpl(int setColumn, int setRow) {
        column = setColumn;
        row = setRow;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPositionImpl that = (ChessPositionImpl) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "ChessPositionImpl{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
