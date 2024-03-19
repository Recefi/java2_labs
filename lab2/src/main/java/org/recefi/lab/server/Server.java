package org.recefi.lab.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;

@WebService
public class Server {
    static String url = "http://localhost:7474/Connect6";
    static private SModel m = new SModel();
    private int clientNum = 0;

    @WebMethod
    public boolean checkStart() {
        return clientNum == 2;
    }

    @WebMethod
    public OwnerEnum checkWin() {
        return m.checkWin();
    }

    @WebMethod
    public OwnerEnum checkMove() {
        //return OwnerEnum.values()[moveCount % 2];
        return OwnerEnum.fromInt(m.getMoveCount() % 2);
    }

    @WebMethod
    public OwnerEnum connect() {
        if (clientNum > 1) {
            m.reset();
            clientNum = 0;
        }
        System.out.println("Client " + clientNum + " connected.");
        //return OwnerEnum.values()[clientNum++];
        return OwnerEnum.fromInt(clientNum++);
    }

    @WebMethod
    public ArrayList<Cell> getBoard() {
        return m.getBoard();
    }

    @WebMethod
    public void makeMove1(Cell cell) {
        m.move1(cell);
    }

    @WebMethod
    public void makeMove2(Cell cell1, Cell cell2) {
        m.move2(cell1, cell2);
    }

    public static void main(String[] args) {
        Server service = new Server();
        Endpoint.publish(url, service);
        System.out.println("Webservice started on " + url);
        m.addObserver(() -> { m.incMove(); });
    }
}
