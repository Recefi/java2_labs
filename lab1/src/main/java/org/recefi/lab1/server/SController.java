package org.recefi.lab1.server;

import com.google.gson.Gson;
import org.recefi.lab1.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SController implements Runnable {
    int id;
    Socket sock1;
    Socket sock2;
    private DataInputStream dis1;
    private DataOutputStream dos1;
    private DataInputStream dis2;
    private DataOutputStream dos2;
    private Model m = new Model();
    private Gson gson = new Gson();

    public SController(int id, Socket sock1, Socket sock2) {
        this.id = id;
        this.sock1 = sock1;
        this.sock2 = sock2;
        try {
            dos1 = new DataOutputStream(sock1.getOutputStream());
            dos2 = new DataOutputStream(sock2.getOutputStream());
            dis1 = new DataInputStream(sock1.getInputStream());
            dis2 = new DataInputStream(sock2.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void nextMove() {
        try {
            OwnerEnum win = m.checkWin();
            if (win == OwnerEnum.NONE) {
                m.incMove();
                String str_msg = gson.toJson(new Msg(ActionEnum.MOVE2, m.getBoard()));
                if (m.getMoveCount() % 2 == 1)
                    dos2.writeUTF(str_msg);
                else
                    dos1.writeUTF(str_msg);
            }
            else {
                if (win == OwnerEnum.BLACK) {
                    dos1.writeUTF(gson.toJson(new Msg(ActionEnum.WIN)));
                    dos2.writeUTF(gson.toJson(new Msg(ActionEnum.LOSE, m.getBoard())));
                } else {
                    dos1.writeUTF(gson.toJson(new Msg(ActionEnum.LOSE, m.getBoard())));
                    dos2.writeUTF(gson.toJson(new Msg(ActionEnum.WIN)));
                }
                sock1.close();
                sock2.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(SController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initRoom() {
        try {
            m.addObserver(this::nextMove);
            Msg msg = new Msg(ActionEnum.MOVE1);
            String str_msg = gson.toJson(msg);
            dos1.writeUTF(str_msg);
            msg = new Msg(ActionEnum.CONNECT);
            str_msg = gson.toJson(msg);
            dos2.writeUTF(str_msg);
        } catch (Exception ex) {
            Logger.getLogger(SController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Room " + id + " started.");
            initRoom();

            while (!sock1.isClosed() && !sock2.isClosed()) {
                if (m.getMoveCount() % 2 == 0) {
                    String str_msg = dis1.readUTF();
                    System.out.println("Room " + id + ", Client 0: " + str_msg);
                    Msg msg = gson.fromJson(str_msg, Msg.class);
                    if (msg.act == ActionEnum.MOVE1)
                        m.move1(msg.cells.get(0));
                    else if (msg.act == ActionEnum.MOVE2)
                        m.move2(msg.cells.get(0), msg.cells.get(1));
                    else
                        throw new IOException(str_msg);
                } else {
                    String str_msg = dis2.readUTF();
                    System.out.println("Room " + id + ", Client 1: " + str_msg);
                    Msg msg = gson.fromJson(str_msg, Msg.class);
                    if (msg.act == ActionEnum.MOVE1)
                        m.move1(msg.cells.get(0));
                    else if (msg.act == ActionEnum.MOVE2)
                        m.move2(msg.cells.get(0), msg.cells.get(1));
                    else
                        throw new IOException(str_msg);
                }
            }
            System.out.println("Room " + id + " ended.");
        } catch (IOException ex) {
            Logger.getLogger(SController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
