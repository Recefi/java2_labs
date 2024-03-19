package org.recefi.lab.client;

import org.recefi.lab.ObserverInt;
import org.recefi.lab.webservice.Cell;
import org.recefi.lab.webservice.OwnerEnum;

import java.util.ArrayList;
import java.util.List;

public class CModel {
    private List<Cell> board = new ArrayList<>();
    private ArrayList<ObserverInt> observers = new ArrayList<>();
    private int moveCount = 0;

    private void init() {
        for (int i = 0; i < 19; ++i) {
            for (int j = 0; j < 19; ++j) {
                Cell cell = new Cell();
                cell.setRowIdx(i);
                cell.setColIdx(j);
                cell.setOwner(OwnerEnum.NONE);
                board.add(cell);
            }
        }
    }

    public void reset() {
        board.clear();
        init();
        refresh();
        moveCount = 0;
    }

    public CModel() {
        init();
    }

    public void addObserver(ObserverInt obs) {
        observers.add(obs);
    }

    private void refresh() {
        for (ObserverInt obs : observers)
            obs.refresh();
    }

    public int getMoveCount() { return moveCount; }
    public void incMove() { moveCount++; }
    public List<Cell> getBoard() { return board; }
    public void setBoard(List<Cell> board) {
        for (int i = 0; i < 19; ++i)
            for (int j = 0; j < 19; ++j)
                this.board.set(i*19 + j, board.get(i*19 + j));
        refresh();
    }

    public void move1(Cell cell) {
        board.set(cell.getRowIdx() * 19 + cell.getColIdx(), cell);
        refresh();
    }
    public void move2(Cell cell1, Cell cell2) {
        board.set(cell1.getRowIdx() * 19 + cell1.getColIdx(), cell1);
        board.set(cell2.getRowIdx() * 19 + cell2.getColIdx(), cell2);
        refresh();
    }

    public OwnerEnum checkWin() {
        for (int i = 0; i < 356; i++) {
            if (board.get(i).getOwner() == OwnerEnum.NONE)
                continue;

            int k = i;
            int count = 1;
            while (board.get(k+1).getOwner() == board.get(k).getOwner() && (k+1)%19 > i%19) {
                count++;
                k++;
                if (k+1 >= 361)
                    break;
            }
            if (count >= 6)
                return board.get(i).getOwner();

            k = i;
            count = 1;
            if (k+20 < 361) {
                while (board.get(k + 20).getOwner() == board.get(k).getOwner() && (k + 20) % 19 > i % 19) {
                    count++;
                    k += 20;
                    if (k+20 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).getOwner();

            k = i;
            count = 1;
            if (k+19 < 361) {
                while (board.get(k+19).getOwner() == board.get(k).getOwner()) {
                    count++;
                    k+=19;
                    if(k+19 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).getOwner();

            k = i;
            count = 1;
            if (k+18 < 361) {
                while (board.get(k+18).getOwner() == board.get(k).getOwner() && (k+18)%19 < i%19) {
                    count++;
                    k+=18;
                    if (k+18 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).getOwner();
        }
        return OwnerEnum.NONE;
    }
}
