package org.recefi.lab;

import javax.jws.WebMethod;

public class Cell {
    public int rowIdx;
    public int colIdx;
    public int ownerId;

    public Cell(int rowIdx, int colIdx, int ownerId) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.ownerId = ownerId;
    }

    public Cell() {
        this.rowIdx = -1;
        this.colIdx = -1;
        this.ownerId = -1;
    }
}
