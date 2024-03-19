package org.recefi.lab1.client;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.recefi.lab1.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CController {
    @FXML private GridPane grid;
    @FXML private Label moveLbl;
    @FXML private Label moveCountLbl;
    @FXML private Label statusLbl;
    @FXML private Button connectBtn;
    @FXML private Circle playerCir;

    InetAddress ip = null;
    int port = 7474;
    Socket sock;
    DataOutputStream dos;
    DataInputStream dis;
    private Model m = new Model();
    private Gson gson = new Gson();

    private OwnerEnum player = OwnerEnum.NONE;
    private int move = 0;
    private ArrayList<Cell> cellBuf = new ArrayList<>();

    private void addPane(int rowIdx, int colIdx) {
        Pane pane = new Pane();
        pane.setOnMouseEntered(evt -> { pane.setStyle("-fx-border-color: gold; -fx-border-width: 2;"); });
        pane.setOnMouseExited(evt -> { pane.setStyle(""); });

        Circle circle = new Circle(8);
        circle.setCenterX(pane.getLayoutX() + 10);
        circle.setCenterY(pane.getLayoutY() + 10);
        circle.setOpacity(0);
        pane.setOnMouseClicked(evt -> {
            if (circle.getOpacity() == 0 && move > 0) {
                moveLbl.setText("" + --move);
                cellBuf.add(new Cell(rowIdx, colIdx, player));
                m.move1(new Cell(rowIdx, colIdx, player));
                OwnerEnum win = m.checkWin();
                if (move == 0 || win != OwnerEnum.NONE) {
                    try {
                        if (win == OwnerEnum.NONE) { statusLbl.setText("Ход противника"); }
                        else { move = 0; statusLbl.setText("Вы победили?"); }
                        if (cellBuf.size() == 2) { dos.writeUTF(gson.toJson(new Msg(ActionEnum.MOVE2, cellBuf))); }
                        else { dos.writeUTF(gson.toJson(new Msg(ActionEnum.MOVE1, cellBuf))); }
                        cellBuf.clear();
                    } catch (IOException ex) {
                        Logger.getLogger(CController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        pane.getChildren().add(circle);
        grid.add(pane, colIdx, rowIdx);
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < 19; i++)
            for (int j = 0; j < 19; j++)
                addPane(i, j);
        m.addObserver(this::repaintBoard);
    }

    @FXML
    private void connect() {
        connectBtn.setDisable(true);
        m.reset();
        playerCir.setOpacity(0);
        move = 0;
        moveLbl.setText(""+move);
        moveCountLbl.setText("Ход: " + m.getMoveCount());
        statusLbl.setText("Ожидание противника...");
        statusLbl.setTextFill(Color.BLACK);

        try {
            ip = InetAddress.getLocalHost();
            sock = new Socket(ip, port);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());

            new Thread(() -> {
                try {
                    while (!sock.isClosed()) {
                        String str_msg = dis.readUTF();
                        System.out.println("Server: " + str_msg);
                        Msg msg = gson.fromJson(str_msg, Msg.class);
                        switch (msg.act) {
                            case CONNECT:
                                player = OwnerEnum.WHITE;
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        statusLbl.setText("Ход противника");
                                        playerCir.setFill(Color.WHITE);
                                        playerCir.setStroke(Color.BLACK);
                                        playerCir.setOpacity(1);
                                    }
                                });
                                break;
                            case MOVE1:
                                player = OwnerEnum.BLACK;
                                move = 1;
                                m.incMove();
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        moveLbl.setText(""+move);
                                        moveCountLbl.setText("Ход: " + m.getMoveCount());
                                        statusLbl.setText("Ваш ход");
                                        playerCir.setFill(Color.BLACK);
                                        playerCir.setStroke(Color.BLACK);
                                        playerCir.setOpacity(1);
                                    }
                                });
                                break;
                            case MOVE2:
                                move = 2;
                                m.incMove();
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        moveLbl.setText(""+move);
                                        moveCountLbl.setText("Ход: " + m.getMoveCount());
                                        statusLbl.setText("Ваш ход");
                                    }
                                });
                                m.setBoard(msg.cells);
                                break;
                            case WIN:
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        statusLbl.setText("Вы победили!");
                                        statusLbl.setTextFill(Color.GREEN);
                                        connectBtn.setDisable(false);
                                    }
                                });
                                sock.close();
                                break;
                            case LOSE:
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        statusLbl.setText("Вы проиграли!");
                                        statusLbl.setTextFill(Color.RED);
                                        connectBtn.setDisable(false);
                                    }
                                });
                                m.setBoard(msg.cells);
                                sock.close();
                                break;
                            default:
                                throw new IOException(str_msg);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        } catch (IOException ex) {
            Logger.getLogger(CController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void repaintBoard() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                ArrayList<Cell> board = m.getBoard();
                for (int i = 0; i < 19; ++i) {
                    for (int j = 0; j < 19; ++j) {
                        Circle cir = (Circle) ((Pane) grid.getChildren().get(i*19 + j)).getChildren().get(0);
                        if (board.get(i*19 + j).owner == OwnerEnum.NONE) {
                            cir.setOpacity(0);
                        } else {
                            cir.setFill((board.get(i*19 + j).owner == OwnerEnum.BLACK) ? Color.BLACK : Color.WHITE);
                            cir.setStroke(Color.BLACK);
                            cir.setOpacity(1);
                        }
                    }
                }
            }
        });
    }

}