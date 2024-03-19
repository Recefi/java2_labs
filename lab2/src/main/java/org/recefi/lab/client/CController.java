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
import org.recefi.lab.webservice.OwnerEnum;
import org.recefi.lab.webservice.Server;
import org.recefi.lab.webservice.ServerService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CController {
    @FXML private GridPane grid;
    @FXML private Label moveLbl;
    @FXML private Label moveCountLbl;
    @FXML private Label statusLbl;
    @FXML private Button connectBtn;
    @FXML private Circle playerCir;

    private CModel m = new CModel();
    Server server;

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
                Cell cell = new Cell();
                cell.setRowIdx(rowIdx);
                cell.setColIdx(colIdx);
                cell.setOwner(player);
                cellBuf.add(cell);
                m.move1(cell);
                OwnerEnum win = m.checkWin();
                if (move == 0 || win != OwnerEnum.NONE) {
                    if (win == OwnerEnum.NONE) { statusLbl.setText("Ход противника"); }
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
        moveCountLbl.setText("Ход: " + m.getMoveCount());
        statusLbl.setText("Ожидание противника...");
        statusLbl.setTextFill(Color.BLACK);

        server = new ServerService().getServerPort();
        player = server.connect();

        new Thread(() -> {
            if (player == OwnerEnum.WHITE) {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        statusLbl.setText("Ход противника");
                        playerCir.setFill(Color.WHITE);
                        playerCir.setStroke(Color.BLACK);
                        playerCir.setOpacity(1);
                    }
                });
            } else if (player == OwnerEnum.BLACK) {
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
            }
            try {
                while (true) {
                    Thread.sleep(500);
                    OwnerEnum win = server.checkWin();
                    if (win != OwnerEnum.NONE) {
                        if (win == player) {
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
                        if (server.checkMove() == player) {
                            move = 2;
                            m.incMove();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    moveLbl.setText("" + move);
                                    moveCountLbl.setText("Ход: " + m.getMoveCount());
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
                        if (board.get(i*19 + j).getOwner() == OwnerEnum.NONE) {
                            cir.setOpacity(0);
                        } else {
                            cir.setFill((board.get(i*19 + j).getOwner() == OwnerEnum.BLACK) ? Color.BLACK:Color.WHITE);
                            cir.setStroke(Color.BLACK);
                            cir.setOpacity(1);
                        }
                    }
                }
            }
        });
    }
}