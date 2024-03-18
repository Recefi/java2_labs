package org.recefi.lab1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Msg {
    public ActionEnum act;
    public ArrayList<Cell> cells = new ArrayList<>();

    public Msg(ActionEnum act) {
        this.act = act;
    }
    public Msg(ActionEnum act, ArrayList<Cell> cells) {
        this.act = act;
        this.cells = cells;
    }

//    @Override
//    public String toString() {
////        String str = "Msg{" + "act=" + act + ", cells=[";
////        for (Cell cell : cells) { str += cell + ", "; }
////        str += "]}";
////        return str;
//        return "Msg{" + "act=" + act + ", cells=[" +
//                                        cells.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]}";
//    }
}
