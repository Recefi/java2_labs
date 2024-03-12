package org.recefi.lab.server;

import org.recefi.lab.ObserverInt;

import java.util.ArrayList;

public class SModel {
    private ArrayList<Cell> board = new ArrayList<>();
    private ArrayList<ObserverInt> observers = new ArrayList<>();

    private void init() {
        for (int i = 0; i < 19; ++i)
            for (int j = 0; j < 19; ++j)
                board.add(new Cell(i, j, -1));
    }

    public void reset() {
        board.clear();
        init();
        refresh();
    }

    public SModel() {
        init();
    }

    public void addObserver(ObserverInt obs) {
        observers.add(obs);
    }

    private void refresh() {
        for (ObserverInt obs : observers)
            obs.refresh();
    }

    public ArrayList<Cell> getBoard() { return board; }
    public void setBoard(ArrayList<Cell> board) {
        for (int i = 0; i < 19; ++i)
            for (int j = 0; j < 19; ++j)
                this.board.set(i*19 + j, board.get(i*19 + j));
        refresh();
    }

    public void move1(Cell cell) {
        board.set(cell.rowIdx * 19 + cell.colIdx, cell);
        refresh();
    }
    public void move2(Cell cell1, Cell cell2) {
        board.set(cell1.rowIdx * 19 + cell1.colIdx, cell1);
        board.set(cell2.rowIdx * 19 + cell2.colIdx, cell2);
        refresh();
    }

    public int checkWin() {
        for (int i = 0; i < 356; i++) {
            if (board.get(i).ownerId == -1)
                continue;

            int k = i;
            int count = 1;
            while (board.get(k+1).ownerId == board.get(k).ownerId && (k+1)%19 > i%19) {
                count++;
                k++;
                if (k+1 >= 361)
                    break;
            }
            if (count >= 6)
                return board.get(i).ownerId;

            k = i;
            count = 1;
            if (k+20 < 361) {
                while (board.get(k+20).ownerId == board.get(k).ownerId && (k+20)%19 > i%19) {
                    count++;
                    k += 20;
                    if (k+20 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).ownerId;

            k = i;
            count = 1;
            if (k+19 < 361) {
                while (board.get(k+19).ownerId == board.get(k).ownerId) {
                    count++;
                    k+=19;
                    if(k+19 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).ownerId;

            k = i;
            count = 1;
            if (k+18 < 361) {
                while (board.get(k+18).ownerId == board.get(k).ownerId && (k+18)%19 < i%19) {
                    count++;
                    k+=18;
                    if (k+18 >= 361)
                        break;
                }
            }
            if (count >= 6)
                return board.get(i).ownerId;
        }
        return -1;
    }
}
