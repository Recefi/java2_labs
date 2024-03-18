package org.recefi.lab1;

public class Cell {
    public int rowIdx;
    public int colIdx;
    public OwnerEnum owner;

    public Cell(int rowIdx, int colIdx, OwnerEnum owner) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Cell{rowIdx=" + rowIdx + ", colIdx=" + colIdx + ", owner=" + owner + "}";
    }
}
