package org.recefi.lab.server;

public class Cell {
    public int rowIdx;
    public int colIdx;
    public OwnerEnum owner;

    public Cell(int rowIdx, int colIdx, OwnerEnum owner) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.owner = owner;
    }

    public Cell() {
        this.rowIdx = -1;
        this.colIdx = -1;
        this.owner = OwnerEnum.NONE;
    }
}
