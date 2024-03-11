package org.recefi.lab.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.recefi.lab.webservice.Cell;
import org.recefi.lab.webservice.Server;
import org.recefi.lab.webservice.ServerService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CController {
    @FXML private GridPane grid;
    @FXML private Label moveLbl;
    @FXML private Label statusLbl;
    @FXML private Button connectBtn;
    @FXML private Circle playerCir;

    private CModel m = new CModel();
    Server server;

    private int playerId = -1;
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
                Cell cell = new Cell();
                cell.setRowIdx(rowIdx);
                cell.setColIdx(colIdx);
                cell.setOwnerId(playerId);
                cellBuf.add(cell);
                m.move1(cell);
                int win = m.checkWin();
                if (move == 0 || win != -1) {
                    if (win == -1) { statusLbl.setText("Ход противника"); }
                    else { move = 0; statusLbl.setText("Вы победили?"); }
                    if (cellBuf.size() == 2) { server.makeMove2(cellBuf.get(0), cellBuf.get(1)); }
                    else { server.makeMove1(cellBuf.get(0)); }
                    cellBuf.clear();
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
        statusLbl.setText("Ожидание противника...");
        statusLbl.setTextFill(Color.BLACK);

        server = new ServerService().getServerPort();
        playerId = server.connect();

        new Thread(() -> {
            if (playerId == 1) {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        moveLbl.setText(""+move);
                        statusLbl.setText("Ход противника");
                        playerCir.setFill(Color.WHITE);
                        playerCir.setStroke(Color.BLACK);
                        playerCir.setOpacity(1);
                    }
                });
            } else if (playerId == 0) {
                boolean tmp = server.checkStart();
                while (!tmp) {
                    try {
                        Thread.sleep(500);
                        tmp = server.checkStart();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                move = 1;
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        moveLbl.setText(""+move);
                        statusLbl.setText("Ваш ход");
                        playerCir.setFill(Color.BLACK);
                        playerCir.setStroke(Color.BLACK);
                        playerCir.setOpacity(1);
                    }
                });
            }
            try {
                while (true) {
                    Thread.sleep(500);
                    int win = server.checkWin();
                    if (win != -1) {
                        if (win == playerId) {
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    statusLbl.setText("Вы победили!");
                                    statusLbl.setTextFill(Color.GREEN);
                                    connectBtn.setDisable(false);
                                }
                            });
                        } else {
                            m.setBoard(server.getBoard());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    statusLbl.setText("Вы проиграли!");
                                    statusLbl.setTextFill(Color.RED);
                                    connectBtn.setDisable(false);
                                }
                            });
                        }
                        break;
                    }
                    if (move == 0) {
                        if (server.checkMove() == playerId) {
                            move = 2;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    moveLbl.setText("" + move);
                                    statusLbl.setText("Ваш ход");
                                }
                            });
                            m.setBoard(server.getBoard());
                        }
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    private void repaintBoard() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                List<Cell> board = m.getBoard();
                for (int i = 0; i < 19; ++i) {
                    for (int j = 0; j < 19; ++j) {
                        Circle cir = (Circle) ((Pane) grid.getChildren().get(i*19 + j)).getChildren().get(0);
                        if (board.get(i*19 + j).getOwnerId() == -1) {
                            cir.setOpacity(0);
                        } else {
                            cir.setFill((board.get(i*19 + j).getOwnerId() == 0) ? Color.BLACK : Color.WHITE);
                            cir.setStroke(Color.BLACK);
                            cir.setOpacity(1);
                        }
                    }
                }
            }
        });
    }
}