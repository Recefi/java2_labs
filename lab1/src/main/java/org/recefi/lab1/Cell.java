package org.recefi.lab1;

public class Cell {
    public int rowIdx;
    public int colIdx;
    public int ownerId;

    public Cell(int rowIdx, int colIdx, int ownerId) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Cell{rowIdx=" + rowIdx + ", colIdx=" + colIdx + ", ownerId=" + ownerId + "}";
    }
}
